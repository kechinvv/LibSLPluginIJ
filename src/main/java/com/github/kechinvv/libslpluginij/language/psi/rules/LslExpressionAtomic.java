package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslExpressionAtomic extends ASTWrapperPsiElement {
    public LslExpressionAtomic(@NotNull ASTNode node) {
        super(node);
    }
}