/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automatos;

import exceptions.LexerException;
import java.util.Arrays;
import java.util.List;
import lexico.CodeBuffer;
import lexico.Token;
import lexico.TokenClass;

/**
 *
 * @author Douglas & Vinicius
 */
public class OperatorAutomaton extends Automaton {

    
    
    private List<Character> starts = Arrays.asList(new Character[]{'+', '-', '=', '!', '<', '>', '&', '|', '/', '.', '*'});
    
    
    public OperatorAutomaton(CodeBuffer buffer) {
        super(buffer);
    }
    
    public boolean startsWith(char c){
        return starts.contains(c);
    }

    @Override
    public Token process() throws LexerException {

        int currentPosition = buffer.getPositionOnLine();
        int currentLine = buffer.getLine();
        char atual = ' ';
        String lexeme = Character.toString(atual = this.buffer.next());
        

        //Estado para o operador + e ++
//        if (lexeme.equals("+")) {
        if (atual=='+') {
            if (buffer.eof()) {
                return new Token(TokenClass.OPERATOR_PLUS, lexeme, currentLine, currentPosition);
            } else if (this.buffer.lookAhead() == '+') {
                lexeme += this.buffer.next();
                return new Token(TokenClass.OPERATOR_INCREMENT, lexeme, currentLine, currentPosition); //retorna '++'
            } else {
                return new Token(TokenClass.OPERATOR_PLUS, lexeme, currentLine, currentPosition); //retorna somente '+'
            }

        }

        // Estado para operador '-' e '--'
        if (atual=='-') // Indice do operador '-'
        {
            if (buffer.eof()) {
                return new Token(TokenClass.OPERATOR_MINUS, lexeme, currentLine, currentPosition);
            } else if (this.buffer.lookAhead() == '-') {
                lexeme += this.buffer.next();
                return new Token(TokenClass.OPERATOR_DECREMENT, lexeme, currentLine, currentPosition);
            } else {
                return new Token(TokenClass.OPERATOR_MINUS, lexeme, currentLine, currentPosition);
            }

        }

        // Estado para operador '=' e '=='
        if (atual=='=') {

            if (buffer.eof()) {
                return new Token(TokenClass.OPERATOR_EQUAL, lexeme, currentLine, currentPosition);
            } else if (this.buffer.lookAhead() == '=') {
                lexeme += this.buffer.next();
                return new Token(TokenClass.OPERATOR_EQUAL, lexeme, currentLine, currentPosition);
            } else {
                return new Token(TokenClass.OPERATOR_ASSIGNMENT, lexeme, currentLine, currentPosition);
            }
        }

        // Estado para operador '!='
        if (atual=='!') {

            if (buffer.eof()) {
                return new Token(TokenClass.OPERATOR_NOT, lexeme, currentLine, currentPosition);
            } else if (this.buffer.lookAhead() == '=') {
                lexeme += this.buffer.next();
                return new Token(TokenClass.OPERATOR_DIFFERENT, lexeme, currentLine, currentPosition);
            } else {
//                lexeme +=  this.buffer.next();
                return new Token(TokenClass.OPERATOR_NOT, lexeme, currentLine, currentPosition);
            }

        } //fim if do !=

        // Estado para operador '<' e '<='
        if (atual=='<') {

            if (buffer.eof()) {
                return new Token(TokenClass.OPERATOR_LESS, lexeme, currentLine, currentPosition);
            } else if (this.buffer.lookAhead() == '=') {
                lexeme += this.buffer.next();
                return new Token(TokenClass.OPERATOR_LESS_EQUAL, lexeme, currentLine, currentPosition);
            } else {
                return new Token(TokenClass.OPERATOR_LESS, lexeme, currentLine, currentPosition);
            }
        }

        // Estado para operador '>' e '>='
        if (atual=='>') {

            if (buffer.eof()) {
                return new Token(TokenClass.OPERATOR_MORE, lexeme, currentLine, currentPosition);
            } else if (this.buffer.lookAhead() == '=') {
                lexeme += this.buffer.next();
                return new Token(TokenClass.OPERATOR_MORE_EQUAL, lexeme, currentLine, currentPosition);
            } else {
                return new Token(TokenClass.OPERATOR_MORE, lexeme, currentLine, currentPosition);
            }
        }

        // Estado para operador '&&'
        if (atual=='&') // Indice do operador
        {
            if (buffer.eof()) {
                throw new LexerException("Operador AND inválido", lexeme, currentLine, currentPosition);
            }

            if (buffer.lookAhead() == '&') {
                lexeme += this.buffer.next();
                return new Token(TokenClass.OPERATOR_AND, lexeme, currentLine, currentPosition);
            } else {
                throw new LexerException("Operador AND inválido", lexeme, currentLine, currentPosition);
                //aqui tem que retorna um erro,
                //pois '&' sozinho não é nada na nossa linguagem
            }
        }

        // Estado para operador '||'
        if (atual=='|') {
            if (buffer.eof()) {
                throw new LexerException("Operador PIPE inválido", lexeme, currentLine, currentPosition);
            }

            if (buffer.lookAhead() == '|') {
                lexeme += this.buffer.next();
                return new Token(TokenClass.OPERATOR_OR, lexeme, currentLine, currentPosition);
            } else {
                throw new LexerException("Operador OR inválido", lexeme, currentLine, currentPosition);
            }
        }

        // Estado para operador '/'
        if (atual=='/') {

            return new Token(TokenClass.OPERATOR_DIVISION, lexeme, currentLine, currentPosition);

        }

        // Estado para operador '.'
        if (atual=='.') {
            return new Token(TokenClass.OPERATOR_POINT, lexeme, currentLine, currentPosition);
        }

        // Estado para o operador *
        if (atual=='*') {
            return new Token(TokenClass.OPERATOR_MULT, lexeme, currentLine, currentPosition);
        }

        return null; // se nao for operador algum       

    } // fim do método do reconhecimento de operador

    @Override
    public TokenClass selectTokenClass(String lexema) {
        return null;
    }

    @Override
    public boolean isFinalizer(char character) {
        return true;
    }
}
