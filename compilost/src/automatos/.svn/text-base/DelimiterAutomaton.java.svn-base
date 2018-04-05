package automatos;

import exceptions.LexerException;
import java.util.Arrays;
import java.util.List;
import lexico.CodeBuffer;
import lexico.Token;
import lexico.TokenClass;

/**
 * @author heekinho
 */
public class DelimiterAutomaton extends Automaton {

    private List<String> finalizers = Arrays.asList(new String[]{";", "(", ")", "{", "}", "[", "]", ","});

    private List<Character> starts = Arrays.asList(new Character[]{';', '(', ')', '{', '}', '[', ']', ','});
    
    public boolean startsWith(char c) {
        return (starts.contains(c));
    }
    
    public DelimiterAutomaton(CodeBuffer buffer) {
        super(buffer);
    }

    @Override
    public Token process() throws LexerException {

        Token token = getTempToken();
        String currentChar = Character.toString(buffer.next());

        if (finalizers.contains(currentChar)) {
            token.setLexeme(currentChar);
            token.setTokenClass(selectTokenClass(currentChar));
            return token;
        }
        return null;
    }

    @Override
    public TokenClass selectTokenClass(String lexema) {
        if (lexema.equals(";")) {
            return TokenClass.DELIMITER_SEMICOLON;
        }
        if (lexema.equals("(")) {
            return TokenClass.DELIMITER_OPENING_PARENTHESIS;
        }
        if (lexema.equals(")")) {
            return TokenClass.DELIMITER_CLOSING_PARENTHESIS;
        }
        if (lexema.equals("{")) {
            return TokenClass.DELIMITER_OPENING_CURLY_BRACKET;
        }
        if (lexema.equals("}")) {
            return TokenClass.DELIMITER_CLOSING_CURLY_BRACKET;
        }
        if (lexema.equals("[")) {
            return TokenClass.DELIMITER_OPENING_SQUARE_BRACKET;
        }
        if (lexema.equals("]")) {
            return TokenClass.DELIMITER_CLOSING_SQUARE_BRACKET;
        }
        if (lexema.equals(",")) {
            return TokenClass.DELIMITER_COMMA;
        }
        return TokenClass.UNDEFINED;
    }

    /**
     * Qualquer caracter é um finalizador.
     * @param character
     * @return
     */
    @Override
    public boolean isFinalizer(char character) {
        return true;
    }
}
