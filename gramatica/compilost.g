grammar compilost;

options {
    k=1;
}

/* ------------------------------------------------------------------------------------------------------------ */	
start		:	algoritmo declarations principal (funcao | variaveis ';')* EOF;

algoritmo	:	'algoritmo' IDENTIFIER ';';

declarations	:	(constante | tipo_definido | (variaveis ';'))*;
/* ------------------------------------------------------------------------------------------------------------ */	

constante	:	'constantes' tipo_primitivo IDENTIFIER '=' ('STRING' | NUMERIC | 'CHARACTER' | 'VERDADEIRO' | 'FALSO') ';';

tipo_definido	:	'tipo' (registro | tipo_primitivo) IDENTIFIER ';';

//registro	:	'registro' '{' (dclr_primitivo ';')+ '}' ;
registro	:	'registro' '{' (dclr_var ';')+ '}' ;

principal	:	'funcao' 'vazio' 'principal' '(' ')' bloco;

/* ------------------------------------------------------------------------------------------------------------ */	
tipo_variavel	:	tipo_primitivo | IDENTIFIER;

dclr_primitivo	:	tipo_primitivo IDENTIFIER array_d*;


/* Declaracao de variavel sem inicializacao */
dclr_var:		tipo_variavel IDENTIFIER array_d*;

/* Declaracao de variavel com possibilidade de inicializacao. Não mais aceita múltiplas atribuições */
dclr_var_init	:	dclr_var ('=' inits)?;

variaveis	:	'variaveis' dclr_var_init (',' dclr_var_init)*;

/* ------------------------------------------------------------------------------------------------------------ */			
inits		:	(valor | init_array);

valor		:	(expr | 'STRING' | 'CHARACTER');

init_array	:	'{' valor ((',' | ';') valor)* '}';
/* ------------------------------------------------------------------------------------------------------------ */	
/* No uso não pode ser nulo */
array		:	'[' expr_arit ']';
/* Na declaração pode ser nulo */
array_d		:	'[' expr_arit? ']';

tipo_primitivo	:	'inteiro' | 'real' | 'logico' | 'caractere' | 'string';

/* ------------------------------------------------------------------------------------------------------------ */

tipo_retorno_f	:	'vazio' | tipo_variavel;

funcao		:	'funcao' tipo_retorno_f ('[' ']')* IDENTIFIER '(' args* ')' bloco;

args		:	dclr_var (',' dclr_var)*;

params		:	valor (',' valor)*;

chamada_funcao	:	'(' params? ')';

/* ------------------------------------------------------------------------------------------------------------ */

bloco		:	'{' instrucao* '}';

instrucao	:	(variaveis ';' | comandos | sentenca ';') | bloco;

/* ------------------------------------------------------------------------------------------------------------ */
comandos	:	se | enquanto | para | escreva | leia | retorno;

se		:	'se' '(' expr ')' 'entao' bloco ('senao' bloco)?;

enquanto	:	'enquanto' '(' expr ')' bloco;

termo_para	:	(tipo_primitivo IDENTIFIER | IDENTIFIER IDENTIFIER?) array*  ('=' valor)?;

para		:	'para' '(' (termo_para (',' termo_para)*)? ';' expr? ';' (expr (',' expr)*)? ')' bloco;

/* ------------------------------------------------------------------------------------------------------------ */

escreva		:	'escreva' '(' valor (',' valor)* ')' ';';

leia		:	'leia' '(' IDENTIFIER (',' IDENTIFIER)* ')' ';';

retorno		:	'retorno' '(' valor ')' ';';

/* ------------------------------------------------------------------------------------------------------------ */
/* Pode ser atribuição, chamada de função, incremento */
sentenca	:	IDENTIFIER (((array)* ('=' valor | op_inc*))|chamada_funcao);

/* ------------------------------------------------------------------------------------------------------------ */

expr		:	expr_and ('||' expr_and)*;

expr_and	:	expr_rel ('&&' expr_rel)*;

expr_rel	:	expr_arit (op_relacional expr_arit)?;

expr_arit	:	termo (op_soma termo)*;

termo		:	fator (op_mult fator)*;

fator		:	('!'|op_soma)? ('(' expr ')' | NUMERIC | NUMERIC_REAL | 'VERDADEIRO' | 'FALSO' | (op_inc)* IDENTIFIER (array)* (chamada_funcao | op_inc*)| cast);    

cast		:	'cast' '(' tipo_variavel',' valor ')';

/* ------------------------------------------------------------------------------------------------------------ */

op_relacional	:	'>'  | '<' | '>=' | '<='| '==' | '!=';

op_inc		:	'++' | '--';







/* ------------------------------------------------------------------------------------------------------------ */
// REVISADAS
/* ------------------------------------------------------------------------------------------------------------ */

op_mult		:	'*'  | '/' ;
op_soma		:	'+'  | '-' ;
op_logico	:	'||' | '&&';

IDENTIFIER 
	:	('a'..'z'|'A'..'Z')+;
NUMERIC	:	('0'..'9')+;	

STRING	:	'"' IDENTIFIER '"';
