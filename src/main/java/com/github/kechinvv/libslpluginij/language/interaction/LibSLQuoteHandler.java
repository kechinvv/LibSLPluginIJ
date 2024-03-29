package com.github.kechinvv.libslpluginij.language.interaction;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

public class LibSLQuoteHandler extends SimpleTokenSetQuoteHandler {
    Logger LOG = Logger.getInstance("QUOTE");

    public LibSLQuoteHandler() {
        super(tokens.get(LibSLLexer.DoubleQuotedString), tokens.get(LibSLLexer.CHARACTER));
    }

    @Override
    public boolean isOpeningQuote(HighlighterIterator highlighterIterator, int i) {
        if (highlighterIterator.getTokenType() == tokens.get(LibSLLexer.BAD_CHARACTER) ||
                highlighterIterator.getTokenType() == tokens.get(LibSLLexer.APOSTROPHE)) {
            var charSymb = highlighterIterator.getDocument().getCharsSequence().charAt(highlighterIterator.getStart());
            return charSymb == '"' || charSymb == '\'';
        }

        return false;
    }


    @Override
    public boolean hasNonClosedLiteral(Editor editor, HighlighterIterator highlighterIterator, int i) {
        return true;
    }

}
