/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 *
 * @author maocleal
 * @author caco
 */
public class CastProduction extends AbstractProductionRule {

    /**
     * Referencia estatica e privada
     */
    private static CastProduction instance = new CastProduction();

    /**
     * Construtor privado para o objeto nao ser instanciado em nenhum lugar alem
     * da classe atual
     */
    /**
     * Verifica se o objeto ja foi instanciado previamente. Se nao, instancia e
     * atribui aa referencia estatica.
     *
     * @return a referencia univoca do objeto
     */
    public static CastProduction getInstance() {
        return instance;
    }

    /**
     * Regra: cast : 'cast' '(' tipo_variavel ',' valor ')';
     * a = cast (inteiro[10], 10);
     * @return
     * @throws ParserException
     */
    public SymbolTableEntry analyze(TokenList tl, ErrorList el) {
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_CAST)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Esperada palavra reservada 'cast'.");
            return new SymbolTableEntry();
        }


        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperado um '(' .");
            return new SymbolTableEntry();
        }

        SymbolTableEntry a = TipoVariavelProduction.getInstance().analyze(tl, el);



        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_COMMA)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperado uma ','.");
            return new SymbolTableEntry();
        }

        
       ValorProduction.getInstance().analyze(tl, el);


        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)) {
            tl.consumeCurrent();
        } else {
            skipUntilSynchronize(tl, el, "Era esperado um ')'.");
            return new SymbolTableEntry();
        }

        return a;
    }

    @Override
    protected void populateFirst() {
        //Revisado por André
        this.first.add(TokenClass.KEYWORD_CAST);
    }

    @Override
    protected void populateFollow() {
        //Revisado por André
        //from fator follow ==> op_mult first + termo follow
        //from op_mult first
        this.first.add(TokenClass.OPERATOR_MULT);
        this.first.add(TokenClass.OPERATOR_DIVISION);
        //Revisado por André
        //from termo follow ==> op_soma first + exp_arit follow
        //from op_soma first
        this.first.add(TokenClass.OPERATOR_PLUS);
        this.first.add(TokenClass.OPERATOR_MINUS);
        //Revisado por André & Salomão
        //from expr_arit follow = op_relacional first + expr_rel follow
        //from op_relacional first
        this.follow.add(TokenClass.OPERATOR_LESS);
        this.follow.add(TokenClass.OPERATOR_LESS_EQUAL);
        this.follow.add(TokenClass.OPERATOR_MORE);
        this.follow.add(TokenClass.OPERATOR_MORE_EQUAL);
        this.follow.add(TokenClass.OPERATOR_DIFFERENT);
        this.follow.add(TokenClass.OPERATOR_EQUAL);
        //from expr_rel follow = '&&' + expr_and follow
        follow.add(TokenClass.OPERATOR_AND);
        //from expr_and follow = '||' + expr follow
        follow.add(TokenClass.OPERATOR_OR);
        //from expr follow = valor follow + ')' + ';'
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        //from valor follow  = inits follow + ',' + '}' + params follow + termo_para follow + sentenca follow
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
    }
}
