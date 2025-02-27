parser grammar LibSLParser;


options { tokenVocab = LibSLLexer; }

/*
 * entry rule
 * specification starts with header block ('libsl', 'library' and other keywords), then
 * semantic types section and declarations (automata and extension functions)
 */
file
   :   header?
       globalStatement*
       EOF
   ;

globalStatement
   :   ImportStatement
   |   IncludeStatement
   |   typesSection
   |   typealiasStatement
   |   typeDefBlock
   |   enumBlock
   |   annotationDecl
   |   actionDecl
   |   topLevelDecl
   ;

topLevelDecl
   :   automatonDecl
   |   functionDecl
   |   variableDecl
   ;

/*
 * header section
 * includes 'libsl' keyword with LibSL version, 'library' keyword with name of the library, and any of these optionally:
 * 'version', 'language' and 'url'
 */
header:
   (LIBSL lslver=DoubleQuotedString SEMICOLON)
   (LIBRARY libraryName=identifier)
   (VERSION ver = DoubleQuotedString)?
   (LANGUAGE lang=DoubleQuotedString)?
   (URL link=DoubleQuotedString)?
   SEMICOLON;

/* typealias statement
 * syntax: typealias name = origintlType
 */
typealiasStatement
   :   annotationUsage* TYPEALIAS left=typeIdentifier ASSIGN_OP right=typeIdentifier SEMICOLON
   ;

/* type define block
 * syntax: type full.name { field1: Type; field2: Type; ... }
 */
typeDefBlock
   :   annotationUsage* TYPE type=typeIdentifier targetType? whereConstraints? typeDefBlockBody?
   ;

targetType
   :   (IS typeIdentifier)? for=identifier typeList
   ;


typeList
   :  typeIdentifier (COMMA typeIdentifier)*
   ;

typeDefBlockStatement
   :   variableDecl
   |   functionDecl
   ;


typeDefBlockBody
   : L_BRACE typeDefBlockStatement* R_BRACE
   ;

/* enum block
 * syntax: enum Name { Variant1=0; Variant2=1; ... }
 */
enumBlock
   :   annotationUsage* ENUM typeIdentifier L_BRACE enumBlockStatement* R_BRACE
   ;

enumBlockStatement
   :   identifier ASSIGN_OP integerNumber SEMICOLON
   ;

/* semantic types section
 * syntax types { semanticTypeDeclaration1; semanticTypeDeclaration2; ... }
 */
typesSection
   :   TYPES L_BRACE semanticTypeDecl* R_BRACE
   ;

semanticTypeDecl
   :    simpleSemanticType
   |    enumSemanticType
   ;

/* simple semantic type
 * syntax: semanticTypeName (realTypeName);
 */
simpleSemanticType
   :   annotationUsage* semanticName=typeIdentifier L_BRACKET realName=typeIdentifier R_BRACKET SEMICOLON
   ;

/* block semantic type
 * syntax: semanticTypeName (realTypeName) {variant1: 0; variant2: 1; ...};
 */
enumSemanticType
   :   annotationUsage* semanticName=identifier L_BRACKET realName=typeIdentifier R_BRACKET L_BRACE enumSemanticTypeEntry+ R_BRACE
   ;

enumSemanticTypeEntry
   :   identifier COLON expressionAtomic SEMICOLON
   ;

/* annotation declaration
 * syntax: annotation Something(
 *             variable1: int = 0,
 *             variable2: int = 1
 *         );
 */
annotationDecl
   :   ANNOTATION name=identifier L_BRACKET annotationDeclParams? R_BRACKET SEMICOLON
   ;

annotationDeclParams
   :   annotationDeclParamsPart (COMMA annotationDeclParamsPart)* (COMMA)?
   ;

annotationDeclParamsPart
   :   nameWithType (ASSIGN_OP expression)?
   ;

actionDecl
   :   annotationUsage*
   DEFINE ACTION generic? actionName=identifier L_BRACKET actionDeclParamList? R_BRACKET (COLON actionType=typeIdentifier)? whereConstraints? SEMICOLON
   ;

