package productions;

import exceptions.ParserException;
import exceptions.SemanticException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SemanticType;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 * @author caco
 */
public class EnquantoProduction extends AbstractProductionRule {
    

    private static EnquantoProduction instance = new EnquantoProduction();

    public static EnquantoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: enquanto : 'enquanto' '(' expr ')' bloco;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {

        SymbolTableEntry ste = null;

        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_ENQUANTO)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el, "Esperada palavra reservada 'enquanto'.");
            return new SymbolTableEntry();
        }
        
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)) {
            tl.consumeCurrent();

        }
        else{
            skipUntilSynchronize(tl, el,  "Era esperado um '('");
            return new SymbolTableEntry();
        }
        
        
        if (ExprProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ste = ExprProduction.getInstance().analyze(tl, el);
            verify(ste,el);
        }
        else{
            skipUntilSynchronize(tl, el,  "Era esperada uma expressão.");
            return new SymbolTableEntry();
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)) {
            tl.consumeCurrent();
        }
        else{
            skipUntilSynchronize(tl, el,  "Era esperado um ')'");
            return new SymbolTableEntry();
        }
        
        if (BlocoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            BlocoProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el,  "Era esperado um '{'");
            return new SymbolTableEntry();
        }
        
        return ste;
    }

    @Override
    protected void populateFirst() {
        //revisado por bittencourt
        first.add(TokenClass.KEYWORD_ENQUANTO);
    }

    @Override
    protected void populateFollow() {
        //revisado por bittencourt
        //from comandos follow
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }

    public void verify (SymbolTableEntry ste, ErrorList el) {

        boolean ok  = false;

        switch (ste.getStructure()) {

            case CONSTANTE:
                            switch(ste.getType()){

                                case TYPE_LOGICO: ok= true;break;

                                case TYPE_INDEFINIDO: ok = true;break;

                                default: ok = false; break;

                            }
                break;

            case FUNCAO:
                            switch(ste.getType()){

                                case TYPE_LOGICO: ok= true;break;

                                case TYPE_INDEFINIDO: ok = true;break;

                                default: ok = false; break;

                            }

                break;

//            case REGISTRO:
//                            switch(ste.getType()){
//
//                                case TYPE_LOGICO: ok= true;break;
//
//                                case TYPE_INDEFINIDO: ok = true;break;
//
//                                default: ok = false; break;
//
//                            }
//                break;
//
//            case TIPODEFINIDO:
//
//                            switch(ste.getType()){
//
//                                case TYPE_LOGICO: ok= true;break;
//
//                                case TYPE_INDEFINIDO: ok = true;break;
//
//                                default: ok = false; break;
//
//                            }
//                break;

            case UNDEFINED:
                            switch(ste.getType()){

                                case TYPE_LOGICO: ok= true;break;

                                case TYPE_INDEFINIDO: ok = true;break;

                                default: ok = false; break;

                            }
                break;

            case VARIABLE:
                            switch(ste.getType()){

                                case TYPE_LOGICO: ok= true;break;

                                case TYPE_INDEFINIDO: ok = true;break;

                                default: ok = false; break;

                            }
                break;

            case VETOR:
                            switch(ste.getType()){

                                case TYPE_LOGICO: ok= true;break;

                                case TYPE_INDEFINIDO: ok = true;break;

                                default: ok = false; break;

                            }
                break;

            default: ok = false; break;

        } // switch

        if(!ok){

                SemanticException se = new SemanticException(
                        "Expressão booleana não encontrada", ste.getToken().getLine(), ste.getToken().getLine());
                el.add(se);


            }

    }
       
}
