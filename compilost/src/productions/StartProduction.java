package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 * @author caco
 */
public class StartProduction extends AbstractProductionRule {

    private static StartProduction instance = new StartProduction();

    public static StartProduction getInstance() {
        return instance;
    }

    /**
     * Regra: start : algoritmo declarations principal (funcao | variaveis ';')* EOF;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {

        //consumindo regra Algoritmo
        if (AlgoritmoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            AlgoritmoProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Era esperada a palavra reservada 'algoritmo'.");
            return new SymbolTableEntry();
        }

        //consumindo regraS Declarations
//        if (DeclarationsProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
        while (DeclarationsProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            DeclarationsProduction.getInstance().analyze(tl, el);
        }

        //consumindo regra Principal
        if (PrincipalProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            PrincipalProduction.getInstance().analyze(tl, el);
        }
        else{
            skipUntilSynchronize(tl, el, "Erro na declaração da função principal.");
            return new SymbolTableEntry();
        }


        //consumindo regraS Funcao e Variaveis alternadamente
        while (!tl.eof() && 
                (FuncaoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())
                || VariaveisProduction.getInstance().isFirst(tl.getCurrent().getTokenClass()))) {

            //consumindo funcao
            if (FuncaoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
                FuncaoProduction.getInstance().analyze(tl, el);
                continue;
            }

            //consumindo variavel
            if (VariaveisProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
                VariaveisProduction.getInstance().analyze(tl, el);

                if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
                    tl.consumeCurrent();
                }
                else{
                    skipUntilSynchronize(tl, el, "Era esperado um ';'.");
                    return new SymbolTableEntry();
                }

                continue;
            }

            skipUntilSynchronize(tl, el, "Erro na estrutura do programa. Era esperada função ou declaração de variável.");
            return new SymbolTableEntry();
        }

        if (!tl.eof()) {
            el.addParserError(tl, "Erro na estrutura do programa. Era esperado fim de arquivo.");
        }
        
        
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //revisado por caco
        first.add(TokenClass.KEYWORD_ALGORITMO);
    }

    @Override
    protected void populateFollow() {
        //revisado por caco
    }
}
