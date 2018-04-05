package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class SeProduction extends AbstractProductionRule {
    

    private static SeProduction instance = new SeProduction();

    public static SeProduction getInstance() {
        return instance;
    }

    /**
     * Regra: se : 'se' '(' expr ')' 'entao' bloco ('senao' bloco)?;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        
        
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_SE)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada palavra reservada 'se' ");
            return new SymbolTableEntry();
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado um '(' ");
            return new SymbolTableEntry();
        }
        
        
        if (ExprProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ExprProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado uma expressão");
            return new SymbolTableEntry();
        }
        
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado um ')' ");
            return new SymbolTableEntry();
        }
        
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_ENTAO)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada a palavra reservada 'entao'.");
            return new SymbolTableEntry();
        }
        
        
        if (BlocoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            BlocoProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um '{'");
            return new SymbolTableEntry();
        }

        
        //opcional
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_SENAO)) {
            tl.consumeCurrent();
        
            if (BlocoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                BlocoProduction.getInstance().analyze(tl, el);
            }
            else{
                skipUntilSynchronize(tl, el, "Era esperado um '{'");
                return new SymbolTableEntry();
            }
        }
        
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //revisado por Douglas
        first.add(TokenClass.KEYWORD_SE);
    }

    @Override
    protected void populateFollow() {
        //revisado por Douglas
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }
}
