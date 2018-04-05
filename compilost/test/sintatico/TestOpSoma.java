/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sintatico;

import lexico.TokenClass;
import exceptions.ParserException;
//import productions.OpSumProduction;
import lexico.ErrorList;
import lexico.TokenList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import productions.OpSomaProduction;
import static org.junit.Assert.*;

/**
 *
 * @author pedro
 */
public class TestOpSoma extends TestSintatic {

    public TestOpSoma() {
    }

    @Test
    public void testOpSoma() throws ParserException {
        makeAnalyzeTest("+", true, OpSomaProduction.getInstance());
        makeAnalyzeTest("-", true, OpSomaProduction.getInstance());
        makeAnalyzeTest("algoritmo teste;", false, OpSomaProduction.getInstance());

    }

    @Test
    public void testeFirst() {
        assertTrue(OpSomaProduction.getInstance().isFirst(TokenClass.OPERATOR_PLUS));
        assertTrue(OpSomaProduction.getInstance().isFirst(TokenClass.OPERATOR_MINUS));
    }

    @Test
    public void testeFollow() {
        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.OPERATOR_NOT));
//        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.DELIMITER_CLOSING_PARENTHESIS));
        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.NUMERIC));
        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.NUMERIC_REAL));
        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.KEYWORD_VERDADEIRO));
        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.KEYWORD_FALSO));
        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.IDENTIFIER));
        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.KEYWORD_CAST));
        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.OPERATOR_MINUS));
        assertTrue(OpSomaProduction.getInstance().isFollow(TokenClass.OPERATOR_PLUS));


        //falsos
        assertFalse(OpSomaProduction.getInstance().isFollow(TokenClass.OPERATOR_ASSIGNMENT));
        assertFalse(OpSomaProduction.getInstance().isFollow(TokenClass.OPERATOR_OR));
        assertFalse(OpSomaProduction.getInstance().isFollow(TokenClass.DELIMITER));
        assertFalse(OpSomaProduction.getInstance().isFollow(TokenClass.KEYWORD_ENQUANTO));
    }
}
