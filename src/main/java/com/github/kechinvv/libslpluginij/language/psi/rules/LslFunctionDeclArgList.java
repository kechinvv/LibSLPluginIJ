package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslParametersOwner;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslFunctionDeclArgList extends ASTWrapperPsiElement implements LslParametersOwner {
    public LslFunctionDeclArgList(@NotNull ASTNode node) {
        super(node);
    }
}