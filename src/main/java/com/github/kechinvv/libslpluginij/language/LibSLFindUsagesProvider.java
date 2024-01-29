package com.github.kechinvv.libslpluginij.language;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
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

public class LibSLFindUsagesProvider implements FindUsagesProvider {

    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
//        return null;
        var lexer = new LibSLLexer(null);
        var adapter = new ANTLRLexerAdaptor(LibSL.INSTANCE, lexer);
        return new DefaultWordsScanner(adapter,
                TokenSet.create(tokens.get(LibSLLexer.Identifier)),
                TokenSet.create(tokens.get(LibSLLexer.LINE_COMMENT), tokens.get(LibSLLexer.COMMENT)),
                TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        //return psiElement instanceof ANTLRPsiNode;
        return true;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
//        if (element instanceof SimpleProperty) {
//            return "simple property";
//        }
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
//        if (element instanceof SimpleProperty) {
//            return ((SimpleProperty) element).getKey();
//        }
        return "";
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
//        if (element instanceof SimpleProperty) {
//            return ((SimpleProperty) element).getKey() +
//                    SimpleAnnotator.SIMPLE_SEPARATOR_STR +
//                    ((SimpleProperty) element).getValue();
//        }
        return "";
    }

}
