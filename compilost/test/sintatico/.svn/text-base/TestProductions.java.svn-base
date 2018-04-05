package sintatico;

import static org.junit.Assert.*;
import exceptions.ParserException;
import org.junit.Assert;
import org.junit.Test;
import productions.*;

/**
 * @author heekinho
 */
public class TestProductions extends TestSintatic {

    public TestProductions() {
    }

    @Test
    public void testAlgoritmo() throws ParserException {
        makeAnalyzeTest("code", false, AlgoritmoProduction.getInstance());
        assertException("algoritmo inteiro a", AlgoritmoProduction.getInstance());
        makeAnalyzeTest("algoritmo teste;", true, AlgoritmoProduction.getInstance());
        assertException("algoritmo", AlgoritmoProduction.getInstance());
    }

    @Test
    public void testLeia() throws ParserException {
        assertException("leia(a, b, c) int a", LeiaProduction.getInstance());
        makeAnalyzeTest("leia(a, b);", true, LeiaProduction.getInstance());
        assertException("leia();", LeiaProduction.getInstance());
    }

    @Test
    public void testTipoPrimitivo() throws ParserException {
        AbstractProductionRule abr = TipoPrimitivoProduction.getInstance();

        makeAnalyzeTest("leia", false, abr);
        makeAnalyzeTest("inteiro", true, abr);
        makeAnalyzeTest("real", true, abr);
        makeAnalyzeTest("bool", false, abr);
        makeAnalyzeTest("booleano", false, abr);
        makeAnalyzeTest("logico", true, abr);
        makeAnalyzeTest("string", true, abr);
        makeAnalyzeTest("caractere", true, abr);
    }

    @Test
    public void testTipoVariavel() throws ParserException {
        makeAnalyzeTest("leia(a, b, c)", false, TipoVariavelProduction.getInstance());
        makeAnalyzeTest("inteiro", true, TipoVariavelProduction.getInstance());
        makeAnalyzeTest("teste;", false, TipoVariavelProduction.getInstance());
        makeAnalyzeTest("real", true, TipoVariavelProduction.getInstance());
    }

    @Test
    public void testConstante() throws ParserException {
        assertException("constantes inteiro a;", ConstanteProduction.getInstance());
        makeAnalyzeTest("inteiro dk", false, ConstanteProduction.getInstance());
        makeAnalyzeTest("constantes inteiro a = 30;", true, ConstanteProduction.getInstance());
        makeAnalyzeTest("const int a = 1;", false, ConstanteProduction.getInstance());
    }

    @Test
    public void testTipoDefinido() throws ParserException {
        makeAnalyzeTest("tipo inteiro int;", true, TipoDefinidoProduction.getInstance());
        assertException("tipo inteiro int", TipoDefinidoProduction.getInstance());
        assertException("tipo int inteiro", TipoDefinidoProduction.getInstance());
        makeAnalyzeTest("tipo registro { inteiro a; } test;", true, TipoDefinidoProduction.getInstance());
        makeAnalyzeTest("tipo registro { inteiro a; int b; real c; } test;", true, TipoDefinidoProduction.getInstance());
        assertException("tipo registro { inteiro a; inteiro;} test;", TipoDefinidoProduction.getInstance());
    }

    @Test
    public void testRegistro() throws ParserException {
        makeAnalyzeTest("registro{ a a; }", true, RegistroProduction.getInstance());
    }

    @Test
    public void testPrincipal() throws ParserException {
        makeAnalyzeTest("funcao vazio principal(){}", true, PrincipalProduction.getInstance());
    }

    @Test
    public void testBloco() throws ParserException {
        makeAnalyzeTest("{}", true, BlocoProduction.getInstance());
    }

    @Test
    public void testSe() throws ParserException {
        makeAnalyzeTest("se(a)entao{}", true, SeProduction.getInstance());
        makeAnalyzeTest("se(a)entao{}senao{}", true, SeProduction.getInstance());
    }

    @Test
    public void testEnquanto() throws ParserException {
        makeAnalyzeTest("enquanto(a){}", true, EnquantoProduction.getInstance());
        assertException("enquanto(){}", EnquantoProduction.getInstance());
    }

    @Test
    public void testValor() throws ParserException {
        makeAnalyzeTest("a", true, ValorProduction.getInstance());
        makeAnalyzeTest("\"teste\"", true, ValorProduction.getInstance());
        makeAnalyzeTest("\"c\"", true, ValorProduction.getInstance());
    }

    @Test
    public void testTipoRetornoF() throws ParserException {
        makeAnalyzeTest("a", true, TipoRetornoFProduction.getInstance());
        makeAnalyzeTest("inteiro", true, TipoRetornoFProduction.getInstance());
        makeAnalyzeTest("vazio", true, TipoRetornoFProduction.getInstance());
    }

