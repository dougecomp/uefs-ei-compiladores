package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class ParamsProduction extends AbstractProductionRule {
    

    private static ParamsProduction instance = new ParamsProduction();

    public static ParamsProduction getInstance() {
        return instance;
    }

    /**
     * Regra: params : valor (',' valor)*;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        
        if (ValorProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ValorProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um valor.");
            return new SymbolTableEntry();
        }
        
        
        while (testEofAndCurrentToken(tl, TokenClass.DELIMITER_COMMA)){
            tl.consumeCurrent();
            
            if (ValorProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
                ValorProduction.getInstance().analyze(tl, el);
            }
            else{
                skipUntilSynchronize(tl, el, "Erro na passagem de parametros");
                return new SymbolTableEntry();
            }
        }
        
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //Revisado por André & Salomão
        //from valor first
        first.add(TokenClass.KEYWORD_STRING);
        first.add(TokenClass.KEYWORD_CARACTERE);
        //from expr first --> expr_and first --> exp_rel first --> exp_arit first --> termo first --> fator first
        first.add(TokenClass.OPERATOR_NOT);
        first.add(TokenClass.DELIMITER_OPENING_PARENTHESIS);
        first.add(TokenClass.NUMERIC);
        first.add(TokenClass.NUMERIC_REAL);
        first.add(TokenClass.KEYWORD_VERDADEIRO);
        first.add(TokenClass.KEYWORD_FALSO);
        first.add(TokenClass.IDENTIFIER);
        this.first.add(TokenClass.OPERATOR_PLUS);
        this.first.add(TokenClass.OPERATOR_MINUS);
        first.add(TokenClass.OPERATOR_INCREMENT);
        first.add(TokenClass.OPERATOR_DECREMENT);
        this.first.add(TokenClass.KEYWORD_CAST);
    }

    @Override
    protected void populateFollow() {
        //Revisado por André
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
    }
}
