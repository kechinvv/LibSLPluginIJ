package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslActionDeclParamList extends ASTWrapperPsiElement {
    public LslActionDeclParamList(@NotNull ASTNode node) {
        super(node);
    }
}