package lexico;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heekinho
 */
public class TokenList extends ArrayList<Token> {

    private int current = 0;

    public TokenList() {
    }

    public void pushBackToken(Token token) {
        this.add(token);
    }

    /**
     * Imprime a lista de tokens
     */
    public void printTokens() {
        System.out.println("Tokens: ");
        for (Token token : this) {
            System.out.println(token);
        }
    }

    /**
     * Imprime os tokens remanescentes.
     */
    public void printTokensFromCurrent() {
        if (current >= size()) {
            return;
        }
        System.out.println("Tokens restantes: ");
        for (int i = current; i < size(); i++) {
            System.out.println(get(i));
        }
    }

    /**
     * Obtém a lista dos tokens remanescentes a partir do ponteiro current.
     * @return
     */
    public List<Token> getRemainingTokens() {
        return new ArrayList<Token>(this.subList(current, size()));
    }

    /**
     * Obtendo o token atual
     * @return o token atual
     */
    public Token getCurrent() {
        return this.get(current);
    }

    /**
     * Retorna o índice do token atual na lista.
     * @return current
     */
    public int getCurrentIndex() {
        return current;
    }

    /**
     * Consome o token atual
     * @return o token atual
     */
    public Token consumeCurrent() {
        return this.get(current++);
    }

    /**
     * Consome o token, e retorna o mais novo getCurrent.
     * Equivale a:
     * st.consumeCurrent();
     * st.getCurrent();
     * @return o "próximo" token (que virou o current).
     */
    public Token getNext() {
        ++current;
        if (!eof()) {
            return this.get(current);
        } else {
            return new Token(TokenClass.UNDEFINED, "EOF", -1, -1);
        }
    }

    public boolean eof() {
        if (this.size() == current) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.current = 0;
    }
}
