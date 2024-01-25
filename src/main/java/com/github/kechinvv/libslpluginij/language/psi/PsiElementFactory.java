package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.language.psi.rules.ProcDecl;
import com.github.kechinvv.libslpluginij.language.psi.rules.VariableAssignment;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;

public class PsiElementFactory {
    public static final Logger LOG = Logger.getInstance("PsiElementFactory");

    public static final PsiElementFactory INSTANCE = new PsiElementFactory();

    public PsiElement createElement(ASTNode node) {
        IElementType elType = node.getElementType();
        if ( elType instanceof TokenIElementType) {
            LOG.info("TOKEN DECL " + node);
            return new ANTLRPsiNode(node);
        }
        if ( !(elType instanceof RuleIElementType ruleElType) ) {
            LOG.info("NOT RULE DECL " + node);
            return new ANTLRPsiNode(node);
        }
        switch (ruleElType.getRuleIndex()) {
            case LibSLParser.RULE_variableAssignment -> {
                LOG.info("ASS DECL " + node);
                return new VariableAssignment(node);
            }
            case LibSLParser.RULE_procDecl -> {
                LOG.info("PROC DECL " + node);
                return new ProcDecl(node, elType);
            }
            case LibSLParser.RULE_functionDecl -> {
                LOG.info("FUN DECL " + node);
                return new ProcDecl(node, elType);
            }
//            case SampleLanguageParser.RULE_vardef -> new VardefSubtree(node, elType);
//            case SampleLanguageParser.RULE_formal_arg -> new ArgdefSubtree(node, elType);
//            case SampleLanguageParser.RULE_block -> new BlockSubtree(node);
//            case SampleLanguageParser.RULE_call_expr -> new CallSubtree(node);
            default -> {
                LOG.info("SOME DECL " + node);
                return new ANTLRPsiNode(node);
            }
        }
    }
}