actionDeclParamList
   :   actionParameter (COMMA actionParameter)* (COMMA)?
   ;

actionParameter
   :   annotationUsage* name=identifier COLON type=typeIdentifier
   ;

/* automaton declaration
 * syntax: [@Annotation1(param: type)
 *         @Annotation2(param: type]
 *         automaton Name [(constructor vars)] : type { statement1; statement2; ... }
 */
automatonDecl
   :   annotationUsage* AUTOMATON CONCEPT? name=periodSeparatedFullName (L_BRACKET constructorVariables* R_BRACKET)?
   COLON type=typeExpression implementedConcepts* automatonBody
   ;

automatonBody
   : L_BRACE automatonStatement* R_BRACE
   ;

constructorVariables
   :   annotationUsage* keyword=(VAR|VAL) nameWithType (COMMA)?
   |   annotationUsage* keyword=(VAR|VAL) nameWithType ASSIGN_OP assignmentRight (COMMA)?
   ;

automatonStatement
   :   automatonStateDecl
   |   automatonShiftDecl
   |   constructorDecl
   |   destructorDecl
   |   procDecl
   |   functionDecl
   |   variableDecl
   ;

implementedConcepts
   :   implements=identifier concept (COMMA concept)*
   ;

concept
   :   name=identifier
   ;

/* state declaration
 * syntax: one of {initstate; state; finishstate} name;
 */
automatonStateDecl
   :   keyword=(INITSTATE | STATE | FINISHSTATE) identifierList SEMICOLON
   ;

/* shift declaration
 * syntax: shift from -> to(function1; function2(optional arg types); ...)
 * syntax: shift (from1, from2, ...) -> to(function1; function2(optional arg types); ...)
 */
automatonShiftDecl
   :   SHIFT from=Identifier MINUS_ARROW to=Identifier BY functionsListPart SEMICOLON
   |   SHIFT from=Identifier MINUS_ARROW to=Identifier BY shiftByList SEMICOLON
   |   SHIFT shiftFromList MINUS_ARROW to=Identifier BY functionsListPart SEMICOLON
   |   SHIFT shiftFromList MINUS_ARROW to=Identifier BY shiftByList SEMICOLON
   ;

shiftByList
   : L_SQUARE_BRACKET functionsList? R_SQUARE_BRACKET
   ;

shiftFromList
   : L_BRACKET identifierList R_BRACKET
   ;

functionsList
   :   functionsListPart (COMMA functionsListPart)* (COMMA)?
   ;

functionsListPart
   :   name=identifier (L_BRACKET typeIdentifier? (COMMA typeIdentifier)* R_BRACKET)?
   ;

/* variable declaration with optional initializers
 * syntax: var NAME [= { new AutomatonName(args); atomic }]
 */
variableDecl
   :   annotationUsage* keyword=(VAR|VAL) nameWithType SEMICOLON
   |   annotationUsage* keyword=(VAR|VAL) nameWithType ASSIGN_OP assignmentRight SEMICOLON
   ;

nameWithType
   :  name=identifier COLON type=typeExpression
   ;

/*
 * syntax: one.two.three<T>
 */

typeExpression
   :   typeIdentifier
   |   typeExpression AMPERSAND  typeExpression
   |   typeExpression BIT_OR typeExpression
   ;

typeIdentifier
   :   (asterisk=ASTERISK)? name=typeIdentifierName generic?
   ;

generic
   :   (L_ARROW typeArgument (COMMA typeArgument)* R_ARROW)
   ;

typeArgument
    : typeIdentifier
    | typeIdentifierBounded
    ;

typeIdentifierBounded
    : genericBound typeIdentifier
    ;

variableAssignment
   :   qualifiedAccess op=ASSIGN_OP assignmentRight SEMICOLON
   |   qualifiedAccess op=(PLUS_EQ | MINUS_EQ | ASTERISK_EQ | SLASH_EQ | PERCENT_EQ) assignmentRight SEMICOLON
   |   qualifiedAccess op=(AMPERSAND_EQ | OR_EQ | XOR_EQ) assignmentRight SEMICOLON
   |   qualifiedAccess op=(R_SHIFT_EQ | L_SHIFT_EQ) assignmentRight SEMICOLON
   ;

