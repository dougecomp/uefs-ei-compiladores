package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author heekinho
 */
public class ParaProduction extends AbstractProductionRule {
    

    private static ParaProduction instance = new ParaProduction();

    public static ParaProduction getInstance() {
        return instance;
    }

    /**
     * Regra: para : 'para' '(' (termo_para (',' termo_para)*)? ';' expr? ';' (expr (',' expr)*)? ')' bloco;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {

        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_PARA)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada palavra reservada 'para' ");
            return new SymbolTableEntry();
        }




        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada  um '('.");
            return new SymbolTableEntry();
        }



        if (!tl.eof() && TermoParaProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            TermoParaProduction.getInstance().analyze(tl, el);

            while (testEofAndCurrentToken(tl, TokenClass.DELIMITER_COMMA)) {
                if (!tl.eof() && TermoParaProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                    TermoParaProduction.getInstance().analyze(tl, el);
                    skipUntilSynchronize(tl, el, "Era esperada uma expressão.");
                } else {
                    return new SymbolTableEntry();
                }
            }
        }




        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado  um ';'.");
            return new SymbolTableEntry();
        }

        //opcional
        if (ExprProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ExprProduction.getInstance().analyze(tl, el);
        }


        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado  um ';'.");
            return new SymbolTableEntry();
        }

        //varias expressoes, opcional
        while (!tl.eof() && ExprProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ExprProduction.getInstance().analyze(tl, el);
            if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_COMMA)) {
                tl.consumeCurrent();
            } else {
                skipUntilSynchronize(tl, el, "Era esperado uma ','.");
                return new SymbolTableEntry();
            }

        }


        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperado  um ')'.");
            return new SymbolTableEntry();
        }

        if (BlocoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            BlocoProduction.getInstance().analyze(tl, el);
        } else {
            skipUntilSynchronize(tl, el, "Esperado  um '{'.");
            return new SymbolTableEntry();
        }

        return new SymbolTableEntry();
    }

    

    @Override
    protected void populateFirst() {
        first.add(TokenClass.KEYWORD_PARA);
    }

    @Override
    protected void populateFollow() {
        //from comandos follow
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }
}
