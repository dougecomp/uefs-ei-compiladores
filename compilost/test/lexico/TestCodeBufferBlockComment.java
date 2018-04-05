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
 * @author amferraz
 */
public class TestCodeBufferBlockComment {

    CodeBuffer buffer;

    public TestCodeBufferBlockComment() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        buffer = new CodeBuffer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSimple() throws LexerException {
        buffer.setCode("/**/");
        assertTrue(buffer.eof());
    }

    @Test
    public void testMultipleSimpleComments() throws LexerException {
        buffer.setCode("/**//**//**//**//**/");
        assertTrue(buffer.eof());
    }

    @Test
    public void testSimple2() throws LexerException {
        buffer.setCode("/****/");
        assertTrue(buffer.eof());
    }

    @Test
    public void testSimpleWord() throws LexerException {
        buffer.setCode("/* caco */");
        assertTrue(buffer.eof());
    }

    @Test
    public void testSimpleWordMultipleAsterisks() throws LexerException {
        buffer.setCode("/** caco **/");
        assertTrue(buffer.eof());
    }

    @Test
    public void testLineBlockComment() throws LexerException {
        buffer.setCode("/**** este eh um comentario de bloco grande ****/");
        assertTrue(buffer.eof());
    }

    @Test
    public void testLineBlockComments() throws LexerException {
        buffer.setCode("/* este eh um comentario de bloco grande "
                + "varias linhas foram adicionadas a esse comentario "
                + "varias linhas foram adicionadas a esse comentario "
                + "varias linhas foram adicionadas a esse comentario "
                + "varias linhas foram adicionadas a esse comentario "
                + "varias linhas foram adicionadas a esse comentario */");
        assertTrue(buffer.eof());
    }

    @Test
    public void testTokenWithCommentInside() throws LexerException {
        buffer.setCode("abcd/* comentario qualquer*//**/efgh");
        assertEquals(buffer.next(), 'a');
        assertEquals(buffer.next(), 'b');
        assertEquals(buffer.next(), 'c');
        assertEquals(buffer.next(), 'd');
        assertEquals(buffer.next(), 'e');
        assertEquals(buffer.next(), 'f');
        assertEquals(buffer.next(), 'g');
        assertEquals(buffer.next(), 'h');
    }

    @Test
    public void testTokenWithCommentInside2() throws LexerException {
        buffer.setCode("inte/* comentario qualquer*/iro");
        assertEquals(buffer.next(), 'i');
        assertEquals(buffer.next(), 'n');
        assertEquals(buffer.next(), 't');
        assertEquals(buffer.next(), 'e');
        assertEquals(buffer.next(), 'i');
        assertEquals(buffer.next(), 'r');
        assertEquals(buffer.next(), 'o');
    }

    @Test(expected = LexerException.class)
    public void testUnfinishedComment() throws LexerException {
        buffer.setCode("/*");
        buffer.next();
    }
}