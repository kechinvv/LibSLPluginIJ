package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslCallable;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslHeader;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslStatementsOwner;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

public class LslFunctionDecl extends LslIdentifierOwner implements PsiElement, LslStatementsOwner, LslCallable {
    public LslFunctionDecl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public LslFunctionHeader header() {
        return PsiTreeUtil.findChildOfType(this, LslFunctionHeader.class);
    }

    public LslFunctionBody body() {
        return PsiTreeUtil.findChildOfType(this, LslFunctionBody.class);
    }
}