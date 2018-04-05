package lexico;

/**
 * @author heekinho
 */
public class Token {

    private TokenClass tokenClass;
    private String lexeme;
    private int line;
    private int position;
    //TODO: Completar com outras informações importantes

    public Token(TokenClass tokenClass, String lexeme, int line, int position) {
        this.tokenClass = tokenClass;
        this.lexeme = lexeme;
        this.line = line;
        this.position = position;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public TokenClass getTokenClass() {
        return tokenClass;
    }

    public void setTokenClass(TokenClass tokenClass) {
        this.tokenClass = tokenClass;
    }

    @Override
    public boolean equals(Object o) {
        Token t = (Token) o;
        return (t.lexeme.equals(this.lexeme) && t.line == this.line && t.position == this.position && t.tokenClass == this.tokenClass);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + lexeme.hashCode();
        result = 37 * result + tokenClass.hashCode();
        result = 37 * result + line;
        result = 37 * result + position;

        return result;
    }

    @Override
    public String toString() {
        return "[ " + getTokenClass() + " | \"" + getLexeme()
                + "\" | " + getLine() + " | " + getPosition() + " ]";
    }
}
