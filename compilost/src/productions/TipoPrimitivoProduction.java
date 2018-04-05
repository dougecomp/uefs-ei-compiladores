package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.Token;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;
import semantico.TypeTable;

/**
 * @author heekinho
 * semantico concluido
 */
public class TipoPrimitivoProduction extends AbstractProductionRule {
    

    private static TipoPrimitivoProduction instance = new TipoPrimitivoProduction();

    public static TipoPrimitivoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: tipo_primitivo: 'inteiro' | 'real' | 'logico' | 'caractere' | 'string';
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        
        if (!tl.eof() && isFirst(tl.getCurrent().getTokenClass())){
            Token tk = tl.consumeCurrent();
            SymbolTableEntry t = new SymbolTableEntry();
            t.setType(TypeTable.getTypeFromTokenClass(tk.getTokenClass()));
            return t;
        }
        else{
            return skipUntilSynchronize(tl, el, "Era esperado um tipo primitivo (inteiro, real, logico, caractere ou string).");
        }
    }

    @Override
    protected void populateFirst() {
        first.add(TokenClass.KEYWORD_INTEIRO);
        first.add(TokenClass.KEYWORD_REAL);
        first.add(TokenClass.KEYWORD_LOGICO);
        first.add(TokenClass.KEYWORD_CARACTERE);
        first.add(TokenClass.KEYWORD_STRING);
    }

    @Override
    protected void populateFollow() {
        //from tipovariavel follow
//        follow.add(TokenClass.DELIMITER_OPENING_SQUARE_BRACKET);
        follow.add(TokenClass.IDENTIFIER);
        follow.add(TokenClass.OPERATOR_ASSIGNMENT);
        follow.add(TokenClass.OPERATOR_INCREMENT);
        follow.add(TokenClass.OPERATOR_DECREMENT);
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_OPENING_PARENTHESIS);

    }
}
