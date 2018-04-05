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
import productions.ExprRelProduction;
import productions.FatorProduction;
import static org.junit.Assert.*;

/**
 *
 * @author caco
 */
public class TestExprRelProduction extends TestSemantic{

    public TestExprRelProduction() {
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
    public void testeExprRelLogicavalida(){
        assertEquals(getCodeType("( falso > (5+11) ));",ExprRelProduction.getInstance()), SemanticType.TYPE_INTEIRO);
    }

    @Test
    public void testeExprRelLogicaInvalida(){
        assertEquals(getCodeType("( verdadeiro == falso ));",ExprRelProduction.getInstance()), SemanticType.TYPE_LOGICO);
    }

    @Test
    public void testeExprRelAritInvalida(){
        assertEquals(getCodeType("( 3+1 < 4+1 ));",ExprRelProduction.getInstance()), SemanticType.TYPE_INTEIRO);
    }


}