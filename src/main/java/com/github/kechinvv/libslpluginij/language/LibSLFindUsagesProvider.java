package com.github.kechinvv.libslpluginij.language;

import com.github.kechinvv.libslpluginij.language.psi.rules.LslIdentifier;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.EmptyFindUsagesProvider;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class LibSLFindUsagesProvider extends EmptyFindUsagesProvider {
    public static final Logger LOG = Logger.getInstance("FindUsage");

    @Override
    public WordsScanner getWordsScanner() {
        return null;
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof LslIdentifier;
    }


}
