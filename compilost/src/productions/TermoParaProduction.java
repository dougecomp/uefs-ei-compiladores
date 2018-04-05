/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenClass;
import lexico.TokenList;
import semantico.SymbolTableEntry;

/**
 *
 * @author caco
 */
public class TermoParaProduction extends AbstractProductionRule {

    
    
    
    private static TermoParaProduction instance = new TermoParaProduction();

    public static TermoParaProduction getInstance() {
        return instance;
    }
    
    
    /**
     * Regra: termo_para : (tipo_primitivo IDENTIFIER | IDENTIFIER IDENTIFIER?) array* ('=' valor)?;
     * @return
     * @throws ParserException
     */
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        
        if (TipoPrimitivoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            TipoPrimitivoProduction.getInstance().analyze(tl, el);
        }
        else if(testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)){
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um tipo primitivo ou definido.");
            return new SymbolTableEntry();
        }
        
        

        if(testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)){
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um identificador.");
            return new SymbolTableEntry();
        }

        
        while (ArrayProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            ArrayProduction.getInstance().analyze(tl, el);
        }

        
        //opcional
        if (testEofAndCurrentToken(tl, TokenClass.OPERATOR_ASSIGNMENT)) {
            tl.consumeCurrent();
            if (ValorProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                ValorProduction.getInstance().analyze(tl, el);
            } else {
                skipUntilSynchronize(tl, el, "Esperado um valor.");
                return new SymbolTableEntry();
            }
        } else {
            skipUntilSynchronize(tl, el, "Esperado um operador de atribuicao.");
            return new SymbolTableEntry();
        }
        
        
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        first.add(TokenClass.IDENTIFIER);
        first.add(TokenClass.KEYWORD_INTEIRO);
        first.add(TokenClass.KEYWORD_REAL);
        first.add(TokenClass.KEYWORD_LOGICO);
        first.add(TokenClass.KEYWORD_CARACTERE);
        first.add(TokenClass.KEYWORD_STRING);
    }

    @Override
    protected void populateFollow() {
        //Revisado por André
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
    }

}
