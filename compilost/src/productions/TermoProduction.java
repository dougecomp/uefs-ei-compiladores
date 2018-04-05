package productions;

import lexico.TokenList;
import exceptions.ParserException;
import exceptions.SemanticException;
import lexico.ErrorList;
import lexico.TokenClass;
import semantico.SemanticStructure;
import semantico.SymbolTable;
import semantico.SymbolTableEntry;
import semantico.TypeTable;

/**
 * @author maocleal
 * @author caco
 */
public class TermoProduction extends AbstractProductionRule {

    private static TermoProduction instance = new TermoProduction();

    public static TermoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: termo  :  fator (op_mult fator)*;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {

        SymbolTableEntry a = new SymbolTableEntry();
        SymbolTableEntry b = new SymbolTableEntry();

        if (FatorProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            a = FatorProduction.getInstance().analyze(tl, el);
        }
        else{
            return skipUntilSynchronize(tl, el, "Esperado um fator.");
        }

        while (!tl.eof() && OpMultProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            OpMultProduction.getInstance().analyze(tl, el);
            if (!tl.eof() &&  FatorProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                b = FatorProduction.getInstance().analyze(tl, el);
                a = verify(a, b, el);
            }
            else{
                return skipUntilSynchronize(tl, el, "Esperado uma fator.");
            }
        }

        return a;
    }


    private SymbolTableEntry verify(SymbolTableEntry t1, SymbolTableEntry t2, ErrorList el){

        if( TypeTable.canAReceiveB(t1.getType(), t2.getType()) ){
            return new SymbolTableEntry(t1.getType(),
                    SemanticStructure.VALOR, null, SymbolTable.getInstance().getScope());
        }

        if( TypeTable.canAReceiveB(t2.getType(), t1.getType()) ){
            return new SymbolTableEntry(t2.getType(),
                    SemanticStructure.VALOR, null, SymbolTable.getInstance().getScope());
        }

        el.add(new SemanticException("Expressão aritmética incompátivel",
                        t1.getToken().getLine(), t1.getToken().getPosition()));
        return new SymbolTableEntry();

    }

    @Override
    protected void populateFirst() {
        // revisado por Ronaldo
        //from fator first
        first.add(TokenClass.OPERATOR_NOT); //
        first.add(TokenClass.DELIMITER_OPENING_PARENTHESIS); //
        first.add(TokenClass.NUMERIC); //
        first.add(TokenClass.NUMERIC_REAL); //
        first.add(TokenClass.KEYWORD_VERDADEIRO); //
        first.add(TokenClass.KEYWORD_FALSO); //
        first.add(TokenClass.IDENTIFIER); //
        first.add(TokenClass.OPERATOR_PLUS); //
        first.add(TokenClass.OPERATOR_MINUS); //
        first.add(TokenClass.OPERATOR_INCREMENT); //
        first.add(TokenClass.OPERATOR_DECREMENT); //
        first.add(TokenClass.KEYWORD_CAST); //
    }

    @Override
    protected void populateFollow() {
        // Revisado por Ronaldo
        //from exprarit follow
        follow.add(TokenClass.DELIMITER_CLOSING_SQUARE_BRACKET);//
        follow.add(TokenClass.OPERATOR_LESS); //
        follow.add(TokenClass.OPERATOR_LESS_EQUAL);//
        follow.add(TokenClass.OPERATOR_MORE);//
        follow.add(TokenClass.OPERATOR_MORE_EQUAL);//
        follow.add(TokenClass.OPERATOR_DIFFERENT);//
        follow.add(TokenClass.OPERATOR_EQUAL);//
        follow.add(TokenClass.OPERATOR_AND);//
        follow.add(TokenClass.DELIMITER_COMMA); //
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);//
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);//
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);//
        follow.add(TokenClass.DELIMITER_SEMICOLON);//
        follow.add(TokenClass.OPERATOR_OR); //

        //from opsoma first
        this.follow.add(TokenClass.OPERATOR_PLUS); //
        this.follow.add(TokenClass.OPERATOR_MINUS); //
    }
}
