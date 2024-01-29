package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.language.psi.rules.ProcDecl;
import com.github.kechinvv.libslpluginij.language.psi.rules.VariableAssignment;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.sun.xml.bind.v2.TODO;
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
        return switch (ruleElType.getRuleIndex()) {
            case LibSLParser.RULE_file -> null;
            case LibSLParser.RULE_globalStatement -> null;
            case LibSLParser.RULE_topLevelDecl -> null;
            case LibSLParser.RULE_header -> null;
            case LibSLParser.RULE_typealiasStatement -> null;
            case LibSLParser.RULE_typeDefBlock -> null;
            case LibSLParser.RULE_targetType -> null;
            case LibSLParser.RULE_typeList -> null;
            case LibSLParser.RULE_typeDefBlockStatement -> null;
            case LibSLParser.RULE_enumBlock -> null;
            case LibSLParser.RULE_enumBlockStatement -> null;
            case LibSLParser.RULE_typesSection -> null;
            case LibSLParser.RULE_semanticTypeDecl -> null;
            case LibSLParser.RULE_simpleSemanticType -> null;
            case LibSLParser.RULE_enumSemanticType -> null;
            case LibSLParser.RULE_enumSemanticTypeEntry -> null;
            case LibSLParser.RULE_annotationDecl -> null;
            case LibSLParser.RULE_annotationDeclParams -> null;
            case LibSLParser.RULE_annotationDeclParamsPart -> null;
            case LibSLParser.RULE_actionDecl -> null;
            case LibSLParser.RULE_actionDeclParamList -> null;
            case LibSLParser.RULE_actionParameter -> null;
            case LibSLParser.RULE_automatonDecl -> null;
            case LibSLParser.RULE_constructorVariables -> null;
            case LibSLParser.RULE_automatonStatement -> null;
            case LibSLParser.RULE_implementedConcepts -> null;
            case LibSLParser.RULE_concept -> null;
            case LibSLParser.RULE_automatonStateDecl -> null;
            case LibSLParser.RULE_automatonShiftDecl -> null;
            case LibSLParser.RULE_functionsList -> null;
            case LibSLParser.RULE_functionsListPart -> null;
            case LibSLParser.RULE_variableDecl -> null;
            case LibSLParser.RULE_nameWithType -> null;
            case LibSLParser.RULE_typeIdentifier -> null;
            case LibSLParser.RULE_generic -> null;
            case LibSLParser.RULE_variableAssignment -> new VariableAssignment(node);
            case LibSLParser.RULE_assignmentRight -> null;
            case LibSLParser.RULE_callAutomatonConstructorWithNamedArgs -> null;
            case LibSLParser.RULE_namedArgs -> null;
            case LibSLParser.RULE_argPair -> null;
            case LibSLParser.RULE_headerWithAsterisk -> null;
            case LibSLParser.RULE_constructorDecl -> null;
            case LibSLParser.RULE_constructorHeader -> null;
            case LibSLParser.RULE_destructorDecl -> null;
            case LibSLParser.RULE_destructorHeader -> null;
            case LibSLParser.RULE_procDecl -> new ProcDecl(node, elType);
            case LibSLParser.RULE_procHeader -> null;
            case LibSLParser.RULE_functionDecl -> null;
            case LibSLParser.RULE_functionHeader -> null;
            case LibSLParser.RULE_functionDeclArgList -> null;
            case LibSLParser.RULE_parameter -> null;
            case LibSLParser.RULE_annotationUsage -> null;
            case LibSLParser.RULE_functionContract -> null;
            case LibSLParser.RULE_functionBody -> null;
            case LibSLParser.RULE_functionBodyStatement -> null;
            case LibSLParser.RULE_ifStatement -> null;
            case LibSLParser.RULE_elseStatement -> null;
            case LibSLParser.RULE_actionUsage -> null;
            case LibSLParser.RULE_procUsage -> null;
            case LibSLParser.RULE_expressionsList -> null;
            case LibSLParser.RULE_annotationArgs -> null;
            case LibSLParser.RULE_argName -> null;
            case LibSLParser.RULE_requiresContract -> null;
            case LibSLParser.RULE_ensuresContract -> null;
            case LibSLParser.RULE_assignsContract -> null;
            case LibSLParser.RULE_expression -> null;
            case LibSLParser.RULE_hasAutomatonConcept -> null;
            case LibSLParser.RULE_bitShiftOp -> null;
            case LibSLParser.RULE_lShift -> null;
            case LibSLParser.RULE_rShift -> null;
            case LibSLParser.RULE_uRShift -> null;
            case LibSLParser.RULE_uLShift -> null;
            case LibSLParser.RULE_expressionAtomic -> null;
            case LibSLParser.RULE_primitiveLiteral -> null;
            case LibSLParser.RULE_qualifiedAccess -> null;
            case LibSLParser.RULE_simpleCall -> null;
            case LibSLParser.RULE_identifierList -> null;
            case LibSLParser.RULE_arrayLiteral -> null;
            case LibSLParser.RULE_periodSeparatedFullName -> null;
            case LibSLParser.RULE_integerNumber -> null;
            case LibSLParser.RULE_floatNumber -> null;
            case LibSLParser.RULE_suffix -> null;
            default ->  new ANTLRPsiNode(node);
        };
    }
}
