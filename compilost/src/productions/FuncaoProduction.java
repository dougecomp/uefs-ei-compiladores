package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.Token;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class FuncaoProduction extends AbstractProductionRule {
    
    private static FuncaoProduction instance = new FuncaoProduction();

    public static FuncaoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: funcao :'funcao' tipo_retorno_f ('[' ']')* IDENTIFIER '(' args* ')' bloco;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {
        
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_FUNCAO)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada palavra reservada 'funcao'");
            return new SymbolTableEntry();
        }
        
        SymbolTableEntry tipofuncao = new SymbolTableEntry();
        if(TipoRetornoFProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            tipofuncao = TipoRetornoFProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Tipo de retorno não definido");
            return new SymbolTableEntry();
        }
       

        
        while (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_SQUARE_BRACKET)) {
            tl.consumeCurrent();
            
            
            if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_SQUARE_BRACKET)) {
                tl.consumeCurrent();
            }
            else{
                skipUntilSynchronize(tl, el, "Era esperado um ']'");
                return new SymbolTableEntry();
            }
            
        }

        
        Token nomefuncao;
        if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)) {
            nomefuncao = tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um identificador");
            return new SymbolTableEntry();
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um '('");
            return new SymbolTableEntry();
        }
        

        while (ArgsProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ArgsProduction.getInstance().analyze(tl, el);
        }

        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um ')'");
            return new SymbolTableEntry();
        }
        
        
        if (BlocoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            BlocoProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um '{'");
            return new SymbolTableEntry();
        }
        
        
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        first.add(TokenClass.KEYWORD_FUNCAO);
    }

    @Override
    protected void populateFollow() {
        //Revisado por André
        //from variaveis first
        //follow.add(TokenClass.KEYWORD_VARIAVEIS);

        //Revisado por André
        //from funcao first
        //follow.add(TokenClass.KEYWORD_FUNCAO);
    }
}
