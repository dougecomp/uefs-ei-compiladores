/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package semantico;

import java.util.ArrayList;
import lexico.Token;

/**
 *
 * @author Odontoradiosis
 */
public class SymbolTable extends ArrayList<SymbolTableEntry>{


    private static SymbolTable instance = new SymbolTable();

    private int currentScope = 0;


    private SymbolTable(){ }


    public static SymbolTable getInstance(){
        return instance;
    }


    public static void restartTable(){
        instance = new SymbolTable();
    }

    public void newScope(){
        currentScope++;
    }

    public void backScope(){
        currentScope--;
    }

    public int getScope(){
        return currentScope;
    }

    public SymbolTableEntry search(Token t){

        SymbolTableEntry ste = null;

        for (int i = 0; i < size(); i++) {

            if( get(i).getToken().getLexeme().equals( t.getLexeme() ) ){
                if(ste == null){

                    ste = get(i);
                    continue;
                }

                if(get(i).getScopeLevel() > ste.getScopeLevel()){
                    ste = get(i);
                    continue;
                }

            }// fim do primeito if;

        }// fim do for(int i);

        return ste;
    }

}