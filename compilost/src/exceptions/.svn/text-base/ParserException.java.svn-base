package exceptions;

/**
 *
 * @author Ronaldo
 * @author caco
 */
public class ParserException extends CompilostException {

    public ParserException(String descricao, int linha, int posicao) {
        this.descricao = descricao;
        this.linha = linha;
        this.posicao = posicao;
    }

    
    /*
     * Devolve uma String com o informações sobre o erro ocorrido, todas as informações sobre o erro;.
     */
    @Override
    public String getErrorMessage() {
        return descricao + "\", linha: " + linha + ", coluna: " + posicao;
    }
}
