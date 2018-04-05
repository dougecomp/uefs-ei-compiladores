package sintatico;

import exceptions.ParserException;
import java.util.ArrayList;
import lexico.ErrorList;
import lexico.Token;
import lexico.TokenClass;
import lexico.TokenList;
import productions.*;
import semantico.SemanticStructure;
import semantico.SemanticType;
import semantico.SymbolTable;
import semantico.SymbolTableEntry;

/**
 * @author Ronaldo
 */
/**
 * Inicia a execução do parser
 */
public class Parser {

    private AbstractProductionRule pr;
    private TokenList st;
    private ErrorList et;
//    private ErrorTableParser etp;

    /**
     *
     * recebe a tabela de simbolos e a tabela de erros.
     * Cada tabela será enviada para cada uma das produções;
     */
    public Parser(TokenList st, ErrorList et) {
        this.et = et;
        this.st = st;
    }

    public void scan() throws ParserException {
        StartProduction.getInstance().analyze(st, et);
    }

    public ErrorList getErrorTable() {
        return et;
    }

    /*
     * Realiza o pré-processamento das funções e os insere na tabela de funções.
     */
    public void preProcess(){

        Token t = null;
        int state = 0;
        SymbolTableEntry entrada = null;
        ArrayList<SymbolTableEntry> parametros = null;


        // varre toda a lista atras de funções;
        while(!st.eof()){

            t =st.consumeCurrent();

            switch(state){

                case 0: if(t.getTokenClass() == TokenClass.KEYWORD_FUNCAO){
                            state = 1;
                            continue;
                        }
                        break;

                case 1: if(t.getTokenClass() == TokenClass.KEYWORD_VAZIO){
                            state = 2;
                            continue;
                        }
                        if(t.getTokenClass() == TokenClass.KEYWORD_INTEIRO){
                            state = 3;
                            continue;
                        }
                        if(t.getTokenClass() == TokenClass.KEYWORD_REAL){
                            state = 4;
                            continue;
                        }
                        if(t.getTokenClass() == TokenClass.KEYWORD_CARACTERE){
                            state = 5;
                            continue;
                        }
                        if(t.getTokenClass() == TokenClass.KEYWORD_STRING) {
                            state = 6;
                            continue;
                        }
                        state = 0;
                        break;

                case 2: if(t.getTokenClass() == TokenClass.IDENTIFIER){
                            entrada = new SymbolTableEntry(
                                    SemanticType.TYPE_VAZIO, SemanticStructure.VARIABLE, t, 0);
                            state = 7;
                            continue;
                        }
                        state = 0;
                        break;

                case 3: if(t.getTokenClass() == TokenClass.IDENTIFIER){
                            entrada = new SymbolTableEntry(
                                    SemanticType.TYPE_INTEIRO, SemanticStructure.VARIABLE, t, 0);
                            state = 7;
                            continue;
                        }
                        state = 0;
                        break;

                case 4: if(t.getTokenClass() == TokenClass.IDENTIFIER){
                            entrada = new SymbolTableEntry(
                                    SemanticType.TYPE_REAL, SemanticStructure.VARIABLE, t, 0);
                            state = 7;
                            continue;
                        }
                        state = 0;
                        break;

                case 5: if(t.getTokenClass() == TokenClass.IDENTIFIER){
                            entrada = new SymbolTableEntry(
                                    SemanticType.TYPE_CARACTERE, SemanticStructure.VARIABLE, t, 0);
                            state = 7;
                            continue;
                        }
                        state = 0;
                        break;

                case 6: if(t.getTokenClass() == TokenClass.IDENTIFIER){
                            entrada = new SymbolTableEntry(
                                    SemanticType.TYPE_STRING, SemanticStructure.VARIABLE, t, 0);
                            state = 7;
                            continue;
                        }
                        state = 0;
                        break;

                case 7: if(t.getTokenClass() == TokenClass.DELIMITER_OPENING_PARENTHESIS){
                            state = 8;
                            parametros = entrada.getParameters();
                            continue;
                        }
                        state = 0;
                        break;

                case 8: if(t.getTokenClass() == TokenClass.KEYWORD_VAZIO){
                            t = st.consumeCurrent();
                            if(t.getTokenClass() == TokenClass.IDENTIFIER){
                                SymbolTableEntry en = new SymbolTableEntry(
                                        SemanticType.TYPE_VAZIO, SemanticStructure.VARIABLE, t, 0);
                                parametros.add(en);
                                state = 9;
                                continue;
                            }
                            state = 0;
                            continue;
                        }
                        if(t.getTokenClass() == TokenClass.KEYWORD_INTEIRO){
                            t = st.consumeCurrent();
                            if(t.getTokenClass() == TokenClass.IDENTIFIER){
                                SymbolTableEntry en = new SymbolTableEntry(
                                        SemanticType.TYPE_INTEIRO, SemanticStructure.VARIABLE, t, 0);
                                parametros.add(en);
                                state = 9;
                                continue;
                            }
                            state = 0;
                            continue;
                        }
                        if(t.getTokenClass() == TokenClass.KEYWORD_REAL){
                            t = st.consumeCurrent();
                            if(t.getTokenClass() == TokenClass.IDENTIFIER){
                                SymbolTableEntry en = new SymbolTableEntry(
                                        SemanticType.TYPE_REAL, SemanticStructure.VARIABLE, t, 0);
                                parametros.add(en);
                                state = 9;
                                continue;
                            }
                            state = 0;
                            continue;
                        }
                        if(t.getTokenClass() == TokenClass.KEYWORD_CARACTERE){
                            t = st.consumeCurrent();
                            if(t.getTokenClass() == TokenClass.IDENTIFIER){
                                SymbolTableEntry en = new SymbolTableEntry(
                                        SemanticType.TYPE_CARACTERE, SemanticStructure.VARIABLE, t, 0);
                                parametros.add(en);
                                state = 9;
                                continue;
                            }
                            state = 0;
                            continue;
                        }
                        if(t.getTokenClass() == TokenClass.KEYWORD_STRING){
                            t = st.consumeCurrent();
                            if(t.getTokenClass() == TokenClass.IDENTIFIER){
                                SymbolTableEntry en = new SymbolTableEntry(
                                        SemanticType.TYPE_STRING, SemanticStructure.VARIABLE, t, 0);
                                parametros.add(en);
                                state = 9;
                                continue;
                            }
                            state = 0;
                            continue;
                        }
                
                        state = 0;
                        break;

                case 9: if(t.getTokenClass() == TokenClass.DELIMITER_COMMA){
                            state = 8;
                            continue;
                        }
                        if(t.getTokenClass() == TokenClass.DELIMITER_CLOSING_PARENTHESIS){
                            SymbolTable.getInstance().add(entrada);
                            state = 0;
                            continue;
                        }
                break;

            }
        } // fim do WHILE( ST.EOF() )

    } // fim do método preProcess();

    public static void main(String[] args) {

        for (TokenClass c : TokenClass.values() ) {
            System.out.println(c + ", "+ c.hashCode());
        }
    }

} // fim da classe PARSER;
