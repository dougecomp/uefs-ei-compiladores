package productions;

import exceptions.ParserException;
import exceptions.SemanticException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTable;
import semantico.SymbolTableEntry;
import semantico.TypeTable;

/**
 * @author heekinho
 */
public class TipoVariavelProduction extends AbstractProductionRule {
    

    private static TipoVariavelProduction instance = new TipoVariavelProduction();

    public static TipoVariavelProduction getInstance() {
        return instance;
    }

    /**
     * Regra: tipo_variavel : tipo_primitivo | IDENTIFIER;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {

        if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)){

            TokenClass tc = tl.consumeCurrent().getTokenClass();
            SymbolTableEntry ste = new SymbolTableEntry();
            ste.setType(TypeTable.getTypeFromTokenClass(tc));
            return ste;
            
        }
        
        if (TipoPrimitivoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            return TipoPrimitivoProduction.getInstance().analyze(tl, el);
        }
        
        skipUntilSynchronize(tl, el, "esperado um tipo primitivo ou definido.");
        return new SymbolTableEntry();
    }
    
    @Override
    protected void populateFirst() {

        //revisado por Douglas
        first.add(TokenClass.IDENTIFIER);
        first.add(TokenClass.KEYWORD_INTEIRO);
        first.add(TokenClass.KEYWORD_REAL);
        first.add(TokenClass.KEYWORD_LOGICO);
        first.add(TokenClass.KEYWORD_CARACTERE);
        first.add(TokenClass.KEYWORD_STRING);
    }

    @Override
    protected void populateFollow() {

        //revisado por Douglas
        follow.add(TokenClass.DELIMITER_OPENING_SQUARE_BRACKET);
        follow.add(TokenClass.IDENTIFIER);               
        follow.add(TokenClass.DELIMITER_COMMA);        
        
    }
}
