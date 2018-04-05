package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class InitArrayProduction extends AbstractProductionRule {

    private static InitArrayProduction instance = new InitArrayProduction();

    public static InitArrayProduction getInstance() {
        return instance;
    }

    /**
     * Regra: init_array : '{' params }';
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_CURLY_BRACKET)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado '{'.");
            return new SymbolTableEntry();
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_CURLY_BRACKET)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Inicialização de array esperada.");
            return new SymbolTableEntry();
        }
        
        
        
        if (ParamsProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ParamsProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Eram esperados parâmetros.");
            return new SymbolTableEntry();
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_CURLY_BRACKET)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado '}'.");
            return new SymbolTableEntry();
        }
        
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        first.add(TokenClass.DELIMITER_OPENING_CURLY_BRACKET);
    }

    @Override
    protected void populateFollow() {
        follow = InitsProduction.getInstance().follow;
    }
}
