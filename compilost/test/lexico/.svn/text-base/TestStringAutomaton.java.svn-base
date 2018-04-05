/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import automatos.StringAutomaton;
import exceptions.LexerException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maria && Eder
 * Classe de Testes para testar o funcionamento do Autômato de Strings
 */
public class TestStringAutomaton {

    StringAutomaton stringAutomaton;
    CodeBuffer buf;

    public TestStringAutomaton() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        buf = new CodeBuffer();
        stringAutomaton = new StringAutomaton(buf);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void stringSimples() throws Exception {
        stringAutomaton.getBuffer().setCode("\"esta é uma string correta\"");
        assertEquals(stringAutomaton.process().getLexeme(), "esta é uma string correta");
    }

    @Test
    public void stringNaoTerminada() throws Exception {
        stringAutomaton.getBuffer().setCode("\"string nao terminada");
        try {
            stringAutomaton.process().getLexeme();
        } catch (LexerException e) {
            assertEquals(e.getErrorMessage(), "String Não Terminada: \"strin...\", linha: 1, coluna: 0");
        }
    }

    @Test
    public void stringGrandeDemaisTerminada() throws Exception {
        stringAutomaton.getBuffer().setCode("\"string grannnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "ddddddddddddddddddddddddddddddddddddddddd"
                + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
                + "deeeeeeeeeeeeeeeeeemmmmmmmmmmmmmmmmmaaaaa"
                + "aaaaaaaaaaaaaaaaaiiiiiiiiisssssssssssssss\"");
        try {
            stringAutomaton.process().getLexeme();
        } catch (LexerException e) {
            assertEquals(e.getErrorMessage(), "String Muito Grande: \"strin...\", linha: 1, coluna: 0");
        }
    }

    @Test
    public void stringGrandeDemaisSemTerminar() throws Exception {
        stringAutomaton.getBuffer().setCode("\"string grannnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"
                + "ddddddddddddddddddddddddddddddddddddddddd"
                + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
                + "deeeeeeeeeeeeeeeeeemmmmmmmmmmmmmmmmmaaaaa"
                + "aaaaaaaaaaaaaaaaaiiiiiiiiisssssssssssssss");
        try {
            stringAutomaton.process().getLexeme();
        } catch (LexerException e) {
            assertEquals(e.getErrorMessage(), "String Não Terminada: \"strin...\", linha: 1, coluna: 0");
        }
    }
}