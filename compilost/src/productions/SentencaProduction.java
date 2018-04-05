package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author maocleal
 */
public class SentencaProduction extends AbstractProductionRule {
    

    private static SentencaProduction instance = new SentencaProduction();

    public static SentencaProduction getInstance() {
        return instance;
    }

    /**
     * Regra: sentenca : IDENTIFIER chamada_funcao|((array)* ('=' valor | op_inc*));
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el){

        if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado um identificador.");
            return new SymbolTableEntry();
        }



        //opcional
        if (ChamadaFuncaoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ChamadaFuncaoProduction.getInstance().analyze(tl, el);
            
            
        }
        else {
            while (ArrayProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                ArrayProduction.getInstance().analyze(tl, el);
            }
            
            if (testEofAndCurrentToken(tl, TokenClass.OPERATOR_ASSIGNMENT)) {
                tl.consumeCurrent();
                if (ValorProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                    ValorProduction.getInstance().analyze(tl, el);
                } else {
                    skipUntilSynchronize(tl, el, "Era esperado uma atribuição");
                    return new SymbolTableEntry();
                }

            } else if (OpIncProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                while (OpIncProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                    OpIncProduction.getInstance().analyze(tl, el);
                }
            } else {
                skipUntilSynchronize(tl, el, "Esperado uma atribuição ou um '++' ou '--'.");
                return new SymbolTableEntry();
            }
        }


        



        



        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //Revisado por André
        first.add(TokenClass.IDENTIFIER);
    }

    @Override
    protected void populateFollow() {
        //Revisado por André
        follow.add(TokenClass.DELIMITER_SEMICOLON);
    }
}
