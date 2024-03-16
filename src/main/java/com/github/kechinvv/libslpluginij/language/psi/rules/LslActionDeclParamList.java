package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslParametersOwner;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslActionDeclParamList extends ASTWrapperPsiElement implements LslParametersOwner {
    public LslActionDeclParamList(@NotNull ASTNode node) {
        super(node);
    }
}