package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslStatement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslConstructorVariables extends ASTWrapperPsiElement implements LslStatement {
    public LslConstructorVariables(@NotNull ASTNode node) {
        super(node);
    }
}