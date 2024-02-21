package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class LslAutomatonDecl extends ASTWrapperPsiElement implements LslStatementsOwner {
    public LslAutomatonDecl(@NotNull ASTNode node) {
        super(node);
    }

    //TODO: reinitialization after file changing in the end for elements creation
    //public final Collection<LslFunctionDecl> functionDeclsList = PsiTreeUtil.findChildrenOfType(this, LslFunctionDecl.class);
    //public final Collection<LslProcDecl> procDeclsList = PsiTreeUtil.findChildrenOfType(this, LslProcDecl.class);

    public @NotNull Collection<LslProcDecl> procDeclsList() {
        return PsiTreeUtil.findChildrenOfType(this, LslProcDecl.class);
    }

    public @NotNull Collection<LslFunctionDecl> functionDeclsList() {
        return PsiTreeUtil.findChildrenOfType(this, LslFunctionDecl.class);
    }

    public List<LslIdentifier> constructorFieldsName() {
        Function<PsiElement, LslIdentifier> mapper = it ->
                Objects.requireNonNull(PsiTreeUtil.findChildOfType(it, LslIdentifier.class));
        return PsiTreeUtil
                .findChildrenOfType(this, LslConstructorVariables.class).stream().map(mapper).toList();
    }

    public List<LslIdentifier> bodyFieldsName() {
        Function<PsiElement, LslIdentifier> mapper = it ->
                Objects.requireNonNull(PsiTreeUtil.findChildOfType(it, LslIdentifier.class));
        return PsiTreeUtil
                .findChildrenOfType(this, LslAutomatonStatement.class)
                .stream()
                .filter(it -> it.getFirstChild() instanceof LslVariableDecl)
                .map(mapper)
                .toList();
    }

}