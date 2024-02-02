package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class LslAutomatonDecl extends ASTWrapperPsiElement {
    public LslAutomatonDecl(@NotNull ASTNode node) {
        super(node);
    }

    //TODO: reinitialization after file changing in the end for elements creation
    //public final Collection<LslFunctionDecl> functionDeclsList = PsiTreeUtil.findChildrenOfType(this, LslFunctionDecl.class);
    //public final Collection<LslProcDecl> procDeclsList = PsiTreeUtil.findChildrenOfType(this, LslProcDecl.class);

    public @NotNull Collection<LslProcDecl> procDeclsList(){
        return PsiTreeUtil.findChildrenOfType(this, LslProcDecl.class);
    }

    public @NotNull Collection<LslFunctionDecl> functionDeclsList(){
        return PsiTreeUtil.findChildrenOfType(this, LslFunctionDecl.class);
    }

}