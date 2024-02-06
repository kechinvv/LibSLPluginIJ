package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.language.psi.LibSLPSIFileRoot;
import com.github.kechinvv.libslpluginij.language.utils.LslUtils;
import com.intellij.extapi.psi.ASTDelegatePsiElement;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Consumer;
import com.intellij.util.SmartList;
import com.intellij.util.containers.SmartHashSet;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import static com.intellij.psi.util.PsiTreeUtil.*;
import static java.util.Objects.*;

public class LslIdentifierReference extends PsiPolyVariantReferenceBase<LslIdentifier> {
    public static final Logger LOG = Logger.getInstance("Reference");

    public LslIdentifierReference(@NotNull LslIdentifier element, TextRange range) {
        super(element, range);
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        var declarations = new SmartList<PsiElement>();
        var name = myElement.getName();
        Consumer<PsiNamedElement> adder = it -> {
            if (Objects.equals(it.getName(), name)) declarations.add(it);
        };
        processDeclarations(adder);
        return PsiElementResolveResult.createResults(declarations);
    }


    @Override
    public Object @NotNull [] getVariants() {
        var variants = new SmartHashSet<String>();
        Consumer<PsiNamedElement> adder = it -> {
            var name = it.getName();
            if (name != null) variants.add(name);
        };
        processDeclarations(adder);
        return variants.toArray();
    }

//    @Override
//    public PsiElement handleElementRename(@NotNull String newElementName) {
//        return myElement.setName(newElementName);
//    }


    private void processDeclarations(Consumer<PsiNamedElement> callback) {
        PsiElement varParent;
        PsiElement prev = myElement.getPrevSibling();

        if (prev != null && prev.getText().equals(".") && prev.getPrevSibling().getText().equals("this"))
            processFieldDeclarationsInAutomaton(requireNonNull(getParentOfType(myElement, LslAutomatonDecl.class)), callback);
        else if (getParentOfType(myElement, LslTypeIdentifier.class) != null) {
        }
//            findFiles(myElement.getProject(), callback);
        else if (myElement.getParent() instanceof LslFunctionHeader || myElement.getParent() instanceof LslProcHeader) {
        } else if ((varParent = getParentOfType(myElement, LslVariableDecl.class)) != null && varParent.getParent() instanceof LslAutomatonStatement) {
        } else {
            var automaton = getParentOfType(myElement, LslAutomatonDecl.class);
            if (automaton != null) processDeclarationsInAutomaton(automaton, callback);
        }

    }


    private void processDeclarationsInHeader(LslFunctionDecl functionDecl, Consumer<PsiNamedElement> callback) {
        var header = functionDecl.header();
        if (header == null) return;
        var func = header.methodName();
        if (func == null) return;
        callback.consume(func);
    }

    private void processDeclarationsInHeader(LslProcDecl procDecl, Consumer<PsiNamedElement> callback) {
        var header = procDecl.header();
        if (header == null) return;
        var proc = header.methodName();
        if (proc == null) return;
        callback.consume(proc);
    }

    private void processDeclarationsInAutomaton(LslAutomatonDecl automatonDecl, Consumer<PsiNamedElement> callback) {
        automatonDecl.functionDeclsList().forEach(functionDecl -> processDeclarationsInHeader(functionDecl, callback));
        automatonDecl.procDeclsList().forEach(procDecl -> processDeclarationsInHeader(procDecl, callback));
    }

    private void processFieldDeclarationsInAutomaton(LslAutomatonDecl automatonDecl, Consumer<PsiNamedElement> callback) {
        automatonDecl.constructorFieldsName().forEach(callback::consume);
        automatonDecl.bodyFieldsName().forEach(callback::consume);
    }

    private void findFiles(Project project, Consumer<PsiNamedElement> callback) {
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(LibSLFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            LibSLPSIFileRoot file = (LibSLPSIFileRoot) PsiManager.getInstance(project).findFile(virtualFile);
            // WANT FIX: DONT WORK, NOT ROOT PSI ELEMENT
            if (file != null) {
                //var node = file.getNode().getFirstChildNode().getPsi();
                var test = file.getTreeElement();
                var typeDefs = getChildrenOfType(file, LslTypeDefBlock.class);
                if (typeDefs != null) {
                    LOG.info("type = " + Arrays.stream(typeDefs).map(LslTypeDefBlock::getText).toList());
                    typeNameResolver(typeDefs, LslTypeDefBlock.class, callback);
                } else {
                    var automatons = getChildrenOfType(file, LslAutomatonDecl.class);
                    if (automatons != null) typeNameResolver(automatons, LslAutomatonDecl.class, callback);
                }
            }
        }
    }

    private <T extends PsiElement> void typeNameResolver(T[] elements, Class<T> klass, Consumer<PsiNamedElement> callback) {
        Function<T, LslIdentifier> mapper = it -> {
            var typeName = getChildOfType(it, klass);
            if (typeName != null) return getChildOfType(typeName, LslIdentifier.class);
            else return null;
        };
        Arrays.stream(elements).map(mapper).filter(Objects::nonNull).forEach(it -> {
            callback.consume(it);
            LOG.info("type = " + it.getName());
        });
    }
}






