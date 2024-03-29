package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.PsiElementFactory;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslStatement;
import com.github.kechinvv.libslpluginij.language.psi.rules.references.LslIdentifierReference;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LslIdentifier extends ASTWrapperPsiElement implements PsiNameIdentifierOwner, LslStatement {
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

//    @Override
//    public @NotNull SearchScope getUseScope(){
//
//    }

    @Override
    public PsiElement setName(@NotNull String s) throws IncorrectOperationException {
        var idLeaf = getNameIdentifier();
        var newIdLeaf = PsiElementFactory.createIdentifierTokenFromText(getProject(), s);
        assert idLeaf != null;
        assert newIdLeaf != null;
        return idLeaf.replace(newIdLeaf);
    }

    @Override
    public PsiReference getReference() {
        var rangeInElement = new TextRange(0, this.getNode().getTextLength());
        return new LslIdentifierReference(this, rangeInElement);
    }
}