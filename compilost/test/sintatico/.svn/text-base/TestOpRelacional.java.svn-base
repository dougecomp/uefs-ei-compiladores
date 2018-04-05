/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sintatico;

import exceptions.ParserException;
import productions.OpRelacionalProduction;
import org.junit.Test;

/**
 *
 * @author Douglas
 */
public class TestOpRelacional extends TestSintatic {

    public TestOpRelacional() {
    }

    @Test
    public void testOpRelacional() throws ParserException {
        makeAnalyzeTest(">=", true, OpRelacionalProduction.getInstance());
        makeAnalyzeTest("<=", true, OpRelacionalProduction.getInstance());
        makeAnalyzeTest("==", true, OpRelacionalProduction.getInstance());
        makeAnalyzeTest("!=", true, OpRelacionalProduction.getInstance());
    }

    @Override
    public void testeFirst() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void testeFollow() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
