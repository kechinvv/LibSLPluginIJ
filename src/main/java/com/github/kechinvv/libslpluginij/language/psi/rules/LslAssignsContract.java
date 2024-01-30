package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslAssignsContract extends ASTWrapperPsiElement {
    public LslAssignsContract(@NotNull ASTNode node) {
        super(node);
    }
}