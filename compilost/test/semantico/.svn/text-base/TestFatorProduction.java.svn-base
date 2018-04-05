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
import productions.FatorProduction;
import static org.junit.Assert.*;

/**
 *
 * @author caco
 */
public class TestFatorProduction extends TestSemantic{

    public TestFatorProduction() {
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
        assertEquals(getCodeType("xxx",FatorProduction.getInstance()), SemanticType.TYPE_INDEFINIDO);
    }

    
//    @Test
//    public void testeInteger(){
//        assertEquals(getCodeType("10;",FatorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
//    }
//
//    @Test
//    public void testeIntegerParenthesis(){
//        assertEquals(getCodeType("(10);",FatorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
//    }
//
//    @Test
//    public void testeIntegerPlusParenthesis(){
//        assertEquals(getCodeType("+(10);",FatorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
//    }
//
//    @Test
//    public void testeIntegerPlusMultParenthesis(){
//        assertEquals(getCodeType("+(((((10)))));",FatorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
//    }
//
//    @Test
//    public void testeRealPlusMultParenthesis(){
//        assertEquals(getCodeType("+(((((10.0)))));",FatorProduction.getInstance()), SemanticType.TYPE_REAL);
//    }
//
//    @Test
//    public void testeReal(){
//        assertEquals(getCodeType("10.0;",FatorProduction.getInstance()), SemanticType.TYPE_REAL);
//    }
//
//    @Test
//    public void testeRealNegative(){
//        assertEquals(getCodeType("-10.0;",FatorProduction.getInstance()), SemanticType.TYPE_REAL);
//    }
//
//    @Test
//    public void testeRealPositive(){
//        assertEquals(getCodeType("+10.0;",FatorProduction.getInstance()), SemanticType.TYPE_REAL);
//    }
//
//    @Test
//    public void testeIntegerPositive(){
//        assertEquals(getCodeType("+10;",FatorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
//    }
//
//    @Test
//    public void testeIntegerNegative(){
//        assertEquals(getCodeType("-10;",FatorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
//    }
//
//
//    @Test
//    public void testeKeywordTrue(){
//        assertEquals(getCodeType("verdadeiro;",FatorProduction.getInstance()), SemanticType.TYPE_LOGICO);
//    }
//    @Test
//    public void testeKeywordFalse(){
//        assertEquals(getCodeType("falso;",FatorProduction.getInstance()), SemanticType.TYPE_LOGICO);
//    }
//    @Test
//    public void testeNotKeywordFalse(){
//        assertEquals(getCodeType("!falso;",FatorProduction.getInstance()), SemanticType.TYPE_LOGICO);
//    }
//    @Test
//    public void testeNotKeywordTrue(){
//        assertEquals(getCodeType("!verdadeiro;",FatorProduction.getInstance()), SemanticType.TYPE_LOGICO);
//    }
//
//
//    @Test
//    public void testeCastInteiro(){
//        assertEquals(getCodeType("cast(inteiro, 10);",FatorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
//    }
//
//    @Test
//    public void testeCastReal(){
//        assertEquals(getCodeType("cast(real, 10);",FatorProduction.getInstance()), SemanticType.TYPE_REAL);
//    }
//
//    @Test
//    public void testeCastString(){
//        assertEquals(getCodeType("cast(string, 10);",FatorProduction.getInstance()), SemanticType.TYPE_STRING);
//    }
//
//
//    @Test
//    public void testeCastCaractere(){
//        assertEquals(getCodeType("cast(caractere, 10);",FatorProduction.getInstance()), SemanticType.TYPE_CARACTERE);
//    }
//
//    @Test
//    public void testeCastLogico(){
//        assertEquals(getCodeType("cast(logico, 10);",FatorProduction.getInstance()), SemanticType.TYPE_LOGICO);
//    }

//    @Test
//    public void testeExprSimples(){
//        assertEquals(getCodeType("(1+1);",FatorProduction.getInstance()), SemanticType.TYPE_INTEIRO);
//    }
//
//    @Test
//    public void testeExprRealSimples(){
//        assertEquals(getCodeType("(1+1.0);",FatorProduction.getInstance()), SemanticType.TYPE_REAL);
//    }

    @Test
    public void testeExprLogicaInvalida(){
        assertEquals(getCodeType("(verdadeiro + verdadeiro);",FatorProduction.getInstance()), SemanticType.TYPE_REAL);
    }

  

}