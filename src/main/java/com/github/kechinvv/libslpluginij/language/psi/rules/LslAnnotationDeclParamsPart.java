package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LslAnnotationDeclParamsPart extends ASTWrapperPsiElement {
    public LslAnnotationDeclParamsPart(@NotNull ASTNode node) {
        super(node);
    }
}