package lexico;

/**
 * Esta classe representa uma marcacao no codigo. Tais marcacoes sao provenientes
 * de comentarios. Apos um preprocessamento, a classe CodeBuffer marca quais
 * as posicoes do codigo que tem comentarios. Nesta classe, ela assinala tambem
 * numero da linha e a posicao da linha a serem atualizados quando um salto eh
 * realizado
 * 
 * @author Cacovsky
 */
public class CommentBookmark {

    /**
     * A posicao no codigo do inicio do comentario
     */
    int charPositionFrom;
    /**
     * A posicao no codigo do final do comentario
     */
    int charPositionTo;
    /**
     * A posicao da linha no codigo do inicio do comentario
     */
    int linePositionFrom;
    /**
     * A posicao da linha no codigo do final do comentario
     */
    int linePositionTo;
    /**
     * O numero da linha do inicio do comentario
     */
    int lineNumberFrom;
    /**
     * O numero da linha do final do comentario
     */
    int lineNumberTo;
    /**
     * Se o comentario foi finalizado corretamente
     */
    boolean comentarioFinalizado;

    public CommentBookmark() {
    }
}
