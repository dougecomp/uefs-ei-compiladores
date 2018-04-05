package sintatico;

import exceptions.ParserException;
import org.junit.Test;
import productions.AlgoritmoProduction;

/**
 * @author heekinho
 */
public class TestAlgoritmo extends TestSintatic {

    public TestAlgoritmo() {
    }

    @Test
    public void testAlgoritmo() throws ParserException {
        makeAnalyzeTest("code", false, AlgoritmoProduction.getInstance());
        makeAnalyzeTest("algoritmo inteiro a", false, AlgoritmoProduction.getInstance());
        makeAnalyzeTest("algoritmo teste;", true, AlgoritmoProduction.getInstance());
        makeAnalyzeTest("algoritmo", false, AlgoritmoProduction.getInstance());
    }

    @Override
    public void testeFirst() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void testeFollow() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
