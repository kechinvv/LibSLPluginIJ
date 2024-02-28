package com.github.kechinvv.libslpluginij.language.psi.rules.references;

import com.github.kechinvv.libslpluginij.language.LibSLDefault;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.language.psi.LibSLPSIFileRoot;
import com.github.kechinvv.libslpluginij.language.psi.rules.*;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslCallable;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Function;
import com.intellij.util.SmartList;
import com.intellij.util.containers.SmartHashSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;

import static com.intellij.psi.util.PsiTreeUtil.getParentOfType;
import static java.util.Objects.requireNonNull;

public class LslIdentifierReference extends PsiPolyVariantReferenceBase<LslIdentifier> {
    public static final Logger LOG = Logger.getInstance("Reference");

    public LslIdentifierReference(@NotNull LslIdentifier element, TextRange range) {
        super(element, range);
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        var declarations = new SmartList<PsiElement>();
        var name = myElement.getName();
        Function<PsiNamedElement, Boolean> adder = it -> {
            if (Objects.equals(it.getName(), name)) {
                declarations.add(it);
                return true;
            }
            return false;
        };
        processDeclarations(adder);
        return PsiElementResolveResult.createResults(declarations);
    }


    @Override
    public Object @NotNull [] getVariants() {
        var variants = new SmartHashSet<String>();
        Function<PsiNamedElement, Boolean> adder = it -> {
            var name = it.getName();
            if (name != null) {
                variants.add(name);
                return true;
            }
            return false;
        };
        processDeclarations(adder);
        return variants.toArray();
    }

    private void processDeclarations(Function<PsiNamedElement, Boolean> callback) {
        var declaration = getDeclarationKind();
        switch (declaration) {
            case LocalFunVar, LocalAutomatonVar, Other -> {
            }
            case ThisField -> processFieldDeclarationsInAutomaton(
                    requireNonNull(PsiTreeUtil.getParentOfType(myElement, LslAutomatonDecl.class)), callback);
            case SomeType -> findTypes(myElement.getProject(), callback);
            case Action -> findActionsDeclarations(myElement.getProject(), callback);
            case Method -> {
                var automaton = getParentOfType(myElement, LslAutomatonDecl.class);
                if (automaton != null) processDeclarationsInAutomaton(automaton, callback);
            }
        }
    }

    private DeclarationKind getDeclarationKind() {
        PsiElement varParent;
        PsiElement prev = myElement.getPrevSibling();
        if (prev != null && prev.getText().equals(".") && prev.getPrevSibling().getText().equals("this"))
            return DeclarationKind.ThisField;
        if (PsiTreeUtil.getParentOfType(myElement, LslTypeIdentifier.class) != null)
            return DeclarationKind.SomeType;
        if (PsiTreeUtil.getParentOfType(myElement, LslActionUsage.class) != null)
            return DeclarationKind.Action;
        if (myElement.getParent() instanceof LslFunctionHeader || myElement.getParent() instanceof LslProcHeader)
            return DeclarationKind.LocalFunVar;
        if ((varParent = getParentOfType(myElement, LslVariableDecl.class)) != null && varParent.getParent() instanceof LslAutomatonStatement)
            return DeclarationKind.LocalAutomatonVar;
        return DeclarationKind.Method;
    }


    private void processDeclarationsInHeader(LslCallable method, Function<PsiNamedElement, Boolean> callback) {
        var header = method.header();
        if (header == null) return;
        var methodName = header.methodName();
        if (methodName == null) return;
        callback.fun(methodName);
    }

    private void processDeclarationsInAutomaton(LslAutomatonDecl automatonDecl, Function<PsiNamedElement, Boolean> callback) {
        automatonDecl.functionDeclsList().forEach(functionDecl -> processDeclarationsInHeader(functionDecl, callback));
        automatonDecl.procDeclsList().forEach(procDecl -> processDeclarationsInHeader(procDecl, callback));
    }

    private void processFieldDeclarationsInAutomaton(LslAutomatonDecl automatonDecl, Function<PsiNamedElement, Boolean> callback) {
        automatonDecl.constructorFieldsName().forEach(callback::fun);
        automatonDecl.bodyFieldsName().forEach(callback::fun);
    }

    //TODO: separate files loop
    private void findTypes(Project project, Function<PsiNamedElement, Boolean> callback) {
        var elText = myElement.getText();
        if (LibSLDefault.INSTANCE.defaultTypes.contains(elText)) return;

        //TODO problem: source folder not declared
        //TODO update: find references in super classes' imports
        var imports = ((LibSLPSIFileRoot) myElement.getContainingFile()).getImportsPathsMap();
        var canResolve = imports.containsKey(elText);

//        if (imports.containsKey(elText)) {
//            var prjPath = myElement.getContainingFile().getOriginalFile().get;
//            var filePath = Paths.get(prjPath, imports.get(elText));
//            var file = filePath.toFile();
//            var virtualFile = LocalFileSystem.getInstance().findFileByIoFile(file);
//        }

//        LocalFileSystem.getInstance().findFileByIoFile(File file) (if the file already exists in VFS)
//        or LocalFileSystem.getInstance().refreshAndFindFileByIoFile(File file)
        Function<LibSLPSIFileRoot, LoopAction> loopAction = file -> {
            if (canResolve && !file.getName().startsWith(elText)) return LoopAction.CONTINUE;

            var typeDefs = file.getTypeDefBlockNames();
            if (typeDefs != null && breakerLoop(typeDefs, callback)) return LoopAction.BREAK;

            var automatons = file.getAutomatonNames();
            if (automatons != null && breakerLoop(automatons, callback)) return LoopAction.BREAK;

            var typeAliases = file.getTypeAliasesNames();
            if (typeAliases != null && breakerLoop(typeAliases, callback)) return LoopAction.BREAK;
            return LoopAction.CONTINUE;
        };

        projectFilesLoop(project, loopAction);
    }

    private void findActionsDeclarations(Project project, Function<PsiNamedElement, Boolean> callback) {
        Function<LibSLPSIFileRoot, LoopAction> loopAction = file -> {
            var actionDeclarations = file.getActionsDeclarationsNames();
            if (actionDeclarations != null && breakerLoop(actionDeclarations, callback)) return LoopAction.BREAK;
            return LoopAction.CONTINUE;
        };

        projectFilesLoop(project, loopAction);
    }

    private boolean breakerLoop(Collection<LslIdentifier> ids, Function<PsiNamedElement, Boolean> callback) {
        for (LslIdentifier id : ids) {
            var added = callback.fun(id);
            if (added) return true;
        }
        return false;
    }

    private void projectFilesLoop(Project project, Function<LibSLPSIFileRoot, LoopAction> action) {
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(LibSLFileType.INSTANCE, GlobalSearchScope.projectScope(project));
        fileLoop:
        for (VirtualFile virtualFile : virtualFiles) {
            LibSLPSIFileRoot file = (LibSLPSIFileRoot) PsiManager.getInstance(project).findFile(virtualFile);
            if (file != null) {
                var loopAction = action.fun(file);
                switch (loopAction) {
                    case BREAK -> {
                        break fileLoop;
                    }
                    case CONTINUE -> {
                    }
                }
            }
        }
    }

    private enum LoopAction {
        BREAK, CONTINUE
    }
}








