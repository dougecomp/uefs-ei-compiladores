package productions;

import exceptions.ParserException;
import java.util.ArrayList;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SemanticType;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class VariaveisProduction extends AbstractProductionRule {

    private static VariaveisProduction instance = new VariaveisProduction();

    public static VariaveisProduction getInstance() {
        return instance;
    }

    /**
     * Regra: variaveis : 'variaveis' dclr_var_init (',' dclr_var_init)*;
     * @return
     * @throws ParserException
     */
      // variaveis inteiro a (=10)?


    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {        

        ArrayList<SymbolTableEntry> ste = new ArrayList<SymbolTableEntry>();

        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_VARIAVEIS)){
            
            tl.consumeCurrent();

        }
        else{
            return skipUntilSynchronize(tl, el, "Esperada a palavra reservada 'variaveis'.");
        }

        
        if (DclrVarInitProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ste.add(DclrVarInitProduction.getInstance().analyze(tl, el));
        }
        else{
            skipUntilSynchronize(tl, el, "Erro na declaração.");
            return new SymbolTableEntry();
        }
        
        while (testEofAndCurrentToken(tl, TokenClass.DELIMITER_COMMA)){
            tl.consumeCurrent();
            
            if (DclrVarInitProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                ste.add(DclrVarInitProduction.getInstance().analyze(tl, el));
            }
            else{
                skipUntilSynchronize(tl, el, "Erro na declaração.");
                return new SymbolTableEntry();
            }            
        }
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //revisado por bittencourt
        first.add(TokenClass.KEYWORD_VARIAVEIS);
    }

    @Override
    protected void populateFollow() {
        //revisado por bittencourt
        follow.add(TokenClass.DELIMITER_SEMICOLON);
    }
}
