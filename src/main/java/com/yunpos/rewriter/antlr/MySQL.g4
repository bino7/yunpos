grammar MySQL;

options {
	//tokenVocab=MySQLBase;
}
/*package  com.yunpos.rewriter;*/

@header {
}

SELECT: 'select';
DELETE: 'delete';
UPDATE: 'update';
INSERT: 'insert';
INTO: 'into';
VALUES: 'value';
FROM: 'from';
SET:'set';
WHERE: 'where';
AS:'as';
AND: 'and' | '&&';
OR: 'or' | '||';
XOR: 'xor';
IS: 'is';
NULL: 'null';
LIKE: 'like';
IN: 'in';
EXISTS: 'exists';
ALL: 'all';
ANY: 'any';
TRUE: 'true';
FALSE: 'false';
DIVIDE	: 'div' | '/' ;
MOD: 'mod' | '%' ;
BETWEEN: 'between';
REGEXP: 'regexp';
PLUS	: '+' ;
MINUS	: '-' ;
NEGATION: '~' ;
VERTBAR	: '|' ;
BITAND	: '&' ;
POWER: '^' ;
BINARY: 'binary';
SHIFT_LEFT	: '<<' ;
SHIFT_RIGHT	: '>>' ;
ESCAPE: 'escape';
ASTERISK: '*' ;
RPAREN	: ')' ;
LPAREN	: '(' ;
RBRACK	: ']' ;
LBRACK	: '[' ;
COLON	: ':' ;
//ALL_FIELDS	: '.*' ;
EQ: '=';
LTH: '<';
GTH: '>';
NOT_EQ: '!=';
NOT: 'not';
LET: '<=';
GET: '>=';
SEMI: ';';
COMMA: ',';
DOT: '.';
COLLATE: 'collate';
INNER: 'inner';
OUTER: 'outer';
JOIN: 'join';
CROSS: 'cross';
USING: 'using';
INDEX: 'index';
KEY: 'keyParam';
ORDER: 'order';
GROUP: 'group';
BY: 'by';
FOR: 'for';
USE: 'use';
IGNORE: 'ignore';
PARTITION: 'partition';
STRAIGHT_JOIN: 'straight_join';
NATURAL: 'natural';
LEFT: 'left';
RIGHT: 'right';
OJ: 'oj';
ON: 'on';
LIMIT:'limit';
ASC:'asc';
DESC:'desc';
HAVING:'having';
OFFSET:'offset';
DEFAULT:'default';
QUTE:'\'';

//EXP_INDEX    : LODASH ( VARIABLE | INT ) ;

//VARIABLE : ID ( 'A'..'Z' | 'a'..'z' | INT )* ;

//CHAR     : [a-zA-Z] ;

LODASH   : '_' ;

ID: ('A'..'Z' | 'a'..'z' | LODASH |MOD )+ (INT)* ;
INT: '0'..'9'+ ;

NEWLINE: '\r' ? '\n' -> skip;
WS: (' ' | '\t' | '\n' | '\r')+ -> skip;

USER_VAR:
	'@' (USER_VAR_SUBFIX1 | USER_VAR_SUBFIX2 | USER_VAR_SUBFIX3 | USER_VAR_SUBFIX4)
;

fragment USER_VAR_SUBFIX1:	(  '`' (~'`' )+ '`'  ) ;
fragment USER_VAR_SUBFIX2:	( '\'' (~'\'')+ '\'' ) ;
fragment USER_VAR_SUBFIX3:	( '\"' (~'\"')+ '\"' ) ;
fragment USER_VAR_SUBFIX4:	( 'A'..'Z' | 'a'..'z' | '_' | '$' | '0'..'9' | DOT )+ ;

/******************************************************************************************/
expr				: term ;

term				: LPAREN term RPAREN
					| atom
					| function
               // ordering is important for correct parsing!
               // pow needs to be right associative for correct parenthesizing
               |<assoc=right> term operator=POWER term
               | term operator=DIVIDE term
               | term operator=ASTERISK term
					| term operator=( PLUS | MINUS ) term
					;

atom           : number
               | neg_number
               | column_name
               //| neg_variable
               | USER_VAR
               | quoted_variable
               ;
quoted_variable:
	QUTE ID QUTE
	;
number			: INT ( DOT INT )? ;

neg_number		: LPAREN MINUS number RPAREN ;

//neg_variable	: LPAREN MINUS column_name RPAREN ;

function       : function_name LPAREN term ( COMMA term )* RPAREN ;

function_name	: ID;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/


/***********************************************************************************************/
stat:
		select_clause|delect_clause|insert_clause|update_clause
	;

schema_name:
    ID
    ;

select_clause:
		SELECT
		column_list_clause
		(FROM table_references)?
		(where_clause)?
		(GROUP BY column_list_clause (ASC | DESC)? (HAVING expression)?)?
		(ORDER BY column_list_clause (ASC | DESC)?)?
		(LIMIT (offset)? row_count | row_count OFFSET offset)?
		;
