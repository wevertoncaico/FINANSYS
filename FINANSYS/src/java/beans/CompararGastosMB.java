package beans;

import dao.DespesaJpaController;
import dao.VendaJpaController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Despesa;
import modelo.Venda;
import util.EMF;

/**
 *
 * @author Monnalisa Christina
 */
@ManagedBean
@RequestScoped
public class CompararGastosMB {

    private DespesaJpaController daoDespesa = new DespesaJpaController(EMF.getEntityManagerFactory());
    private VendaJpaController daoVenda = new VendaJpaController(EMF.getEntityManagerFactory());
    private Venda venda = new Venda();
    private Despesa despesa = new Despesa();
    private List<Despesa> listaPasivo_1 = new ArrayList<Despesa>();
    private List<Despesa> listaPasivo_2 = new ArrayList<Despesa>();
    private List<Venda> listaAtivo_1 = new ArrayList<Venda>();
    private List<Venda> listaAtivo_2 = new ArrayList<Venda>();
    private Date data_1; //data carregada no primeiro painel
    private Date data_2; //data carregada no segundo painel
    private Double pL_1 = 0.0; //Patrimônio liquido do primeiro painel
    private Double pL_2 = 0.0; //Patrimônio liquido do segundo painel
    private Double totalAtivo = 0.0;
    private Double totalPassivo = 0.0;
    private Double totalAtivo_2 = 0.0;
    private Double totalPassivo_2 = 0.0;
    
    public CompararGastosMB() {
    }

    /**
     * @return the venda
     */
    public Venda getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    /**
     * @return the despesa
     */
    public Despesa getDespesa() {
        return despesa;
    }

    /**
     * @param despesa the despesa to set
     */
    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    /**
     * @return the data_1
     */
    public Date getData_1() {
        return data_1;
    }

    /**
     * @param data_1 the data_1 to set
     */
    public void setData_1(Date data_1) {
        this.data_1 = data_1;
    }

    /**
     * @return the data_2
     */
    public Date getData_2() {
        return data_2;
    }

    /**
     * @param data_2 the data_2 to set
     */
    public void setData_2(Date data_2) {
        this.data_2 = data_2;
    }

    /**
     * @return the listaPasivo_1
     */
    public List<Despesa> getListaPasivo_1() {
        return listaPasivo_1;
    }

    /**
     * @param listaPasivo_1 the listaPasivo_1 to set
     */
    public void setListaPasivo_1(List<Despesa> listaPasivo_1) {
        this.listaPasivo_1 = listaPasivo_1;
    }

    /**
     * @return the listaAtivo_2
     */
    public List<Venda> getListaAtivo_2() {
        return listaAtivo_2;
    }

    /**
     * @param listaAtivo_2 the listaAtivo_2 to set
     */
    public void setListaAtivo_2(List<Venda> listaAtivo_2) {
        this.listaAtivo_2 = listaAtivo_2;
    }

    /**
     * @return the listaPasivo_2
     */
    public List<Despesa> getListaPasivo_2() {
        return listaPasivo_2;
    }

    /**
     * @param listaPasivo_2 the listaPasivo_2 to set
     */
    public void setListaPasivo_2(List<Despesa> listaPasivo_2) {
        this.listaPasivo_2 = listaPasivo_2;
    }

    /**
     * @return the listaAtivo_1
     */
    public List<Venda> getListaAtivo_1() {
        return listaAtivo_1;
    }

    /**
     * @param listaAtivo_1 the listaAtivo_1 to set
     */
    public void setListaAtivo_1(List<Venda> listaAtivo_1) {
        this.listaAtivo_1 = listaAtivo_1;
    }

    /**
     * @return the pL_1
     */
    public Double getpL_1() {
        return pL_1;
    }

    /**
     * @param pL_1 the pL_1 to set
     */
    public void setpL_1(Double pL_1) {
        this.pL_1 = pL_1;
    }

    /**
     * @return the pL_2
     */
    public Double getpL_2() {
        return pL_2;
    }

    /**
     * @param pL_2 the pL_2 to set
     */
    public void setpL_2(Double pL_2) {
        this.pL_2 = pL_2;
    }

    /**
     * @return the totalAtivo
     */
    public Double getTotalAtivo() {
        return totalAtivo;
    }

    /**
     * @param totalAtivo the totalAtivo to set
     */
    public void setTotalAtivo(Double totalAtivo) {
        this.totalAtivo = totalAtivo;
    }

    /**
     * @return the totalpassivo
     */
    public Double getTotalPassivo() {
        return totalPassivo;
    }

    /**
     * @param totalpassivo the totalpassivo to set
     */
    public void setTotalPassivo(Double totalPassivo) {
        this.totalPassivo = totalPassivo;
    }
    
    /**
     * @return the totalAtivo_2
     */
    public Double getTotalAtivo_2() {
        return totalAtivo_2;
    }

    /**
     * @param totalAtivo_2 the totalAtivo_2 to set
     */
    public void setTotalAtivo_2(Double totalAtivo_2) {
        this.totalAtivo_2 = totalAtivo_2;
    }

    /**
     * @return the totalpassivo_2
     */
    public Double getTotalPassivo_2() {
        return totalPassivo_2;
    }

    /**
     * @param totalpassivo_2 the totalpassivo_2 to set
     */
    public void setTotalPassivo_2(Double totalPassivo_2) {
        this.totalPassivo_2 = totalPassivo_2;
    }

    public void balanco_1() {
        
        listaPasivo_1 = new ArrayList<Despesa>();
        listaPasivo_2 = new ArrayList<Despesa>();
        listaAtivo_1 = new ArrayList<Venda>();
        listaAtivo_2 = new ArrayList<Venda>();

        for (Despesa d : daoDespesa.findDespesaEntities()) {
            if(d.getDat().getMonth() == getData_1().getMonth()){    
                    
                listaPasivo_1.add(d);
            }else if(d.getDat().getMonth() == getData_2().getMonth()){
              
                listaPasivo_2.add(d);
                
            }

        }
        for (Venda v : daoVenda.findVendaEntities()) {
            if(v.getDataVenda().getMonth() == getData_1().getMonth()){
                listaAtivo_1.add(v);
            }else if(v.getDataVenda().getMonth() == getData_2().getMonth()){
                listaAtivo_2.add(v);
            }
        }
        patrimonioLiquido();

    }

    public void patrimonioLiquido() {

        totalAtivo = 0.0;
        totalPassivo = 0.0;
        totalAtivo_2 = 0.0;
        totalPassivo_2 = 0.0;
        
        for (Venda vend : getListaAtivo_1()) {
            totalAtivo += vend.getTotalDaVenda();
        }
        for (Despesa desp : getListaPasivo_1()) {
            totalPassivo += desp.getValor();
        }

        pL_1 = getTotalAtivo() - getTotalPassivo();

        for (Venda vend : getListaAtivo_2()) {
            totalAtivo_2 += vend.getTotalDaVenda();
        }
        for (Despesa desp : getListaPasivo_2()) {
            totalPassivo_2 += desp.getValor();
        }

        pL_2 = getTotalAtivo_2() - getTotalPassivo_2();

    }

}
