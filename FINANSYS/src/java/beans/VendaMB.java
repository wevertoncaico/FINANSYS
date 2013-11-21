package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.EMF;
import modelo.Produto;
import modelo.Item;
import modelo.Venda;
import java.util.List;
import dao.VendaJpaController;
import java.util.ArrayList;

/**
 *
 * @author Monnalisa Christina
 */
@ManagedBean
@SessionScoped
public class VendaMB {

    private Produto p = new Produto();
    private Venda v = new Venda();
    private Item i = new Item();
    private Integer quantidade;
    private boolean mostrarTotalVenda = false;
    private boolean mostrarMensagemSucesso = false;
    private VendaJpaController daoVenda = new VendaJpaController(EMF.getEntityManagerFactory());

    public VendaMB() {
        //limparVenda();
    }

    /**
     * @return the p
     */
    public Produto getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Produto p) {
        this.p = p;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the v
     */
    public Venda getV() {
        return v;
    }

    /**
     * @param v the v to set
     */
    public void setV(Venda v) {
        this.v = v;
    }

    /**
     * @return the i
     */
    public Item getI() {
        return i;
    }

    /**
     * @param i the i to set
     */
    public void setI(Item i) {
        this.i = i;
    }

    /**
     * @return the mostrarTotalVenda
     */
    public boolean isMostrarTotalVenda() {
        return mostrarTotalVenda;
    }

    /**
     * @param mostrarTotalVenda the mostrarTotalVenda to set
     */
    public void setMostrarTotalVenda(boolean mostrarTotalVenda) {
        this.mostrarTotalVenda = mostrarTotalVenda;
    }

    /**
     * @return the mostrarMensagemSucesso
     */
    public boolean isMostrarMensagemSucesso() {
        return mostrarMensagemSucesso;
    }

    /**
     * @param mostrarMensagemSucesso the mostrarMensagemSucesso to set
     */
    public void setMostrarMensagemSucesso(boolean mostrarMensagemSucesso) {
        this.mostrarMensagemSucesso = mostrarMensagemSucesso;
    }

    public void adicionar() {

        i.setProduto(getP());
        i.setQuantidade(getQuantidade());
        v.addItem(i);
        setMostrarTotalVenda(true);
        setMostrarMensagemSucesso(false);
    }

    public void limparVenda() {
        
        setP(new Produto());
        setV(new Venda());
        setI(new Item());
        setMostrarTotalVenda(false);
        v.setItens(new ArrayList<Item>());

    }
    
    public void finalizarCompra() {

        daoVenda.create(v);
        p = new Produto();
        mostrarTotalVenda = false;
        v = new Venda();
        i = new Item();
        setMostrarMensagemSucesso(true);

    }
}
