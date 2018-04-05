package sintatico;

import static org.junit.Assert.*;
import exceptions.ParserException;
import lexico.Lexico;
import lexico.CodeBuffer;
import lexico.ErrorList;
import lexico.TokenList;
import productions.*;
import org.junit.Test;

/**
 * @author caco
 * @author jairo
 */
public abstract class TestSintatic {

    protected Lexico lex = null;
    protected ErrorList et = null;
    protected TokenList st = null;

    public TestSintatic() {
    }

    /**
     * Faz testes simples de aceitação passando apenas alguns parametros:
     * ex.: makeAnalyzeTest("algoritmo teste;", true, AlgoritmoRule.getInstance());
     * Lembra-se que a não deve sobrar tokens.
     */
    public void makeAnalyzeTest(String code, boolean expected, AbstractProductionRule rule) throws ParserException {
        scanCode(code);
        rule.analyze(st, et);
        
        assertEquals(expected,  lex.getTokenList().getRemainingTokens().isEmpty() && et.isEmpty());
    }

    public void assertException(String code, AbstractProductionRule rule) throws ParserException {
        makeAnalyzeTest(code, false, rule);
    }

    public void startTables() {
        st = lex.getTokenList();
        et = new ErrorList();
    }

    public void scanCode(String code) {
        lex = new Lexico(new CodeBuffer(code));
        lex.scan();
        startTables();
    }

    @Test
    public abstract void testeFirst();

    @Test
    public abstract void testeFollow();
}
