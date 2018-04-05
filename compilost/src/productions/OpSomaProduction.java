package productions;

import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author pedro
 * @author heekinho
 */
public class OpSomaProduction extends AbstractProductionRule {

    private static OpSomaProduction instance = new OpSomaProduction();

    public static OpSomaProduction getInstance() {
        return instance;
    }

    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        if (!tl.eof() && isFirst(tl.getCurrent().getTokenClass())){
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um operador de soma/subtração.");
        }
        return new SymbolTableEntry();
        
    }

    @Override
    protected void populateFirst() {
        this.first.add(TokenClass.OPERATOR_PLUS);
        this.first.add(TokenClass.OPERATOR_MINUS);
    }

    @Override
    protected void populateFollow() {
        //from opinc first
        follow.add(TokenClass.OPERATOR_INCREMENT);
        follow.add(TokenClass.OPERATOR_DECREMENT);

        //from termo first
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

        //from cast first
        follow.add(TokenClass.KEYWORD_CAST);

        follow.add(TokenClass.DELIMITER_OPENING_PARENTHESIS);
        follow.add(TokenClass.NUMERIC);
        follow.add(TokenClass.NUMERIC_REAL);
        follow.add(TokenClass.KEYWORD_VERDADEIRO);
        follow.add(TokenClass.KEYWORD_FALSO);
        follow.add(TokenClass.IDENTIFIER);
    }
}
