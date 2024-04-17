package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslStatementsOwner;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslEnumBlock extends ASTWrapperPsiElement implements LslStatementsOwner {
    public LslEnumBlock(@NotNull ASTNode node) {
        super(node);
    }
}