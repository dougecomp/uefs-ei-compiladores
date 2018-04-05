package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SemanticType;
import semantico.SymbolTableEntry;
import semantico.TypeTable;

/**
 * @author heekinho
 */
public class ExprAritProduction extends AbstractProductionRule {

    private static ExprAritProduction instance = new ExprAritProduction();

    public static ExprAritProduction getInstance() {
        return instance;
    }

    /**
     * Regra: expr_arit : termo (op_soma termo)*;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {

        SymbolTableEntry a = new SymbolTableEntry();

        if (TermoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            a = TermoProduction.getInstance().analyze(tl, el);
        } else {
            return skipUntilSynchronize(tl, el, "Era esperado um termo.");
        }

        while (!tl.eof() && OpSomaProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            OpSomaProduction.getInstance().analyze(tl, el);

            if (!tl.eof() && TermoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                SymbolTableEntry b = TermoProduction.getInstance().analyze(tl, el);

                if ((a.getType() != SemanticType.TYPE_INDEFINIDO) && (TypeTable.typeOperationAWithB(a, b) == SemanticType.TYPE_INDEFINIDO)) {
                    el.addSemanticError("Tipos incompatíveis.");
                } 
                //tipos compativeis
                else {
                    a.setType(TypeTable.typeOperationAWithB(a, b));
                }

            } else {
                return skipUntilSynchronize(tl, el, "Era esperado um termo.");
            }
        }

        return a;
    }

    @Override
    protected void populateFirst() {
        //Revisado por André & Salomão
        //from termo first
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



        // revisado por salomao e bené
        //from oprelacional first
        this.follow.add(TokenClass.OPERATOR_LESS);
        this.follow.add(TokenClass.OPERATOR_LESS_EQUAL);
        this.follow.add(TokenClass.OPERATOR_MORE);
        this.follow.add(TokenClass.OPERATOR_MORE_EQUAL);
        this.follow.add(TokenClass.OPERATOR_DIFFERENT);
        this.follow.add(TokenClass.OPERATOR_EQUAL);

        //from exprrel follow

        //revisado por salomão 
        follow.add(TokenClass.OPERATOR_AND);
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
        follow.add(TokenClass.OPERATOR_OR);
    }
}
