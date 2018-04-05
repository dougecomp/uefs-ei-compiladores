/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sintatico;

import exceptions.ParserException;
import org.junit.Test;
import productions.CastProduction;

/**
 *
 * @author pedro
 */
public class TestCast extends TestSintatic {

    public TestCast() {
    }

    @Test
    public void testeCast() throws ParserException {
        makeAnalyzeTest("cast(inteiro, a)", true, CastProduction.getInstance());
        makeAnalyzeTest("code", false, CastProduction.getInstance());
        makeAnalyzeTest("cast(meuTipo, b)", true, CastProduction.getInstance());
        makeAnalyzeTest("cast(,)", false, CastProduction.getInstance());
    }
    /*
    @Test
    public void testCorrectCasting() throws ParserException{
    String code = "(real)";
    
    scanCode(code);
    
    //sintatico
    SymbolTable  st = lex.getTable();
    ErrorTableParser etp = new ErrorTableParser();
    assertTrue(CastProduction.getInstance().analyze());
    }
    
    @Test
    public void testWithoutOpeningParenthesisCasting() throws ParserException{
    String code = "int)";
    
    scanCode(code);
    
    //sintatico       
    assertFalse(CastProduction.getInstance().analyze());
    
    }
    
    @Test
    public void testWithoutClosingParenthesisCasting() throws ParserException{
    String code = "(int";
    
    scanCode(code);
    
    //sintatico        
    assertFalse(CastProduction.getInstance().analyze());
    
    }
     */

    @Override
    public void testeFirst() {
    }

    @Override
    public void testeFollow() {
    }
}
