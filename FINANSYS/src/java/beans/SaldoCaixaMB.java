/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DespesaJpaController;
import dao.VendaJpaController;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Despesa;
import modelo.Venda;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import util.EMF;

/**
 *
 * @author Monnalisa Christina
 */
@ManagedBean
@RequestScoped
public class SaldoCaixaMB {

    private DespesaJpaController daoDespesa = new DespesaJpaController(EMF.getEntityManagerFactory());
    private VendaJpaController daoVenda = new VendaJpaController(EMF.getEntityManagerFactory());
    private CartesianChartModel linearModel;  // tipo cartesiano para criar gráfico
    private Double total1 = 0.0;
    private Double total2 = 0.0;
    private Double total3 = 0.0;
    private Double total4 = 0.0;
    private Double total5 = 0.0;
    private Double total6 = 0.0;
    private Double total7 = 0.0;
    private Double total8 = 0.0;
    private Double total9 = 0.0;
    private Double total10 = 0.0;
    private Double total11 = 0.0;
    private Double total12 = 0.0;

    public SaldoCaixaMB() {
        calcularSaldoPorMes();
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    public void calcularSaldoPorMes() {

        Date anoAtual = new Date();

        for (Venda ven : daoVenda.findVendaEntities()) {
            if (ven.getDataVenda().getYear() == anoAtual.getYear()) {
                if (ven.getDataVenda().getMonth() == 0) {
                    total1 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 1) {
                    total2 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 2) {
                    total3 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 3) {
                    total4 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 4) {
                    total5 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 5) {
                    total6 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 6) {
                    total7 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 7) {
                    total8 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 8) {
                    total9 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 9) {
                    total10 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 10) {
                    total11 += ven.getTotalDaVenda();
                } else if (ven.getDataVenda().getMonth() == 11) {
                    total12 += ven.getTotalDaVenda();
                }
            }

        }
        
        for (Despesa des : daoDespesa.findDespesaEntities()) {
            if (des.getDat().getYear() == anoAtual.getYear()) {
                if (des.getDat().getMonth() == 0) {
                    total1 -= des.getValor();
                } else if (des.getDat().getMonth() == 1) {
                    total2 -= des.getValor();
                } else if (des.getDat().getMonth() == 2) {
                    total3 -= des.getValor();
                } else if (des.getDat().getMonth() == 3) {
                    total4 -= des.getValor();
                } else if (des.getDat().getMonth() == 4) {
                    total5 -= des.getValor();
                } else if (des.getDat().getMonth() == 5) {
                    total6 -= des.getValor();
                } else if (des.getDat().getMonth() == 6) {
                    total7 -= des.getValor();
                } else if (des.getDat().getMonth() == 7) {
                    total8 -= des.getValor();
                } else if (des.getDat().getMonth() == 8) {
                    total9 -= des.getValor();
                } else if (des.getDat().getMonth() == 9) {
                    total10 -= des.getValor();
                } else if (des.getDat().getMonth() == 10) {
                    total11 -= des.getValor();
                } else if (des.getDat().getMonth() == 11) {
                    total12 -= des.getValor();
                }
            }

        }

        gerarGraficoCaixaMensal();

    }

    public void gerarGraficoCaixaMensal() {

        linearModel = new CartesianChartModel();

        LineChartSeries despesaMes_1 = new LineChartSeries();
        despesaMes_1.setLabel(" Total em caixa");
        
        despesaMes_1.set(" Mês 1", total1);
        despesaMes_1.set("Mês 2", total2);
        despesaMes_1.set("Mês 3", total3);
        despesaMes_1.set("Mês 4", total4);
        despesaMes_1.set("Mês 5", total5);
        despesaMes_1.set("Mês 6", total6);
        despesaMes_1.set("Mês 7", total7);
        despesaMes_1.set("Mês 8", total8);
        despesaMes_1.set("Mês 9", total9);
        despesaMes_1.set("Mês 10", total10);
        despesaMes_1.set("Mês 11", total11);
        despesaMes_1.set("Mês 12", total12);

        linearModel.addSeries(despesaMes_1);
    }
}
