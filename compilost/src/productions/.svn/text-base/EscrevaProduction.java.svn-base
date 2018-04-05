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
public class EscrevaProduction extends AbstractProductionRule {
    

    private static EscrevaProduction instance = new EscrevaProduction();

    public static EscrevaProduction getInstance() {
        return instance;
    }

    /**
     * Regra: escreva : 'escreva' '(' params ')' ';';
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el){

        
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_ESCREVA)) {
            tl.consumeCurrent();
        }
        else{
            
            return skipUntilSynchronize(tl, el, "Esperada palavra reservada 'escreva'.");
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)) {
            tl.consumeCurrent();
        }
        else{
            
            return skipUntilSynchronize(tl, el, "Era esperado um '('");
        }
        
        
        
        if (ParamsProduction.getInstance().isFirst(tl.getCurrent().getTokenClass()) ) {
            ParamsProduction.getInstance().analyze(tl, el);
        }
        else{

            return skipUntilSynchronize(tl, el, "Era esperado um parametro");
          
        }
        
        
       
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)) {
            tl.consumeCurrent();
        }
        else{
            
            return skipUntilSynchronize(tl, el, "Era esperado um ')'");
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
            tl.consumeCurrent();
        }
        else{
            
            return skipUntilSynchronize(tl, el, "Era esperado um ';'");
        }
       

        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //revisado por bittencourt
        first.add(TokenClass.KEYWORD_ESCREVA);
    }

    @Override
    protected void populateFollow() {
        //revisado por bittencourt
        //from comandos follow
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }
}
