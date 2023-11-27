package com.github.kechinvv.libslpluginij.language;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;

import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class LibSLSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    public static final TextAttributesKey ID =
            createTextAttributesKey("SAMPLE_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("SAMPLE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("SAMPLE_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("SAMPLE_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("SAMPLE_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    static {
        PSIElementTypeFactory.defineLanguageIElementTypes(LibSL.INSTANCE,
                LibSLParser.tokenNames,
                LibSLParser.ruleNames);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        LibSLLexer lexer = new LibSLLexer(null);
        return new ANTLRLexerAdaptor(LibSL.INSTANCE, lexer);
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if ( !(tokenType instanceof TokenIElementType) ) return EMPTY_KEYS;
        TokenIElementType myType = (TokenIElementType)tokenType;
        int ttype = myType.getANTLRTokenType();
        TextAttributesKey attrKey;
        switch ( ttype ) {
            case LibSLLexer.IDENTIFIER:
                attrKey = ID;
                break;
            case LibSLLexer.VAR:
            case LibSLLexer.DEFINE:
            case LibSLLexer.IF:
            case LibSLLexer.ELSE:
            case LibSLLexer.FUN:
            case LibSLLexer.TYPE:
            case LibSLLexer.TRUE:
            case LibSLLexer.FALSE:
                attrKey = KEYWORD;
                break;
            case LibSLLexer.DoubleQuotedString:
                attrKey = STRING;
                break;
            case LibSLLexer.COMMENT :
                attrKey = LINE_COMMENT;
                break;
            case LibSLLexer.LINE_COMMENT :
                attrKey = BLOCK_COMMENT;
                break;
            default :
                return EMPTY_KEYS;
        }
        return new TextAttributesKey[] {attrKey};
    }

}
