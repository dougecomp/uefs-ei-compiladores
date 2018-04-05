package automatos;

import exceptions.LexerException;
import lexico.CodeBuffer;
import lexico.Token;
import lexico.TokenClass;

/**
 * @author heekinho
 */
public abstract class Automaton {

    protected CodeBuffer buffer;
    

    public Automaton(CodeBuffer buffer) {
        this.buffer = buffer;
    }
    
    
    /**
     * Informa se o automato em questao pode iniciar com o caractere do
     * parametro
     */
    public abstract boolean startsWith(char c);

    /**
     * Onde o processamento todo vai ocorrer.
     * Corresponde aos métodos já implementados em "Lexico".
     * @return Token formado.
     * @throws Exception
     */
    public abstract Token process() throws LexerException;

    /**
     * Retorna a classe do lexema dado. Note que se um lexema que não é
     * responsabilidade do respectivo automato for passado, a resposta
     * TokenClass.UNDEFINED deve ser fornecida.
     * @param lexema
     * @return TokenClass do lexema em questão
     */
    public abstract TokenClass selectTokenClass(String lexema);

    /**
     * Obtém uma lista de "delimitadores de finalização".
     * Estes delimitadores são responsáveis por terminar o processamento do
     * token sem erro, uma vez que nem sempre o caracter "espaço" é suficiente
     * para realizar este trabalho.
     * Coloquei "Finalizers" para evitar confusão no automato de delimitadores.
     * @return Lista de delimitadores
     */
    /* NOTA: Pode não ser interessante engessar com uma lista. Por isso criei
     * o método abaixo. Bem mais prático e pode-se fazer uso das listas.
     * Apesar de nem ser necessário, aqui nesta classe, existir este método */
    //public abstract List<String> getFinalizers();
    /**
     * Nem sempre é interessante consultar por uma lista, para saber se um
     * caracter é um finalizador ou não.
     * @param character
     * @return true se character é finalizador.
     */
    public abstract boolean isFinalizer(char character);

    /**
     * Obtém uma referência para o buffer de caracteres.
     * @return buffer
     */
    public CodeBuffer getBuffer() {
        return buffer;
    }

    /**
     * Constrói um token temporário, por comodidade.
     * @return
     */
    public Token getTempToken() {
        return new Token(TokenClass.UNDEFINED, "", buffer.getLine(), buffer.getPositionOnLine());
    }

}
