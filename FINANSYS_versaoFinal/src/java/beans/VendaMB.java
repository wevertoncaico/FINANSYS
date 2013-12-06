package beans;

import dao.ProdutoJpaController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.EMF;
import modelo.Produto;
import modelo.Item;
import modelo.Venda;
import java.util.List;
import dao.VendaJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private ProdutoJpaController daoProduto = new ProdutoJpaController(EMF.getEntityManagerFactory());
    private VendaJpaController daoVenda = new VendaJpaController(EMF.getEntityManagerFactory());
    private List<Produto> listaVenda = new ArrayList<Produto>(); // lista de produtos que já esta add a venda, no momento
    private List<Produto> produtosBaixaEstoque = new ArrayList<Produto>();
    private List<Item> carrinho = new ArrayList<Item>();
    private Integer quan = 0;// guarda quantidade de itens selecionados pelo usuário
    private Double total = 0.0; // armazena total da venda, enquanto ela não é finalizada
    private Integer estoqueProd = 0; // armazena estoque atual do produto
    private boolean mostrarTotalVenda = false;
    private boolean mostrarMensagemSucesso = false;
    private boolean estoqueZero = false;

    public VendaMB() {
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

    /**
     * @return the listaVenda
     */
    public List<Produto> getListaVenda() {
        return listaVenda;
    }

    /**
     * @param listaVenda the listaVenda to set
     */
    public void setListaVenda(List<Produto> listaVenda) {
        this.listaVenda = listaVenda;
    }

    /**
     * @return the produtosBaixaEstoque
     */
    public List<Produto> getProdutosBaixaEstoque() {
        return produtosBaixaEstoque;
    }

    /**
     * @param produtosBaixaEstoque the produtosBaixaEstoque to set
     */
    public void setProdutosBaixaEstoque(List<Produto> produtosBaixaEstoque) {
        this.produtosBaixaEstoque = produtosBaixaEstoque;
    }

    /**
     * @return the quan
     */
    public Integer getQuan() {
        return quan;
    }

    /**
     * @param quan the quan to set
     */
    public void setQuan(Integer quan) {
        this.quan = quan;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the estoqueProd
     */
    public Integer getEstoqueProd() {
        return estoqueProd;
    }

    /**
     * @param estoqueProd the estoqueProd to set
     */
    public void setEstoqueProd(Integer estoqueProd) {
        this.estoqueProd = estoqueProd;
    }

    /**
     * @return the estoqueZero
     */
    public boolean isEstoqueZero() {
        return estoqueZero;
    }

    /**
     * @param estoqueZero the estoqueZero to set
     */
    public void setEstoqueZero(boolean estoqueZero) {
        this.estoqueZero = estoqueZero;
    }

    public void adicionar() {

        if (p.getQntEstoque() < getQuan()) {
            setEstoqueZero(true);
        } else {
		if(quan > 0){


            i.setQuantidade(quan);
            i.setProduto(getP());
            vendaAtual(getP());
            v.addItem(i);
            carrinho.add(i);
            baixarEstoque(quan);
            setMostrarTotalVenda(true);
            setMostrarMensagemSucesso(false);
            setEstoqueZero(false);
            setQuan(0);
            totalVenda(i);
            i = new Item();
        }
     }

    }

    public void limparVenda() {
        for(Item x:carrinho)
        {
            for(Produto y:produtosBaixaEstoque)
            {
                if(x.getProduto().getId()== y.getId())
                {
                    Produto z = new Produto();
                    z = daoProduto.findProduto(y.getId());
                    y.setQntEstoque(z.getQntEstoque());
                }
            }
        }

        setP(new Produto());
        setV(new Venda());
        setI(new Item());
        setMostrarTotalVenda(false);
        setMostrarMensagemSucesso(false);
        setEstoqueZero(false);
        v.setItens(new ArrayList<Item>());
        setListaVenda(new ArrayList<Produto>());
        setTotal(0.0);
        setProdutosBaixaEstoque(new ArrayList<Produto>());

    }

    public void finalizarCompra() {

        daoVenda.create(v);
        gravarEstoqueAtual();
        p = new Produto();
        mostrarTotalVenda = false;
        v = new Venda();
        i = new Item();
        setMostrarMensagemSucesso(true);
        setEstoqueZero(false);
        setTotal(0.0);
        setListaVenda(new ArrayList<Produto>());
        setProdutosBaixaEstoque(new ArrayList<Produto>());

    }

    public Double totalVenda(Item i) {

        total += i.totalItem();
        v.setTotalDaVenda(getTotal());

        return getTotal();
    }

    public void baixarEstoque(Integer quan) {

        estoqueProd = p.getQntEstoque();
        estoqueProd -= quan;

        p.setQntEstoque(estoqueProd);

        produtosBaixaEstoque.add(p);

    }

    public void vendaAtual(Produto prod) {

        listaVenda.add(prod);

    }

    public void gravarEstoqueAtual(){

        for (Produto prod : getProdutosBaixaEstoque()) {
            try {
                
                daoProduto.edit(prod);
                
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(VendaMB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VendaMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