    @Test
    public void testParams() throws ParserException {
        makeAnalyzeTest("a", true, ParamsProduction.getInstance());
        makeAnalyzeTest("a,b,c", true, ParamsProduction.getInstance());
        makeAnalyzeTest("\"1\",b,d", true, ParamsProduction.getInstance());
        makeAnalyzeTest("a,teste,ea,\"kkk\"", true, ParamsProduction.getInstance());
    }

    @Test
    public void testArgs() throws ParserException {
        assertException("a,b,c", ArgsProduction.getInstance());
        makeAnalyzeTest("\"1\",b,d", false, ArgsProduction.getInstance());
        assertException("a,teste,ea,\"kkk\"", ArgsProduction.getInstance());
        makeAnalyzeTest("int a, inteiro c", true, ArgsProduction.getInstance());
    }

    @Test
    public void testChamadaFuncao() throws ParserException {
        makeAnalyzeTest("()", true, ChamadaFuncaoProduction.getInstance());
        makeAnalyzeTest("(a)", true, ChamadaFuncaoProduction.getInstance());
        makeAnalyzeTest("(a,b,c)", true, ChamadaFuncaoProduction.getInstance());
    }

    @Test
    public void testEscreva() throws ParserException {
        assertException("escreva(a, b, c) int a;", EscrevaProduction.getInstance());
        makeAnalyzeTest("escreva(a,b,c,d);", true, EscrevaProduction.getInstance());
        makeAnalyzeTest("(a,b,c)", false, EscrevaProduction.getInstance());
    }

    @Test
    public void testRetorno() throws ParserException {
        makeAnalyzeTest("escreva(a, b, c)", false, RetornoProduction.getInstance());
        assertException("retorno a", RetornoProduction.getInstance());
        makeAnalyzeTest("retorno(a);", true, RetornoProduction.getInstance());
    }

    @Test
    public void testInitArray() throws ParserException {
        makeAnalyzeTest("escreva(a, b, c)", false, InitArrayProduction.getInstance());
        assertException("{}", InitArrayProduction.getInstance());
        makeAnalyzeTest("{a}", true, InitArrayProduction.getInstance());
        makeAnalyzeTest("{a,b,c}", true, InitArrayProduction.getInstance());
    }

    @Test
    public void testInits() throws ParserException {
        makeAnalyzeTest("escreva(a, b, c)", false, InitsProduction.getInstance());
        assertException("{}", InitsProduction.getInstance());
        makeAnalyzeTest("a", true, InitsProduction.getInstance());
        makeAnalyzeTest("{a,b,c}", true, InitsProduction.getInstance());
    }

    @Test
    public void testArray() throws ParserException {
        makeAnalyzeTest("{a}", false, ArrayProduction.getInstance());
        makeAnalyzeTest("[a]", true, ArrayProduction.getInstance());
        assertException("[]", ArrayProduction.getInstance());
    }

    @Test
    public void testArrayD() throws ParserException {
        makeAnalyzeTest("[]", true, ArrayDProduction.getInstance());
        makeAnalyzeTest("[a]", true, ArrayDProduction.getInstance());
    }

    @Test
    public void testDclrPrimitivo() throws ParserException {
//		makeAnalyzeTest("inteiro a[][a]", true, DclrPrimitivoProduction.getInstance());
//		makeAnalyzeTest("inteiro a", true, DclrPrimitivoProduction.getInstance());
    }

    @Test
    public void testDclrVar() throws ParserException {
        makeAnalyzeTest("int a[][a]", true, DclrVarProduction.getInstance());
        makeAnalyzeTest("int a", true, DclrVarProduction.getInstance());
    }

    @Test
    public void testFuncao() throws ParserException {
        makeAnalyzeTest("int a[][a]", false, FuncaoProduction.getInstance());
        makeAnalyzeTest("funcao inteiro[][][] a(int a, int b[], inteiro c){}", true, FuncaoProduction.getInstance());
        makeAnalyzeTest("funcao vazio resetar(){}", true, FuncaoProduction.getInstance());
    }

    @Test
    public void testDclrVarInit() throws ParserException {
        makeAnalyzeTest("int a[][] = b", true, DclrVarInitProduction.getInstance());
        makeAnalyzeTest("int a", true, DclrVarInitProduction.getInstance());
    }

    @Test
    public void testVariaveis() throws ParserException {
        makeAnalyzeTest("variaveis int a[][] = b", true, VariaveisProduction.getInstance());
        makeAnalyzeTest("variaveis int a[][] = b, int b = x", true, VariaveisProduction.getInstance());
    }

