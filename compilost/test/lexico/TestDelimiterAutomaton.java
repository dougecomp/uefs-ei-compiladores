/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import automatos.DelimiterAutomaton;
import automatos.OperatorAutomaton;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import exceptions.LexerException;

/**
 *
 * @author Salomao, Pedro refeito 24/09/2010
 */
public class TestDelimiterAutomaton {

    CodeBuffer buf;
    DelimiterAutomaton automatoDelimitador;
    OperatorAutomaton automatoOperador;

    public TestDelimiterAutomaton() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        automatoDelimitador = new DelimiterAutomaton(new CodeBuffer());
        // automatoOperador = new OperatorAutomaton( new CharBuf());
        buf = new CodeBuffer();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void identificandoDelimitadorPontoVirgula() throws LexerException {
        automatoDelimitador.getBuffer().setCode(";");
        assertEquals(automatoDelimitador.process().getTokenClass(), TokenClass.DELIMITER_SEMICOLON);
    }

    @Test
    public void identificandoAbreParenteses() throws LexerException {
        automatoDelimitador.getBuffer().setCode("(");
        assertEquals(automatoDelimitador.process().getTokenClass(), TokenClass.DELIMITER_OPENING_PARENTHESIS);
    }

    @Test
    public void identificandoFechaParenteses() throws LexerException {
        automatoDelimitador.getBuffer().setCode(")");
        assertEquals(automatoDelimitador.process().getTokenClass(), TokenClass.DELIMITER_CLOSING_PARENTHESIS);
    }

    @Test
    public void identificandoAbreCochete() throws LexerException {
        automatoDelimitador.getBuffer().setCode("[");
        assertEquals(automatoDelimitador.process().getTokenClass(), TokenClass.DELIMITER_OPENING_SQUARE_BRACKET);
    }

    @Test
    public void identificandoFechaCochete() throws LexerException {
        automatoDelimitador.getBuffer().setCode("]");
        assertEquals(automatoDelimitador.process().getTokenClass(), TokenClass.DELIMITER_CLOSING_SQUARE_BRACKET);
    }

    @Test
    public void identificandoAbreChaves() throws LexerException {
        automatoDelimitador.getBuffer().setCode("{");
        assertEquals(automatoDelimitador.process().getTokenClass(), TokenClass.DELIMITER_OPENING_CURLY_BRACKET);
    }

    @Test
    public void identificandoFechaChaves() throws LexerException {
        automatoDelimitador.getBuffer().setCode("}");
        assertEquals(automatoDelimitador.process().getTokenClass(), TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }
}