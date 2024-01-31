package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;

public class LslIdentifierReference extends PsiPolyVariantReferenceBase<LslIdentifier> {
    public LslIdentifierReference(@NotNull PsiElement psiElement) {
        super((LslIdentifier) psiElement);
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean b) {
        return new ResolveResult[0];
    }
}
