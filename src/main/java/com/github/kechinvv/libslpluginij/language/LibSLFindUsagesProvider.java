package com.github.kechinvv.libslpluginij.language;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslIdentifier;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.EmptyFindUsagesProvider;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.intellij.lang.annotations.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

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
