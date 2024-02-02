package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

public class LslFunctionDecl extends LslIdentifierOwner implements PsiElement {
    public LslFunctionDecl(@NotNull ASTNode node) {
        super(node);
    }

    public LslFunctionHeader header() {
        return PsiTreeUtil.findChildOfType(this, LslFunctionHeader.class);
    }

    public LslFunctionBody body() {
        return PsiTreeUtil.findChildOfType(this, LslFunctionBody.class);
    }
}