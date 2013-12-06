package beans;

import dao.DespesaJpaController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Despesa;
import util.EMF;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Monnalisa Christina
 */
@ManagedBean
@RequestScoped
public class CompararGastosMB {

    private Despesa despesa = new Despesa();
    private Despesa d = new Despesa();
    private Despesa despesaComp1 = new Despesa(); // variavel que guarda a despesa para comparar no gráfico
    private Despesa desComp2 = new Despesa();// variavel que guarda a despesa para comparar no gráfico
    private List<Despesa> listaGastos = new ArrayList<Despesa>();
    private List<Despesa> listaGastos_2 = new ArrayList<Despesa>();
    private DespesaJpaController daoDespesa = new DespesaJpaController(EMF.getEntityManagerFactory());
    private Date dataGasto; // primeira data selecionada pelo usuario
    private Date dataGasto_2; // segunda data selecionada pelo usuario
    private Double total_1 = 0.0; // total das despesas no 1 mês
    private Double total_2 = 0.0; // total das despesas no 2 mês
    private CartesianChartModel categoryModel; // tipo cartesiano para criar gráfico

    public CompararGastosMB() {
        criarGraficos(0.0, 0.0, 0.0, 0.0); // inicialização necessária do gráfico
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
     * @return the listaGastos
     */
    public List<Despesa> getListaGastos() {
        return listaGastos;
    }

    /**
     * @param listaGastos the listaGastos to set
     */
    public void setListaGastos(List<Despesa> listaGastos) {
        this.listaGastos = listaGastos;
    }

    /**
     * @return the listaGastos_2
     */
    public List<Despesa> getListaGastos_2() {
        return listaGastos_2;
    }

    /**
     * @param listaGastos_2 the listaGastos_2 to set
     */
    public void setListaGastos_2(List<Despesa> listaGastos_2) {
        this.listaGastos_2 = listaGastos_2;
    }

    /**
     * @return the dataGasto
     */
    public Date getDataGasto() {
        return dataGasto;
    }

    /**
     * @param dataGasto the dataGasto to set
     */
    public void setDataGasto(Date dataGasto) {
        this.dataGasto = dataGasto;
    }

    /**
     * @return the dataGasto_2
     */
    public Date getDataGasto_2() {
        return dataGasto_2;
    }

    /**
     * @param dataGasto_2 the dataGasto_2 to set
     */
    public void setDataGasto_2(Date dataGasto_2) {
        this.dataGasto_2 = dataGasto_2;
    }

    /**
     * @return the total_1
     */
    public Double getTotal_1() {
        return total_1;
    }

    /**
     * @param total_1 the total_1 to set
     */
    public void setTotal_1(Double total_1) {
        this.total_1 = total_1;
    }

    /**
     * @return the total_2
     */
    public Double getTotal_2() {
        return total_2;
    }

    /**
     * @param total_2 the total_2 to set
     */
    public void setTotal_2(Double total_2) {
        this.total_2 = total_2;
    }

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public void selecionarGastos() {

        listaGastos = new ArrayList<Despesa>();
        listaGastos_2 = new ArrayList<Despesa>();
        total_1 = 0.0;
        total_2 = 0.0;

        for (Despesa des : daoDespesa.findDespesaEntities()) {
            if (des.getDat().getYear() == getDataGasto().getYear()) {
                if (des.getDat().getMonth() == getDataGasto().getMonth()) {
                    listaGastos.add(des);
                }
            }
        }
        for (Despesa des : daoDespesa.findDespesaEntities()) {
            if (des.getDat().getYear() == getDataGasto_2().getYear()) {
                if (des.getDat().getMonth() == getDataGasto_2().getMonth()) {
                    listaGastos_2.add(des);
                }
            }
        }

        compararGastos();
    }

    public void compararGastos() {

        Double valor1 = 0.0;
        Double valor2 = 0.0;
        Double valor3 = 0.0;
        Double valor4 = 0.0;

        for (Despesa des : getListaGastos()) {
            total_1 += des.getValor();
            if (des.getValor() > valor1) {

                valor1 = des.getValor();
                despesa = des;
            }

        }
        for (Despesa des : getListaGastos_2()) {
            total_2 += des.getValor();
            if (des.getValor() > valor2) {

                valor2 = des.getValor();
                d = des;
            }
        }
        for (Despesa des : getListaGastos_2()) {

            if (des.getDescricao().equals(despesa.getDescricao())) {
                desComp2 = des;
                valor4 = des.getValor();
            }

        }

        for (Despesa des : getListaGastos()) {

            if (des.getDescricao().equals(d.getDescricao())) {
                despesaComp1 = des;
                valor3 = des.getValor();
            }

        }


        criarGraficos(valor1, valor2, valor3, valor4);
    }

    public void criarGraficos(Double valor1, Double valor2, Double valor3, Double valor4) {

        categoryModel = new CartesianChartModel();

        ChartSeries despesaMes_1 = new ChartSeries();
        despesaMes_1.setLabel("Maior despesa no mês 1: " + despesa.getDescricao());
        ChartSeries despesaMes_2 = new ChartSeries();
        despesaMes_2.setLabel("Maior despesa no mês 2:" + d.getDescricao());
        ChartSeries despesaTotal_1 = new ChartSeries();
        despesaTotal_1.setLabel("Total das despesas mês 1");
        ChartSeries despesaTotal_2 = new ChartSeries();
        despesaTotal_2.setLabel("Total das despesas mês 2");
        ChartSeries despesaCompara_1 = new ChartSeries();
        despesaCompara_1.setLabel(desComp2.getDescricao() + " No mês 1");
        ChartSeries despesaCompara_2 = new ChartSeries();
        despesaCompara_2.setLabel(despesaComp1.getDescricao() + " No mês 2");

        despesaTotal_1.set("", total_1);
        despesaTotal_2.set("", total_2);
        despesaMes_1.set("", valor1);
        despesaMes_2.set("", valor2);
        despesaCompara_1.set("", valor3);
        despesaCompara_2.set("", valor4);


        categoryModel.addSeries(despesaMes_1);
        categoryModel.addSeries(despesaMes_2);
        categoryModel.addSeries(despesaTotal_1);
        categoryModel.addSeries(despesaTotal_2);
        categoryModel.addSeries(despesaCompara_1);
        categoryModel.addSeries(despesaCompara_2);
    }
}
