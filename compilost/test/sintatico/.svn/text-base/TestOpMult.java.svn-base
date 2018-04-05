/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sintatico;

import exceptions.ParserException;
import lexico.TokenClass;
import org.junit.Test;
import productions.OpMultProduction;
import static org.junit.Assert.*;

/**
 *
 * @author Salomão
 * @author Caco (revisado)
 *
 */
public class TestOpMult extends TestSintatic {

    public TestOpMult() {
    }

    @Test
    public void testeOpMult() throws ParserException {
        makeAnalyzeTest("*", true, OpMultProduction.getInstance());
        makeAnalyzeTest("/", true, OpMultProduction.getInstance());
        makeAnalyzeTest("algoritmo teste;", false, OpMultProduction.getInstance());
    }

    @Test
    public void testeFirst() {
        assertTrue(OpMultProduction.getInstance().isFirst(TokenClass.OPERATOR_MULT));
        assertTrue(OpMultProduction.getInstance().isFirst(TokenClass.OPERATOR_DIVISION));
    }

    @Test
    public void testeFollow() {
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.OPERATOR_NOT));
//        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.DELIMITER_CLOSING_PARENTHESIS));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.NUMERIC));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.NUMERIC_REAL));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.KEYWORD_VERDADEIRO));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.KEYWORD_FALSO));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.IDENTIFIER));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.KEYWORD_CAST));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.OPERATOR_INCREMENT));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.OPERATOR_DECREMENT));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.OPERATOR_PLUS));
        assertTrue(OpMultProduction.getInstance().isFollow(TokenClass.OPERATOR_MINUS));

        //falsos
        assertFalse(OpMultProduction.getInstance().isFollow(TokenClass.OPERATOR_ASSIGNMENT));
        assertFalse(OpMultProduction.getInstance().isFollow(TokenClass.OPERATOR_OR));
        assertFalse(OpMultProduction.getInstance().isFollow(TokenClass.DELIMITER));
        assertFalse(OpMultProduction.getInstance().isFollow(TokenClass.KEYWORD_ENQUANTO));
    }
}
