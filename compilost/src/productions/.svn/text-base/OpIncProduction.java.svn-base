package productions;

import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author pedro
 * @author heekinho
 * @author caco
 */
public class OpIncProduction extends AbstractProductionRule {

    private static OpIncProduction instance = new OpIncProduction();

    public static OpIncProduction getInstance() {
        return instance;
    }

    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        if (!tl.eof() &&  isFirst(tl.getCurrent().getTokenClass()) ) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado um '++' ou '--'.");
        }
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        first.add(TokenClass.OPERATOR_INCREMENT);
        first.add(TokenClass.OPERATOR_DECREMENT);
    }

    @Override
    protected void populateFollow() {
        follow.add(TokenClass.IDENTIFIER);
        follow.addAll(first);

        //from fator follow
        this.follow.add(TokenClass.OPERATOR_MULT);
        this.follow.add(TokenClass.OPERATOR_DIVISION);
        follow.add(TokenClass.DELIMITER_CLOSING_SQUARE_BRACKET);
        this.follow.add(TokenClass.OPERATOR_LESS);
        this.follow.add(TokenClass.OPERATOR_LESS_EQUAL);
        this.follow.add(TokenClass.OPERATOR_MORE);
        this.follow.add(TokenClass.OPERATOR_MORE_EQUAL);
        this.follow.add(TokenClass.OPERATOR_DIFFERENT);
        this.follow.add(TokenClass.OPERATOR_EQUAL);
        follow.add(TokenClass.OPERATOR_AND);
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.OPERATOR_OR);
        this.follow.add(TokenClass.OPERATOR_PLUS);
        this.follow.add(TokenClass.OPERATOR_MINUS);

        //from sentenca follow
        follow.add(TokenClass.DELIMITER_SEMICOLON);
    }
}