    @Test
    public void testDeclarations() throws ParserException {
        makeAnalyzeTest("variaveis x a;", true, DeclarationsProduction.getInstance());
        makeAnalyzeTest("variaveis int a[][] = b, int b = x;", true, DeclarationsProduction.getInstance());
    }

    @Test
    public void testStart() throws ParserException {
        String code = ""
                + "algoritmo teste;"
                + "constantes inteiro MAX = 50;"
                + "constantes real MIN = 20.5;"
                + "variaveis inteiro teste = \"testando\";"
                + "tipo inteiro int;"
                + "tipo registro { inteiro x; real y;int pos[];} pair;"
                + "funcao vazio principal(){}"
                + "funcao vazio resetar(){}";
		;

        //+ "variaveis int a = 1;";
        makeAnalyzeTest(code, true, StartProduction.getInstance());
    }

    @Test
    public void testComandos() throws ParserException {
        makeAnalyzeTest("se(a)entao{}", true, ComandosProduction.getInstance());
        makeAnalyzeTest("enquanto(b){}", true, ComandosProduction.getInstance());
        makeAnalyzeTest("para(inteiro i = a, inteiro b;a;a,b){}", true, ComandosProduction.getInstance());
        makeAnalyzeTest("escreva(a);", true, ComandosProduction.getInstance());
        makeAnalyzeTest("leia(a,b);", true, ComandosProduction.getInstance());
        makeAnalyzeTest("retorno(x);", true, ComandosProduction.getInstance());
    }

    @Test
    public void testSentenca() throws ParserException {
        makeAnalyzeTest("a", true, SentencaProduction.getInstance());
        makeAnalyzeTest("teste()", true, SentencaProduction.getInstance());
        makeAnalyzeTest("b++", true, SentencaProduction.getInstance());
        makeAnalyzeTest("algo(a) = b", false, SentencaProduction.getInstance());
        makeAnalyzeTest("b = a", true, SentencaProduction.getInstance());
    }

    @Test
    public void testCast() throws ParserException {
        makeAnalyzeTest("cast(inteiro, x)", true, CastProduction.getInstance());
    }

    @Test
    public void testFator() throws ParserException {
        makeAnalyzeTest("cast(inteiro, x)", true, FatorProduction.getInstance());
        makeAnalyzeTest("a++", true, FatorProduction.getInstance());
        makeAnalyzeTest("++b", true, FatorProduction.getInstance());
        makeAnalyzeTest("cast(inteiro, x)", true, FatorProduction.getInstance());
        makeAnalyzeTest("teste()", true, FatorProduction.getInstance());
        makeAnalyzeTest("teste()++", true, FatorProduction.getInstance());
        makeAnalyzeTest("++teste()", true, FatorProduction.getInstance());
        makeAnalyzeTest("a", true, FatorProduction.getInstance());
    }

    @Test
    public void testTermo() throws ParserException {
        makeAnalyzeTest("cast(inteiro, x)*x", true, TermoProduction.getInstance());
        makeAnalyzeTest("a", true, TermoProduction.getInstance());
        makeAnalyzeTest("a * b", true, TermoProduction.getInstance());
    }

    @Test
    public void testExprArit() throws ParserException {
        makeAnalyzeTest("a + b", true, ExprAritProduction.getInstance());
        makeAnalyzeTest("inteiro", false, ExprAritProduction.getInstance());
    }

    @Test
    public void testExprRel() throws ParserException {
        makeAnalyzeTest("cast(inteiro, x)*x", true, ExprRelProduction.getInstance());
        makeAnalyzeTest("a * b + c < a + b", true, ExprRelProduction.getInstance());
        makeAnalyzeTest("a * b", true, ExprRelProduction.getInstance());
    }

    @Test
    public void testExprAnd() throws ParserException {
        makeAnalyzeTest("cast(inteiro, x)*x", true, ExprAndProduction.getInstance());
        makeAnalyzeTest("a * b + c < a + b", true, ExprAndProduction.getInstance());
        makeAnalyzeTest("a * b", true, ExprAndProduction.getInstance());
        makeAnalyzeTest("a * b && x", true, ExprAndProduction.getInstance());
    }

    @Test
    public void testExpr() throws ParserException {
        makeAnalyzeTest("a == b", true, ExprProduction.getInstance());
        makeAnalyzeTest("a > b + c", true, ExprProduction.getInstance());
        makeAnalyzeTest("a + (x * d) - 5 > 15.8", true, ExprProduction.getInstance());
        makeAnalyzeTest("a < b == c", false, ExprProduction.getInstance());
        makeAnalyzeTest("a == ++b - b ---b", true, ExprProduction.getInstance());
    }

    @Override
    public void testeFirst() {
    }

    @Override
    public void testeFollow() {
    }
}
