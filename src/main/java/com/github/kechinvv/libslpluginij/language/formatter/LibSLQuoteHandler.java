package com.github.kechinvv.libslpluginij.language.formatter;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.intellij.codeInsight.editorActions.QuoteHandler;
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.tree.IElementType;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

public class LibSLQuoteHandler extends SimpleTokenSetQuoteHandler {
    Logger LOG = Logger.getInstance("QUOTE");

    public LibSLQuoteHandler() {
        super(tokens.get(LibSLLexer.DoubleQuotedString), tokens.get(LibSLLexer.CHARACTER));
    }

    //TODO: problem with parsing single quote and ESCAPED_QUOTE
    @Override
    public boolean isOpeningQuote(HighlighterIterator highlighterIterator, int i) {
        LOG.info("ITS NOT " + highlighterIterator.getTokenType().toString());

        if (highlighterIterator.getTokenType() == tokens.get(LibSLLexer.BAD_CHARACTER) ||
                highlighterIterator.getTokenType().toString() == "'''") {
            var charSymb = highlighterIterator.getDocument().getCharsSequence().charAt(highlighterIterator.getStart());
            LOG.info("ITS WORK " + (charSymb == '"' || charSymb == '\''));
            return charSymb == '"' || charSymb == '\'';
        }

        return false;
    }


    @Override
    public boolean hasNonClosedLiteral(Editor editor, HighlighterIterator highlighterIterator, int i) {
        return true;
    }

}
