/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automatos;

import exceptions.LexerException;
import lexico.CodeBuffer;
import lexico.Token;
import lexico.TokenClass;

/**
 * Classe de Autômato para Detecção de Strings
 * @author Maria && Eder
 */
public class StringAutomaton extends Automaton {

    char currentChar;
    int startLine;
    int startColumn;

    public StringAutomaton(CodeBuffer buffer) {
        super(buffer);
    }
    
    public boolean startsWith(char c){
        return (c=='"');
    }

    @Override
    public Token process() throws LexerException {
        //i.e.: chegar ao fim do arquivo e não fechar com \"

        String lexeme = "";
        buffer.next(); //consome as aspas iniciais
        currentChar = this.buffer.getCode().charAt(this.buffer.getCurrentChar());
        this.startLine = this.buffer.getLine();
        this.startColumn = this.buffer.getPositionOnLine() - 1;

        while (buffer.lookAhead() != '\"') {
            if (this.buffer.getCode().length() - 1 == this.buffer.getCurrentChar()) {
                buffer.next();
                throw new LexerException("String Não Terminada", String.copyValueOf(lexeme.toCharArray(), 0, 5) + "...", this.startLine, this.startColumn);

            } else {
                if (lexeme.length() > 256) {
                    while (buffer.lookAhead() != '\"') {
                        if (this.buffer.getCode().length() - 1 == this.buffer.getCurrentChar()) {
                            buffer.next();
                            throw new LexerException("String Não Terminada", String.copyValueOf(lexeme.toCharArray(), 0, 5) + "...", this.startLine, this.startColumn);
                        }
                        buffer.next(); //consome caracteres da string grande sem atribuir a nada

                    }
                    buffer.next();
                    throw new LexerException("String Muito Grande", String.copyValueOf(lexeme.toCharArray(), 0, 5) + "...", this.startLine, this.startColumn);
                }
                lexeme += buffer.next();
            }
        }
        buffer.next();
        if (lexeme.length() == 1) {
            return new Token(TokenClass.CARACTERE, lexeme, this.buffer.getLine(), this.startColumn);
        }
        return new Token(TokenClass.STRING, lexeme, this.buffer.getLine(), this.startColumn);
    }

    @Override
    public TokenClass selectTokenClass(String lexema) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isFinalizer(char character) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
