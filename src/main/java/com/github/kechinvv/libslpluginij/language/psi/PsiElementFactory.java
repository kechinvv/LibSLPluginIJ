package com.github.kechinvv.libslpluginij.language.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;

public class PsiElementFactory {
    public static final PsiElementFactory INSTANCE = new PsiElementFactory();

    public PsiElement createElement(ASTNode node) {
        IElementType elType = node.getElementType();
        if ( elType instanceof TokenIElementType) {
            return new ANTLRPsiNode(node);
        }
        if ( !(elType instanceof RuleIElementType ruleElType) ) {
            return new ANTLRPsiNode(node);
        }
        return switch (ruleElType.getRuleIndex()) {
//            case LibSLParser.FUN -> new FunctionSubtree(node, elType);
//            case SampleLanguageParser.RULE_vardef -> new VardefSubtree(node, elType);
//            case SampleLanguageParser.RULE_formal_arg -> new ArgdefSubtree(node, elType);
//            case SampleLanguageParser.RULE_block -> new BlockSubtree(node);
//            case SampleLanguageParser.RULE_call_expr -> new CallSubtree(node);
            default -> new ANTLRPsiNode(node);
        };
    }
}
