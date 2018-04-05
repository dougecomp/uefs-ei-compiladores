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
public class ArgsProduction extends AbstractProductionRule {
    

    private static ArgsProduction instance = new ArgsProduction();

    public static ArgsProduction getInstance() {
        return instance;
    }

    /**
     * Regra: args : dclr_var (',' dclr_var)*;
     * @return
     * @throws ParserException
     * 
     * TODO:
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el){

        if (!tl.eof() && DclrVarProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            DclrVarProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Erro na passagem de parametros");
            return new SymbolTableEntry();
        }

        while ( testEofAndCurrentToken(tl, TokenClass.DELIMITER_COMMA)) {
            tl.consumeCurrent();
            if (!tl.eof() && DclrVarProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                DclrVarProduction.getInstance().analyze(tl, el);
            }
            else{
                skipUntilSynchronize(tl, el, "Esperada uma varável.");
                return new SymbolTableEntry();
            }
        }

        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {

        //from declrvar.first
        //revisado por Douglas
        first.add(TokenClass.IDENTIFIER);
        first.add(TokenClass.KEYWORD_INTEIRO);
        first.add(TokenClass.KEYWORD_REAL);
        first.add(TokenClass.KEYWORD_LOGICO);
        first.add(TokenClass.KEYWORD_CARACTERE);
        first.add(TokenClass.KEYWORD_STRING);
    }

    @Override
    protected void populateFollow() {
        //Revisado por Douglas
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
    }
}
