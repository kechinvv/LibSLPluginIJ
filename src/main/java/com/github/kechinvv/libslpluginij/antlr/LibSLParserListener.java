// Generated from C:/Users/valer/IdeaProjects/LibSLPluginIJ/src/main/java/com/github/kechinvv/libslpluginij/antlr/LibSLParser.g4 by ANTLR 4.13.1
package com.github.kechinvv.libslpluginij.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LibSLParser}.
 */
public interface LibSLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LibSLParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(LibSLParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(LibSLParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#globalStatement}.
	 * @param ctx the parse tree
	 */
	void enterGlobalStatement(LibSLParser.GlobalStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#globalStatement}.
	 * @param ctx the parse tree
	 */
	void exitGlobalStatement(LibSLParser.GlobalStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#topLevelDecl}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelDecl(LibSLParser.TopLevelDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#topLevelDecl}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelDecl(LibSLParser.TopLevelDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(LibSLParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(LibSLParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#typealiasStatement}.
	 * @param ctx the parse tree
	 */
	void enterTypealiasStatement(LibSLParser.TypealiasStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#typealiasStatement}.
	 * @param ctx the parse tree
	 */
	void exitTypealiasStatement(LibSLParser.TypealiasStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#typeDefBlock}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefBlock(LibSLParser.TypeDefBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#typeDefBlock}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefBlock(LibSLParser.TypeDefBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#typeDefBlockStatement}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefBlockStatement(LibSLParser.TypeDefBlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#typeDefBlockStatement}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefBlockStatement(LibSLParser.TypeDefBlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#enumBlock}.
	 * @param ctx the parse tree
	 */
	void enterEnumBlock(LibSLParser.EnumBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#enumBlock}.
	 * @param ctx the parse tree
	 */
	void exitEnumBlock(LibSLParser.EnumBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#enumBlockStatement}.
	 * @param ctx the parse tree
	 */
	void enterEnumBlockStatement(LibSLParser.EnumBlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#enumBlockStatement}.
	 * @param ctx the parse tree
	 */
	void exitEnumBlockStatement(LibSLParser.EnumBlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#typesSection}.
	 * @param ctx the parse tree
	 */
	void enterTypesSection(LibSLParser.TypesSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#typesSection}.
	 * @param ctx the parse tree
	 */
	void exitTypesSection(LibSLParser.TypesSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#semanticTypeDecl}.
	 * @param ctx the parse tree
	 */
	void enterSemanticTypeDecl(LibSLParser.SemanticTypeDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#semanticTypeDecl}.
	 * @param ctx the parse tree
	 */
	void exitSemanticTypeDecl(LibSLParser.SemanticTypeDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#simpleSemanticType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleSemanticType(LibSLParser.SimpleSemanticTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#simpleSemanticType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleSemanticType(LibSLParser.SimpleSemanticTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#enumSemanticType}.
	 * @param ctx the parse tree
	 */
	void enterEnumSemanticType(LibSLParser.EnumSemanticTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#enumSemanticType}.
	 * @param ctx the parse tree
	 */
	void exitEnumSemanticType(LibSLParser.EnumSemanticTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#enumSemanticTypeEntry}.
	 * @param ctx the parse tree
	 */
	void enterEnumSemanticTypeEntry(LibSLParser.EnumSemanticTypeEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#enumSemanticTypeEntry}.
	 * @param ctx the parse tree
	 */
	void exitEnumSemanticTypeEntry(LibSLParser.EnumSemanticTypeEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#annotationDecl}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationDecl(LibSLParser.AnnotationDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#annotationDecl}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationDecl(LibSLParser.AnnotationDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#annotationDeclParams}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationDeclParams(LibSLParser.AnnotationDeclParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#annotationDeclParams}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationDeclParams(LibSLParser.AnnotationDeclParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#annotationDeclParamsPart}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationDeclParamsPart(LibSLParser.AnnotationDeclParamsPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#annotationDeclParamsPart}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationDeclParamsPart(LibSLParser.AnnotationDeclParamsPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#actionDecl}.
	 * @param ctx the parse tree
	 */
	void enterActionDecl(LibSLParser.ActionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#actionDecl}.
	 * @param ctx the parse tree
	 */
	void exitActionDecl(LibSLParser.ActionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#actionDeclParamList}.
	 * @param ctx the parse tree
	 */
	void enterActionDeclParamList(LibSLParser.ActionDeclParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#actionDeclParamList}.
	 * @param ctx the parse tree
	 */
	void exitActionDeclParamList(LibSLParser.ActionDeclParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#actionParameter}.
	 * @param ctx the parse tree
	 */
	void enterActionParameter(LibSLParser.ActionParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#actionParameter}.
	 * @param ctx the parse tree
	 */
	void exitActionParameter(LibSLParser.ActionParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#automatonDecl}.
	 * @param ctx the parse tree
	 */
	void enterAutomatonDecl(LibSLParser.AutomatonDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#automatonDecl}.
	 * @param ctx the parse tree
	 */
	void exitAutomatonDecl(LibSLParser.AutomatonDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#constructorVariables}.
	 * @param ctx the parse tree
	 */
	void enterConstructorVariables(LibSLParser.ConstructorVariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#constructorVariables}.
	 * @param ctx the parse tree
	 */
	void exitConstructorVariables(LibSLParser.ConstructorVariablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#automatonStatement}.
	 * @param ctx the parse tree
	 */
	void enterAutomatonStatement(LibSLParser.AutomatonStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#automatonStatement}.
	 * @param ctx the parse tree
	 */
	void exitAutomatonStatement(LibSLParser.AutomatonStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#automatonStateDecl}.
	 * @param ctx the parse tree
	 */
	void enterAutomatonStateDecl(LibSLParser.AutomatonStateDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#automatonStateDecl}.
	 * @param ctx the parse tree
	 */
	void exitAutomatonStateDecl(LibSLParser.AutomatonStateDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#automatonShiftDecl}.
	 * @param ctx the parse tree
	 */
	void enterAutomatonShiftDecl(LibSLParser.AutomatonShiftDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#automatonShiftDecl}.
	 * @param ctx the parse tree
	 */
	void exitAutomatonShiftDecl(LibSLParser.AutomatonShiftDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#functionsList}.
	 * @param ctx the parse tree
	 */
	void enterFunctionsList(LibSLParser.FunctionsListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#functionsList}.
	 * @param ctx the parse tree
	 */
	void exitFunctionsList(LibSLParser.FunctionsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#functionsListPart}.
	 * @param ctx the parse tree
	 */
	void enterFunctionsListPart(LibSLParser.FunctionsListPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#functionsListPart}.
	 * @param ctx the parse tree
	 */
	void exitFunctionsListPart(LibSLParser.FunctionsListPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void enterVariableDecl(LibSLParser.VariableDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void exitVariableDecl(LibSLParser.VariableDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#nameWithType}.
	 * @param ctx the parse tree
	 */
	void enterNameWithType(LibSLParser.NameWithTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#nameWithType}.
	 * @param ctx the parse tree
	 */
	void exitNameWithType(LibSLParser.NameWithTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#typeIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeIdentifier(LibSLParser.TypeIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#typeIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeIdentifier(LibSLParser.TypeIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignment(LibSLParser.VariableAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignment(LibSLParser.VariableAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#assignmentRight}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentRight(LibSLParser.AssignmentRightContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#assignmentRight}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentRight(LibSLParser.AssignmentRightContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#callAutomatonConstructorWithNamedArgs}.
	 * @param ctx the parse tree
	 */
	void enterCallAutomatonConstructorWithNamedArgs(LibSLParser.CallAutomatonConstructorWithNamedArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#callAutomatonConstructorWithNamedArgs}.
	 * @param ctx the parse tree
	 */
	void exitCallAutomatonConstructorWithNamedArgs(LibSLParser.CallAutomatonConstructorWithNamedArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#namedArgs}.
	 * @param ctx the parse tree
	 */
	void enterNamedArgs(LibSLParser.NamedArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#namedArgs}.
	 * @param ctx the parse tree
	 */
	void exitNamedArgs(LibSLParser.NamedArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#argPair}.
	 * @param ctx the parse tree
	 */
	void enterArgPair(LibSLParser.ArgPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#argPair}.
	 * @param ctx the parse tree
	 */
	void exitArgPair(LibSLParser.ArgPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#constructorDecl}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDecl(LibSLParser.ConstructorDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#constructorDecl}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDecl(LibSLParser.ConstructorDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#destructorDecl}.
	 * @param ctx the parse tree
	 */
	void enterDestructorDecl(LibSLParser.DestructorDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#destructorDecl}.
	 * @param ctx the parse tree
	 */
	void exitDestructorDecl(LibSLParser.DestructorDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#procDecl}.
	 * @param ctx the parse tree
	 */
	void enterProcDecl(LibSLParser.ProcDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#procDecl}.
	 * @param ctx the parse tree
	 */
	void exitProcDecl(LibSLParser.ProcDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(LibSLParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(LibSLParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#functionDeclArgList}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclArgList(LibSLParser.FunctionDeclArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#functionDeclArgList}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclArgList(LibSLParser.FunctionDeclArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(LibSLParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(LibSLParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#annotationUsage}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationUsage(LibSLParser.AnnotationUsageContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#annotationUsage}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationUsage(LibSLParser.AnnotationUsageContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#functionContract}.
	 * @param ctx the parse tree
	 */
	void enterFunctionContract(LibSLParser.FunctionContractContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#functionContract}.
	 * @param ctx the parse tree
	 */
	void exitFunctionContract(LibSLParser.FunctionContractContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBody(LibSLParser.FunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBody(LibSLParser.FunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#functionBodyStatement}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBodyStatement(LibSLParser.FunctionBodyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#functionBodyStatement}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBodyStatement(LibSLParser.FunctionBodyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(LibSLParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(LibSLParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(LibSLParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(LibSLParser.ElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#actionUsage}.
	 * @param ctx the parse tree
	 */
	void enterActionUsage(LibSLParser.ActionUsageContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#actionUsage}.
	 * @param ctx the parse tree
	 */
	void exitActionUsage(LibSLParser.ActionUsageContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#procUsage}.
	 * @param ctx the parse tree
	 */
	void enterProcUsage(LibSLParser.ProcUsageContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#procUsage}.
	 * @param ctx the parse tree
	 */
	void exitProcUsage(LibSLParser.ProcUsageContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#expressionsList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionsList(LibSLParser.ExpressionsListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#expressionsList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionsList(LibSLParser.ExpressionsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#annotationArgs}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationArgs(LibSLParser.AnnotationArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#annotationArgs}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationArgs(LibSLParser.AnnotationArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#argName}.
	 * @param ctx the parse tree
	 */
	void enterArgName(LibSLParser.ArgNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#argName}.
	 * @param ctx the parse tree
	 */
	void exitArgName(LibSLParser.ArgNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#requiresContract}.
	 * @param ctx the parse tree
	 */
	void enterRequiresContract(LibSLParser.RequiresContractContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#requiresContract}.
	 * @param ctx the parse tree
	 */
	void exitRequiresContract(LibSLParser.RequiresContractContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#ensuresContract}.
	 * @param ctx the parse tree
	 */
	void enterEnsuresContract(LibSLParser.EnsuresContractContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#ensuresContract}.
	 * @param ctx the parse tree
	 */
	void exitEnsuresContract(LibSLParser.EnsuresContractContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#assignsContract}.
	 * @param ctx the parse tree
	 */
	void enterAssignsContract(LibSLParser.AssignsContractContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#assignsContract}.
	 * @param ctx the parse tree
	 */
	void exitAssignsContract(LibSLParser.AssignsContractContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LibSLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LibSLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#hasAutomaton}.
	 * @param ctx the parse tree
	 */
	void enterHasAutomaton(LibSLParser.HasAutomatonContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#hasAutomaton}.
	 * @param ctx the parse tree
	 */
	void exitHasAutomaton(LibSLParser.HasAutomatonContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#bitShiftOp}.
	 * @param ctx the parse tree
	 */
	void enterBitShiftOp(LibSLParser.BitShiftOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#bitShiftOp}.
	 * @param ctx the parse tree
	 */
	void exitBitShiftOp(LibSLParser.BitShiftOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#lShift}.
	 * @param ctx the parse tree
	 */
	void enterLShift(LibSLParser.LShiftContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#lShift}.
	 * @param ctx the parse tree
	 */
	void exitLShift(LibSLParser.LShiftContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#rShift}.
	 * @param ctx the parse tree
	 */
	void enterRShift(LibSLParser.RShiftContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#rShift}.
	 * @param ctx the parse tree
	 */
	void exitRShift(LibSLParser.RShiftContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#uRShift}.
	 * @param ctx the parse tree
	 */
	void enterURShift(LibSLParser.URShiftContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#uRShift}.
	 * @param ctx the parse tree
	 */
	void exitURShift(LibSLParser.URShiftContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(LibSLParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(LibSLParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#expressionAtomic}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAtomic(LibSLParser.ExpressionAtomicContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#expressionAtomic}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAtomic(LibSLParser.ExpressionAtomicContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#primitiveLiteral}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveLiteral(LibSLParser.PrimitiveLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#primitiveLiteral}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveLiteral(LibSLParser.PrimitiveLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#qualifiedAccess}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedAccess(LibSLParser.QualifiedAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#qualifiedAccess}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedAccess(LibSLParser.QualifiedAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#simpleCall}.
	 * @param ctx the parse tree
	 */
	void enterSimpleCall(LibSLParser.SimpleCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#simpleCall}.
	 * @param ctx the parse tree
	 */
	void exitSimpleCall(LibSLParser.SimpleCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(LibSLParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(LibSLParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(LibSLParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(LibSLParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#periodSeparatedFullName}.
	 * @param ctx the parse tree
	 */
	void enterPeriodSeparatedFullName(LibSLParser.PeriodSeparatedFullNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#periodSeparatedFullName}.
	 * @param ctx the parse tree
	 */
	void exitPeriodSeparatedFullName(LibSLParser.PeriodSeparatedFullNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#integerNumber}.
	 * @param ctx the parse tree
	 */
	void enterIntegerNumber(LibSLParser.IntegerNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#integerNumber}.
	 * @param ctx the parse tree
	 */
	void exitIntegerNumber(LibSLParser.IntegerNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#floatNumber}.
	 * @param ctx the parse tree
	 */
	void enterFloatNumber(LibSLParser.FloatNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#floatNumber}.
	 * @param ctx the parse tree
	 */
	void exitFloatNumber(LibSLParser.FloatNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibSLParser#suffix}.
	 * @param ctx the parse tree
	 */
	void enterSuffix(LibSLParser.SuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibSLParser#suffix}.
	 * @param ctx the parse tree
	 */
	void exitSuffix(LibSLParser.SuffixContext ctx);
}