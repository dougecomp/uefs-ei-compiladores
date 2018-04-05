/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import exceptions.LexerException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aluno
 */
public class TestCodeBufferInlineComment {

    CodeBuffer buf;

    public TestCodeBufferInlineComment() {
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
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void singleBar() {
        buf.setCode("/");
        assertFalse(buf.eof());
    }

    @Test
    public void doubleBars() {
        buf.setCode("//");
        assertTrue(buf.eof());
    }

    @Test
    public void doubleBarsAndBreaks() {
        buf.setCode("\n"
                + "\n"
                + "\n"
                + "//"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n");
        assertTrue(buf.eof());
    }

    @Test
    public void inlineComment() {
        buf.setCode("//este eh um comentario");
        assertTrue(buf.eof());
    }

    @Test
    public void inlineComments() throws LexerException {
        buf.setCode("//estes sao varios comentarios\n"
                + "\n"
                + "\n"
                + "//todos eles devem ser pulados\n"
                + "//\n"
                + "//\n"
                + "//serah que serao pulados adequadamente?\n\n\n\n\n   \n\n");
        assertEquals(buf.next(), ' ');
        assertEquals(buf.next(), ' ');
        assertEquals(buf.next(), ' ');

        assertTrue(buf.eof());
    }

    @Test
    public void inlineCommentsOnCode() throws LexerException {
        buf.setCode("int a=0;"
                + "a=a+2;"
                + "\n"
                + "\n"
                + "//todos eles devem ser pulados\n"
                + "//\n"
                + "//\n"
                + "//serah que serao pulados adequadamente?\n\n\n\n\n   \n\n");

        assertFalse(buf.eof());
        assertEquals(buf.next(), 'i');
        assertEquals(buf.next(), 'n');
        assertEquals(buf.next(), 't');
        assertTrue(buf.isDelimiter(buf.next()));
        assertEquals(buf.next(), 'a');
        assertEquals(buf.next(), '=');
        assertEquals(buf.next(), '0');
        assertEquals(buf.next(), ';');
        assertEquals(buf.next(), 'a');
        assertEquals(buf.next(), '=');
        assertEquals(buf.next(), 'a');
        assertEquals(buf.next(), '+');
        assertEquals(buf.next(), '2');
        assertEquals(buf.next(), ';');
        assertEquals(buf.next(), ' ');
        assertEquals(buf.next(), ' ');
        assertEquals(buf.next(), ' ');
        assertTrue(buf.eof());
    }
}