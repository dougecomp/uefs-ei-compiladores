package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SemanticStructure;
import semantico.SemanticType;
import semantico.SymbolTable;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 * 
 */
public class TipoRetornoFProduction extends AbstractProductionRule {
    

    private static TipoRetornoFProduction instance = new TipoRetornoFProduction();

    public static TipoRetornoFProduction getInstance() {
        return instance;
    }

    /**
     * Regra: tipo_retorno_f : 'vazio' | tipo_variavel;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        
        SymbolTableEntry ste = new SymbolTableEntry();
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_VAZIO)) {
            tl.consumeCurrent();
            ste.setType(SemanticType.TYPE_VAZIO);
            return ste;
        }
        
        if (TipoVariavelProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            return TipoVariavelProduction.getInstance().analyze(tl, el);
        }
        
        return skipUntilSynchronize(tl, el, "Esperado um tipo de retorno.");
    }

    @Override
    protected void populateFirst() {
        first.add(TokenClass.KEYWORD_VAZIO);

        //from TipoVariavelProduction
        first.add(TokenClass.IDENTIFIER);
        first.add(TokenClass.KEYWORD_INTEIRO);
        first.add(TokenClass.KEYWORD_REAL);
        first.add(TokenClass.KEYWORD_LOGICO);
        first.add(TokenClass.KEYWORD_CARACTERE);
        first.add(TokenClass.KEYWORD_STRING);
    }

    @Override
    protected void populateFollow() {
        follow.add(TokenClass.DELIMITER_OPENING_SQUARE_BRACKET);
        follow.add(TokenClass.IDENTIFIER);
    }
}
