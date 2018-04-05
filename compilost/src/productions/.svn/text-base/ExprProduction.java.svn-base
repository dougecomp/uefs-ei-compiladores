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
public class ExprProduction extends AbstractProductionRule {
    

    private static ExprProduction instance = new ExprProduction();

    public static ExprProduction getInstance() {
        return instance;
    }

    /**
     * Regra: expr : expr_and ('||' expr_and)*;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        //possiveis parcelas
        SymbolTableEntry a = new SymbolTableEntry();
        SymbolTableEntry b = new SymbolTableEntry();
        
        if (ExprAndProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            a = ExprAndProduction.getInstance().analyze(tl, el);
        }
        else{
            return skipUntilSynchronize(tl, el, "Era esperada uma expressão.");
        }

        
        //todo no caso de uma operação
        while (testEofAndCurrentToken(tl, TokenClass.OPERATOR_OR) ) {
            
            tl.consumeCurrent();
            
            if (!tl.eof() && ExprAndProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                //tipo da operacao do primeiro com o segundo elemento
                b = ExprAndProduction.getInstance().analyze(tl, el);
                
                //default é tipo indefinido
                SymbolTableEntry ret = new SymbolTableEntry();
                
                //sao logicos
                if ( TypeTable.matchType(a, b, SemanticType.TYPE_LOGICO) ){
                    ret.setType(TypeTable.typeOperationAWithB(a, b));
                }
                //nao sao indefinidos
                if ( !TypeTable.anyIsUndefined(a, b) ){
                    el.addSemanticError("Era esperado um tipo lógico.");
                }
                
                return ret;
                
                
            }
            else{
                return skipUntilSynchronize(tl, el, "Era esperada uma expressão.");
            }
        }
        
        //caso só tenha um elemento
        return a;
    }

    @Override
    protected void populateFirst() {
        //Revisado por André & Salomao
        //from exprand first
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
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);

        //from valor follow
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
    }
}
