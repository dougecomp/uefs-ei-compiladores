package productions;

import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author André
 * @author heekinho
 */
public class OpMultProduction extends AbstractProductionRule {

    private static OpMultProduction instance = new OpMultProduction();

    public static OpMultProduction getInstance() {
        return instance;
    }

    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        if (!tl.eof() &&  isFirst(tl.getCurrent().getTokenClass()) ) {
            tl.consumeCurrent();
        } else {
            return skipUntilSynchronize(tl, el, "Esperado um '*' ou '/'.");
        }
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        this.first.add(TokenClass.OPERATOR_MULT);
        this.first.add(TokenClass.OPERATOR_DIVISION);
    }

    @Override
    protected void populateFollow() {
        //from fator first
        follow.add(TokenClass.OPERATOR_NOT);
        follow.add(TokenClass.DELIMITER_OPENING_PARENTHESIS);
        follow.add(TokenClass.NUMERIC);
        follow.add(TokenClass.NUMERIC_REAL);
        follow.add(TokenClass.KEYWORD_VERDADEIRO);
        follow.add(TokenClass.KEYWORD_FALSO);
        follow.add(TokenClass.IDENTIFIER);
        this.follow.add(TokenClass.OPERATOR_PLUS);
        this.follow.add(TokenClass.OPERATOR_MINUS);
        follow.add(TokenClass.OPERATOR_INCREMENT);
        follow.add(TokenClass.OPERATOR_DECREMENT);
        this.follow.add(TokenClass.KEYWORD_CAST);
    }
}
