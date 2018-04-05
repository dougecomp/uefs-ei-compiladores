package productions;

import exceptions.ParserException;
import exceptions.SemanticException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class RetornoProduction extends AbstractProductionRule {
    

    private static RetornoProduction instance = new RetornoProduction();

    public static RetornoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: retorno: 'retorno' '(' valor ')' ';';
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {

        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_RETORNO)) {
            tl.consumeCurrent();
        } else {
            return skipUntilSynchronize(tl, el, "Esperada palavra reservada 'retorno' ");
        }
        
        
        
        if (ExprProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            SymbolTableEntry a = ExprProduction.getInstance().analyze(tl, el);

            
            verify(new SymbolTableEntry(),el);

        }
        else{

            skipUntilSynchronize(tl, el, "Esperado um valor.");
            return new SymbolTableEntry();
        }

        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperado um ';'");
            return new SymbolTableEntry();
        }
       
        return new SymbolTableEntry();
    }

    private void verify(SymbolTableEntry temp, ErrorList el){

        boolean ok = false;

        switch(temp.getStructure()){

            case VARIABLE: switch(temp.getType()){
                                case TYPE_INTEIRO: ok = true; break;
                                case TYPE_REAL: ok = true; break;
                                case TYPE_LOGICO: ok = true; break;
                                case TYPE_INDEFINIDO: ok = true; break;
                                default: ok = false;
                            }
                break;


            case UNDEFINED: switch(temp.getType()){
                                case TYPE_INTEIRO: ok = true; break;
                                case TYPE_REAL: ok = true; break;
                                case TYPE_CARACTERE: ok = true; break;
                                case TYPE_STRING: ok = true; break;
                                case TYPE_LOGICO: ok = true; break;
                                case TYPE_INDEFINIDO: ok = true; break;
                                default: ok = false;
                            }
                break;

            default: ok = false;
        }// fim do switch;

        if(!ok){
            SemanticException ex = new SemanticException(
                    "Erro ao acessar o vetor (não inteiro)", temp.getToken().getLine(), temp.getToken().getPosition());
            el.add(ex);
        }
    }
    
     @Override
    protected void populateFirst() {
        first.add(TokenClass.KEYWORD_RETORNO);
    }

    @Override
    protected void populateFollow() {
        //from comandos follow

        // revisadas por salomao
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }
}
