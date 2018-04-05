package automatos;

import exceptions.LexerException;
import lexico.CodeBuffer;
import lexico.Token;
import lexico.TokenClass;

/**
 * @author André e Ronaldo
 */
public class NumericAutomaton extends Automaton {

    private char finalizers[] = new char[]{' ', '-', '+', '*', '/', ';', '[', ']', '{', '}', '|', '&', '(', ')', '=', '!', '>', '<', '\"'};

    public NumericAutomaton(CodeBuffer buffer) {
        super(buffer);
    }
    

    public boolean startsWith(char c) {
        return (Character.isDigit(c));
    }

    @Override
    public Token process() throws LexerException {
        int state = 0;

        String lexeme = "";
        char temp = ' ';

        int currentLine = buffer.getLine();
        int currentPosition = buffer.getPositionOnLine();

        while (!buffer.eof()) {
            temp = buffer.lookAhead();

            switch (state) {
                case 0:
                    if (Character.isDigit(temp)) {
                        state = 1;
                        lexeme += buffer.next();
                        continue;
                    }
                    break;
                case 1:
                    if (Character.isDigit(temp)) {
                        lexeme += buffer.next();
                        continue;
                    }
                    if (temp == '.') {
                        lexeme += buffer.next();
                        state = 2;
                        continue;
                    }
                    if (isFinalizer(temp)) {
                        return new Token(TokenClass.NUMERIC, lexeme, currentLine, currentPosition);
                    }
                    state = 8;
                    break;
                case 2:
                    if (Character.isDigit(temp)) {
                        state = 3;
                        lexeme += buffer.next();
                        continue;
                    }
                    state = 8;
                    break;
                case 3:
                    if (Character.isDigit(temp)) {
                        lexeme += buffer.next();
                        continue;
                    }
                    if (isFinalizer(temp)) {
                        return new Token(TokenClass.NUMERIC_REAL, lexeme, currentLine, currentPosition);
                    }
                    state = 8;
                    break;
                case 8:
                    if (isFinalizer(temp)) {
                        throw new LexerException("Número Inválido", lexeme, currentLine, currentPosition);
                    }
                    lexeme += buffer.next();
                    break;

            } // fim do switch(state);
        } // fim do while(state);
        if (lexeme.length() > 20) {
            throw new LexerException("Número muito grande", lexeme, currentLine, currentPosition);
        }

        if (state == 1) {
            return new Token(TokenClass.NUMERIC, lexeme, currentLine, currentPosition);
        }
        if (state == 3) {
            return new Token(TokenClass.NUMERIC_REAL, lexeme, currentLine, currentPosition);
        }
        if (state == 2) {
            throw new LexerException("Número Inválido", lexeme, currentLine, currentPosition);
        }
        if (state == 8) {
            throw new LexerException("Número Inválido", lexeme, currentLine, currentPosition);
        }

        throw new LexerException("Desconhecido", lexeme, currentLine, currentPosition);
    } // fim do método process();

    @Override
    public TokenClass selectTokenClass(String lexema) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isFinalizer(char character) {

        for (int i = 0; i < finalizers.length; i++) {
            if (character == finalizers[i]) {
                return true;
            }
        }

        return false;
    }
} // FIM DA CLASSE NumericAutomaton;
