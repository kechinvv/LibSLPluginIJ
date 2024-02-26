package com.github.kechinvv.libslpluginij.language.highlight;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.github.kechinvv.libslpluginij.language.LibSL;
import com.github.kechinvv.libslpluginij.language.psi.LibSLTokenSets;
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
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        var group = LibSLTokenSets.INSTANCE.getHighlightGroupOf(tokenType);
        return switch (group) {
            case BadGroup -> new TextAttributesKey[]{BAD_CHARACTER};
            case EmptyKeysGroup -> EMPTY_KEYS;
            case KeywordGroup -> new TextAttributesKey[]{KEYWORD};
            case StringGroup -> new TextAttributesKey[]{STRING};
            case IdGroup -> new TextAttributesKey[]{ID};
            case MlCommentsGroup -> new TextAttributesKey[]{BLOCK_COMMENT};
            case CommentsGroup -> new TextAttributesKey[]{LINE_COMMENT};
        };
    }

}
