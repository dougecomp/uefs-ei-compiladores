package lexico;

import exceptions.CompilostException;
import exceptions.ParserException;
import exceptions.SemanticException;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ErrorList extends ArrayList<CompilostException> {

    public ErrorList() {
    }

    public void pushBackLexerException(CompilostException ce) {
        add(ce);
    }

    /**
     * Talvez a responsabilidade de tratamento dos tokens seja dentro da classe
     * e não fora. Algo como o currentToken e getNextToken. Porém este efeito
     * pode ser conseguido facilmente pela iteração da Estrutura de dados fora
     * daqui.
     * @return
     */
    public ArrayList<CompilostException> getExceptions() {
        return this;
    }

    /**
     * Imprime a lista de tokens
     */
    public void printErrors() {
        System.out.println("Erros: ");

        for (CompilostException ce : this) {
            System.out.print("| " + ce.getDescricao() + " ");
//            System.out.print("| \"" + ce.getLexema() + "\" ");
            System.out.print("| " + ce.getLinha() + " ");
            System.out.println("| " + ce.getPosicao() + " |");
        }
    }

    public void addParserError(TokenList st, String msg) {
        if (st.eof()) {
            add(new ParserException(msg + "\nFinal de arquivo alcançado prematuramente.", -1, -1));
        } else {
            add(new ParserException(msg, st.getCurrent().getLine(), st.getCurrent().getPosition()));
        }
    }
    
    public void addSemanticError(String msg){
//        add(new SemanticException(msg, st.getCurrent().getLine(), st.getCurrent().getPosition()));
        add(new SemanticException(msg, -1, -1));
    }
}
