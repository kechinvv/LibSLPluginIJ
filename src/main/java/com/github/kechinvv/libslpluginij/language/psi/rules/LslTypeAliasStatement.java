package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslStatement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslTypeAliasStatement extends ASTWrapperPsiElement implements LslStatement {
    public LslTypeAliasStatement(@NotNull ASTNode node) {
        super(node);
    }
}