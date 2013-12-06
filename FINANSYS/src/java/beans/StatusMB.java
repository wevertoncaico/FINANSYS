/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DespesaJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Despesa;
import util.EMF;

/**
 *
 * @author weverton
 */
@ManagedBean
@RequestScoped
public class StatusMB {

    private Despesa d = new Despesa();
    private List<Despesa> despesas = new ArrayList<Despesa>();
    private List<Despesa> despesasPgOrNPg;
    private DespesaJpaController dao = new DespesaJpaController(EMF.getEntityManagerFactory());
    
    
    public StatusMB(){
        pesquisarAll();
        //mostrarDespesasNaoPagas();
        //mostrarDespesasPagas();
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
    
    /**
     *
     * @param pesquisa
     */
    public List<Despesa> pesquisarAll() 
    {
        setDespesas(dao.findDespesaEntities());
        return getDespesas();
    }
    
    public List<Despesa> mostrarDespesasNaoPagas()
    {
        despesasPgOrNPg = new ArrayList<Despesa>();
        for(Despesa x:despesas)
        {
            if(!x.getStatus().equalsIgnoreCase("PAGA"))
            {
               despesasPgOrNPg.add(x);
            }
        }
        return getDespesasPgOrNPg();
    }
    
    public List<Despesa> mostrarDespesasPagas()
    {
        despesasPgOrNPg = new ArrayList<Despesa>();
        for(Despesa x:despesas)
        {
            if(x.getStatus().equalsIgnoreCase("PAGA"))
            {
               despesasPgOrNPg.add(x);
            }
        }
        return getDespesasPgOrNPg();
    }

    /**
     * @return the despesas
     */
    public List<Despesa> getDespesas() {
        return despesas;
    }

    /**
     * @param despesas the despesas to set
     */
    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    /**
     * @return the naoPagas
     */
    public List<Despesa> getDespesasPgOrNPg() {
        return despesasPgOrNPg;
    }

    /**
     * @param naoPagas the naoPagas to set
     */
    public void setDespesasPgOrNPg(List<Despesa> despesasPgOrNPg) {
        this.despesasPgOrNPg = despesasPgOrNPg;
    }
}
