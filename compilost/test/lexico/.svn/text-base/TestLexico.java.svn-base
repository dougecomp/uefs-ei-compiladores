/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import lexico.Token;
import lexico.CodeBuffer;
import lexico.TokenList;
import lexico.Lexico;
import lexico.TokenClass;
import exceptions.LexerException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cacovsky
 */
public class TestLexico {

    Lexico analisador;

    public TestLexico() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        analisador = new Lexico(new CodeBuffer());
    }

    @After
    public void tearDown() {
    }

    public void setCodeAndScan(String code) {
        analisador.getBuffer().setCode(code);
        analisador.scan();
    }

    @Test
    public void testIdentificador() {
        setCodeAndScan("ident");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);

        assertEquals(tabela.get(0), new Token(TokenClass.IDENTIFIER, "ident", 1, 0));

    }

    @Test
    public void testeDeclaracaoEAtribuicao() {
        setCodeAndScan("int a=2;");
        TokenList tabela = analisador.getTokenList();

        assertEquals(tabela.size(), 5);
        assertEquals(tabela.get(0), new Token(TokenClass.IDENTIFIER, "int", 1, 0));
        assertEquals(tabela.get(1), new Token(TokenClass.IDENTIFIER, "a", 1, 4));
        assertEquals(tabela.get(2), new Token(TokenClass.OPERATOR_ASSIGNMENT, "=", 1, 5));
        assertEquals(tabela.get(3), new Token(TokenClass.NUMERIC, "2", 1, 6));
        assertEquals(tabela.get(4), new Token(TokenClass.DELIMITER_SEMICOLON, ";", 1, 7));
    }

    @Test
    public void testeComentarioEmKeyword() {
        setCodeAndScan("inte/*comentario*/iro");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0), new Token(TokenClass.KEYWORD_INTEIRO, "inteiro", 1, 0));
    }

    /* Teste de vÃ¡rios caracteres invÃ¡lidos */
    @Test
    public void caracteresInvalidosAcentuacao() throws LexerException {
        setCodeAndScan("Ã¡ Ã© Ã­ Ã³ Ãº Ã Ã Ã Ã Ã Ã£ Ãµ Ã Ã Ã  Ã¨ Ã¬ Ã² Ã¹ Ã Ã Ã Ã Ã"
                + "Ã¢ Ãª Ã® Ã´ Ã» Ã Ã Ã Ã Ã ` ' ^ ~");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 0);
    }

    @Test
    public void caracteresInvalidosGerais() throws LexerException {
        setCodeAndScan("? Âª Âº @ # $ % Â§ & | \\ : _ Â¹ Â²");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 0);
    }


    /* Testes com Strings depois de outros tipos de tokens */
    @Test
    public void stringDepoisDeString() throws LexerException {
        setCodeAndScan("\"primeira string\"\"segunda string\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "primeira string");
        assertEquals(tabela.get(1).getLexeme(), "segunda string");
    }

    @Test
    public void stringDepoisDeDelimitador() throws LexerException {
        setCodeAndScan(";\"string depois de delimitador\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(1).getLexeme(), "string depois de delimitador");
    }

    @Test
    public void stringDepoisDeOperador() throws LexerException {
        setCodeAndScan("+\"string depois de operador\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(1).getLexeme(), "string depois de operador");
    }

    @Test
    public void stringDepoisDeNumero() throws LexerException {
        setCodeAndScan("5.6\"string depois de nÃºmero\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(1).getLexeme(), "string depois de nÃºmero");
    }

    @Test
    public void stringDepoisDeIdentificador() throws LexerException {
        setCodeAndScan("id\"string depois de identificador\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(1).getLexeme(), "string depois de identificador");
    }

    @Test
    public void stringDepoisDePalavraChave() throws LexerException {
        setCodeAndScan("se\"string depois de palavra-chave\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(1).getLexeme(), "string depois de palavra-chave");
    }

    @Test
    public void stringDepoisDeCaractereInvalido() throws LexerException {
        setCodeAndScan("_\"string depois de caractere invalido\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "string depois de caractere invalido");
    }

    /* Testes com Strings antes de outros tipos de tokens */
    @Test
    public void stringAntesDeDelimitador() throws LexerException {
        setCodeAndScan("\"string antes de delimitador\")");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "string antes de delimitador");
    }

    @Test
    public void stringAntesDeOperador() throws LexerException {
        setCodeAndScan("\"string antes de operador\"-");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "string antes de operador");
    }

    @Test
    public void stringAntesDeNumero() throws LexerException {
        setCodeAndScan("\"string antes de numero\"-0");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "string antes de numero");
    }

    @Test
    public void stringAntesDeIdentificador() throws LexerException {
        setCodeAndScan("\"string antes de identificador\" id4");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "string antes de identificador");
    }

    @Test
    public void stringAntesDePalavraChave() throws LexerException {
        setCodeAndScan("\"string antes de palavra-chave\"vazio");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "string antes de palavra-chave");
    }

    @Test
    public void stringAntesDeCaractereInvalido() throws LexerException {
        setCodeAndScan("\"string antes de caractere invalido\"!");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "string antes de caractere invalido");
    }

    /* Testes com delimitador depois de outros tipos de token */
    @Test
    public void delimitadorDepoisDeDelimitador() throws LexerException {
        setCodeAndScan("][");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "]");
        assertEquals(tabela.get(1).getLexeme(), "[");
    }

    @Test
    public void delimitadorDepoisDeOperador() throws LexerException {
        setCodeAndScan("/;");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "/");
        assertEquals(tabela.get(1).getLexeme(), ";");
    }

    @Test
    public void delimitadorDepoisDeNumero() throws LexerException {
        setCodeAndScan("1006)");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "1006");
        assertEquals(tabela.get(1).getLexeme(), ")");
    }

    @Test
    public void delimitadorDepoisDeIdentificador() throws LexerException {
        setCodeAndScan("var{");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "var");
        assertEquals(tabela.get(1).getLexeme(), "{");
    }

    @Test
    public void delimitadorDepoisDePalavraChave() throws LexerException {
        setCodeAndScan("logico}");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "logico");
        assertEquals(tabela.get(1).getLexeme(), "}");
    }

    @Test
    public void delimitadorDepoisDeCaractereInvalido() throws LexerException {
        setCodeAndScan("#(");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "(");
    }

    /* Testes com delimitador antes de outros tipos de token */
    @Test
    public void delimitadorAntesDeOperador() throws LexerException {
        setCodeAndScan("/;");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "/");
        assertEquals(tabela.get(1).getLexeme(), ";");
    }

    @Test
    public void delimitadorAntesDeNumero() throws LexerException {
        setCodeAndScan("1006)");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "1006");
        assertEquals(tabela.get(1).getLexeme(), ")");
    }

    @Test
    public void delimitadorAntesDeIdentificador() throws LexerException {
        setCodeAndScan("var{");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "var");
        assertEquals(tabela.get(1).getLexeme(), "{");
    }

    @Test
    public void delimitadorAntesDePalavraChave() throws LexerException {
        setCodeAndScan("logico}");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "logico");
        assertEquals(tabela.get(1).getLexeme(), "}");
    }

    @Test
    public void delimitadorAntesDeCaractereInvalido() throws LexerException {
        setCodeAndScan("#(");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "(");
    }

    /* Testes com operador depois de outros tipos de token */
    @Test
    public void operadorDepoisDeOperador() throws LexerException {
        setCodeAndScan("++--");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "++");
        assertEquals(tabela.get(1).getLexeme(), "--");
    }

    @Test
    public void operadorDepoisDeNumero() throws LexerException {
        setCodeAndScan("-56*");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "-");
        assertEquals(tabela.get(1).getLexeme(), "56");
        assertEquals(tabela.get(2).getLexeme(), "*");
    }

    @Test
    public void operadorDepoisDeIdentificador() throws LexerException {
        setCodeAndScan("x-");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "x");
        assertEquals(tabela.get(1).getLexeme(), "-");
    }

    @Test
    public void operadorDepoisDePalavraChave() throws LexerException {
        setCodeAndScan("principal+");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "principal");
        assertEquals(tabela.get(1).getLexeme(), "+");
    }

    @Test
    public void operadorDepoisDeCaractereInvalido() throws LexerException {
        setCodeAndScan("Â§/");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "/");
    }

    /* Testes com operador antes de outros tipos de token */
    @Test
    public void operadorAntesDeNumero() throws LexerException {
        setCodeAndScan("*-8");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "*");
        assertEquals(tabela.get(1).getLexeme(), "-");
        assertEquals(tabela.get(2).getLexeme(), "8");
    }

    @Test
    public void operadorAntesDeIdentificador() throws LexerException {
        setCodeAndScan("-oito");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "-");
        assertEquals(tabela.get(1).getLexeme(), "oito");
    }

    @Test
    public void operadorAntesDePalavraChave() throws LexerException {
        setCodeAndScan("+caractere");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "+");
        assertEquals(tabela.get(1).getLexeme(), "caractere");
    }

    @Test
    public void operadorAntesDeCaractereInvalido() throws LexerException {
        setCodeAndScan("/Â§");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "/");
    }

    /* Testes com nÃºmero depois de outros tipos de token */
    @Test
    public void numeroDepoisDeNumero() throws LexerException {
        setCodeAndScan("4056.2-56");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "4056.2");
        assertEquals(tabela.get(1).getLexeme(), "-");
        assertEquals(tabela.get(2).getLexeme(), "56");
    }

    @Test
    public void numeroDepoisDeIdentificador() throws LexerException {
        setCodeAndScan("a 98");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "a");
        assertEquals(tabela.get(1).getLexeme(), "98");
    }

    @Test
    public void numeroDepoisDePalavraChave() throws LexerException {
        setCodeAndScan("falso 45");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "falso");
        assertEquals(tabela.get(1).getLexeme(), "45");
    }

    @Test
    public void numeroDepoisDeCaractereInvalido() throws LexerException {
        setCodeAndScan("@37");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "37");
    }

    /* Testes com nÃºmero antes de outros tipos de token */
    @Test
    public void numeroAntesDeIdentificador() throws LexerException {
        setCodeAndScan("65.2 y");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "65.2");
        assertEquals(tabela.get(1).getLexeme(), "y");
    }

    @Test
    public void numeroAntesDePalavraChave() throws LexerException {
        setCodeAndScan("1 registro");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "1");
        assertEquals(tabela.get(1).getLexeme(), "registro");
    }

    @Test
    public void numeroAntesDeCaractereInvalido() throws LexerException {
        setCodeAndScan("21 `");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "21");
    }

    /* Testes com identificador depois de outros tipos de token */
    @Test
    public void identificadorDepoisDeIdentificador() throws LexerException {
        setCodeAndScan("a4 b2");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "a4");
        assertEquals(tabela.get(1).getLexeme(), "b2");
    }

    @Test
    public void identificadorDepoisDePalavraChave() throws LexerException {
        setCodeAndScan("entao y6");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "entao");
        assertEquals(tabela.get(1).getLexeme(), "y6");
    }

    @Test
    public void identificadorDepoisDeCaractereInvalido() throws LexerException {
        setCodeAndScan("@id4");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "id4");
    }

    /* Testes com identificador antes de outros tipos de token */
    @Test
    public void identificadorAntesDePalavraChave() throws LexerException {
        setCodeAndScan("w senao");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "w");
        assertEquals(tabela.get(1).getLexeme(), "senao");
    }

    @Test
    public void identificadorAntesDeCaractereInvalido() throws LexerException {
        setCodeAndScan("ohm Âª");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "ohm");
    }

    /* Testes com palavra-chave depois de outros tipos de token */
    @Test
    public void palavraChaveDepoisDePalavraChave() throws LexerException {
        setCodeAndScan("constantes retorno");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "constantes");
        assertEquals(tabela.get(1).getLexeme(), "retorno");
    }

    @Test
    public void palavraChaveDepoisDeCaractereInvalido() throws LexerException {
        setCodeAndScan("!para");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "!");
        assertEquals(tabela.get(1).getLexeme(), "para");
    }

    /* Testes com palavra-chave antes de outros tipos de token */
    @Test
    public void palavraChaveAntesDeCaractereInvalido() throws LexerException {
        setCodeAndScan("leia Â£");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "leia");
    }

    /* Testes com string entre outros tipos de token */
    @Test
    public void stringEntreStrings() throws LexerException {
        setCodeAndScan("\"primeira string\"\"string do meio\"\"segunda string\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "primeira string");
        assertEquals(tabela.get(1).getLexeme(), "string do meio");
        assertEquals(tabela.get(2).getLexeme(), "segunda string");
    }

    @Test
    public void stringEntreDelimitadores() throws LexerException {
        setCodeAndScan(";\"string entre delimitadores\"[");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), ";");
        assertEquals(tabela.get(1).getLexeme(), "string entre delimitadores");
        assertEquals(tabela.get(2).getLexeme(), "[");
    }

    @Test
    public void stringEntreOperadores() throws LexerException {
        setCodeAndScan("+\"string entre operadores\"-");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "+");
        assertEquals(tabela.get(1).getLexeme(), "string entre operadores");
        assertEquals(tabela.get(2).getLexeme(), "-");
    }

    @Test
    public void stringEntreNumeros() throws LexerException {
        setCodeAndScan("-6\"string entre numeros\"22356.986875");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 4);
        assertEquals(tabela.get(0).getLexeme(), "-");
        assertEquals(tabela.get(1).getLexeme(), "6");
        assertEquals(tabela.get(2).getLexeme(), "string entre numeros");
        assertEquals(tabela.get(3).getLexeme(), "22356.986875");
    }

    @Test
    public void stringEntreIdentificadores() throws LexerException {
        setCodeAndScan("id\"string entre identificadores\"id2");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "id");
        assertEquals(tabela.get(1).getLexeme(), "string entre identificadores");
        assertEquals(tabela.get(2).getLexeme(), "id2");
    }

    @Test
    public void stringEntrePalavrasChave() throws LexerException {
        setCodeAndScan("se\"string entre palavras-chave\"senao");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "se");
        assertEquals(tabela.get(1).getLexeme(), "string entre palavras-chave");
        assertEquals(tabela.get(2).getLexeme(), "senao");
    }

    @Test
    public void stringEntreCaracteresInvalidos() throws LexerException {
        setCodeAndScan("$\"string entre caracteres invÃ¡lidos\"#");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 1);
        assertEquals(tabela.get(0).getLexeme(), "string entre caracteres invÃ¡lidos");
    }

    /* Testes com delimitador entre outros tipos de token */
    @Test
    public void delimitadorEntreStrings() throws LexerException {
        setCodeAndScan("\"primeira string\"{\"segunda string\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "primeira string");
        assertEquals(tabela.get(1).getLexeme(), "{");
        assertEquals(tabela.get(2).getLexeme(), "segunda string");
    }

    @Test
    public void delimitadorEntreDelimitadores() throws LexerException {
        setCodeAndScan(";}[");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), ";");
        assertEquals(tabela.get(1).getLexeme(), "}");
        assertEquals(tabela.get(2).getLexeme(), "[");
    }

    @Test
    public void delimitadorEntreOperadores() throws LexerException {
        setCodeAndScan("++)--");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "++");
        assertEquals(tabela.get(1).getLexeme(), ")");
        assertEquals(tabela.get(2).getLexeme(), "--");
    }

    @Test
    public void delimitadorEntreNumeros() throws LexerException {
        setCodeAndScan("-6 (-0.7");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 5);
        assertEquals(tabela.get(0).getLexeme(), "-");
        assertEquals(tabela.get(1).getLexeme(), "6");
        assertEquals(tabela.get(2).getLexeme(), "(");
        assertEquals(tabela.get(3).getLexeme(), "-");
        assertEquals(tabela.get(4).getLexeme(), "0.7");
    }

    @Test
    public void delimitadorEntreIdentificadores() throws LexerException {
        setCodeAndScan("id]id2");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "id");
        assertEquals(tabela.get(1).getLexeme(), "]");
        assertEquals(tabela.get(2).getLexeme(), "id2");
    }

    @Test
    public void delimitadorEntrePalavrasChave() throws LexerException {
        setCodeAndScan("real;verdadeiro");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "real");
        assertEquals(tabela.get(1).getLexeme(), ";");
        assertEquals(tabela.get(2).getLexeme(), "verdadeiro");
    }

    @Test
    public void delimitadorEntreCaracteresInvalidos() throws LexerException {
        setCodeAndScan("${!");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "{");
        assertEquals(tabela.get(1).getLexeme(), "!");
    }

    /* Testes com operador entre outros tipos de token */
    @Test
    public void operadorEntreStrings() throws LexerException {
        setCodeAndScan("\"primeira string\".\"segunda string\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "primeira string");
        assertEquals(tabela.get(1).getLexeme(), ".");
        assertEquals(tabela.get(2).getLexeme(), "segunda string");
    }

    @Test
    public void operadorEntreDelimitadores() throws LexerException {
        setCodeAndScan(";*[");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), ";");
        assertEquals(tabela.get(1).getLexeme(), "*");
        assertEquals(tabela.get(2).getLexeme(), "[");
    }

    @Test
    public void operadorEntreOperadores() throws LexerException {
        setCodeAndScan("+++--");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "++");
        assertEquals(tabela.get(1).getLexeme(), "+");
        assertEquals(tabela.get(2).getLexeme(), "--");
    }

    @Test
    public void operadorEntreNumeros() throws LexerException {
        setCodeAndScan("-6--0.7");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 4);
        assertEquals(tabela.get(0).getLexeme(), "-");
        assertEquals(tabela.get(1).getLexeme(), "6");
        assertEquals(tabela.get(2).getLexeme(), "--");
        assertEquals(tabela.get(3).getLexeme(), "0.7");
    }

    @Test
    public void operadorEntreIdentificadores() throws LexerException {
        setCodeAndScan("id<id2");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "id");
        assertEquals(tabela.get(1).getLexeme(), "<");
        assertEquals(tabela.get(2).getLexeme(), "id2");
    }

    @Test
    public void operadorEntrePalavrasChave() throws LexerException {
        setCodeAndScan("real>verdadeiro");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "real");
        assertEquals(tabela.get(1).getLexeme(), ">");
        assertEquals(tabela.get(2).getLexeme(), "verdadeiro");
    }

    @Test
    public void operadorEntreCaracteresInvalidos() throws LexerException {
        setCodeAndScan("$==!");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "==");
        assertEquals(tabela.get(1).getLexeme(), "!");
    }

    /* Testes com nÃºmero entre outros tipos de token */
    @Test
    public void numeroEntreStrings() throws LexerException {
        setCodeAndScan("\"primeira string\"-96.4\"segunda string\"");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 4);
        assertEquals(tabela.get(0).getLexeme(), "primeira string");
        assertEquals(tabela.get(1).getLexeme(), "-");
        assertEquals(tabela.get(2).getLexeme(), "96.4");
        assertEquals(tabela.get(3).getLexeme(), "segunda string");
    }

    @Test
    public void numeroEntreDelimitadores() throws LexerException {
        setCodeAndScan(";*[");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), ";");
        assertEquals(tabela.get(1).getLexeme(), "*");
        assertEquals(tabela.get(2).getLexeme(), "[");
    }

    @Test
    public void numeroEntreOperadores() throws LexerException {
        setCodeAndScan("+++--");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "++");
        assertEquals(tabela.get(1).getLexeme(), "+");
        assertEquals(tabela.get(2).getLexeme(), "--");
    }

    @Test
    public void numeroEntreNumeros() throws LexerException {
        setCodeAndScan("-6--0.7");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 4);
        assertEquals(tabela.get(0).getLexeme(), "-");
        assertEquals(tabela.get(1).getLexeme(), "6");
        assertEquals(tabela.get(2).getLexeme(), "--");
        assertEquals(tabela.get(3).getLexeme(), "0.7");
    }

    @Test
    public void numeroEntreIdentificadores() throws LexerException {
        setCodeAndScan("id<id2");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "id");
        assertEquals(tabela.get(1).getLexeme(), "<");
        assertEquals(tabela.get(2).getLexeme(), "id2");
    }

    @Test
    public void numeroEntrePalavrasChave() throws LexerException {
        setCodeAndScan("real>verdadeiro");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 3);
        assertEquals(tabela.get(0).getLexeme(), "real");
        assertEquals(tabela.get(1).getLexeme(), ">");
        assertEquals(tabela.get(2).getLexeme(), "verdadeiro");
    }

    @Test
    public void numeroEntreCaracteresInvalidos() throws LexerException {
        setCodeAndScan("$==!");
        TokenList tabela = analisador.getTokenList();
        assertEquals(tabela.size(), 2);
        assertEquals(tabela.get(0).getLexeme(), "==");
    }

    /* Testes com identificador entre outros tipos de token */
    /* Testes com palavra-chave entre outros tipos de token */
}
