package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.utils.LslUtils;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LslIdentifier extends ASTWrapperPsiElement implements PsiNameIdentifierOwner {
    public LslIdentifier(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable PsiElement getNameIdentifier() {
        return getFirstChild();
    }

    @Override
    public String getName() {
        return getText();
    }

    @Override
    public PsiElement setName(@NotNull String s) throws IncorrectOperationException {
        var idLeaf = getNameIdentifier();
        var newIdLeaf = LslUtils.createIdentifierTokenFromText(getProject(), s);
        assert idLeaf != null;
        assert newIdLeaf != null;
        return idLeaf.replace(newIdLeaf);
    }

    @Override
    public PsiReference getReference() {
        var rangeInElement = new TextRange(0, this.getNode().getTextLength());
        if (this.getTextLength() > 1) return new LslIdentifierReference(this, rangeInElement);
        else return null;
    }
}