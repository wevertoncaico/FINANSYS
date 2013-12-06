/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import auxiliar.MaisVendidos;
import dao.ItemJpaController;
import dao.ProdutoJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Item;
import modelo.Produto;
import util.EMF;

/**
 *
 * @author Monnalisa Christina
 */
@ManagedBean
@RequestScoped
public class MaisVendidosMB {

    private List<MaisVendidos> listaMaisVendidos = new ArrayList<MaisVendidos>();
    private MaisVendidos maisV = new MaisVendidos();
    private Item item = new Item();
    private Produto produto = new Produto();
    private ItemJpaController daoItem = new ItemJpaController(EMF.getEntityManagerFactory());
    private ProdutoJpaController daoProduto = new ProdutoJpaController(EMF.getEntityManagerFactory());

    public MaisVendidosMB() {
        listarProdutosVendidos();
    }

    /**
     * @return the listaMaisVendidos
     */
    public List<MaisVendidos> getListaMaisVendidos() {
        return listaMaisVendidos;
    }

    /**
     * @param listaMaisVendidos the listaMaisVendidos to set
     */
    public void setListaMaisVendidos(List<MaisVendidos> listaMaisVendidos) {
        this.listaMaisVendidos = listaMaisVendidos;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the maisV
     */
    public MaisVendidos getMaisV() {
        return maisV;
    }

    /**
     * @param maisV the maisV to set
     */
    public void setMaisV(MaisVendidos maisV) {
        this.maisV = maisV;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void listarProdutosVendidos() {

        String nome = " ";
        Integer totalItens = 0;
        maisV = new MaisVendidos();

        for (Produto p : daoProduto.findProdutoEntities()) {

            for (Item i : daoItem.findItemEntities()) {

                if (p.getId() == i.getProduto().getId()) {

                    if (totalItens == 0) {

                        nome = p.getNome();
                        totalItens = i.getQuantidade();

                    } else {

                        totalItens += i.getQuantidade();
                    }

                }

            }
            maisV.setNome(nome);
            maisV.setTotalItens(totalItens);
            listaMaisVendidos.add(maisV);
            nome = "";
            totalItens = 0;
            maisV = new MaisVendidos();
        }

    }

}

