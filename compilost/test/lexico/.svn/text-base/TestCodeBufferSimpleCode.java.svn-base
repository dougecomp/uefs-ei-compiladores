package lexico;

import exceptions.LexerException;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cacovsky
 * Para maiores informacoes sobre testes com JUnit 4, consulte:
 * http://www.devmedia.com.br/post-4798-Criando-Testes-de-Unidades-com-o-Junit-4-usando-anotacoes.html
 */
public class TestCodeBufferSimpleCode {

    CodeBuffer buf;

    public TestCodeBufferSimpleCode() {
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

    // Adicione os testes aqui
    // Os métodos que são testes devem ter a anotação @Test.
    @Test
    public void hitEofFalse() {
        buf.setCode("a");
        assertFalse(buf.eof());
    }

    @Test
    public void hitEofTrue() throws LexerException {
        buf.setCode("a");
        buf.next();
        assertTrue(buf.eof());
    }

    @Test
    public void sweepSimpleCode() throws LexerException {
        buf.setCode("abcdefghij");
        assertFalse(buf.eof());
        assertEquals(buf.next(), 'a');
        assertEquals(buf.next(), 'b');
        assertEquals(buf.next(), 'c');
        assertEquals(buf.next(), 'd');
        assertEquals(buf.next(), 'e');
        assertEquals(buf.next(), 'f');
        assertEquals(buf.next(), 'g');
        assertEquals(buf.next(), 'h');
        assertEquals(buf.next(), 'i');
        assertEquals(buf.next(), 'j');
        assertTrue(buf.eof());
    }

    @Test
    public void emptyCodeEof() {
        buf.setCode("");
        assertTrue(buf.eof());
    }

    //Este teste espera por uma excecao durante a sua execucao
    @Test(expected = NoSuchElementException.class)
    public void sweepEmptyCode() throws LexerException {
        buf.setCode("");
        buf.next();
    }

    @Test
    public void simpleLookahead() throws LexerException {
        buf.setCode("ab");
        assertEquals(buf.lookAhead(), 'a');
    }

    @Test
    public void multipleLookahead() throws LexerException {
        buf.setCode("abcdef");
        assertEquals(buf.lookAhead(), 'a');
        assertEquals(buf.lookAhead(), 'a');
        assertEquals(buf.lookAhead(), 'a');
        assertEquals(buf.lookAhead(), 'a');
        assertEquals(buf.lookAhead(), 'a');
        assertEquals(buf.lookAhead(), 'a');
        assertEquals(buf.next(), 'a');
        assertEquals(buf.lookAhead(), 'b');
        assertEquals(buf.next(), 'b');
        assertEquals(buf.lookAhead(), 'c');
        assertEquals(buf.next(), 'c');
        assertEquals(buf.lookAhead(), 'd');
        assertEquals(buf.next(), 'd');
        assertEquals(buf.lookAhead(), 'e');
        assertEquals(buf.next(), 'e');
    }

    @Test(expected = NoSuchElementException.class)
    public void lookaheadEmptyCode() throws LexerException {
        buf.setCode("");
        buf.lookAhead();
    }

    @Test(expected = NoSuchElementException.class)
    public void lookaheadNoSuchElement() throws LexerException {
        buf.setCode("a");
        buf.next();
        buf.lookAhead();
    }

    @Test(expected = NoSuchElementException.class)
    public void doublelookaheadNoSuchElement() {
        buf.setCode("a");
        buf.doubleLookAhead();
    }

    @Test
    public void doublelookaheadMultipleElements() throws LexerException {
        buf.setCode("abcd");
        assertEquals(buf.lookAhead(), 'a');
        assertEquals(buf.doubleLookAhead(), 'b');
        assertEquals(buf.next(), 'a');
        assertEquals(buf.lookAhead(), 'b');
        assertEquals(buf.doubleLookAhead(), 'c');
        assertEquals(buf.next(), 'b');
        assertEquals(buf.lookAhead(), 'c');
        assertEquals(buf.doubleLookAhead(), 'd');
        assertEquals(buf.next(), 'c');
    }
}
