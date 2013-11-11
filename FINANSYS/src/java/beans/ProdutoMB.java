package beans;

import dao.ProdutoJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import modelo.Produto;
import util.EMF;

/**
 *
 * @author Monnalisa Christina
 */
@ManagedBean
@SessionScoped
public class ProdutoMB {

    private Produto p = new Produto();
    private ProdutoJpaController dao = new ProdutoJpaController(EMF.getEntityManagerFactory());
    private List<Produto> listaProduto = new ArrayList<Produto>();
    private String pesquisa;

    public ProdutoMB() {
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
     * @return the listaProduto
     */
    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    /**
     * @param listaProduto the listaProduto to set
     */
    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    /**
     * @return the pesquisa
     */
    public String getPesquisa() {
        return pesquisa;
    }

    /**
     * @param pesquisa the pesquisa to set
     */
    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public void pesquisarAll() {
        listaProduto = dao.findProdutoEntities();
    }
    
     public void cadastrar() {
        try {
            dao.create(p);
            p = new Produto();

        } catch (EntityExistsException e) {
        } catch (RollbackException e) {
        }
        pesquisarAll();
    }

    public void alterar() {
        try {

            dao.edit(p);
            p = new Produto();

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProdutoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisarAll();
    }

    public void excluir(Long id) {
        try {
            dao.destroy(id);

        } catch (NonexistentEntityException ex) {

            Logger.getLogger(ProdutoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisarAll();
    }

    public void limpar() {
        setP(new Produto());
    }
    
    public void PesquisarPorNome(){
    
      //listaProduto = dao.pesquisarPorNome(pesquisa);
        
    }
}
