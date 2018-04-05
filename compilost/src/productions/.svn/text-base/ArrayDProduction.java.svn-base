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
public class ArrayDProduction extends AbstractProductionRule {


    private static ArrayDProduction instance = new ArrayDProduction();

    public static ArrayDProduction getInstance() {
        return instance;
    }

    /**
     * Regra: array : '[' expr_arit? ']';
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {

        SymbolTableEntry temp = null;

        if(testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_SQUARE_BRACKET)){
            tl.consumeCurrent();
        }
        else{
            return skipUntilSynchronize(tl, el, "Era esperado um '['");
        }

        //opcional
        if (!tl.eof() && ExprAritProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            temp = ExprAritProduction.getInstance().analyze(tl, el);
            verify(temp, el);
        }


        if(testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_SQUARE_BRACKET)){
            tl.consumeCurrent();
        }
        else{
            return skipUntilSynchronize(tl, el, "Era esperado um ']'");
            
        }

        return temp;


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
            //==================================================================
            case FUNCAO: switch(temp.getType()){
                                case TYPE_INTEIRO: ok = true; break;
                                case TYPE_INDEFINIDO: ok = true; break;
                                default: ok = false;
                            }
                break;
            //==================================================================
//            case REGISTRO: switch(temp.getType()){
//                                case TYPE_INTEIRO: ok = true; break;
//                                case TYPE_INDEFINIDO: ok = true; break;
//                                default: ok = false;
//                            }
//                break;
//            //==================================================================
//            case TIPODEFINIDO: switch(temp.getType()){
//                                case TYPE_INTEIRO: ok = true; break;
//                                case TYPE_INDEFINIDO: ok = true; break;
//                                default: ok = false;
//                            }
//                break;
            //==================================================================
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
            el.add( new SemanticException( "Erro ao acessar o vetor (não inteiro)",
                   temp.getToken().getLine(), temp.getToken().getPosition()));            
        }

    }


    @Override
    protected void populateFirst() {
        // revisado por Ronaldo
        first.add(TokenClass.DELIMITER_OPENING_SQUARE_BRACKET);
    }


    @Override
    protected void populateFollow() {
        //Revisado por Ronaldo
        //from declrvar.follow
        follow.add(TokenClass.OPERATOR_ASSIGNMENT);
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_OPENING_SQUARE_BRACKET);
    }
}
