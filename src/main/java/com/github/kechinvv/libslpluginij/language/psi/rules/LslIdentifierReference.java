package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.LibSLDefault;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.language.psi.LibSLPSIFileRoot;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslCallable;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Function;
import com.intellij.util.SmartList;
import com.intellij.util.containers.SmartHashSet;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
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

    //TODO: refactoring this scary condition monster
    private void processDeclarations(Function<PsiNamedElement, Boolean> callback) {
        PsiElement varParent;
        PsiElement prev = myElement.getPrevSibling();

        if (prev != null && prev.getText().equals(".") && prev.getPrevSibling().getText().equals("this"))
            processFieldDeclarationsInAutomaton(requireNonNull(getParentOfType(myElement, LslAutomatonDecl.class)), callback);
        else if (getParentOfType(myElement, LslTypeIdentifier.class) != null) {
            findTypes(myElement.getProject(), callback);
        } else if (getParentOfType(myElement, LslActionUsage.class) != null) {
            findActionsDeclarations(myElement.getProject(), callback);
        } else if (myElement.getParent() instanceof LslFunctionHeader || myElement.getParent() instanceof LslProcHeader) {
        } else if ((varParent = getParentOfType(myElement, LslVariableDecl.class)) != null && varParent.getParent() instanceof LslAutomatonStatement) {
        } else {
            var automaton = getParentOfType(myElement, LslAutomatonDecl.class);
            if (automaton != null) processDeclarationsInAutomaton(automaton, callback);
        }

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
//        LocalFileSystem.getInstance().findFileByIoFile(File file) (if the file
//        already exists in VFS)
//        or LocalFileSystem.getInstance().refreshAndFindFileByIoFile(File file)
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(LibSLFileType.INSTANCE, GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            LibSLPSIFileRoot file = (LibSLPSIFileRoot) PsiManager.getInstance(project).findFile(virtualFile);
            if (file != null) {
                if (canResolve && !file.getName().startsWith(elText)) continue;

                var typeDefs = file.getTypeDefBlockNames();
                if (typeDefs != null && breakerLoop(typeDefs, callback)) break;

                var automatons = file.getAutomatonNames();
                if (automatons != null && breakerLoop(automatons, callback)) break;

                var typeAliases = file.getTypeAliasesNames();
                if (typeAliases != null && breakerLoop(typeAliases, callback)) break;
            }
        }
    }

    private void findActionsDeclarations(Project project, Function<PsiNamedElement, Boolean> callback) {
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(LibSLFileType.INSTANCE, GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            LibSLPSIFileRoot file = (LibSLPSIFileRoot) PsiManager.getInstance(project).findFile(virtualFile);
            if (file != null) {
                var actionDeclarations = file.getActionsDeclarationsNames();
                if (actionDeclarations != null && breakerLoop(actionDeclarations, callback)) break;
            }
        }
    }

    private boolean breakerLoop(Collection<LslIdentifier> ids, Function<PsiNamedElement, Boolean> callback) {
        for (LslIdentifier id : ids) {
            var added = callback.fun(id);
            if (added) return true;
        }
        return false;
    }
}








