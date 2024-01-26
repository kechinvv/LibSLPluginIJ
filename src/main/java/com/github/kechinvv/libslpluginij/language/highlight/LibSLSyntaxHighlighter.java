package com.github.kechinvv.libslpluginij.language.highlight;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.github.kechinvv.libslpluginij.language.LibSL;
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
            createTextAttributesKey("LIBSL_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("LIBSL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("LIBSL_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("LIBSL_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("LIBSL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("LSL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

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
        TokenIElementType myType = (TokenIElementType) tokenType;
        int ttype = myType.getANTLRTokenType();
        TextAttributesKey attrKey;
        switch ( ttype ) {
            case LibSLLexer.Identifier:
                attrKey = ID;
                break;
            case LibSLLexer.VAR:
            case LibSLLexer.VAL:
            case LibSLLexer.DEFINE:
            case LibSLLexer.IF:
            case LibSLLexer.ELSE:
            case LibSLLexer.FUN:
            case LibSLLexer.PROC:
            case LibSLLexer.TYPE:
            case LibSLLexer.TRUE:
            case LibSLLexer.FALSE:
            case LibSLLexer.IMPORT:
            case LibSLLexer.ACTION:
            case LibSLLexer.NEW:
            case LibSLLexer.AUTOMATON:
            case LibSLLexer.ENUM:
            case LibSLLexer.AS:
            case LibSLLexer.LIBRARY:
            case LibSLLexer.VERSION:
            case LibSLLexer.LANGUAGE:
            case LibSLLexer.URL:
            case LibSLLexer.LIBSL:
            case LibSLLexer.SHIFT:
            case LibSLLexer.STATE:
            case LibSLLexer.INITSTATE:
            case LibSLLexer.FINISHSTATE:
            case LibSLLexer.CONSTRUCTOR:
            case LibSLLexer.DESTRUCTOR:
            case LibSLLexer.BY:
                attrKey = KEYWORD;
                break;
            case LibSLLexer.DoubleQuotedString:
                attrKey = STRING;
                break;
            case LibSLLexer.COMMENT :
                attrKey = BLOCK_COMMENT;
                break;
            case LibSLLexer.LINE_COMMENT :
                attrKey = LINE_COMMENT;
                break;
            case LibSLLexer.BAD_CHARACTER:
                attrKey = BAD_CHARACTER;
                break;
            default :
                return EMPTY_KEYS;
        }
        return new TextAttributesKey[] {attrKey};
    }

}
