package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslStatement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslEnumBlockStatement extends ASTWrapperPsiElement implements LslStatement {
    public LslEnumBlockStatement(@NotNull ASTNode node) {
        super(node);
    }
}