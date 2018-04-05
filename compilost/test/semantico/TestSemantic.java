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
public class TestSemantic {

    protected Lexico lex = null;
    protected ErrorList el = null;
//    protected TokenList tl = null;

    public TestSemantic() {
    }

    /**
     * Faz testes simples de aceitação passando apenas alguns parametros:
     * ex.: makeAnalyzeTest("algoritmo teste;", true, AlgoritmoRule.getInstance());
     * Lembra-se que a não deve sobrar tokens.
     */
//    public void makeAnalyzeTest(String code, boolean expected, AbstractProductionRule rule) throws ParserException {
//        scanCode(code);
//        rule.analyze(tl, et);
//        assertEquals(expected, lex.getTokenList().getRemainingTokens().isEmpty() && et.isEmpty());
//    }
//
//    public void startTables() {
//        tl = lex.getTokenList();
//        et = new ErrorList();
//    }
//
//    public void scanCode(String code) {
//        lex = new Lexico(new CodeBuffer(code));
//        lex.scan();
//        startTables();
//    }
//
//    @Test
//    public void testConstantes() throws ParserException {
//        makeAnalyzeTest("constantes inteiro a = 20;", true, ConstanteProduction.getInstance());
//        makeAnalyzeTest("constantes inteiro a = 20.5;", false, ConstanteProduction.getInstance());
//        makeAnalyzeTest("constantes string a = 20.5;", false, ConstanteProduction.getInstance());
//        makeAnalyzeTest("constantes caractere a = 20.5;", false, ConstanteProduction.getInstance());
//        makeAnalyzeTest("constantes logico a = 20.5;", false, ConstanteProduction.getInstance());
//
//        makeAnalyzeTest("constantes logico a = verdadeiro;", true, ConstanteProduction.getInstance());
//    }
//
//    @Test
//    public void testTipoVariavel()throws ParserException {
//        makeAnalyzeTest("inteiro", true, TipoVariavelProduction.getInstance());
//        makeAnalyzeTest("#", false, TipoVariavelProduction.getInstance());
//
//    }
//
//    @Test
//    public void testSe() throws ParserException{
//
//        makeAnalyzeTest("se(VERDADEIRO) entao {}", true, SeProduction.getInstance());
//        makeAnalyzeTest("se(i=0) entao {}", false, SeProduction.getInstance());
//
//    }
//
//    @Test
//    public void testArgs() throws ParserException{
//
//        makeAnalyzeTest("inteiro", false, ArgsProduction.getInstance());
//        makeAnalyzeTest("real r", true, ArgsProduction.getInstance());
//        makeAnalyzeTest("logico v", true, ArgsProduction.getInstance());
//        makeAnalyzeTest("inteiro i, string", false, ArgsProduction.getInstance());
//   }
//    @Test
//    public void testArray() throws ParserException{
//
//        makeAnalyzeTest("[i++]", true, ArrayProduction.getInstance());
//        makeAnalyzeTest("[a", false, ArrayProduction.getInstance());
//        makeAnalyzeTest("[a+]", false, ArrayProduction.getInstance());
//        makeAnalyzeTest("[]", false, ArrayProduction.getInstance());
//        makeAnalyzeTest("[a+7]", true, ArrayProduction.getInstance());
//    }
//
//    @Test
//    public void Variaveis() throws ParserException{
//
//        makeAnalyzeTest("variaveis inteiro a", true, VariaveisProduction.getInstance());
//        makeAnalyzeTest("variaveis inteiro b = 0", true, VariaveisProduction.getInstance());
//
//    }
//
//    @Test
//    public void Declaracao() throws ParserException{
//        makeAnalyzeTest("constantes inteiro a = 20;", true, DeclarationsProduction.getInstance());
//        makeAnalyzeTest("variaveis inteiro a = 20;", true, DeclarationsProduction.getInstance());
//        
//    }
//    @Test
//    public void Intrucao() throws ParserException{
//
//        makeAnalyzeTest("variaveis inteiro a = 20;", true, InstrucaoProduction.getInstance());
//
//    }
//
//    @Test
//    public void ExprArit() throws ParserException{
//
//        makeAnalyzeTest("(3+2)+(4+2);", true, InstrucaoProduction.getInstance());
//        makeAnalyzeTest("3 + (3+9);"  , true, InstrucaoProduction.getInstance ());
//
//
//
//    }

    
    @Before
    public void scanCode(){
        lex = new Lexico(new CodeBuffer());
        el = new ErrorList();
    }

    
    public SemanticType getCodeType(String code, AbstractProductionRule apr){
        SymbolTable.restartTable();
        lex.getBuffer().setCode(code);
        lex.scan();
        return apr.analyze(lex.getTokenList(), el).getType();
    }

    public void analyzeCode(String code, AbstractProductionRule apr){
        getCodeType(code, apr);
    }







}


