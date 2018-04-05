package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.Token;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SemanticType;
import semantico.SymbolTableEntry;
import semantico.TypeTable;

/**
 * @author heekinho
 */
public class ExprRelProduction extends AbstractProductionRule {
    

    private static ExprRelProduction instance = new ExprRelProduction();

    public static ExprRelProduction getInstance() {
        return instance;
    }

    /**
     * Regra: expr_rel :  expr_arit (op_relacional expr_arit)?;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {

        SymbolTableEntry steA;// = new SymbolTableEntry();
        SymbolTableEntry steB; //= new SymbolTableEntry();
        SymbolTableEntry steC = new SymbolTableEntry();
        Token tk;

        if (ExprAritProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            steA = ExprAritProduction.getInstance().analyze(tl, el);
            //steA.setType(ExprAritProduction.getInstance().analyze(tl, el).getType());

        }
        else{
            skipUntilSynchronize(tl, el, "Era esperada uma expressão.");
            return new SymbolTableEntry();
        }

        //opcional
        if (!tl.eof() && OpRelacionalProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            tk = tl.getCurrent();
            OpRelacionalProduction.getInstance().analyze(tl, el);


            if (!tl.eof() && ExprAritProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
               steB = ExprAritProduction.getInstance().analyze(tl, el);
               //steB.setType(ExprAritProduction.getInstance().analyze(tl, el).getType());

                if ((steA.getType() == SemanticType.TYPE_LOGICO) && (steB.getType() == SemanticType.TYPE_LOGICO) && !((tk.getTokenClass() == TokenClass.OPERATOR_EQUAL) || tk.getTokenClass() == TokenClass.OPERATOR_DIFFERENT)) {
                    el.addSemanticError("Não é possível realizar esta operação");
                } //              
                else if ((steA.getType() == SemanticType.TYPE_LOGICO) && (steB.getType() != SemanticType.TYPE_LOGICO)) {
                    el.addSemanticError("Não é possível realizar esta operação");
                } else if ((steA.getType() != SemanticType.TYPE_LOGICO) && (steB.getType() == SemanticType.TYPE_LOGICO)) {
                    el.addSemanticError("Não é possível realizar esta operação");
                } else if (((steA.getType() != SemanticType.TYPE_INDEFINIDO) || (steB.getType() != SemanticType.TYPE_INDEFINIDO)) && (TypeTable.typeOperationAWithB(steA, steB) == SemanticType.TYPE_INDEFINIDO)) {
                    el.addSemanticError("Tipos incompatíveis.");
                }

                steC.setType(SemanticType.TYPE_LOGICO);
                return steC;

            } else {
                skipUntilSynchronize(tl, el, "Era esperada uma expressão.");
                return new SymbolTableEntry();
            }

        }
        
       return steA;
    }

    @Override
    protected void populateFirst() {
        //Revisado por André & Salomão
        //from exprarit first
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
        follow.add(TokenClass.OPERATOR_AND);

        //from exprand follow
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.OPERATOR_OR);
    }
}
