/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package semantico;

import exceptions.ParserException;
import lexico.TokenList;
import lexico.CodeBuffer;
import lexico.ErrorList;
import lexico.Lexico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import productions.TipoPrimitivoProduction;
import static org.junit.Assert.*;

/**
 *
 * @author caco
 */
public class TestTipoPrimitivo extends TestSemantic{

    public TestTipoPrimitivo() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    

        
    
    
    
    @Test
    public void testTiposBasicos(){
        assertEquals(getCodeType("inteiro", TipoPrimitivoProduction.getInstance()),  SemanticType.TYPE_INTEIRO);
        assertEquals(getCodeType("logico", TipoPrimitivoProduction.getInstance()),   SemanticType.TYPE_LOGICO);
        assertEquals(getCodeType("caractere",TipoPrimitivoProduction.getInstance()), SemanticType.TYPE_CARACTERE);
        assertEquals(getCodeType("real",TipoPrimitivoProduction.getInstance()),      SemanticType.TYPE_REAL);
        assertEquals(getCodeType("string",TipoPrimitivoProduction.getInstance()),    SemanticType.TYPE_STRING);
        assertEquals(getCodeType("xxx",TipoPrimitivoProduction.getInstance()),       SemanticType.TYPE_INDEFINIDO);
        
    }

}