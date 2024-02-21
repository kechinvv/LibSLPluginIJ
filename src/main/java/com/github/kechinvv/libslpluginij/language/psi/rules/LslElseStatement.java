package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslElseStatement extends ASTWrapperPsiElement implements LslStatement, LslStatementsOwner {
    public LslElseStatement(@NotNull ASTNode node) {
        super(node);
    }
}