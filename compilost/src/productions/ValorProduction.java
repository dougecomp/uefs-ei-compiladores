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
public class ValorProduction extends AbstractProductionRule {

    private static ValorProduction instance = new ValorProduction();

    public static ValorProduction getInstance() {
        return instance;
    }

    /**
     * Regra: valor : (expr | 'STRING' | 'CHARACTER');
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {
        
        if (testEofAndCurrentToken(tl, TokenClass.STRING) || testEofAndCurrentToken(tl, TokenClass.CARACTERE)){
            TokenClass tc = tl.consumeCurrent().getTokenClass();
            SymbolTableEntry ste = new SymbolTableEntry();
            ste.setType(TypeTable.getTypeFromTokenClass(tc));
            return ste;
        }
        
        if (ExprProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            return ExprProduction.getInstance().analyze(tl, el);
        }
        
        return skipUntilSynchronize(tl, el, "Era esperado um valor.");
    }

    @Override
    protected void populateFirst() {
        //from expr first
        first.add(TokenClass.OPERATOR_NOT);
        first.add(TokenClass.DELIMITER_OPENING_PARENTHESIS);
        first.add(TokenClass.NUMERIC);
        first.add(TokenClass.NUMERIC_REAL);
        first.add(TokenClass.KEYWORD_VERDADEIRO);
        first.add(TokenClass.KEYWORD_FALSO);
        first.add(TokenClass.IDENTIFIER);
        first.add(TokenClass.OPERATOR_PLUS);
        first.add(TokenClass.OPERATOR_MINUS);
        first.add(TokenClass.OPERATOR_INCREMENT);
        first.add(TokenClass.OPERATOR_DECREMENT);
        first.add(TokenClass.KEYWORD_CAST);
        first.add(TokenClass.STRING);
        first.add(TokenClass.CARACTERE);
    }

    @Override
    protected void populateFollow() {
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);

        //from params follow
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);

        //from sentenca follow
        follow.add(TokenClass.DELIMITER_SEMICOLON);

        //from inits follow
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
    }
}
