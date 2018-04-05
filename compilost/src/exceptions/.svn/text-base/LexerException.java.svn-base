package exceptions;

/**
 *
 * @author Douglas
 * @author Vinícius
 * @author caco
 */
public class LexerException extends CompilostException {

    /* Lexema onde o erro ocorreu */
    private String lexema;

    public LexerException(String descricao, String lexema, int linha, int posicao) {

        this.descricao = descricao;
        this.lexema = lexema;
        this.linha = linha;
        this.posicao = posicao;
    }

    /*
     * Devolve uma String com o lexema 'defeituoso'.
     */
    public String getLexema() {
        return lexema;
    }


    @Override
    public String getErrorMessage() {
        return descricao + ": \"" + lexema + "\", linha: " + linha + ", coluna: " + posicao;
    }
}
