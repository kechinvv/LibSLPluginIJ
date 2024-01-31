package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LslIdentifierOwner extends ASTWrapperPsiElement implements PsiNameIdentifierOwner {

    LslIdentifier identifier = this.findChildByClass(LslIdentifier.class);

    public LslIdentifierOwner(@NotNull ASTNode node) {
        super(node);
    }


    @Override
    public String getName() {
        if (identifier != null) return identifier.getName();
        else return null;
    }

    @Override
    public int getTextOffset() {
        if (identifier != null) return identifier.getTextOffset();
        else return super.getTextOffset();
    }

    @Override
    public @Nullable PsiElement getNameIdentifier() {
        if (identifier != null) return identifier.getNameIdentifier();
        else return this;
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        if (identifier != null) return identifier.setName(s);
        else return this;
    }
}
