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
    private boolean mostrarDesCadastrado = false;
    private boolean mostarDesCadastroErro = false;
    private boolean mostrarDesCadastroExiste = false;
    private boolean mostarDesAlterar = false;
    private boolean mostarDesAlterarErro = false;
    private boolean mostarDesAltErros = false;
    private boolean mostarDesExcluir = false;
    private boolean mostarDesExcErros = false;
    private boolean validaValor = false;

    public DespesaMB() {
        pesquisarAll();

    }

    /**
     * @return the mostrarDesCadastrado
     */
    public boolean isMostrarDesCadastrado() {
        return mostrarDesCadastrado;
    }

    /**
     * @param mostrarDesCadastrado the mostrarDesCadastrado to set
     */
    public void setMostrarDesCadastrado(boolean mostrarDesCadastrado) {
        this.mostrarDesCadastrado = mostrarDesCadastrado;
    }

    /**
     * @return the mostarDesCadastroErro
     */
    public boolean isMostarDesCadastroErro() {
        return mostarDesCadastroErro;
    }

    /**
     * @param mostarDesCadastroErro the mostarDesCadastroErro to set
     */
    public void setMostarDesCadastroErro(boolean mostarDesCadastroErro) {
        this.mostarDesCadastroErro = mostarDesCadastroErro;
    }

    /**
     * @return the mostrarDesCadastroExiste
     */
    public boolean isMostrarDesCadastroExiste() {
        return mostrarDesCadastroExiste;
    }

    /**
     * @param mostrarDesCadastroExiste the mostrarDesCadastroExiste to set
     */
    public void setMostrarDesCadastroExiste(boolean mostrarDesCadastroExiste) {
        this.mostrarDesCadastroExiste = mostrarDesCadastroExiste;
    }

    /**
     * @return the mostarDesAlterar
     */
    public boolean isMostarDesAlterar() {
        return mostarDesAlterar;
    }

    /**
     * @param mostarDesAlterar the mostarDesAlterar to set
     */
    public void setMostarDesAlterar(boolean mostarDesAlterar) {
        this.mostarDesAlterar = mostarDesAlterar;
    }

    /**
     * @return the mostarDesAlterarErro
     */
    public boolean isMostarDesAlterarErro() {
        return mostarDesAlterarErro;
    }

    /**
     * @param mostarDesAlterarErro the mostarDesAlterarErro to set
     */
    public void setMostarDesAlterarErro(boolean mostarDesAlterarErro) {
        this.mostarDesAlterarErro = mostarDesAlterarErro;
    }

    /**
     * @return the mostarDesAltErros
     */
    public boolean isMostarDesAltErros() {
        return mostarDesAltErros;
    }

    /**
     * @param mostarDesAltErros the mostarDesAltErros to set
     */
    public void setMostarDesAltErros(boolean mostarDesAltErros) {
        this.mostarDesAltErros = mostarDesAltErros;
    }

    /**
     * @return the mostarDesExcluir
     */
    public boolean isMostarDesExcluir() {
        return mostarDesExcluir;
    }

    /**
     * @param mostarDesExcluir the mostarDesExcluir to set
     */
    public void setMostarDesExcluir(boolean mostarDesExcluir) {
        this.mostarDesExcluir = mostarDesExcluir;
    }

    /**
     * @return the mostarDesExcErros
     */
    public boolean isMostarDesExcErros() {
        return mostarDesExcErros;
    }

    /**
     * @param mostarDesExcErros the mostarDesExcErros to set
     */
    public void setMostarDesExcErros(boolean mostarDesExcErros) {
        this.mostarDesExcErros = mostarDesExcErros;
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

    public void pesquisarPorDescricao() {
        listaDespesa = dao.pesquisarPorDescricao(pesquisa);
    }

    /**
     * @return the validaValor
     */
    public boolean isValidaValor() {
        return validaValor;
    }

    /**
     * @param validaValor the validaValor to set
     */
    public void setValidaValor(boolean validaValor) {
        this.validaValor = validaValor;
    }

    public void cadastrar() {
        try {

            if (d.getValor() == 0.0) {

                setValidaValor(true);
                setMostrarDesCadastrado(false);
                setMostarDesCadastroErro(false);
                setMostrarDesCadastroExiste(false);
                setMostarDesAlterar(false);
                setMostarDesAlterarErro(false);
                setMostarDesAltErros(false);
                setMostarDesExcluir(false);
                setMostarDesExcErros(false);

            } else {
                dao.create(d);
                d = new Despesa();

                setValidaValor(false);
                setMostrarDesCadastrado(true);
                setMostarDesCadastroErro(false);
                setMostrarDesCadastroExiste(false);
                setMostarDesAlterar(false);
                setMostarDesAlterarErro(false);
                setMostarDesAltErros(false);
                setMostarDesExcluir(false);
                setMostarDesExcErros(false);
            }

        } catch (EntityExistsException e) {

            setValidaValor(false);
            setMostrarDesCadastrado(false);
            setMostarDesCadastroErro(false);
            setMostrarDesCadastroExiste(true);
            setMostarDesAlterar(false);
            setMostarDesAlterarErro(false);
            setMostarDesAltErros(false);
            setMostarDesExcluir(false);
            setMostarDesExcErros(false);

        } catch (RollbackException e) {

            setValidaValor(false);
            setMostrarDesCadastrado(false);
            setMostarDesCadastroErro(true);
            setMostrarDesCadastroExiste(false);
            setMostarDesAlterar(false);
            setMostarDesAlterarErro(false);
            setMostarDesAltErros(false);
            setMostarDesExcluir(false);
            setMostarDesExcErros(false);
        }
        pesquisarAll();
    }

    public void alterar() {
        try {
            if (d.getValor() == 0.0) {

                setValidaValor(true);
                setMostrarDesCadastrado(false);
                setMostarDesCadastroErro(false);
                setMostrarDesCadastroExiste(false);
                setMostarDesAlterar(false);
                setMostarDesAlterarErro(false);
                setMostarDesAltErros(false);
                setMostarDesExcluir(false);
                setMostarDesExcErros(false);

            } else {

                dao.edit(d);
                d = new Despesa();

                setValidaValor(false);
                setMostrarDesCadastrado(false);
                setMostarDesCadastroErro(false);
                setMostrarDesCadastroExiste(false);
                setMostarDesAlterar(true);
                setMostarDesAlterarErro(false);
                setMostarDesAltErros(false);
                setMostarDesExcluir(false);
                setMostarDesExcErros(false);
            }

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DespesaMB.class.getName()).log(Level.SEVERE, null, ex);

            setValidaValor(false);
            setMostrarDesCadastrado(false);
            setMostarDesCadastroErro(false);
            setMostrarDesCadastroExiste(false);
            setMostarDesAlterar(false);
            setMostarDesAlterarErro(false);
            setMostarDesAltErros(true);
            setMostarDesExcluir(false);
            setMostarDesExcErros(false);

        } catch (Exception ex) {
            Logger.getLogger(DespesaMB.class.getName()).log(Level.SEVERE, null, ex);

            setValidaValor(false);
            setMostrarDesCadastrado(false);
            setMostarDesCadastroErro(false);
            setMostrarDesCadastroExiste(false);
            setMostarDesAlterar(false);
            setMostarDesAlterarErro(true);
            setMostarDesAltErros(false);
            setMostarDesExcluir(false);
            setMostarDesExcErros(false);
        }
        pesquisarAll();
    }

    public void excluir(Long id) {
        try {
            dao.destroy(id);

            setValidaValor(false);
            setMostrarDesCadastrado(false);
            setMostarDesCadastroErro(false);
            setMostrarDesCadastroExiste(false);
            setMostarDesAlterar(false);
            setMostarDesAlterarErro(false);
            setMostarDesAltErros(false);
            setMostarDesExcluir(true);
            setMostarDesExcErros(false);

        } catch (NonexistentEntityException ex) {

            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);

            setValidaValor(false);
            setMostrarDesCadastrado(false);
            setMostarDesCadastroErro(false);
            setMostrarDesCadastroExiste(false);
            setMostarDesAlterar(false);
            setMostarDesAlterarErro(false);
            setMostarDesAltErros(false);
            setMostarDesExcluir(false);
            setMostarDesExcErros(true);
        }
        pesquisarAll();
    }

    public void limpar() {

        setD(new Despesa());
        limapMsg();
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
            if ((des.getDat().equals(pesData))) {

                listaDespesa.add(des);
            }
        }
        setPesData(null);
    }

    public void limapMsg() {

        setValidaValor(false);
        setMostrarDesCadastrado(false);
        setMostarDesCadastroErro(false);
        setMostrarDesCadastroExiste(false);
        setMostarDesAlterar(false);
        setMostarDesAlterarErro(false);
        setMostarDesAltErros(false);
        setMostarDesExcluir(false);
        setMostarDesExcErros(false);
    }
}
