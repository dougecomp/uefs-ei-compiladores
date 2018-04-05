package productions;

import exceptions.ParserException;
import exceptions.SemanticException;
import lexico.ErrorList;
import lexico.Token;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SemanticStructure;
import semantico.SemanticType;
import semantico.SymbolTable;
import semantico.SymbolTableEntry;

/**
 *
 * @author Salomao e Pedro
 */
public class LeiaProduction extends AbstractProductionRule {
    

    private static LeiaProduction instance = new LeiaProduction();

    public static LeiaProduction getInstance() {
        return instance;
    }

    /**
     * Regra: leia        :    'leia' '(' IDENTIFIER (',' IDENTIFIER)* ')' ';';
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {

        SymbolTableEntry t = null;
        Token temp = null;

        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_LEIA)) {
            tl.consumeCurrent();
        }
        else{
            return skipUntilSynchronize(tl, el, "Esperada palavra reservada 'leia'.");
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)) {
            tl.consumeCurrent();
        }
        else{
            return skipUntilSynchronize(tl, el, "Esperada um '('.");
        }
        
        
        
        if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)) {
            temp = tl.consumeCurrent();
            verify(temp, el);
        }
        else{
            return skipUntilSynchronize(tl, el, "Esperado um identificador.");

        }
        
          
        while (testEofAndCurrentToken(tl, TokenClass.DELIMITER_COMMA)){
            tl.consumeCurrent();   
            if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)) {
                temp = tl.consumeCurrent();
                verify(temp, el);
            }
            else{
                return skipUntilSynchronize(tl, el, "Esperado um identificador.");
            }
        }
        
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada um ')'.");
        }
        
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada um ';'.");
            return new SymbolTableEntry();
        }
           

        return new SymbolTableEntry();
    }

    private void verify(Token t, ErrorList el){
        
        SymbolTableEntry temp = null;
        
        temp = SymbolTable.getInstance().search(t);

        if(temp == null){
            SymbolTable.getInstance().add(
                    new SymbolTableEntry(SemanticType.TYPE_STRING, SemanticStructure.VARIABLE, t, -1));
            el.add(new SemanticException("Variável não declarada", t.getLine(), t.getPosition()));
            return;
        }

        boolean ok = (temp.getStructure() == SemanticStructure.VETOR);
        if(ok){
            el.add(new SemanticException("Tipo de variável incompatível", t.getLine(), t.getPosition()));
        }
    }

    @Override
    protected void populateFirst() {
        //Revisado por Ronaldo
        first.add(TokenClass.KEYWORD_LEIA);
    }

    @Override
    protected void populateFollow() {
        //Revisado por Ronaldo
        //from comandos follow
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }
}
