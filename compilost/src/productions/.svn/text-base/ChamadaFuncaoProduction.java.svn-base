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
public class ChamadaFuncaoProduction extends AbstractProductionRule {
    

    private static ChamadaFuncaoProduction instance = new ChamadaFuncaoProduction();

    public static ChamadaFuncaoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: chamada_funcao : '(' params? ')';
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)){
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um '('");
            return new SymbolTableEntry();
        }
        
        //opcional
        if (ParamsProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            ParamsProduction.getInstance().analyze(tl, el);
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)){
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um ')'");
            return new SymbolTableEntry();
        }
             
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //revisado por caco
        first.add(TokenClass.DELIMITER_OPENING_PARENTHESIS);
    }

    @Override
    protected void populateFollow() {
        //revisado por caco
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_OPENING_SQUARE_BRACKET);
        follow.add(TokenClass.OPERATOR_INCREMENT);
        follow.add(TokenClass.OPERATOR_DECREMENT);

    }
}
