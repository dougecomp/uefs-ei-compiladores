/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sintatico;

import exceptions.ParserException;
import org.junit.Test;
import productions.OpIncProduction;

/**
 *
 * @author Pedro
 */
public class TestOpIncDec extends TestSintatic {

    public TestOpIncDec() {
    }

    @Test
    public void TestOpIncDec() throws ParserException {
        //makeAnalyzeTest("++", false, OpIncProduction.getInstance());
        //makeAnalyzeTest("algoritmo inteiro a", false, OpIncProduction.getInstance());
        makeAnalyzeTest("++", true, OpIncProduction.getInstance());
        makeAnalyzeTest("--", true, OpIncProduction.getInstance());
        makeAnalyzeTest("+", false, OpIncProduction.getInstance());
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
