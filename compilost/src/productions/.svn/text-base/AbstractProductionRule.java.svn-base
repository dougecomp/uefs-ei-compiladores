package productions;

import exceptions.ParserException;
import java.util.ArrayList;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SymbolTableEntry;

/**
 * @author eder
 * @author caco
 * @author heekinho
 */
public abstract class AbstractProductionRule {

    protected ArrayList<TokenClass> first = new ArrayList<TokenClass>();
    protected ArrayList<TokenClass> follow = new ArrayList<TokenClass>();
    protected ArrayList<TokenClass> sync = new ArrayList<TokenClass>();

    protected AbstractProductionRule() {
        this.populateFirst();
        this.populateFollow();
        this.populateSync();
    }

    
    
    protected boolean testEofAndCurrentToken(TokenList tl, TokenClass tc){
        if(!tl.eof() && tc == tl.getCurrent().getTokenClass()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Realiza a analise sintatica para a determinada regra
     *
     * @param tl a lista de simbolos
     * @param el a lista de erros
     * @return true se a sequencia foi reconhecida, falso caso contrario
     */
//    public abstract boolean analyze(TokenList st, ErrorList et) throws ParserException;
    public abstract SymbolTableEntry analyze(TokenList tl, ErrorList el);


    /**
     * Popula o conjunto primeiro da regra.
     *
     */
    protected abstract void populateFirst();

    /**
     * Popula o conjunto seguinte da regra.
     *
     */
    protected abstract void populateFollow();

    /**
     * Popula o conjunto de sincronizacao
     *
     */
    protected void populateSync() {
        sync = follow;
    }

    /**
     * Informa se <code>tclass</code> esta no conjunto primeiro da regra
     * @param tclass um TokenClass
     * @return true se sim, falso caso contrario
     */
    public boolean isFirst(TokenClass tclass) {
        return first.contains(tclass);
    }

    /**
     * Informa se <code>tclass</code> esta no conjunto seguinte da regra
     * @param tclass um TokenClass
     * @return true se sim, falso caso contrario
     */
    public boolean isFollow(TokenClass tclass) {
        return follow.contains(tclass);
    }

    /**
     * Informa se <code>tclass</code> esta no conjunto de sincronizacao da regra
     * @param tclass um TokenClass
     * @return true se sim, falso caso contrario
     */
    public boolean isSynchronizer(TokenClass tclass) {
        return sync.contains(tclass);
    }

    /**
     * Pula os tokens ate chegar em um token do conjunto de sincronizacao,
     * alem de adicionar uma mensagem de erro relevante
     * @param st
     */
    protected SymbolTableEntry skipUntilSynchronize(TokenList st, ErrorList et, String message) {

        et.addParserError(st, message);

        while (!st.eof() && !isSynchronizer(st.getCurrent().getTokenClass())) {
            st.consumeCurrent();
        }
        
        return new SymbolTableEntry();


    }
}
