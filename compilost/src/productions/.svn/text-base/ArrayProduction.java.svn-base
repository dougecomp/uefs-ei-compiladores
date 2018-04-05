package productions;

import exceptions.ParserException;
import exceptions.SemanticException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 * @author caco
 */
public class ArrayProduction extends AbstractProductionRule {
    

    private static ArrayProduction instance = new ArrayProduction();

    public static ArrayProduction getInstance() {
        return instance;
    }

    /**
     * Regra: array : '[' expr_arit ']';
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {

        SymbolTableEntry temp = null;

        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_SQUARE_BRACKET)){
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um '['");
            return new SymbolTableEntry();
        }

        
        if (!tl.eof() && ExprAritProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            temp = ExprAritProduction.getInstance().analyze(tl, el);
            verify(temp, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Esperado uma expressão aritmética");
            return new SymbolTableEntry();
        }
        
        //pode ser necessário tratamento de tipo aqui
        ExprAritProduction.getInstance().analyze(tl, el);
                
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_SQUARE_BRACKET)){
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um ']'");
            return new SymbolTableEntry();
        }
        
        
    
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {

        //revisado por Douglas
        first.add(TokenClass.DELIMITER_OPENING_SQUARE_BRACKET);
    }

    @Override
    protected void populateFollow() {

        //revisado por Douglas
        follow.add(TokenClass.OPERATOR_ASSIGNMENT);
        follow.add(TokenClass.OPERATOR_INCREMENT);
        follow.add(TokenClass.OPERATOR_DECREMENT);
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_OPENING_PARENTHESIS);
        follow.add(TokenClass.KEYWORD_CAST);
        follow.add(TokenClass.OPERATOR_PLUS);
        follow.add(TokenClass.OPERATOR_MINUS);
        follow.add(TokenClass.OPERATOR_MULT);
        follow.add(TokenClass.OPERATOR_DIVISION);
        follow.add(TokenClass.OPERATOR_AND);
        follow.add(TokenClass.OPERATOR_OR);
        follow.add(TokenClass.OPERATOR_LESS);
        follow.add(TokenClass.OPERATOR_LESS_EQUAL);
        follow.add(TokenClass.OPERATOR_MORE);
        follow.add(TokenClass.OPERATOR_MORE_EQUAL);
        follow.add(TokenClass.OPERATOR_DIFFERENT);
        follow.add(TokenClass.OPERATOR_EQUAL);
        follow.add(TokenClass.KEYWORD_CAST);
        follow.add(TokenClass.KEYWORD_CAST);
        follow.add(TokenClass.KEYWORD_CAST);
    }

    private void verify(SymbolTableEntry temp, ErrorList el){

        boolean ok = false;

        switch(temp.getStructure()){

            case CONSTANTE: switch(temp.getType()){
                                case TYPE_INTEIRO: ok = true; break;
                                case TYPE_INDEFINIDO: ok = true; break;
                                default: ok = false;
                            }
                break;
            
            case FUNCAO: switch(temp.getType()){
                                case TYPE_INTEIRO: ok = true; break;
                                case TYPE_INDEFINIDO: ok = true; break;
                                default: ok = false;
                            }
                break;

            case UNDEFINED: switch(temp.getType()){
                                case TYPE_INTEIRO: ok = true; break;
                                case TYPE_INDEFINIDO: ok = true; break;
                                default: ok = false;
                            }
                break;
            //==================================================================
            case VARIABLE: switch(temp.getType()){
                                case TYPE_INTEIRO: ok = true; break;
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
}
