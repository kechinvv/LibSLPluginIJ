package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.language.psi.rules.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

public class PsiElementFactory {
    public static final Logger LOG = Logger.getInstance("PsiElementFactory");

    public static final PsiElementFactory INSTANCE = new PsiElementFactory();

    private PsiElementFactory() {
    }

    public PsiElement createElement(ASTNode node) {
        IElementType elType = node.getElementType();
        if (elType instanceof TokenIElementType) return new ASTWrapperPsiElement(node);

        if (!(elType instanceof RuleIElementType ruleElType)) return new ASTWrapperPsiElement(node);

        return switch (ruleElType.getRuleIndex()) {
            case LibSLParser.RULE_file -> new LslFile(node);
            case LibSLParser.RULE_globalStatement -> new LslGlobalStatement(node);
            case LibSLParser.RULE_topLevelDecl -> new LslTopLevelDecl(node);
            case LibSLParser.RULE_header -> new LslHeader(node);
            case LibSLParser.RULE_typealiasStatement -> new LslTypeAliasStatement(node);
            case LibSLParser.RULE_typeDefBlock -> new LslTypeDefBlock(node);
            case LibSLParser.RULE_targetType -> new LslTargetType(node);
            case LibSLParser.RULE_typeList -> new LslTypeList(node);
            case LibSLParser.RULE_typeDefBlockStatement -> new LslTypeDefBlockStatement(node);
            case LibSLParser.RULE_typeDefBlockBody -> new LslTypeDefBlockBody(node);
            case LibSLParser.RULE_enumBlock -> new LslEnumBlock(node);
            case LibSLParser.RULE_enumBlockStatement -> new LslEnumBlockStatement(node);
            case LibSLParser.RULE_typesSection -> new LslTypesSection(node);
            case LibSLParser.RULE_semanticTypeDecl -> new LslSemanticTypeDecl(node);
            case LibSLParser.RULE_simpleSemanticType -> new LslSimpleSemanticType(node);
            case LibSLParser.RULE_enumSemanticType -> new LslEnumSemanticType(node);
            case LibSLParser.RULE_enumSemanticTypeEntry -> new LslEnumSemanticTypeEntry(node);
            case LibSLParser.RULE_annotationDecl -> new LslAnnotationDecl(node);
            case LibSLParser.RULE_annotationDeclParams -> new LslAnnotationDeclParams(node);
            case LibSLParser.RULE_annotationDeclParamsPart -> new LslAnnotationDeclParamsPart(node);
            case LibSLParser.RULE_actionDecl -> new LslActionDecl(node);
            case LibSLParser.RULE_actionDeclParamList -> new LslActionDeclParamList(node);
            case LibSLParser.RULE_actionParameter -> new LslActionParameter(node);
            case LibSLParser.RULE_automatonDecl -> new LslAutomatonDecl(node);
            case LibSLParser.RULE_automatonBody -> new LslAutomatonBody(node);
            case LibSLParser.RULE_constructorVariables -> new LslConstructorVariables(node);
            case LibSLParser.RULE_automatonStatement -> new LslAutomatonStatement(node);
            case LibSLParser.RULE_implementedConcepts -> new LslImplementedConcepts(node);
            case LibSLParser.RULE_concept -> new LslConcept(node);
            case LibSLParser.RULE_automatonStateDecl -> new LslAutomatonStateDecl(node);
            case LibSLParser.RULE_automatonShiftDecl -> new LslAutomatonShiftDecl(node);
            case LibSLParser.RULE_shiftByList -> new LslShiftByList(node);
            case LibSLParser.RULE_shiftFromList -> new LslShiftFromList(node);
            case LibSLParser.RULE_functionsList -> new LslFunctionsList(node);
            case LibSLParser.RULE_functionsListPart -> new LslFunctionsListPart(node);
            case LibSLParser.RULE_variableDecl -> new LslVariableDecl(node);
            case LibSLParser.RULE_nameWithType -> new LslNameWithType(node);

            case LibSLParser.RULE_typeExpression -> new LslTypeExpression(node);

            case LibSLParser.RULE_typeIdentifier -> new LslTypeIdentifier(node);
            case LibSLParser.RULE_generic -> new LslGeneric(node);

            case LibSLParser.RULE_typeArgument -> new LslTypeArgument(node);
            case LibSLParser.RULE_typeIdentifierBounded -> new LslTypeIdentifierBounded(node);

            case LibSLParser.RULE_variableAssignment -> new VariableAssignment(node);
            case LibSLParser.RULE_assignmentRight -> new LslAssignmentRight(node);
            case LibSLParser.RULE_callAutomatonConstructorWithNamedArgs ->
                    new LslCallAutomatonConstructorWithNamedArgs(node);
            case LibSLParser.RULE_namedArgs -> new LslNamedArgs(node);
            case LibSLParser.RULE_argPair -> new LslArgPair(node);
            case LibSLParser.RULE_headerWithAsterisk -> new LslHeaderWithAsterisk(node);
            case LibSLParser.RULE_constructorDecl -> new LslConstructorDecl(node);
            case LibSLParser.RULE_constructorHeader -> new LslConstructorHeader(node);
            case LibSLParser.RULE_destructorDecl -> new LslDestructorDecl(node);
            case LibSLParser.RULE_destructorHeader -> new LslDestructorHeader(node);
            case LibSLParser.RULE_procDecl -> new LslProcDecl(node);
            case LibSLParser.RULE_procHeader -> new LslProcHeader(node);
            case LibSLParser.RULE_functionDecl -> new LslFunctionDecl(node);
            case LibSLParser.RULE_functionHeader -> new LslFunctionHeader(node);
            case LibSLParser.RULE_functionDeclArgList -> new LslFunctionDeclArgList(node);
            case LibSLParser.RULE_parameter -> new LslParameter(node);
            case LibSLParser.RULE_annotationUsage -> new LslAnnotationUsage(node);
            case LibSLParser.RULE_functionContract -> new LslFunctionContract(node);
            case LibSLParser.RULE_functionBody -> new LslFunctionBody(node);
            case LibSLParser.RULE_functionBodyStatement -> new LslFunctionBodyStatement(node);
            case LibSLParser.RULE_conditionBody -> new LslConditionBody(node);
            case LibSLParser.RULE_ifStatement -> new LslIfStatement(node);
            case LibSLParser.RULE_elseStatement -> new LslElseStatement(node);
            case LibSLParser.RULE_actionUsage -> new LslActionUsage(node);
            case LibSLParser.RULE_procUsage -> new LslProcUsage(node);
            case LibSLParser.RULE_expressionsList -> new LslExpressionList(node);
            case LibSLParser.RULE_annotationArgs -> new LslAnnotationArgs(node);
            case LibSLParser.RULE_argName -> new LslArgName(node);
            case LibSLParser.RULE_requiresContract -> new LslRequiresContract(node);
            case LibSLParser.RULE_ensuresContract -> new LslEnsuresContract(node);
            case LibSLParser.RULE_assignsContract -> new LslAssignsContract(node);
            case LibSLParser.RULE_expression -> new LslExpression(node);
            case LibSLParser.RULE_hasAutomatonConcept -> new LslHasAutomatonConcept(node);
            case LibSLParser.RULE_bitShiftOp -> new LslBitShiftOp(node);
            case LibSLParser.RULE_lShift -> new LslLShift(node);
            case LibSLParser.RULE_rShift -> new LslRShift(node);
            case LibSLParser.RULE_uRShift -> new LslURShift(node);
            case LibSLParser.RULE_uLShift -> new LslULShift(node);
            case LibSLParser.RULE_expressionAtomic -> new LslExpressionAtomic(node);
            case LibSLParser.RULE_primitiveLiteral -> new LslPrimitiveLiteral(node);
            case LibSLParser.RULE_qualifiedAccess -> new LslQualifiedAccess(node);
            case LibSLParser.RULE_simpleCall -> new LslSimpleCall(node);
            case LibSLParser.RULE_identifierList -> new LslIdentifierList(node);
            case LibSLParser.RULE_arrayLiteral -> new LslArrayLiteral(node);
            case LibSLParser.RULE_periodSeparatedFullName -> new LslPeriodSeparatedFullName(node);
            case LibSLParser.RULE_integerNumber -> new LslIntegerNumber(node);
            case LibSLParser.RULE_floatNumber -> new LslFloatNumber(node);

            case LibSLParser.RULE_suffix -> new LslSuffix(node);
            case LibSLParser.RULE_typeConstraint -> new LslTypeConstraint(node);
            case LibSLParser.RULE_whereConstraints -> new LslWhereConstraint(node);
            case LibSLParser.RULE_genericBound -> new LslGenericBound(node);
            case LibSLParser.RULE_typeIdentifierName -> new LslTypeIdentifierName(node);

            case LibSLParser.RULE_identifier -> new LslIdentifier(node);
            default -> new ASTWrapperPsiElement(node);
        };
    }

    public static PsiElement createIdentifierTokenFromText(Project project, String text) {
        var child = createIdentifierFromText(project, text)
                .getNode()
                .findChildByType(tokens.get(LibSLLexer.Identifier));
        if (child != null) return child.getPsi();
        else return null;
    }

    public static LslIdentifier createIdentifierFromText(Project project, String text) {
        var lslFile = createFileFromText(project, text);
        return PsiTreeUtil.findChildOfType(lslFile, LslIdentifier.class, true);
    }

    public static LibSLPSIFileRoot createFileFromText(Project project, String text)  {
        return (LibSLPSIFileRoot) PsiFileFactory.getInstance(project)
                .createFileFromText("stub.lsl", LibSLFileType.INSTANCE, text);
    }


}
