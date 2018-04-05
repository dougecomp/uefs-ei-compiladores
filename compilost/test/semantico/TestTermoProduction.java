/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package semantico;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import productions.TermoProduction;
import static org.junit.Assert.*;

/**
 *
 * @author caco
 */
public class TestTermoProduction extends TestSemantic{

    public TestTermoProduction() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    public void testeTipoIndefinido(){
        assertEquals(getCodeType("xxx",TermoProduction.getInstance()), SemanticType.TYPE_INDEFINIDO);
    }
    
    @Test
    public void testeCaractere(){
        assertEquals(getCodeType("\"x\"",TermoProduction.getInstance()),SemanticType.TYPE_CARACTERE);
    }

    @Test
    public void testeString(){
        assertEquals(getCodeType("\"uma string qualquer\"",TermoProduction.getInstance()), SemanticType.TYPE_STRING);
    }
    
    @Test
    public void testeInteger(){
        assertEquals(getCodeType("10",TermoProduction.getInstance()), SemanticType.TYPE_INTEIRO);
    }
    
    @Test
    public void testeReal(){
        assertEquals(getCodeType("10.0",TermoProduction.getInstance()), SemanticType.TYPE_REAL);
    }
    
    @Test
    public void testeExprReal(){
        assertEquals(getCodeType("(10.0)",TermoProduction.getInstance()), SemanticType.TYPE_REAL);
    }
    
    @Test
    public void testeExprInt(){
        assertEquals(getCodeType("(((10)))",TermoProduction.getInstance()), SemanticType.TYPE_INTEIRO);
    }
    
    
    @Test
    public void testeExprCompostaInt(){
        assertEquals(getCodeType("(((10+9)))",TermoProduction.getInstance()), SemanticType.TYPE_INTEIRO);
    }
    
    
    @Test
    public void testeExprCompostaReal(){
        assertEquals(getCodeType("(((10+9.0)))",TermoProduction.getInstance()), SemanticType.TYPE_REAL);
    }
    
    


}