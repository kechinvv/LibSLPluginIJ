package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslAutomatonStateDecl extends ASTWrapperPsiElement implements LslStatementsOwner {
    public LslAutomatonStateDecl(@NotNull ASTNode node) {
        super(node);
    }
}