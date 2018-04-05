package semantico;

import static org.junit.Assert.*;
import exceptions.ParserException;
import lexico.Lexico;
import lexico.CodeBuffer;
import lexico.ErrorList;
import lexico.TokenList;
import org.junit.Before;
import org.junit.Test;
import productions.*;

/**
 * @author heekinho
 */
public class TestDclrVarProduction extends TestSemantic{

//    protected Lexico lex = null;
//    protected ErrorList el = null;

    public TestDclrVarProduction() {
    }

    @Test
    public void test(){

        lex.getBuffer().setCode("variaveis inteiro a = 10");
        lex.scan();
        assertEquals(VariaveisProduction.getInstance().analyze(lex.getTokenList(), el).getType(), SemanticType.TYPE_INTEIRO);

    }

    

}


