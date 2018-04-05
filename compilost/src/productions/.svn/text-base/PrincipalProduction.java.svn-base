package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class PrincipalProduction extends AbstractProductionRule {
    

    private static PrincipalProduction instance = new PrincipalProduction();

    public static PrincipalProduction getInstance() {
        return instance;
    }

    /**
     * Regra: principal: 'funcao' 'vazio' 'principal' '(' ')' bloco;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {


        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_FUNCAO)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada palavra reservada 'funcao' ");
            return new SymbolTableEntry();
        }


        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_VAZIO)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada palavra reservada 'vazio' ");
            return new SymbolTableEntry();
        }
        
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_PRINCIPAL)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada palavra reservada 'principal' ");
            return new SymbolTableEntry();
        }
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada  um '('.");
            return new SymbolTableEntry();
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada  um ')'.");
            return new SymbolTableEntry();
        }
        
        
        if (BlocoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            BlocoProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada  um ')'.");
            return new SymbolTableEntry();
        }

        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        first.add(TokenClass.KEYWORD_FUNCAO);
    }

    @Override
    protected void populateFollow() {
        //from funcao
        follow.add(TokenClass.KEYWORD_FUNCAO);

        //from variaveis
        follow.add(TokenClass.KEYWORD_VARIAVEIS);
    }
}