assignmentRight
   :   expression
   ;

callAutomatonConstructorWithNamedArgs
   :   NEW name=periodSeparatedFullName generic? L_BRACKET (namedArgs)? R_BRACKET
   ;

namedArgs
   :   argPair (COMMA argPair)* (COMMA)?
   ;

argPair
   :   name=STATE ASSIGN_OP expressionAtomic
   |   name=Identifier ASSIGN_OP expression
   ;

headerWithAsterisk
   :   ASTERISK DOT
   ;

constructorDecl
   :   constructorHeader (SEMICOLON | functionBody )
   ;

constructorHeader
   :   annotationUsage* CONSTRUCTOR headerWithAsterisk? functionName=identifier? L_BRACKET functionDeclArgList? R_BRACKET
   (COLON functionType=typeIdentifier)?
   ;

destructorDecl
   :   destructorHeader (SEMICOLON | functionBody )?
   ;

destructorHeader
   :   annotationUsage* DESTRUCTOR headerWithAsterisk? functionName=identifier? L_BRACKET functionDeclArgList? R_BRACKET
   (COLON functionType=typeIdentifier)?
   ;

procDecl
   :   procHeader (SEMICOLON | functionBody )
   ;

procHeader
   :   annotationUsage* PROC headerWithAsterisk? functionName=identifier generic? L_BRACKET functionDeclArgList? R_BRACKET
   (COLON functionType=typeExpression)? whereConstraints?
   ;
/*
 * syntax: @Annotation
 *         fun name(@annotation arg1: type, arg2: type, ...) [: type] [preambule] { statement1; statement2; ... }
 * In case of declaring extension-function, name must look like Automaton.functionName
 */
functionDecl
   :   functionHeader (SEMICOLON | functionBody?)
   ;

functionHeader
   :   annotationUsage* modifier=identifier? FUN (automatonName=periodSeparatedFullName DOT)? headerWithAsterisk? functionName=identifier generic?
   L_BRACKET functionDeclArgList? R_BRACKET (COLON functionType=typeExpression)? whereConstraints?
   ;

functionDeclArgList
   :   parameter (COMMA parameter)*
   ;

parameter
   :   annotationUsage* name=identifier COLON type=typeExpression
   ;

/* annotation
 * syntax: @annotationName(args)
 */
annotationUsage
   :   AT identifier (L_BRACKET annotationArgs* R_BRACKET)?
   ;

functionContract
   :   requiresContract
   |   ensuresContract
   |   assignsContract
   ;

functionBody
   :   L_BRACE functionContract* functionBodyStatement* R_BRACE
   ;

functionBodyStatement
   :   variableAssignment
   |   variableDecl
   |   ifStatement
   |   expression SEMICOLON
   ;

conditionBody
   : L_BRACE functionBodyStatement* R_BRACE
   ;

ifStatement
   :   IF expression conditionBody (elseStatement)?
   |   IF expression functionBodyStatement (elseStatement)?
   ;

elseStatement
   :   ELSE conditionBody
   |   ELSE functionBodyStatement
   ;

/* semantic action
 * syntax: action ActionName(args)
 */
actionUsage
   :   ACTION identifier generic? L_BRACKET expressionsList? R_BRACKET
   ;

procUsage
   :   qualifiedAccess generic? L_BRACKET expressionsList? R_BRACKET
   ;

expressionsList
   :   expression (COMMA expression)* (COMMA)?
   ;

annotationArgs
   :   argName? expression (COMMA)?
   ;

argName
   :   name=identifier ASSIGN_OP
   ;

/* requires contract
 * syntax: requires [name:] condition
 */
requiresContract
   :   REQUIRES (name=identifier COLON)? expression SEMICOLON
   ;

/* ensures contract
 * syntax: ensures [name:] condition
 */
