package com.github.kechinvv.libslpluginij.language;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.EmptyFindUsagesProvider;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.COMMENTS;
import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

public class LibSLFindUsagesProvider extends EmptyFindUsagesProvider {
    public static final Logger LOG = Logger.getInstance("FindUsage");

    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        //      return null;
        var lexer = new LibSLLexer(null);
        var adapter = new ANTLRLexerAdaptor(LibSL.INSTANCE, lexer);
        return new DefaultWordsScanner(adapter,
                TokenSet.create(tokens.get(LibSLLexer.Identifier)),
                TokenSet.create(tokens.get(LibSLLexer.LINE_COMMENT), tokens.get(LibSLLexer.COMMENT)),
                TokenSet.create(tokens.get(LibSLLexer.TRUE),
                                tokens.get(LibSLLexer.FALSE),
                                tokens.get(LibSLLexer.NULL),
                                tokens.get(LibSLLexer.CHARACTER),
                                tokens.get(LibSLLexer.DoubleQuotedString),
                                tokens.get(LibSLLexer.Digit),
                                tokens.get(LibSLLexer.DOT)));
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
//        //return psiElement instanceof ANTLRPsiNode;
        LOG.info("IDENTIFIER = " + psiElement.getNode().getElementType());
        LOG.info("Text PSI = " + psiElement.getText());
        return true;
    }


}
