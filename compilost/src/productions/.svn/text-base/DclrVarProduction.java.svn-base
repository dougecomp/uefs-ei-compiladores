package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 * @author caco
 */
public class DclrVarProduction extends AbstractProductionRule {
    

    private static DclrVarProduction instance = new DclrVarProduction();

    public static DclrVarProduction getInstance() {
        return instance;
    }

    /**
     * Regra: dclr_var : tipo_variavel IDENTIFIER array_d*;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {
        
        if (TipoVariavelProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            
            TipoVariavelProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada uma declaração de variável.");
        }
        
        if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada uma declaração de variável.");
        }
        
        
        while (ArrayDProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ArrayDProduction.getInstance().analyze(tl, el);
        }

        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        first.add(TokenClass.IDENTIFIER);
        first.add(TokenClass.KEYWORD_INTEIRO);
        first.add(TokenClass.KEYWORD_REAL);
        first.add(TokenClass.KEYWORD_LOGICO);
        first.add(TokenClass.KEYWORD_CARACTERE);
        first.add(TokenClass.KEYWORD_STRING);
        
    }

    @Override
    protected void populateFollow() {
        follow.add(TokenClass.OPERATOR_ASSIGNMENT);
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);

        //from declrvarinit.follow
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);

        //from args.follow
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);

    }
}
