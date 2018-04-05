package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 * @author caco
 */
public class ConstanteProduction extends AbstractProductionRule {
    

    private static ConstanteProduction instance = new ConstanteProduction();

    public static ConstanteProduction getInstance() {
        return instance;
    }

    /**
     * Regra: constante : 'constantes' tipo_primitivo IDENTIFIER '=' ('STRING' | 'NUMERIC' | 'CHARACTER' | 'VERDADEIRO' | 'FALSO') ';'
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {

        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_CONSTANTES)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperada a palavra reservada 'constantes'.");
            return new SymbolTableEntry();
        }


        if (TipoPrimitivoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            TipoPrimitivoProduction.getInstance().analyze(tl, el);
        } else {
            skipUntilSynchronize(tl, el, "Era esperada um tipo primitivo.");
            return new SymbolTableEntry();
        }



        if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperado um identificador.");
            return new SymbolTableEntry();
        }


        if (testEofAndCurrentToken(tl, TokenClass.OPERATOR_ASSIGNMENT)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperado um '='.");
            return new SymbolTableEntry();
        }


        TokenClass tc = tl.getCurrent().getTokenClass();
        if (!tl.eof() && (tc == TokenClass.STRING || tc == TokenClass.CARACTERE
                || tc == TokenClass.NUMERIC || tc == TokenClass.NUMERIC_REAL
                || tc == TokenClass.KEYWORD_VERDADEIRO || tc == TokenClass.KEYWORD_FALSO)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado string ou caractere ou número ou valor lógico.");
            return new SymbolTableEntry();
        }

        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperado um ';'.");
            return new SymbolTableEntry();
        }

        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //revisado por pedro

        first.add(TokenClass.KEYWORD_CONSTANTES);
    }

    @Override
    protected void populateFollow() {
        //revisado por pedro

        //from declarations.follow
        follow.add(TokenClass.KEYWORD_FUNCAO);

        //from tipodefinido.first
        follow.add(TokenClass.KEYWORD_TIPO);

        //from variaveis.first
        follow.add(TokenClass.KEYWORD_VARIAVEIS);

    }
}
