
package semantico;

import java.util.ArrayList;
import lexico.Token;

/**
 *
 * @author caco
 */
public class SymbolTableEntry {

    /**
     * Inteiro, real, string...
     */
    private SemanticType type;


    /**
     * Funcao, variavel, registro, array...
     */
    private SemanticStructure structure;


    /**
     * O token
     */
    private Token token;

    /**
     * Escopo do elemento
     */
    private int scopeLevel;

    /*
     * lista de parametros;
     */
     private ArrayList<SymbolTableEntry> parameters;

    /**
     * @return the type
     */
    public SemanticType getType() {
        return type;
    }

    public SymbolTableEntry(SemanticType type, SemanticStructure structure,  Token token, int scopeLevel) {
        this.type = type;
        this.token = token;
        this.scopeLevel = scopeLevel;

        parameters = new ArrayList<SymbolTableEntry>();
    }

    public SymbolTableEntry(){
        this( SemanticType.TYPE_INDEFINIDO, SemanticStructure.UNDEFINED, null, -1);
    }


    /**
     * @param type the type to set
     */
    public void setType(SemanticType type) {
        this.type = type;
    }

    /**
     * @return the token
     */
    public Token getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * @return the scopeLevel
     */
    public int getScopeLevel() {
        return scopeLevel;
    }

    /**
     * @param scopeLevel the scopeLevel to set
     */
    public void setScopeLevel(int scopeLevel) {
        this.scopeLevel = scopeLevel;
    }

    /**
     * @return the structure
     */
    public SemanticStructure getStructure() {
        return structure;
    }

    /**
     * @param structure the structure to set
     */
    public void setStructure(SemanticStructure structure) {
        this.structure = structure;
    }

    /*
     * retorna a lista de parâmetros para o usuário inseri-los;
     */
    public ArrayList<SymbolTableEntry> getParameters(){

            return parameters;
    }

    /*
     * retorna o número de parãmetros;
     */
    public int getNumberParameters(){
        return parameters.size();
    }

}
