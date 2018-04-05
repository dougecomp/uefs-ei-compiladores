/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package semantico;

import exceptions.ParserException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import productions.ValorProduction;
import static org.junit.Assert.*;

/**
 *
 * @author caco
 */
public class TestValorProduction extends TestSemantic {

    public TestValorProduction() {
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
    public void testeTipoIndefinido(){
        assertEquals(getCodeType("xxx",ValorProduction.getInstance()), SemanticType.TYPE_INDEFINIDO);
    }
    
    @Test
    public void testeCaractere(){
        assertEquals(getCodeType("\"x\"",ValorProduction.getInstance()),SemanticType.TYPE_CARACTERE);
    }

    @Test
    public void testeString(){
        assertEquals(getCodeType("\"uma string qualquer\"",ValorProduction.getInstance()), SemanticType.TYPE_STRING);
    }
    
    @Test
    public void testeInteger(){
        assertEquals(getCodeType("10",ValorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
    }
    
    @Test
    public void testeReal(){
        assertEquals(getCodeType("10.0",ValorProduction.getInstance()), SemanticType.TYPE_REAL);
    }
    
    
    @Test
    public void testeExprReal(){
        assertEquals(getCodeType("(10.0)",ValorProduction.getInstance()), SemanticType.TYPE_REAL);
    }
    
    @Test
    public void testeExprInt(){
        assertEquals(getCodeType("(((10)))",ValorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
    }
    
    
    @Test
    public void testeExprCompostaInt(){
        assertEquals(getCodeType("(((10+9)))",ValorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
    }
    
    
    @Test
    public void testeExprCompostaReal(){
        assertEquals(getCodeType("(((10+9.0)))",ValorProduction.getInstance()), SemanticType.TYPE_REAL);
    }

}