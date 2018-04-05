package lexico;

import automatos.DelimiterAutomaton;
import automatos.OperatorAutomaton;
import automatos.NumericAutomaton;
import exceptions.LexerException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author André e Ronaldo
 */
public class TestNumericAutomaton {

    NumericAutomaton automatoNumero;
    OperatorAutomaton automatoOperador;
    DelimiterAutomaton automatoDelimitador;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        automatoNumero = new NumericAutomaton(new CodeBuffer());
        automatoOperador = new OperatorAutomaton(new CodeBuffer());
        automatoDelimitador = new DelimiterAutomaton(new CodeBuffer());
    }

    @After
    public void tearDown() {
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @TESTES BÁSICOS RETORNANDO OS TIPOS NUMÉRICOS
     * Aqui são testados somente os tipos de números. Testes de erros serão evidenciados mais abaixo
     */
    @Test
    public void testSimpleNumber() throws Exception {
        automatoNumero.getBuffer().setCode("2");
        assertEquals(TokenClass.NUMERIC, automatoNumero.process().getTokenClass());
    }

    @Test
    public void testeNumeroRealPositivo() throws Exception {
        automatoNumero.getBuffer().setCode("2.714");
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
    }

    @Test
    public void testeNumeroPositivoMuitoGrande() throws LexerException {
        automatoNumero.getBuffer().setCode("786257257864387596723654365896538685678938960746709962345978436756293465342568365836583475684365834");
        try {
            automatoNumero.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Número muito grande".toLowerCase(), e.getDescricao().toLowerCase());
        }
    }

    @Test
    public void testeNumeroMuitoGrande() throws Exception {
        automatoNumero.getBuffer().setCode("17657896536425678346586285682934589293589432785789235786257257864387596723654365896538685678938960746709962345978436756293465342568365836583475684365834");
        try {
            automatoNumero.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Número muito grande".toLowerCase(), e.getDescricao().toLowerCase());
        }
    }

    @Test
    public void testeNumeroRealPositivoMuitoGrande() throws Exception {
        automatoNumero.getBuffer().setCode("786257257864387596723654365896538685678938960746709962345978.436756293465342568365836583475684365834");
        try {
            automatoNumero.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Número muito grande".toLowerCase(), e.getDescricao().toLowerCase());
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @TESTES PARA CONSTRUÇÕES NUMÉRICAS VÁLIDAS E INVÁLIDAS
     * Testando a real verificação de erros em algumas construções numéricas
     */
    @Test
    public void testNumeroSeguidoDeOperadorSITUACAOvalida() throws Exception {
        automatoNumero.getBuffer().setCode("2+");
        assertEquals(TokenClass.NUMERIC, automatoNumero.process().getTokenClass());
        assertEquals('+', automatoNumero.getBuffer().lookAhead());
    }

    @Test
    public void testNumeroEOperadoresAlternadosSITUACAOvalida() throws LexerException {
        automatoNumero.getBuffer().setCode("278+3;");
        Token t = automatoNumero.process();
        assertEquals(t.getTokenClass(), TokenClass.NUMERIC);
        assertEquals(t.getLexeme(), "278");
        assertEquals(automatoNumero.getBuffer().lookAhead(), '+');
        assertEquals(automatoNumero.getBuffer().doubleLookAhead(), '3');
    }

    @Test
    public void testeComposicaoNumericaSITUACAOinvalidaFinalizador() throws Exception {
        automatoNumero.getBuffer().setCode("9.7.");
        try {
            automatoNumero.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Número Inválido", e.getDescricao());
        }
    }

    @Test
    public void testeComposicaoNumericaSITUACAOinvalida() throws Exception {
        automatoNumero.getBuffer().setCode("9.");
        try {
            automatoNumero.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Número Inválido", e.getDescricao());
        }
    }

    @Test
    public void testeComposicaoNumericaNegativaSITUACAOinvalida() throws Exception {
        automatoNumero.getBuffer().setCode("9.");
        try {
            automatoNumero.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Número Inválido", e.getDescricao());
        }
    }

    @Test
    public void testeComposicaoNumericaSITUACAOinvalidaNumeroComLetras() throws Exception {
        automatoNumero.getBuffer().setCode("9ab+");
        try {
            automatoNumero.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Número Inválido", e.getDescricao());
        }
        assertEquals(automatoNumero.getBuffer().lookAhead(), '+');
    }

    @Test
    public void testeComposicaoNumericaSITUACAOinvalidaNumeroComLetras2() throws Exception {
        automatoNumero.getBuffer().setCode("9ab9.4");
        try {
            automatoNumero.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Número Inválido", e.getDescricao());
        }
        assertTrue(automatoNumero.getBuffer().eof());
    }

    @Test
    public void testeCombinatorioINVALIDOeVALIDO() throws Exception {
        automatoNumero.getBuffer().setCode("9ab 9.4");
        try {
            automatoNumero.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Número Inválido", e.getDescricao());
        }
        assertEquals(' ', automatoNumero.getBuffer().next());
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @TESTES PARA USO DE FINALIZADORES APÓS OS NÚMEROS
     * Testado o uso de alguns caracteres finalizadores de numeros
     */
    @Test
    public void testNumeroFinalizadorMais() throws Exception {
        automatoNumero.getBuffer().setCode("1+");
        assertEquals(TokenClass.NUMERIC, automatoNumero.process().getTokenClass());
        automatoOperador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.OPERATOR_PLUS, automatoOperador.process().getTokenClass());
    }

    @Test
    public void testNumeroFinalizadorMenos() throws Exception {
        automatoNumero.getBuffer().setCode("2.1-");
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
        automatoOperador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.OPERATOR_MINUS, automatoOperador.process().getTokenClass());
    }

    @Test
    public void testNumeroFinalizadorVezes() throws Exception {
        automatoNumero.getBuffer().setCode("17.3*");
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
        automatoOperador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.OPERATOR_MULT, automatoOperador.process().getTokenClass());
    }

    @Test
    public void testNumeroFinalizadorPontoEVirgula() throws Exception {
        automatoNumero.getBuffer().setCode("2.14;");
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
        automatoDelimitador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.DELIMITER_SEMICOLON, automatoDelimitador.process().getTokenClass());
    }

    @Test
    public void testNumeroFinalizadorPipe() throws Exception {
        automatoNumero.getBuffer().setCode("0.000002|");
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
        automatoOperador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        try {
            automatoOperador.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Operador PIPE inválido", e.getDescricao());
        }
        automatoNumero.getBuffer().next();
        assertTrue(automatoNumero.getBuffer().eof());
    }

    @Test
    public void testNumeroFinalizadorEComercial() throws Exception {
        automatoNumero.getBuffer().setCode("2.156&");
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
        automatoOperador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        try {
            automatoOperador.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Operador AND inválido", e.getDescricao());
        }
        automatoNumero.getBuffer().next();
        assertTrue(automatoNumero.getBuffer().eof());
    }

    @Test
    public void testNumeroFinalizadorIgual() throws Exception {
        automatoNumero.getBuffer().setCode("1422=");
        assertEquals(TokenClass.NUMERIC, automatoNumero.process().getTokenClass());
        automatoOperador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.OPERATOR_EQUAL, automatoOperador.process().getTokenClass());
    }

    @Test
    public void testNumeroFinalizadorExclamacao() throws Exception {
        automatoNumero.getBuffer().setCode("927!");
        assertEquals(TokenClass.NUMERIC, automatoNumero.process().getTokenClass());
        automatoOperador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        try {
            automatoOperador.process().getTokenClass();
        } catch (LexerException e) {
            assertEquals("Operador diferente inválido", e.getDescricao());
        }
        assertTrue(automatoOperador.getBuffer().eof());
    }

    @Test
    public void testNumeroFinalizadorMaior() throws Exception {
        automatoNumero.getBuffer().setCode("244>");
        assertEquals(TokenClass.NUMERIC, automatoNumero.process().getTokenClass());
        automatoOperador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.OPERATOR_MORE, automatoOperador.process().getTokenClass());
    }


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @TESTES PARA COMPOSICOES DE FINALIZADORES E NÚMEROS
     * Testado o uso de alguns delimitadores utilizados nsa tabelas
     * Para os testes abaixo, seus nomes indicam a ordem de aparicao das composições em seu devido código.
     * 'N' para numero; 'F' para finalizador; O para operador.
     * Ex: NFFNONF é um exemplo de expressao do tipo: "9.145]}4.14+-1.14)"
     */
    @Test
    public void testNumeroFinalizadorFina() throws Exception {
        automatoNumero.getBuffer().setCode("9.145]}4.14+1.14)");
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
        automatoDelimitador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.DELIMITER_CLOSING_SQUARE_BRACKET, automatoDelimitador.process().getTokenClass());
        assertEquals(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET, automatoDelimitador.process().getTokenClass());
        automatoNumero.getBuffer().setCode(automatoDelimitador.getBuffer().getCode().substring(automatoDelimitador.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
        automatoOperador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.OPERATOR_PLUS, automatoOperador.process().getTokenClass());
        automatoNumero.getBuffer().setCode(automatoOperador.getBuffer().getCode().substring(automatoOperador.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.NUMERIC_REAL, automatoNumero.process().getTokenClass());
        automatoDelimitador.getBuffer().setCode(automatoNumero.getBuffer().getCode().substring(automatoNumero.getBuffer().getPositionOnLine()));
        assertEquals(TokenClass.DELIMITER_CLOSING_PARENTHESIS, automatoDelimitador.process().getTokenClass());
        assertTrue(automatoDelimitador.getBuffer().eof());
    }
}
