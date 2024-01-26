package com.github.kechinvv.libslpluginij.language.highlight;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

public class LibSLBraceMatcher implements PairedBraceMatcher {

    BracePair[] PAIRS = {
            new BracePair(tokens.get(LibSLLexer.L_BRACE), tokens.get(LibSLLexer.R_BRACE), true),
            new BracePair(tokens.get(LibSLLexer.L_BRACKET), tokens.get(LibSLLexer.R_BRACKET), true),
            new BracePair(tokens.get(LibSLLexer.L_SQUARE_BRACKET), tokens.get(LibSLLexer.R_SQUARE_BRACKET), true),
    };

    @Override
    public BracePair @NotNull [] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return 0;
    }
}


