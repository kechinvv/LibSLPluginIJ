// Generated from C:/Users/valer/IdeaProjects/LibSLPluginIJ/src/main/java/com/github/kechinvv/libslpluginij/antlr/LibSLParser.g4 by ANTLR 4.13.1
package com.github.kechinvv.libslpluginij.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LibSLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LibSLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LibSLParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(LibSLParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#globalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalStatement(LibSLParser.GlobalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#topLevelDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevelDecl(LibSLParser.TopLevelDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(LibSLParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#typealiasStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypealiasStatement(LibSLParser.TypealiasStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#typeDefBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefBlock(LibSLParser.TypeDefBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#typeDefBlockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefBlockStatement(LibSLParser.TypeDefBlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#enumBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumBlock(LibSLParser.EnumBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#enumBlockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumBlockStatement(LibSLParser.EnumBlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#typesSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypesSection(LibSLParser.TypesSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#semanticTypeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSemanticTypeDecl(LibSLParser.SemanticTypeDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#simpleSemanticType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleSemanticType(LibSLParser.SimpleSemanticTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#enumSemanticType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumSemanticType(LibSLParser.EnumSemanticTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#enumSemanticTypeEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumSemanticTypeEntry(LibSLParser.EnumSemanticTypeEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#annotationDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationDecl(LibSLParser.AnnotationDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#annotationDeclParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationDeclParams(LibSLParser.AnnotationDeclParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#annotationDeclParamsPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationDeclParamsPart(LibSLParser.AnnotationDeclParamsPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#actionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionDecl(LibSLParser.ActionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#actionDeclParamList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionDeclParamList(LibSLParser.ActionDeclParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#actionParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionParameter(LibSLParser.ActionParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#automatonDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutomatonDecl(LibSLParser.AutomatonDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#constructorVariables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorVariables(LibSLParser.ConstructorVariablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#automatonStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutomatonStatement(LibSLParser.AutomatonStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#automatonStateDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutomatonStateDecl(LibSLParser.AutomatonStateDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#automatonShiftDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutomatonShiftDecl(LibSLParser.AutomatonShiftDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#functionsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionsList(LibSLParser.FunctionsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#functionsListPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionsListPart(LibSLParser.FunctionsListPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#variableDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDecl(LibSLParser.VariableDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#nameWithType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNameWithType(LibSLParser.NameWithTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#typeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIdentifier(LibSLParser.TypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#variableAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAssignment(LibSLParser.VariableAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#assignmentRight}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentRight(LibSLParser.AssignmentRightContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#callAutomatonConstructorWithNamedArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallAutomatonConstructorWithNamedArgs(LibSLParser.CallAutomatonConstructorWithNamedArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#namedArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedArgs(LibSLParser.NamedArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#argPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgPair(LibSLParser.ArgPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#constructorDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDecl(LibSLParser.ConstructorDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#destructorDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestructorDecl(LibSLParser.DestructorDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#procDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcDecl(LibSLParser.ProcDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(LibSLParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#functionDeclArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclArgList(LibSLParser.FunctionDeclArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(LibSLParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#annotationUsage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationUsage(LibSLParser.AnnotationUsageContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#functionContract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionContract(LibSLParser.FunctionContractContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#functionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBody(LibSLParser.FunctionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#functionBodyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBodyStatement(LibSLParser.FunctionBodyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(LibSLParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(LibSLParser.ElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#actionUsage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionUsage(LibSLParser.ActionUsageContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#procUsage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcUsage(LibSLParser.ProcUsageContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#expressionsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionsList(LibSLParser.ExpressionsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#annotationArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationArgs(LibSLParser.AnnotationArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#argName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgName(LibSLParser.ArgNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#requiresContract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequiresContract(LibSLParser.RequiresContractContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#ensuresContract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnsuresContract(LibSLParser.EnsuresContractContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#assignsContract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignsContract(LibSLParser.AssignsContractContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LibSLParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#hasAutomaton}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHasAutomaton(LibSLParser.HasAutomatonContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#bitShiftOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitShiftOp(LibSLParser.BitShiftOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#lShift}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLShift(LibSLParser.LShiftContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#rShift}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRShift(LibSLParser.RShiftContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#uRShift}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitURShift(LibSLParser.URShiftContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(LibSLParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#expressionAtomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAtomic(LibSLParser.ExpressionAtomicContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#primitiveLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveLiteral(LibSLParser.PrimitiveLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#qualifiedAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedAccess(LibSLParser.QualifiedAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#simpleCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleCall(LibSLParser.SimpleCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(LibSLParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(LibSLParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#periodSeparatedFullName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPeriodSeparatedFullName(LibSLParser.PeriodSeparatedFullNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#integerNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerNumber(LibSLParser.IntegerNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#floatNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatNumber(LibSLParser.FloatNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibSLParser#suffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuffix(LibSLParser.SuffixContext ctx);
}