/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

/**
 *
 * @author weverton
 */
public class MaisVendidos {
    
    private String nome;
    private Integer totalItens;
   
    
    public MaisVendidos()
    {
        
    }

    /**
     * @return the totalItens
     */
    public Integer getTotalItens() {
        return totalItens;
    }

    /**
     * @param totalItens the totalItens to set
     */
    public void setTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