ensuresContract
   :   ENSURES (name=identifier COLON)? expression SEMICOLON
   ;

/* assigns contract
 * syntax: assigns [name:] condition
 */
assignsContract
   :   ASSIGNS (name=identifier COLON)? expression SEMICOLON
   ;

/*
 * expression
 */
expression
   :   expressionAtomic
   // primaryNoNewArray
   |   qualifiedAccess apostrophe=APOSTROPHE
   |   qualifiedAccess
   |   procUsage
   |   actionUsage
   |   callAutomatonConstructorWithNamedArgs
   |   lbracket=L_BRACKET expression rbracket=R_BRACKET
   |   hasAutomatonConcept

   // unaryExpression + unaryExpressionNotPlusMinus
   |   unaryOp=(PLUS | MINUS | TILDE | EXCLAMATION) expression

   // castExpression
   |   expression typeOp=AS typeIdentifier

   // multiplicativeExpression
   |   expression op=(ASTERISK | SLASH | PERCENT) expression

   // additiveExpression
   |   expression op=(PLUS | MINUS) expression

   // shiftExpression
   |   expression bitShiftOp expression

   // relationalExpression
   |   expression op=(L_ARROW | R_ARROW | L_ARROW_EQ | R_ARROW_EQ) expression
   |   expression typeOp=IS typeIdentifier

   // equalityExpression
   |   expression op=(EQ | EXCLAMATION_EQ) expression

   // inclusiveOrExpression
   |   expression op=BIT_OR expression
   // exclusiveOrExpression
   |   expression op=XOR expression
   // andExpression
   |   expression op=AMPERSAND expression

   // conditionalOrExpression
   |   expression op=LOGIC_OR expression
   // conditionalAndExpression
   |   expression op=DOUBLE_AMPERSAND expression
   ;

hasAutomatonConcept
   :   qualifiedAccess has=identifier name=identifier
   ;

bitShiftOp
   :   lShift
   |   rShift
   |   uRShift
   |   uLShift
   ;

lShift
   :   L_ARROW L_ARROW
   ;

rShift
   :   R_ARROW R_ARROW
   ;

uRShift
   :   R_ARROW R_ARROW R_ARROW
   ;

uLShift
   :   L_ARROW L_ARROW L_ARROW
   ;

expressionAtomic
   :   primitiveLiteral
   |   arrayLiteral
   |   qualifiedAccess
   ;

primitiveLiteral
   :   integerNumber
   |   floatNumber
   |   DoubleQuotedString
   |   CHARACTER
   |   bool=(TRUE | FALSE)
   |   nullLiteral=NULL
   ;

qualifiedAccess
   :   periodSeparatedFullName
   |   qualifiedAccess L_SQUARE_BRACKET expression R_SQUARE_BRACKET (DOT qualifiedAccess)?
   |   simpleCall DOT qualifiedAccess
   |   simpleCall DOT procUsage
   ;

simpleCall
   :   identifier generic? L_BRACKET qualifiedAccess R_BRACKET
   ;

identifierList
   :   identifier (COMMA identifier)*
   ;

arrayLiteral
   :   L_SQUARE_BRACKET expressionsList? R_SQUARE_BRACKET
   ;

periodSeparatedFullName
   :   identifier
   |   identifier (DOT identifier)*
   |   BACK_QOUTE identifier (DOT identifier)* BACK_QOUTE
   |   UNBOUNDED
   ;

integerNumber
   :   (MINUS | PLUS)? IntegerLiteral
   ;

floatNumber
   :   (MINUS | PLUS)? FloatingPointLiteral
   ;

suffix
   :   identifier
   ;

typeConstraint
    : paramName=identifier COLON paramConstraint=typeArgument
    ;

whereConstraints
    : WHERE typeConstraint (COMMA typeConstraint)*
    ;

genericBound
   :   bound=(IN | OUT)
   ;

typeIdentifierName
   :   periodSeparatedFullName
   |   primitiveLiteral
   ;

identifier
   :   Identifier
   ;