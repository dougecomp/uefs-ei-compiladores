/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author caco
 */
public class SemanticException extends CompilostException{

    
    public SemanticException(String descricao, int linha, int posicao) {
        this.descricao = descricao;
        this.linha = linha;
        this.posicao = posicao;
    }
    
    
    
    @Override
    public String getErrorMessage() {
        return descricao + "\", linha: " + linha + ", coluna: " + posicao;
    }
    
    

}
