package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslHeader;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class LslProcHeader extends ASTWrapperPsiElement implements LslHeader {
    public LslProcHeader(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public LslIdentifier methodName() {
        return (LslIdentifier) PsiTreeUtil.skipMatching(this.getFirstChild(),
                PsiElement::getNextSibling,
                it -> !(it instanceof LslIdentifier));
    }

}