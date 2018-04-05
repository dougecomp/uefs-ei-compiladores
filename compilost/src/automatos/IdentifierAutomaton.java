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
public class IdentifierAutomaton extends Automaton {

    private List<String> keywords =
            Arrays.asList(new String[]{"algoritmo", "variaveis", "constantes",
                "registro", "tipo", "funcao", "retorno", "vazio", "principal", "se",
                "entao", "senao", "enquanto", "para", "leia", "escreva", "inteiro",
                "real", "logico", "caractere", "string", "cast", "verdadeiro", "falso"});

    private List<Character> starts = Arrays.asList(new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'});
    
    
    public IdentifierAutomaton(CodeBuffer buffer) {
        super(buffer);
    }
    
    public boolean startsWith(char c){
        return starts.contains(Character.toLowerCase(c));
    }
    

    @Override
    public boolean isFinalizer(char aChar) {
        return (buffer.isDelimiter(aChar) || aChar == '=' || aChar == '+' || aChar == '-'
                || aChar == '/' || aChar == '(' || aChar == ')' || aChar == '[' || aChar == ']'
                || aChar == '{' || aChar == '}' || aChar == ' ' || aChar == '\"' || aChar == ';'
                || aChar == '*' || aChar == '.' || aChar == '&' || aChar == '|' || aChar == '>'
                || aChar == '<' || aChar == ' ' || aChar == ',');
    }

    @Override
    public Token process() throws LexerException {


        String lexeme = "";
        byte estado = 0; //Estado do automato
        boolean continua = true;
        int currentLine = buffer.getLine();
        int currentPosition = buffer.getPositionOnLine();

        while (continua) {
            switch (estado) {

                //identificador soh comeca com letra
                case 0:

                    if (this.startsWith(buffer.lookAhead())) {
                        lexeme = String.valueOf(buffer.next());
                        estado = 1;
                    } else {
                        estado = 3;
                        continua = false;
                    }
                    break;

                //aqui ele pode ter letra, numero ou underscore, desde que nao tenha acabado
                case 1:
                    if (!buffer.eof() && !isFinalizer(buffer.lookAhead()) && (this.startsWith(buffer.lookAhead()) || Character.isDigit(buffer.lookAhead()) || buffer.lookAhead() == '_')) {
                        lexeme += buffer.next();
                    } //se acabou ou tem um finalizador correto
                    else if (buffer.eof() || isFinalizer(buffer.lookAhead())) {
                        estado = 2;
                        continua = false;
                    } //lascou
                    else {
                        estado = 3;
                        continua = false;
                    }
                    break;

                case 2:
                    continua = false;
                    break;
            }
        }

        if (estado == 3) {
            buffer.next();
            while (!buffer.eof() && !isFinalizer(buffer.lookAhead())) {
                buffer.next();
            }
            throw new LexerException("Caractere inválido para identificador", lexeme + "...", buffer.getLine(), buffer.getPositionOnLine());
        }

        //tcharam!
        return new Token(selectTokenClass(lexeme), lexeme, currentLine, currentPosition);
    }

    @Override
    public TokenClass selectTokenClass(String lexema) {

        if (lexema.equals("algoritmo")) {
            return TokenClass.KEYWORD_ALGORITMO;
        } else if (lexema.equals("cast")) {
            return TokenClass.KEYWORD_CAST;
        } else if (lexema.equals("variaveis")) {
            return TokenClass.KEYWORD_VARIAVEIS;
        } else if (lexema.equals("constantes")) {
            return TokenClass.KEYWORD_CONSTANTES;
        } else if (lexema.equals("registro")) {
            return TokenClass.KEYWORD_REGISTRO;
        } else if (lexema.equals("tipo")) {
            return TokenClass.KEYWORD_TIPO;
        } else if (lexema.equals("funcao")) {
            return TokenClass.KEYWORD_FUNCAO;
        } else if (lexema.equals("retorno")) {
            return TokenClass.KEYWORD_RETORNO;
        } else if (lexema.equals("vazio")) {
            return TokenClass.KEYWORD_VAZIO;
        } else if (lexema.equals("principal")) {
            return TokenClass.KEYWORD_PRINCIPAL;
        } else if (lexema.equals("se")) {
            return TokenClass.KEYWORD_SE;
        } else if (lexema.equals("entao")) {
            return TokenClass.KEYWORD_ENTAO;
        } else if (lexema.equals("senao")) {
            return TokenClass.KEYWORD_SENAO;
        } else if (lexema.equals("enquanto")) {
            return TokenClass.KEYWORD_ENQUANTO;
        } else if (lexema.equals("para")) {
            return TokenClass.KEYWORD_PARA;
        } else if (lexema.equals("leia")) {
            return TokenClass.KEYWORD_LEIA;
        } else if (lexema.equals("escreva")) {
            return TokenClass.KEYWORD_ESCREVA;
        } else if (lexema.equals("inteiro")) {
            return TokenClass.KEYWORD_INTEIRO;
        } else if (lexema.equals("real")) {
            return TokenClass.KEYWORD_REAL;
        } else if (lexema.equals("logico")) {
            return TokenClass.KEYWORD_LOGICO;
        } else if (lexema.equals("caractere")) {
            return TokenClass.KEYWORD_CARACTERE;
        } else if (lexema.equals("string")) {
            return TokenClass.KEYWORD_STRING;
        } else if (lexema.equals("verdadeiro")) {
            return TokenClass.KEYWORD_VERDADEIRO;
        } else if (lexema.equals("falso")) {
            return TokenClass.KEYWORD_FALSO;
        }

//        else if (lexema.equals("nulo"))      return TokenClass.KEYWORD_NULO;

        //default
        return TokenClass.IDENTIFIER;

    }
}
