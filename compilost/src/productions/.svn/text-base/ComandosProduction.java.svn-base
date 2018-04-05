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
public class ComandosProduction extends AbstractProductionRule {
    

    private static ComandosProduction instance = new ComandosProduction();

    public static ComandosProduction getInstance() {
        return instance;
    }

    /**
     * Regra: comandos : se | enquanto | para | escreva | leia | retorno;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {

        if (SeProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            
            return SeProduction.getInstance().analyze(tl, el);
            
        }

        if (EnquantoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {

            return EnquantoProduction.getInstance().analyze(tl, el);
            
        }

        if (ParaProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            
            return ParaProduction.getInstance().analyze(tl, el);
        }


        if (EscrevaProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            
            return EscrevaProduction.getInstance().analyze(tl, el);
        }

        if (LeiaProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            
            return LeiaProduction.getInstance().analyze(tl, el);
            
        }
        
        if (RetornoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            
            return RetornoProduction.getInstance().analyze(tl, el);
        }
        
        
        return skipUntilSynchronize(tl, el, "Esperado um comando se, enquanto, para, escreva, leia ou retorno");
    }

    @Override
    protected void populateFirst() {
        //revisado por bittencourt
        first.add(TokenClass.KEYWORD_SE);
        first.add(TokenClass.KEYWORD_PARA);
        first.add(TokenClass.KEYWORD_ENQUANTO);
        first.add(TokenClass.KEYWORD_LEIA);
        first.add(TokenClass.KEYWORD_ESCREVA);
        first.add(TokenClass.KEYWORD_RETORNO);

    }

    @Override
    protected void populateFollow() {
        //revisado por bittencourt    
        //from instrucao follow
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }
}
