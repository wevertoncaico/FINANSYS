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
public class BalancoMB {

    private DespesaJpaController daoDespesa = new DespesaJpaController(EMF.getEntityManagerFactory());
    private VendaJpaController daoVenda = new VendaJpaController(EMF.getEntityManagerFactory());
    private Venda venda = new Venda();
    private Despesa despesa = new Despesa();
    private List<Despesa> listaPasivo_1 = new ArrayList<Despesa>();
    private List<Venda> listaAtivo_1 = new ArrayList<Venda>();
    private Date data_1;
    private Double pL_1 = 0.0; //Patrimônio liquido
    private Double totalAtivo = 0.0;
    private Double totalPassivo = 0.0;
    
    public BalancoMB() {
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

    public void balancoMensal() {

        listaPasivo_1 = new ArrayList<Despesa>();
        listaAtivo_1 = new ArrayList<Venda>();

        for (Despesa d : daoDespesa.findDespesaEntities()) {
            if (d.getDat().getMonth() == getData_1().getMonth()) {

                listaPasivo_1.add(d);
            }

        }
        for (Venda v : daoVenda.findVendaEntities()) {
            if (v.getDataVenda().getMonth() == getData_1().getMonth()) {
                listaAtivo_1.add(v);
            }
        }
        patrimonioLiquido();

    }

    public void balancoAnual() {

        listaPasivo_1 = new ArrayList<Despesa>();
        listaAtivo_1 = new ArrayList<Venda>();

        for (Despesa d : daoDespesa.findDespesaEntities()) {
            if (d.getDat().getYear() == getData_1().getYear()) {

                listaPasivo_1.add(d);
            }

        }
        for (Venda v : daoVenda.findVendaEntities()) {
            if (v.getDataVenda().getYear() == getData_1().getYear()) {
                listaAtivo_1.add(v);
            }
        }
        patrimonioLiquido();

    }

    public void patrimonioLiquido() {

        totalAtivo = 0.0;
        totalPassivo = 0.0;

        for (Venda vend : getListaAtivo_1()) {
            totalAtivo += vend.getTotalDaVenda();
        }
        for (Despesa desp : getListaPasivo_1()) {
            totalPassivo += desp.getValor();
        }

        pL_1 = getTotalAtivo() - getTotalPassivo();

    }

}