delect_clause:
		DELETE
		FROM table_references
		(WHERE expression)?
		(ORDER BY column_list_clause (ASC | DESC)?)?
		(LIMIT (offset)? row_count | row_count OFFSET offset)?
		;
insert_clause:
		INSERT (INTO)? table_references
		(( LPAREN column_list_clause RPAREN )?
		VALUES
		LPAREN values_list_clause RPAREN)
		|
		(SET (column_name EQ value)+)
		;
update_clause:
		UPDATE
		table_references
		SET column_name EQ (expr|DEFAULT) (COMMA column_name EQ (expr|DEFAULT))*
		(WHERE expression)?
		(ORDER BY column_list_clause (ASC | DESC)?)?
        (LIMIT (offset)? row_count | row_count OFFSET offset)?
		;
table_name:
	ID (table_alias)?
	;

table_alias:
     (AS)? ID
    ;
value:
	expr
;
values_list_clause:
	value(COMMA value)*
;
column:
	expr (column_alias)?
;

column_name:
	    ((schema_name DOT)? ID DOT)? ID
	|
	    (table_alias DOT)? ID
	|
       	USER_VAR
    |
    	all_coumn_name
    ;

all_coumn_name:
	(table_alias DOT)?ASTERISK
	;

column_alias:
	(AS)? ID
	;

index_name:
    ID
    ;

column_list:
	LPAREN column_name (COMMA column_name)* RPAREN
;

column_list_clause:
		column (COMMA column)*
	;

from_clause:
		FROM table_name (COMMA table_name)*
	;
where_clause:
		WHERE expression_clause
	;
/*expression:
		LPAREN expression RPAREN
	|
		expression_list (OR expression_list)*
	;

expression_list:
			LPAREN expression_list RPAREN
		|
			simple_expression (AND simple_expression)*
	;*/
expression_clause:
		expression (expr_op expression)*
	;
expression:
		quoted_expression
	|
		simple_expression (expr_op (expression|simple_expression))*
	;

quoted_expression:
		LPAREN expression RPAREN
	;

/*element:
		USER_VAR | ID | ('|' ID '|') | INT
	|
		column_name
	|
		expr
	;*/

right_element:
		expr
	;

left_element:
		expr
	;

target_element:
		expr
	;

relational_op:
		EQ | LTH | GTH | NOT_EQ | LET | GET | LIKE  ;

expr_op:
		AND | XOR | OR | NOT ;

between_op:
		BETWEEN
	;

is_or_is_not:
		IS | IS NOT
	;

simple_expression:
		left_element relational_op right_element
	|
		target_element between_op left_element AND right_element
	|
		target_element is_or_is_not NULL
	;

table_references:
        table_reference ( (COMMA table_reference) | join_clause )*
;

table_reference:
	table_atom
;

table_factor1:
	table_factor2 (  (INNER | CROSS)? JOIN table_atom (join_condition)? )?
;

table_factor2:
	table_factor3 (  STRAIGHT_JOIN table_atom (ON expression)?  )?
;

table_factor3:
	table_factor4 (  (LEFT|RIGHT) (OUTER)? JOIN table_factor4 join_condition  )?
;

table_factor4:
	table_atom (  NATURAL ( (LEFT|RIGHT) (OUTER)? )? JOIN table_atom )?
;

table_atom:
	  ( table_name (partition_clause)? (table_alias)? (index_hint_list)? )
	| ( subquery subquery_alias )
	| ( LPAREN table_references RPAREN )
	| ( OJ table_reference LEFT OUTER JOIN table_reference ON expression )
;

join_clause:
        (  (INNER | CROSS)? JOIN table_atom (join_condition)? )
    |
        (  STRAIGHT_JOIN table_atom (ON expression)?  )
    |
        (  (LEFT|RIGHT) (OUTER)? JOIN table_factor4 join_condition  )
    |
        (  NATURAL ( (LEFT|RIGHT) (OUTER)? )? JOIN table_atom )
    ;

join_condition:
	  (ON expression (expr_op expression)*) | (USING column_list)
;

index_hint_list:
	index_hint (COMMA index_hint)*
;

index_options:
	(INDEX | KEY) (FOR ((JOIN) | (ORDER BY) | (GROUP BY)))?
;

index_hint:
	  USE    index_options LPAREN (index_list)? RPAREN
	| IGNORE index_options LPAREN index_list RPAREN
;

index_list:
	index_name (COMMA index_name)*
;

partition_clause:
	PARTITION LPAREN partition_names RPAREN
;

partition_names:
	partition_name (COMMA partition_name)* ;

partition_name:
    ID
    ;

subquery_alias:
    (AS)? ID
    ;

subquery:
	LPAREN select_clause RPAREN
;

offset:
	INT
	;

row_count:
	INT
	;