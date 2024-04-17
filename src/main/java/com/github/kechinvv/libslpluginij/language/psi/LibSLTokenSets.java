package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

public class LibSLTokenSets {
    public final static LibSLTokenSets INSTANCE = new LibSLTokenSets();

    public TokenSet WHITESPACES = TokenSet.create(tokens.get(LibSLLexer.WS),
            tokens.get(LibSLLexer.BR));

    public TokenSet TAB_HEADER = TokenSet.create(tokens.get(LibSLLexer.VERSION),
            tokens.get(LibSLLexer.LANGUAGE),
            tokens.get(LibSLLexer.URL));

    public TokenSet BRACES = TokenSet.create(
            tokens.get(LibSLLexer.L_BRACE),
            tokens.get(LibSLLexer.R_BRACE),
            tokens.get(LibSLLexer.L_BRACKET),
            tokens.get(LibSLLexer.R_BRACKET),
            tokens.get(LibSLLexer.L_SQUARE_BRACKET),
            tokens.get(LibSLLexer.R_SQUARE_BRACKET)
    );

    public TokenSet COMMENTS = TokenSet.create(
            tokens.get(LibSLLexer.COMMENT),
            tokens.get(LibSLLexer.LINE_COMMENT)
    );

    public TokenSet KEYWORDS = TokenSet.create(
            tokens.get(LibSLLexer.LIBSL),
            tokens.get(LibSLLexer.LIBRARY),
            tokens.get(LibSLLexer.VERSION),
            tokens.get(LibSLLexer.LANGUAGE),
            tokens.get(LibSLLexer.URL),
            tokens.get(LibSLLexer.TYPEALIAS),
            tokens.get(LibSLLexer.TYPE),
            tokens.get(LibSLLexer.TYPES),
            tokens.get(LibSLLexer.ENUM),
            tokens.get(LibSLLexer.ANNOTATION),
            tokens.get(LibSLLexer.AUTOMATON),
            tokens.get(LibSLLexer.CONCEPT),
            tokens.get(LibSLLexer.VAR),
            tokens.get(LibSLLexer.VAL),
            tokens.get(LibSLLexer.INITSTATE),
            tokens.get(LibSLLexer.STATE),
            tokens.get(LibSLLexer.FINISHSTATE),
            tokens.get(LibSLLexer.SHIFT),
            tokens.get(LibSLLexer.NEW),
            tokens.get(LibSLLexer.FUN),
            tokens.get(LibSLLexer.CONSTRUCTOR),
            tokens.get(LibSLLexer.DESTRUCTOR),
            tokens.get(LibSLLexer.PROC),
            tokens.get(LibSLLexer.ACTION),
            tokens.get(LibSLLexer.REQUIRES),
            tokens.get(LibSLLexer.ENSURES),
            tokens.get(LibSLLexer.ASSIGNS),
            tokens.get(LibSLLexer.TRUE),
            tokens.get(LibSLLexer.FALSE),
            tokens.get(LibSLLexer.DEFINE),
            tokens.get(LibSLLexer.IF),
            tokens.get(LibSLLexer.ELSE),
            tokens.get(LibSLLexer.BY),
            tokens.get(LibSLLexer.IS),
            tokens.get(LibSLLexer.AS),
            tokens.get(LibSLLexer.NULL)
    );

    public TokenSet OPERATIONS = TokenSet.create(
            tokens.get(LibSLLexer.EQ),
            tokens.get(LibSLLexer.MINUS_ARROW),
            tokens.get(LibSLLexer.L_ARROW),
            tokens.get(LibSLLexer.R_ARROW),
            tokens.get(LibSLLexer.ASTERISK),
            tokens.get(LibSLLexer.TYPEALIAS),
            tokens.get(LibSLLexer.SLASH),
            tokens.get(LibSLLexer.PERCENT),
            tokens.get(LibSLLexer.PLUS),
            tokens.get(LibSLLexer.MINUS),
            tokens.get(LibSLLexer.INCREMENT),
            tokens.get(LibSLLexer.DECREMENT),
            tokens.get(LibSLLexer.PLUS_EQ),
            tokens.get(LibSLLexer.MINUS_EQ),
            tokens.get(LibSLLexer.ASTERISK_EQ),
            tokens.get(LibSLLexer.SLASH_EQ),
            tokens.get(LibSLLexer.PERCENT_EQ),
            tokens.get(LibSLLexer.EXCLAMATION),
            tokens.get(LibSLLexer.EXCLAMATION_EQ),
            tokens.get(LibSLLexer.L_ARROW_EQ),
            tokens.get(LibSLLexer.R_ARROW_EQ),
            tokens.get(LibSLLexer.AMPERSAND),
            tokens.get(LibSLLexer.DOUBLE_AMPERSAND),
            tokens.get(LibSLLexer.BIT_OR),
            tokens.get(LibSLLexer.LOGIC_OR),
            tokens.get(LibSLLexer.XOR),
            tokens.get(LibSLLexer.TILDE),
            tokens.get(LibSLLexer.AMPERSAND_EQ),
            tokens.get(LibSLLexer.OR_EQ),
            tokens.get(LibSLLexer.XOR_EQ),
            tokens.get(LibSLLexer.L_SHIFT_EQ),
            tokens.get(LibSLLexer.R_SHIFT_EQ),
            tokens.get(LibSLLexer.BY),
            tokens.get(LibSLLexer.IS),
            tokens.get(LibSLLexer.AS),
            tokens.get(LibSLLexer.NULL)
    );

    public TokenSet STRING = TokenSet.create(
            tokens.get(LibSLLexer.DoubleQuotedString),
            tokens.get(LibSLLexer.CHARACTER)
    );

    private LibSLTokenSets() {
    }

    public TokensHighlightGroup getHighlightGroupOf(IElementType tokenType) {
        if (!(tokenType instanceof TokenIElementType myType)) return TokensHighlightGroup.EmptyKeysGroup;
        if (myType.getANTLRTokenType() == LibSLLexer.Identifier) return TokensHighlightGroup.IdGroup;
        if (myType.getANTLRTokenType() == LibSLLexer.COMMENT) return TokensHighlightGroup.MlCommentsGroup;
        if (myType.getANTLRTokenType() == LibSLLexer.LINE_COMMENT) return TokensHighlightGroup.CommentsGroup;
        if (myType.getANTLRTokenType() == LibSLLexer.BAD_CHARACTER) return TokensHighlightGroup.BadGroup;
        if (LibSLTokenSets.INSTANCE.KEYWORDS.contains(myType)) return TokensHighlightGroup.KeywordGroup;
        if (LibSLTokenSets.INSTANCE.STRING.contains(myType)) return TokensHighlightGroup.StringGroup;
        return TokensHighlightGroup.EmptyKeysGroup;
    }
}
