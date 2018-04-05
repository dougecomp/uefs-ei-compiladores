package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class InitsProduction extends AbstractProductionRule {
    

    private static InitsProduction instance = new InitsProduction();

    public static InitsProduction getInstance() {
        return instance;
    }

    /**
     * Regra: inits : (valor | init_array);
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {
        
        if (ValorProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            return ValorProduction.getInstance().analyze(tl, el);
        }
        
        if (InitArrayProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            InitArrayProduction.getInstance().analyze(tl, el);
            return new SymbolTableEntry();
        }
        
        skipUntilSynchronize(tl, el, "Esperado um valor ou uma inicialização de array.");
        return new SymbolTableEntry();        
    }

    @Override
    protected void populateFirst() {
        //from valor first
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
        first.add(TokenClass.STRING);
        first.add(TokenClass.CARACTERE);

        //from initarray
        first.add(TokenClass.DELIMITER_OPENING_CURLY_BRACKET);
    }

    @Override
    protected void populateFollow() {
        //from dclrvarinit follow
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
    }
}
