/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SemanticType;
import semantico.SymbolTableEntry;
import semantico.TypeTable;

/**
 *
 * @author Marcone
 * @author caco
 */
public class ExprAndProduction extends AbstractProductionRule {

    private static ExprAndProduction instance = new ExprAndProduction();

    public static ExprAndProduction getInstance() {
        return instance;
    }

    /**
     * Regra: expr_and : expr_rel ('&&' expr_rel)*;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {
        SymbolTableEntry a = new SymbolTableEntry();
        SymbolTableEntry b = new SymbolTableEntry();
        
        if (ExprRelProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            a = ExprRelProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada uma expressão relacional");
        }

        while (testEofAndCurrentToken(tl, TokenClass.OPERATOR_AND)){
            tl.consumeCurrent();
            
            if (ExprRelProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                b = ExprRelProduction.getInstance().analyze(tl, el);

                SymbolTableEntry ste = new SymbolTableEntry();

                if(TypeTable.matchType(a, b, SemanticType.TYPE_LOGICO)){
                    ste.setType(TypeTable.typeOperationAWithB(a, b));
                }
                 if ( !TypeTable.anyIsUndefined(a, b) ){
                    el.addSemanticError("Era esperado um tipo lógico.");
                }

                return ste;


            }
            else{
                return skipUntilSynchronize(tl, el, "Era esperada uma expressão.");
            }
            
            
        }

        return a;
    }

    @Override
    protected void populateFirst() {
        //Revisado por André & Salomao
        //from exprrel first
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
        //from expr follow
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);

        follow.add(TokenClass.OPERATOR_OR);
    }
}
