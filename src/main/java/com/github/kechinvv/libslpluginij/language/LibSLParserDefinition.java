package com.github.kechinvv.libslpluginij.language;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.github.kechinvv.libslpluginij.language.psi.LibSLPSIFileRoot;
import com.github.kechinvv.libslpluginij.language.psi.PsiElementFactory;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class LibSLParserDefinition implements ParserDefinition {
    public static final Logger LOG = Logger.getInstance("ParserDef");

    public static final IFileElementType FILE = new IFileElementType(LibSL.INSTANCE);

    public static List<TokenIElementType> tokens;
    public static List<RuleIElementType> rules;


    static {
        PSIElementTypeFactory.defineLanguageIElementTypes(
                LibSL.INSTANCE,
                LibSLParser.tokenNames,
                LibSLParser.ruleNames);
        tokens = PSIElementTypeFactory.getTokenIElementTypes(LibSL.INSTANCE);
        rules = PSIElementTypeFactory.getRuleIElementTypes(LibSL.INSTANCE);
    }

    public static final TokenSet COMMENTS =
            PSIElementTypeFactory.createTokenSet(
                    LibSL.INSTANCE,
                    LibSLLexer.COMMENT,
                    LibSLLexer.LINE_COMMENT);

    public static final TokenSet WHITESPACE =
            PSIElementTypeFactory.createTokenSet(
                    LibSL.INSTANCE,
                    LibSLLexer.WS, LibSLLexer.BR);

    public static final TokenSet STRING =
            PSIElementTypeFactory.createTokenSet(
                    LibSL.INSTANCE,
                    LibSLLexer.DoubleQuotedString);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        LibSLLexer lexer = new LibSLLexer(null);
        return new ANTLRLexerAdaptor(LibSL.INSTANCE, lexer);
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        final LibSLParser parser = new LibSLParser(null);
        return new ANTLRParserAdaptor(LibSL.INSTANCE, parser) {
            @Override
            protected ParseTree parse(Parser parser, IElementType root) {
                if (root instanceof IFileElementType) {
                    return ((LibSLParser) parser).file();
                } else throw new UnsupportedOperationException("Wrong Lsl file structure");
            }
        };
    }

    /**
     * "Tokens of those types are automatically skipped by PsiBuilder."
     */
    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITESPACE;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return STRING;
    }

    /**
     * What is the IFileElementType of the root parse tree node? It
     * is called from {@link #createFile(FileViewProvider)} at least.
     */
    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    /**
     * Create the root of your PSI tree (a PsiFile).
     * <p>
     * From IntelliJ IDEA Architectural Overview:
     * "A PSI (Program Structure Interface) file is the root of a structure
     * representing the contents of a file as a hierarchy of elements
     * in a particular programming language."
     * <p>
     * PsiFile is to be distinguished from a FileASTNode, which is a parse
     * tree node that eventually becomes a PsiFile. From PsiFile, we can get
     * it back via: {@link PsiFile#getNode}.
     */
    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new LibSLPSIFileRoot(viewProvider);
    }

    /**
     * Convert from *NON-LEAF* parse node (AST they call it)
     * to PSI node. Leaves are created in the AST factory.
     * Rename re-factoring can cause this to be
     * called on a TokenIElementType since we want to rename ID nodes.
     * In that case, this method is called to create the root node
     * but with ID type. Kind of strange, but we can simply create a
     * ASTWrapperPsiElement to make everything work correctly.
     * <p>
     * RuleIElementType.  Ah! It's that ID is the root
     * IElementType requested to parse, which means that the root
     * node returned from parsetree->PSI conversion.  But, it
     * must be a CompositeElement! The adaptor calls
     * rootMarker.done(root) to finish off the PSI conversion.
     * See {@link ANTLRParserAdaptor#parse(IElementType root,
     * PsiBuilder)}
     * <p>
     * If you don't care to distinguish PSI nodes by type, it is
     * sufficient to create a {@link ANTLRPsiNode} around
     * the parse tree node
     */
    @NotNull
    public PsiElement createElement(ASTNode node) {
        return PsiElementFactory.INSTANCE.createElement(node);
    }
}