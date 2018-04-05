package productions;

import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author Douglas
 * @author heekinho
 */
public class OpRelacionalProduction extends AbstractProductionRule {

    private static OpRelacionalProduction instance = new OpRelacionalProduction();

    public static OpRelacionalProduction getInstance() {
        return instance;
    }

    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        if (!tl.eof() &&  isFirst(tl.getCurrent().getTokenClass()) ) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado um operador relacional.");
        }
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //Revisado por Ronaldo
        this.first.add(TokenClass.OPERATOR_LESS); //
        this.first.add(TokenClass.OPERATOR_LESS_EQUAL);//
        this.first.add(TokenClass.OPERATOR_MORE);//
        this.first.add(TokenClass.OPERATOR_MORE_EQUAL);//
        this.first.add(TokenClass.OPERATOR_DIFFERENT);//
        this.first.add(TokenClass.OPERATOR_EQUAL);//
    }

    @Override
    protected void populateFollow() {
        //Revisado por Ronaldo
        //from exprarit first
        follow.add(TokenClass.OPERATOR_NOT); //
        follow.add(TokenClass.DELIMITER_OPENING_PARENTHESIS); //
        follow.add(TokenClass.NUMERIC); //
        follow.add(TokenClass.NUMERIC_REAL); //
        follow.add(TokenClass.KEYWORD_VERDADEIRO); //
        follow.add(TokenClass.KEYWORD_FALSO); //
        follow.add(TokenClass.IDENTIFIER); //
        follow.add(TokenClass.OPERATOR_PLUS); //
        follow.add(TokenClass.OPERATOR_MINUS); //
        follow.add(TokenClass.OPERATOR_INCREMENT); //
        follow.add(TokenClass.OPERATOR_DECREMENT); //
        follow.add(TokenClass.KEYWORD_CAST); //
    }
}
