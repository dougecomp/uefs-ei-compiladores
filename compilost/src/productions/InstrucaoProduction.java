package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class InstrucaoProduction extends AbstractProductionRule {

    
    private static InstrucaoProduction instance = new InstrucaoProduction();

    public static InstrucaoProduction getInstance() {
        return instance;
    }

    /**
     * Regra: instrucao : (variaveis ';' | comandos | sentenca ';') | bloco;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {


        if (VariaveisProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            VariaveisProduction.getInstance().analyze(tl, el);

            if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
                tl.consumeCurrent();
            } else {
                skipUntilSynchronize(tl, el, "Era esperado um ';'");
            }

            return new SymbolTableEntry();
        }


        if (ComandosProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ComandosProduction.getInstance().analyze(tl, el);
            return new SymbolTableEntry();
        }


        if (SentencaProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            SentencaProduction.getInstance().analyze(tl, el);

            if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
                tl.consumeCurrent();
            } else {
                skipUntilSynchronize(tl, el, "Era esperado um ';'");
            }

            return new SymbolTableEntry();
        }



        if (BlocoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            BlocoProduction.getInstance().analyze(tl, el);
            return new SymbolTableEntry();
        }

        skipUntilSynchronize(tl, el, "Esperada uma variável, um comando, uma senteça ou um bloco.");
        return new SymbolTableEntry();

    }

    @Override
    protected void populateFirst() {
        //revisado por pedro

        //from variaveis first
        first.add(TokenClass.KEYWORD_VARIAVEIS);

        //from comandos first
        first.add(TokenClass.KEYWORD_SE);
        first.add(TokenClass.KEYWORD_PARA);
        first.add(TokenClass.KEYWORD_ENQUANTO);
        first.add(TokenClass.KEYWORD_LEIA);
        first.add(TokenClass.KEYWORD_ESCREVA);
        first.add(TokenClass.KEYWORD_RETORNO);

        //from sentenca first
        first.add(TokenClass.IDENTIFIER);

        //from bloco first
        first.add(TokenClass.DELIMITER_OPENING_CURLY_BRACKET);
    }

    @Override
    protected void populateFollow() {
        //revisado por pedro
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }
}
