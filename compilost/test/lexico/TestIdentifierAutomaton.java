/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import automatos.IdentifierAutomaton;
import exceptions.LexerException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcone
 */
public class TestIdentifierAutomaton {

    IdentifierAutomaton automatoIdentificador;

    public TestIdentifierAutomaton() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

        automatoIdentificador = new IdentifierAutomaton(new CodeBuffer());

    }

    @After
    public void tearDown() {
    }

    @Test
    public void identificadorTest() throws LexerException {
        automatoIdentificador.getBuffer().setCode("t1Eeste_final");
        assertEquals(automatoIdentificador.process().getTokenClass(), TokenClass.IDENTIFIER);
    }

    @Test(expected = LexerException.class)
    public void identificadorComAcento() throws LexerException {
        automatoIdentificador.getBuffer().setCode("variável");
        automatoIdentificador.process();
    }

    @Test(expected = LexerException.class)
    public void identificadorInvalidoTest() throws LexerException {
        automatoIdentificador.getBuffer().setCode("@1a_e12tf");
        automatoIdentificador.process();
    }
}