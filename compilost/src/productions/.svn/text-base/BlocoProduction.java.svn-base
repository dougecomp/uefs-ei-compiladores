package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTable;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 * @author caco
 */
public class BlocoProduction extends AbstractProductionRule {


    private static BlocoProduction instance = new BlocoProduction();

    public static BlocoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: bloco : '{' instrucao* '}';
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {

        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_CURLY_BRACKET)){
            tl.consumeCurrent();
            SymbolTable.getInstance().newScope();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um '{'");
            return new SymbolTableEntry();
        }


        //varias instruções
        while (InstrucaoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            InstrucaoProduction.getInstance().analyze(tl, el);
        }

        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_CURLY_BRACKET)) {
            tl.consumeCurrent();
            SymbolTable.getInstance().backScope();
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperado um '}'");
            return new SymbolTableEntry();
        }

        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //revisado por caco
        first.add(TokenClass.DELIMITER_OPENING_CURLY_BRACKET);
    }

    @Override
    protected void populateFollow() {
        //revisado por caco

        //from principal follow and funcao first and variaveis first and funcao follow
        follow.add(TokenClass.KEYWORD_FUNCAO);
        follow.add(TokenClass.KEYWORD_VARIAVEIS);


        //from instruction follow
        follow.add(TokenClass.DELIMITER_OPENING_CURLY_BRACKET);

        //from se follow
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);

        //from senao
        follow.add(TokenClass.KEYWORD_SENAO);
    }
}