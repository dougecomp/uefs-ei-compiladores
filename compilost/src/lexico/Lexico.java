package lexico;

import automatos.Automaton;
import automatos.DelimiterAutomaton;
import automatos.IdentifierAutomaton;
import automatos.NumericAutomaton;
import automatos.OperatorAutomaton;
import automatos.StringAutomaton;
import exceptions.LexerException;

/**
 * @author heekinho
 */
public class Lexico {

    private CodeBuffer buffer;
    private TokenList tokenList;
    private ErrorList errorList;
    private Automaton delimiterAutomaton;
    private Automaton operatorAutomaton;
    private Automaton stringAutomaton;
    private Automaton identifierAutomaton;
    private Automaton numericAutomaton;

    /**
     * Realiza o processamento Léxico do código contido no buffer.
     * @param buffer
     */
    public Lexico(CodeBuffer buffer) {
        this.buffer = buffer;
        makeAutomata();

    }

    private void makeAutomata() {
        delimiterAutomaton = new DelimiterAutomaton(buffer);
        operatorAutomaton = new OperatorAutomaton(buffer);
        stringAutomaton = new StringAutomaton(buffer);
        identifierAutomaton = new IdentifierAutomaton(buffer);
        numericAutomaton = new NumericAutomaton(buffer);
        /* TODO: Colocar outros automatos */
    }

    /* Como ficou decidido na seção tutorial, o switch vai ser a partir do
     * primeiro char do possível token. É necessário guardar as informações
     * do primeiro caractere, até que a leitura de um novo token começe a ser
     * realizada. */
    private char currentChar;		// Char onde começou o token.
    private int currentLine;		// Linha deste primeiro char.
    private int currentPosition;	// Coluna deste primeiro char.

    /**
     * Realiza o scan propriamente dito do código.
     */
    public void scan() {

        tokenList = new TokenList();
        errorList = new ErrorList();

        while (!buffer.eof()) {

            try {
                /* Atualiza informação do primeiro caracter do próximo token.
                 * Deve ser usado posteriormente para compor o token */
                currentChar = buffer.lookAhead();
                currentLine = buffer.getLine();
                currentPosition = buffer.getPositionOnLine();


                if (identifierAutomaton.startsWith(currentChar)) {
                    tokenList.pushBackToken(identifierAutomaton.process());
                    continue;
                }

                if (numericAutomaton.startsWith(currentChar)) {
                    tokenList.pushBackToken(numericAutomaton.process());
                    continue;
                }
                if (operatorAutomaton.startsWith(currentChar)) {
                    tokenList.pushBackToken(operatorAutomaton.process());
                    continue;
                }
                if (stringAutomaton.startsWith(currentChar)) {
                    tokenList.pushBackToken(stringAutomaton.process());
                    continue;
                }
                if (delimiterAutomaton.startsWith(currentChar)) {
                    tokenList.pushBackToken(delimiterAutomaton.process());
                    continue;
                }

                //se nao pular nenhum um espaco em branco, tab ou \n, eh erro
                if (!buffer.skipUnnecessaryCode()) {
                    throw new LexerException("Caractere inválido!", String.valueOf(buffer.next()), currentLine, currentPosition);
                }

            } catch (LexerException e) {
                errorList.pushBackLexerException(e);
            }
        }


    }

    public ErrorList getErrorList() {
        return errorList;
    }

    public TokenList getTokenList() {
        return tokenList;
    }

    public CodeBuffer getBuffer() {
        return this.buffer;
    }
}
