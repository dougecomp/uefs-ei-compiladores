/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import exceptions.LexerException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author Cacovsky
 */
public final class CodeBuffer {

    private String code = "";
    private int currentCharPosition = 0;
    private int currentLinePosition = 0;
    private int currentLineNumber = 1;
    private ArrayList<CommentBookmark> marcacoesComentarios;

    public CodeBuffer() {
    }

    /**
     * Instancia um CodeBuffer para o codigo <code>code</code>.
     * @param code
     */
    public CodeBuffer(String code) {
        setCode(code);
    }

    /**
     * Define um codigo fonte
     * @param code
     */
    public final void setCode(String code) {
        this.code = code;
        currentCharPosition = 0;
        currentLinePosition = 0;
        currentLineNumber = 1;
        marcacoesComentarios = new ArrayList<CommentBookmark>();
        bookmarkComments();
//        trySkipLineBreaksAndComments();
    }

    /**
     * Assinala os comentarios do codigo
     */
    private void bookmarkComments() {


        //para restaurar depois
        int originalCurrentCharPosition = currentCharPosition;
        int originalCurrentLineNumber = currentLineNumber;
        int originalCurrentLinePosition = currentLinePosition;


        //varrer ate a ultima posicao do codigo
        while (!isEndOfCode()) {

            int estado = 0;
            boolean terminar = false;
            boolean toBookmark = false;

            int auxCurrentCharPosition = currentCharPosition;
            int auxCurrentLineNumber = currentLineNumber;
            int auxCurrentLinePosition = currentLinePosition;

            /* inicio do automato */
            while (!isEndOfCode() && !terminar) {
                switch (estado) {

                    case 0:
                        if (currentChar() == '/') {
                            nextChar();
                            estado = 1;
                        } else {
                            estado = -1;
                            nextChar();
                            terminar = true;
                        }
                        break;

                    case 1:
                        if (currentChar() == '/') {
                            nextChar();
                            estado = 2;
                            toBookmark = true;
                        } else if (currentChar() == '*') {
                            nextChar();
                            estado = 4;
                            toBookmark = true;
                        } else {
                            terminar = true;
                            nextChar();
                            estado = -1;
                        }
                        break;

                    case 2:
                        if (nextChar() == '\n') {
                            estado = 3;
                        }
                        break;

                    case 3:
                        terminar = true;
                        break;

                    case 4:
                        if (nextChar() == '*') {
                            estado = 5;
                        }
                        break;

                    case 5:
                        if (currentChar() == '*') {
                            nextChar();
                        } else if (currentChar() != '/') {
                            nextChar();
                            estado = 4;
                        } else {
                            nextChar();
                            estado = 6;
                        }
                        break;

                    case 6:
                        terminar = true;
                        break;
                }
            }
            /* fim do automato */

            if (estado != -1 && toBookmark) {
                CommentBookmark mark = new CommentBookmark();

                //estados finais
                mark.comentarioFinalizado = (estado == 2 || estado == 3 || estado == 6);

                mark.lineNumberFrom = auxCurrentLineNumber;
                mark.lineNumberTo = currentLineNumber;
                mark.linePositionFrom = auxCurrentLinePosition;
                mark.linePositionTo = currentLinePosition;
                mark.charPositionFrom = auxCurrentCharPosition;
                mark.charPositionTo = currentCharPosition;

                marcacoesComentarios.add(mark);

            }

        }


        //resetando valores
        currentCharPosition = originalCurrentCharPosition;
        currentLineNumber = originalCurrentLineNumber;
        currentLinePosition = originalCurrentLinePosition;

    }

    /**
     * Retorna o codigo fonte
     * @return
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Ignora quebras de linha e espacos em branco subsequentes
     */
    private void skipLineBreaksAndComments() throws LexerException {
        int posAtual = -1;
        while (posAtual != currentCharPosition) {
            posAtual = currentCharPosition;
            skipLineBreaks();
            jumpComments();
        }
    }

    private void trySkipLineBreaksAndComments() {
        int posAtual = -1;
        while (posAtual != currentCharPosition) {
            posAtual = currentCharPosition;
            skipLineBreaks();
            tryJumpComments();
        }
    }

    /**
     * consome e retorna o caractere atual
     * @return o caractere atual
     */
    public char next() throws LexerException {

        skipLineBreaksAndComments();

        if (eof()) {
            throw new NoSuchElementException();
        }


        return nextChar();
    }

    /**
     * Testa se ha um caractere uma posicao aa frente
     * @return
     */
    public boolean hasDoubleLookAhead() {
        return (code.length() > currentCharPosition + 1);
    }

    /**
     * Retorna o caractere uma posicao aa frente
     * @return
     */
    public char doubleLookAhead() {
        if (code.length() <= currentCharPosition + 1) {
            throw new NoSuchElementException();
        }
        return code.charAt(currentCharPosition + 1);
    }

