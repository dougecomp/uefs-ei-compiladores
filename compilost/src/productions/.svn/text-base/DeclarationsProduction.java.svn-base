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
public class DeclarationsProduction extends AbstractProductionRule {
   

    private static DeclarationsProduction instance = new DeclarationsProduction();

    public static DeclarationsProduction getInstance() {
        return instance;
    }

    /**
     * Regra: declarations : (constante | tipo_definido | (variaveis ';'))*;
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el){

        while (!tl.eof() && first.contains(tl.getCurrent().getTokenClass())) {
            if (ConstanteProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                ConstanteProduction.getInstance().analyze(tl, el);
                continue;
            }
            
            if (TipoDefinidoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                TipoDefinidoProduction.getInstance().analyze(tl, el);
                continue;
            }
            
            
            if(VariaveisProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
                VariaveisProduction.getInstance().analyze(tl, el);
                        
                if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_SEMICOLON)) {
                    tl.consumeCurrent();
                }
                else{
                    skipUntilSynchronize(tl, el, "Era esperado um ';'.");
                    return new SymbolTableEntry();
                }
            }
            else{
                skipUntilSynchronize(tl, el, "Esperado uma declaração de tipo, de uma constante ou de uma variável");
            }
        }

        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //revisado por pedro
        
        //from constante
        first.add(TokenClass.KEYWORD_CONSTANTES);

        //from tipodefinido
        first.add(TokenClass.KEYWORD_TIPO);

        //from variaveis
        first.add(TokenClass.KEYWORD_VARIAVEIS);
    }

    @Override
    protected void populateFollow() {
        //revisado por pedro
        
        //from principal
        follow.add(TokenClass.KEYWORD_FUNCAO);
    }
}
