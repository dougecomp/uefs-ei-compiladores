/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import automatos.OperatorAutomaton;
import exceptions.LexerException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author douglas & Vinicius
 */
public class TestOperatorAutomaton {

    OperatorAutomaton automatoOperador;

    public TestOperatorAutomaton() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        automatoOperador = new OperatorAutomaton(new CodeBuffer());

    }

    @After
    public void tearDown() {
    }

    @Test
    public void plusTest() throws LexerException {

        automatoOperador.getBuffer().setCode("++--==!=><.*/");
        assertEquals(automatoOperador.process().getLexeme(), "++");
        assertEquals(automatoOperador.process().getLexeme(), "--");
        assertEquals(automatoOperador.process().getLexeme(), "==");
        assertEquals(automatoOperador.process().getLexeme(), "!=");
        assertEquals(automatoOperador.process().getLexeme(), ">");
        assertEquals(automatoOperador.process().getLexeme(), "<");
        assertEquals(automatoOperador.process().getLexeme(), ".");
        assertEquals(automatoOperador.process().getLexeme(), "*");
        assertEquals(automatoOperador.process().getLexeme(), "/");

    }

    @Test(expected = LexerException.class)
    public void invalidOperatorTest() throws LexerException {
        automatoOperador.getBuffer().setCode("=|");
        assertEquals(automatoOperador.process().getLexeme(), "=");
        automatoOperador.process();

    }
}