    /**
     * Mostra o caractere seguint, sem consumir
     * @return o caractere seguinte
     */
    public char lookAhead() throws LexerException {

        skipLineBreaksAndComments();

        if (isEndOfCode()) {
            throw new NoSuchElementException();
        }
        return currentChar();

    }

    private char currentChar() {
        return code.charAt(currentCharPosition);
    }

    /**
     * Verifica se chegou ao final do arquivo, automaticamente ignhorando
     * quebras de linha e espacos em branco.
     * @return
     */
    public boolean eof() {
        trySkipLineBreaksAndComments();

        return isEndOfCode();
    }

    /**
     * Retorna a linha atual de leitura.
     * Note que este método considera o primeiro caracter como linha 1.
     * @return
     */
    public int getLine() {
        return currentLineNumber;
    }

    /**
     * Retorna a posição na linha do caractere atual de leitura. "Coluna".
     * Note que este método considera o primeiro caracter como posição 1.
     * @return coluna do caractere
     */
    public int getPositionOnLine() {
        return (currentCharPosition - currentLinePosition);
    }

    /**
     * Retorna a posição o índice do caractere atual de leitura
     * da String do buffer.
     */
    public int getCurrentChar() {
        return currentCharPosition;
    }

    /**
     * Verifica se <code>aChar</code> eh um espaco em branco, tabulacao
     * ou quebra de linha
     * @param aChar
     * @return
     */
    public boolean isDelimiter(char aChar) {
        return aChar == ' ' || aChar == '\t' || aChar == '\n';
    }

    /**
     * Pula comentarios a partir da posicao atual
     */
    private void jumpComments() throws LexerException {
        for (CommentBookmark mark : marcacoesComentarios) {
            //se currentCharPosition estiver numa posicao de comentario
            if (mark.charPositionFrom <= currentCharPosition && currentCharPosition <= mark.charPositionTo) {
                if (!mark.comentarioFinalizado) {
                    //eof() na mao.
                    currentCharPosition = code.length();
                    throw new LexerException("Comentário não finalizado.", "/*...", currentLineNumber, getPositionOnLine());
                } else {
                    currentCharPosition = mark.charPositionTo;
                    currentLineNumber = mark.lineNumberTo;
                    currentLinePosition = mark.linePositionTo;
                }



            }
        }
    }

    private void tryJumpComments() {
        for (CommentBookmark mark : marcacoesComentarios) {
            //se currentCharPosition estiver numa posicao de comentario
            if (mark.comentarioFinalizado && mark.charPositionFrom <= currentCharPosition && currentCharPosition <= mark.charPositionTo) {
                currentCharPosition = mark.charPositionTo;
                currentLineNumber = mark.lineNumberTo;
                currentLinePosition = mark.linePositionTo;
            }
        }
    }

    /**
     * Verifica se currentCharPosition esta no final do arquivo
     * @return
     */
    private boolean isEndOfCode() {
        return this.currentCharPosition == this.code.length();
    }

    /**
     * Este metodo soh retorna o prximo caractere, fazendo o controle das
     * numeracoes das linhas.
     * Qualquer pedido de novo caracter deve ser feito atraves dele,
     * direta ou indiretamente. Filtros como pular comentarios, pular espacos em
     * branco ou pular quebras de linha devem ser feitas sempre acessando este
     * metodo, para manter a integridade dos numero das linhas.
     * @return o proximo char do buffer, qualquer que seja.
     */
    private char nextChar() {

        /* Controle de linhas e posição */
        if (code.charAt(currentCharPosition) == '\n') {
            currentLineNumber++;
            currentLinePosition = currentCharPosition + 1;
        }

        return code.charAt(currentCharPosition++);
    }

    /**
     * Pula espacos em branco, quebras de linha e tabulacoes
     */
    private void skipDelimiters() {
        while (!isEndOfCode() && isDelimiter(currentChar())) {
            nextChar();
        }
    }

    public boolean skipUnnecessaryCode() {
        int posAtual = -1;
        boolean mudouPosicao = false;
        while (posAtual != currentCharPosition) {
            posAtual = currentCharPosition;
            skipDelimiters();
            tryJumpComments();

            mudouPosicao = mudouPosicao || posAtual != currentCharPosition;
        }

        return mudouPosicao;
    }

    /**
     * Pula  quebras de linha
     */
    private void skipLineBreaks() {
        while (!isEndOfCode() && currentChar() == '\n') {
            nextChar();
        }
    }

    public void printBookmarks() {

        System.out.println(marcacoesComentarios.size() + " comentarios encontrados.");
        for (CommentBookmark mark : marcacoesComentarios) {
            System.out.println("Marcacao em:");
            System.out.println(mark.comentarioFinalizado + " | "
                    + mark.lineNumberFrom + " | " + mark.lineNumberTo + " | "
                    + mark.linePositionFrom + " | " + mark.linePositionTo + " | "
                    + mark.charPositionFrom + " | " + mark.charPositionTo);
            System.out.println();
        }

    }
}
