package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.Consumer;
import com.intellij.util.SmartList;
import com.intellij.util.containers.SmartHashSet;
import org.jetbrains.annotations.NotNull;

public class LslIdentifierReference extends PsiPolyVariantReferenceBase<LslIdentifier> {
    public static final Logger LOG = Logger.getInstance("Reference");

    public LslIdentifierReference(@NotNull LslIdentifier element, TextRange range) {
        super(element, range);
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
//        List<ResolveResult> results = new ArrayList<>();
//        for (SimpleProperty property : properties) {
//            results.add(new PsiElementResolveResult(property));
//        }
//        return results.toArray(new ResolveResult[0]);
        var declarations = new SmartList<PsiElement>();
//        var declarations = new ArrayList<ResolveResult>();
        var name = myElement.getName();
        Consumer<PsiNamedElement> adder = it -> {
//            LOG.info("it.getName = " + it.getName());
//            LOG.info("myName = " + name);

            if (it.getName().equals(name)) declarations.add(it);
        };
        processDeclarations(adder);
//        LOG.info("element = " + myElement.getName());
//        LOG.info("declarations " + declarations);

        return PsiElementResolveResult.createResults(declarations); //.toArray(new ResolveResult[0]);
    }


    @Override
    public Object @NotNull [] getVariants() {
        var variants = new SmartHashSet<String>();
        Consumer<PsiNamedElement> adder = it -> {
            var name = it.getName();
            if (name != null) variants.add(name);
        };
        processDeclarations(adder);
//        LOG.info("element = " + myElement.getName());
//        LOG.info("variants " + variants);
        return variants.toArray();
    }

//    @Override
//    public PsiElement handleElementRename(@NotNull String newElementName) {
//        return myElement.setName(newElementName);
//    }


    private void processDeclarations(Consumer<PsiNamedElement> callback) {
        //PsiElement prev = myElement;
        var current = myElement.getParent();

        while (current != null) {
            //processDeclarationsIteration(current, callback);
            //prev = current;
            current = current.getParent();
            if (current instanceof LslAutomatonDecl) break;
        }
        processDeclarationsIteration(current, callback);
    }

    private void processDeclarationsIteration(PsiElement current, Consumer<PsiNamedElement> callback) {
        switch (current) {
//            case JavaScriptStatementsOwner -> processDeclarationsInScope(current, callback)
//            case LslFunctionDecl functionDecl -> processDeclarationsInHeader(functionDecl, callback);
//            case LslProcDecl procDecl -> processDeclarationsInHeader(procDecl, callback);
            case LslAutomatonDecl automatonDecl -> processDeclarationsInAutomaton(automatonDecl, callback);
//            case LslFunction -> processDeclarationsInParameters(current, callback)
//            is JavaScriptCatch -> {
//                val assignable = current.assignable
//                if (assignable != null) processDeclarationsInAssignable(assignable, callback)
//            }
//            is JavaScriptIterationStatement -> {
//                val variableDeclarationList = when (current) {
//                    is JavaScriptDoWhileStatement -> null
//                    is JavaScriptWhileStatement -> null
//                    is JavaScriptForStatement -> current.variableDeclarationList
//                    is JavaScriptForInStatement -> current.variableDeclarationList
//                    is JavaScriptForOfStatement -> current.variableDeclarationList
//                }
//                if (variableDeclarationList != null) processDeclarationsInVariableDeclarationList(variableDeclarationList, callback)
            default -> {
            }
        }
    }

    private void processDeclarationsInHeader(LslFunctionDecl functionDecl, Consumer<PsiNamedElement> callback) {
        var header = functionDecl.header();
        if (header == null) return;
        var func = header.methodName();
        if (func == null) return;
//        LOG.info("func name " + func.getName());
        func.forEach(callback::consume);
    }

    private void processDeclarationsInHeader(LslProcDecl procDecl, Consumer<PsiNamedElement> callback) {
        var header = procDecl.header();
        if (header == null) return;
        var proc = header.methodName();
        if (proc == null) return;
//        LOG.info("proc name " + proc.getName());
        proc.forEach(callback::consume);
    }

    private void processDeclarationsInAutomaton(LslAutomatonDecl automatonDecl, Consumer<PsiNamedElement> callback) {
//        LOG.info("SIZE func " + automatonDecl.functionDeclsList().size());
//        LOG.info("SIZE proc " + automatonDecl.procDeclsList().size());

        automatonDecl.functionDeclsList().forEach(functionDecl -> processDeclarationsInHeader(functionDecl, callback));
        automatonDecl.procDeclsList().forEach(procDecl -> processDeclarationsInHeader(procDecl, callback));

    }

}


