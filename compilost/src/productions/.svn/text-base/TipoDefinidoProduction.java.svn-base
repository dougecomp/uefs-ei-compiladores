package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class TipoDefinidoProduction extends AbstractProductionRule {
    

    private static TipoDefinidoProduction instance = new TipoDefinidoProduction();

    public static TipoDefinidoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: 'tipo' (registro | tipo_primitivo) IDENTIFIER ';'
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {
        
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_TIPO)) {
            tl.consumeCurrent();
//            return new SymbolTableEntry();
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada a palavra reservada 'tipo'.");
            return new SymbolTableEntry();
        }
        

        
        if (TipoPrimitivoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            TipoPrimitivoProduction.getInstance().analyze(tl, el);
        }
        else if (RegistroProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            RegistroProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Esperado um identificador.");
            return new SymbolTableEntry();
        }
        

        
        if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada um identificador.");
            return new SymbolTableEntry();
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

    @Override
    protected void populateFirst() {
        //Revisador por André
        first.add(TokenClass.KEYWORD_TIPO);
    }

    @Override
    protected void populateFollow() {
        //Revisado por André
        //from declarations.follow
        follow.add(TokenClass.KEYWORD_FUNCAO);

        //Revisado por André
        //from variaveis.first
        //follow.add(TokenClass.KEYWORD_VARIAVEIS);     (Inexistente)

        //Revisado por André
        //from constantes.first
        //follow.add(TokenClass.KEYWORD_CONSTANTES);    (Inexistente)
    }
}
