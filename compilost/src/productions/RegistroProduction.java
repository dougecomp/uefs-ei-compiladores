package productions;

import exceptions.ParserException;
import lexico.*;
import lexico.TokenClass;
import lexico.TokenList;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class RegistroProduction extends AbstractProductionRule {
    

    private static RegistroProduction instance = new RegistroProduction();

    public static RegistroProduction getInstance() {
        return instance;
    }

    /**
     * Regra: registro : 'registro' '{' (dclr_var ';')+ '}'
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {


        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_REGISTRO)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada palavra reservada 'registro' ");
            return new SymbolTableEntry();
        }



        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_CURLY_BRACKET)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado um '{' ");
            return new SymbolTableEntry();
        }

        //pelo menos um
        if (DclrVarProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            while (DclrVarProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                DclrVarProduction.getInstance().analyze(tl, el);
                if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
                    tl.consumeCurrent();
                } else {
                    skipUntilSynchronize(tl, el, "Esperado um ';' ");
                    return new SymbolTableEntry();
                }
            }
        } else {
            skipUntilSynchronize(tl, el, "Esperada pelo menos uma declaração de campo do registro");
            return new SymbolTableEntry();
        }


        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_CURLY_BRACKET)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado um '}' ");
            return new SymbolTableEntry();
        }

        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //revisado por pedro
        first.add(TokenClass.KEYWORD_REGISTRO);
    }

    @Override
    protected void populateFollow() {
        //revisado por pedro
        follow.add(TokenClass.IDENTIFIER);
    }
}
