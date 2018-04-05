package productions;

import exceptions.ParserException;
import exceptions.SemanticException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SemanticType;
import semantico.SymbolTableEntry;
import semantico.TypeTable;

/**
 * @author heekinho
 * @author caco
 */
public class DclrVarInitProduction extends AbstractProductionRule {
    

    private static DclrVarInitProduction instance = new DclrVarInitProduction();

    public static DclrVarInitProduction getInstance() {
        return instance;
    }

    /**
     * Regra: dclr_var_init : dclr_var ('=' inits)?;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {

          SymbolTableEntry ste = new SymbolTableEntry();
          SymbolTableEntry ste2 = new SymbolTableEntry();

        if (DclrVarProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            
            ste = DclrVarProduction.getInstance().analyze(tl, el);

        }
        else{
            skipUntilSynchronize(tl, el, "Esperada uma declaração de variável.");
        }

        
        if (testEofAndCurrentToken(tl, TokenClass.OPERATOR_ASSIGNMENT)) {
            tl.consumeCurrent();
            
            if (InitsProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {

                ste2 = InitsProduction.getInstance().analyze(tl, el);
                //return new SymbolTableEntry();
                if(TypeTable.canAReceiveB(ste.getType(), ste2.getType())){
                    return ste;
                }
                else{
                    SemanticException se = new SemanticException("Tipos Incompativeis",ste.getToken().getLine(), ste.getToken().getPosition());
                    el.add(se);
                }

            }
            else{
                skipUntilSynchronize(tl, el, "Esperada uma inicialização de variável.");
            }

        }

        return new SymbolTableEntry();
    }


    @Override
    protected void populateFirst() {
        //from DeclrVar
        first.add(TokenClass.IDENTIFIER);
        first.add(TokenClass.KEYWORD_INTEIRO);
        first.add(TokenClass.KEYWORD_REAL);
        first.add(TokenClass.KEYWORD_LOGICO);
        first.add(TokenClass.KEYWORD_CARACTERE);
        first.add(TokenClass.KEYWORD_STRING);
    }

    @Override
    protected void populateFollow() {
        follow.add(TokenClass.DELIMITER_COMMA);


        //revisado por salomao
        //from Variaveis
        follow.add(TokenClass.DELIMITER_SEMICOLON);

    }
}
