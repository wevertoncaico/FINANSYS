package beans;

import dao.DespesaJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import modelo.Despesa;
import util.EMF;

/**
 *
 * @author Monnalisa Christina
 */
@ManagedBean
@SessionScoped
public class DespesaMB {

    private Despesa d = new Despesa();
    private List<Despesa> listaDespesa = new ArrayList<Despesa>();
    private DespesaJpaController dao = new DespesaJpaController(EMF.getEntityManagerFactory());
    private String pesquisa;
    private Date pesData;
    
    public DespesaMB() {
        pesquisarAll();
    }

    /**
     * @return the listaDespesa
     */
    public List<Despesa> getListaDespesa() {
        return listaDespesa;
    }

    /**
     * @param listaDespesa the listaDespesa to set
     */
    public void setListaDespesa(List<Despesa> listaDespesa) {
        this.listaDespesa = listaDespesa;
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

    /**
     * @return the d
     */
    public Despesa getD() {
        return d;
    }
    
      /**
     * @param d the d to set
     */
    public void setD(Despesa d) {
        this.d = d;
    }
    
     public void pesquisarAll() {
        listaDespesa = dao.findDespesaEntities();

    }
    
    public void pesquisarPorDescricao(){
        listaDespesa = dao.pesquisarPorDescricao(pesquisa);
    }

    public void cadastrar() {
        try {
            dao.create(d);
            d = new Despesa();

        } catch (EntityExistsException e) {
        } catch (RollbackException e) {
        }
        pesquisarAll();
    }

    public void alterar() {
        try {

            dao.edit(d);
            d = new Despesa();

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DespesaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DespesaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisarAll();
    }

    public void excluir(Long id) {
        try {
            dao.destroy(id);

        } catch (NonexistentEntityException ex) {

            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisarAll();
    }

    public void limpar() {
       
        setD(new Despesa());
    }

    /**
     * @return the pesData
     */
    public Date getPesData() {
        return pesData;
    }

    /**
     * @param pesData the pesData to set
     */
    public void setPesData(Date pesData) {
        this.pesData = pesData;
    }
    
    
    
     public void pesquisarData() {
        listaDespesa = new ArrayList<Despesa>();
        for (Despesa des : dao.findDespesaEntities()) {
            if ((des.getDat().equals(pesData))){
                   
                listaDespesa.add(des);
            }
        }
      setPesData(null);
    }

}
