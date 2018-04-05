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
public class AlgoritmoProduction extends AbstractProductionRule {
    

    private static AlgoritmoProduction instance = new AlgoritmoProduction();

    public static AlgoritmoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: algoritmo: 'algoritmo' IDENTIFIER ';'
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {

        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_ALGORITMO)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperada palavra 'algoritmo'.");
            return new SymbolTableEntry();
        }


        if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperado um identificador.");
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
        //revisado por caco
        this.first.add(TokenClass.KEYWORD_ALGORITMO);
    }

    @Override
    protected void populateFollow() {
        //revisado por caco

        //from  declarations.first
        follow.add(TokenClass.KEYWORD_CONSTANTES);
        follow.add(TokenClass.KEYWORD_TIPO);
        follow.add(TokenClass.KEYWORD_VARIAVEIS);

        //from principal.first
        this.follow.add(TokenClass.KEYWORD_FUNCAO);
    }
}
