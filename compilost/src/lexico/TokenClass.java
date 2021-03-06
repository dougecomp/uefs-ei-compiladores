package lexico;

/**
 * @author heekinho
 */
public enum TokenClass {

    IDENTIFIER,
    // (...)
    NUMERIC,
    NUMERIC_REAL,
    // (...)
    KEYWORD,
    KEYWORD_ALGORITMO,
    KEYWORD_CAST,
    KEYWORD_VARIAVEIS,
    KEYWORD_CONSTANTES,
    KEYWORD_REGISTRO,
    KEYWORD_TIPO,
    KEYWORD_FUNCAO,
    KEYWORD_RETORNO,
    KEYWORD_VAZIO,
    KEYWORD_PRINCIPAL,
    KEYWORD_SE,
    KEYWORD_ENTAO,
    KEYWORD_SENAO,
    KEYWORD_ENQUANTO,
    KEYWORD_PARA,
    KEYWORD_LEIA,
    KEYWORD_ESCREVA,
    KEYWORD_INTEIRO,
    KEYWORD_REAL,
    KEYWORD_STRING,
    KEYWORD_LOGICO,
    KEYWORD_CARACTERE,
    KEYWORD_VERDADEIRO,
    KEYWORD_FALSO,
    KEYWORD_NULO,
    STRING,
    CARACTERE,
    OPERATOR,
    OPERATOR_PLUS,
    OPERATOR_MINUS,
    OPERATOR_INCREMENT,
    OPERATOR_DECREMENT,
    OPERATOR_MORE,
    OPERATOR_MORE_EQUAL,
    OPERATOR_LESS,
    OPERATOR_LESS_EQUAL,
    OPERATOR_EQUAL,
    OPERATOR_ASSIGNMENT,
    OPERATOR_DIFFERENT,
    OPERATOR_AND,
    OPERATOR_OR,
    OPERATOR_NOT,
    OPERATOR_DIVISION,
    OPERATOR_POINT,
    OPERATOR_MULT,
    // (...)
    DELIMITER,
    DELIMITER_OPENING_PARENTHESIS, // (
    DELIMITER_CLOSING_PARENTHESIS, // )
    DELIMITER_OPENING_SQUARE_BRACKET, // [
    DELIMITER_CLOSING_SQUARE_BRACKET, // ]
    DELIMITER_OPENING_CURLY_BRACKET, // {
    DELIMITER_CLOSING_CURLY_BRACKET, // }
    DELIMITER_SEMICOLON, //  ;
    DELIMITER_COMMA, //  ,

    // ETC.
    UNDEFINED
}
