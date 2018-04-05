/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author caco
 */
public abstract class CompilostException extends Exception {

    /*String com a descrição do tipo de erro*/
    protected String descricao;
    /*Coluna onde ocorreu o erro*/
    protected int posicao;

    /*linha onde ocorreu o erro*/
    protected int linha;

    /*
     * Devolve uma String com o informações sobre o erro ocorrido, apenas o erro.
     */
    public String getDescricao() {
        return descricao;
    }

    /*
     * Devolve a linha onde o erro ocorreu;
     */
    public int getLinha() {
        return linha;
    }

    /*
     * Devolve a coluna onde o erro ocorreu;
     */
    public int getPosicao() {
        return posicao;
    }

    public abstract String getErrorMessage();
}
