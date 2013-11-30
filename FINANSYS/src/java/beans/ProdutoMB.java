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
    private boolean mostrarProCadastrado = false;
    private boolean mostarProCadastroErro = false;
    private boolean mostrarProCadastroExiste = false;
    private boolean mostarProAlterar = false;
    private boolean mostarProAlterarErro = false;
    private boolean mostarProAltErros = false;
    private boolean mostarProExcluir = false;
    private boolean mostarProExcErros = false;
    private boolean validaPreco = false;

    public ProdutoMB() {
        pesquisarAll();

    }

    /**
     * @return the mostrarProCadastrado
     */
    public boolean isMostrarProCadastrado() {
        return mostrarProCadastrado;
    }

    /**
     * @param mostrarProCadastrado the mostrarProCadastrado to set
     */
    public void setMostrarProCadastrado(boolean mostrarProCadastrado) {
        this.mostrarProCadastrado = mostrarProCadastrado;
    }

    /**
     * @return the mostarProCadastroErro
     */
    public boolean isMostarProCadastroErro() {
        return mostarProCadastroErro;
    }

    /**
     * @param mostarProCadastroErro the mostarProCadastroErro to set
     */
    public void setMostarProCadastroErro(boolean mostarProCadastroErro) {
        this.mostarProCadastroErro = mostarProCadastroErro;
    }

    /**
     * @return the mostrarProCadastroExiste
     */
    public boolean isMostrarProCadastroExiste() {
        return mostrarProCadastroExiste;
    }

    /**
     * @param mostrarProCadastroExiste the mostrarProCadastroExiste to set
     */
    public void setMostrarProCadastroExiste(boolean mostrarProCadastroExiste) {
        this.mostrarProCadastroExiste = mostrarProCadastroExiste;
    }

    /**
     * @return the mostarProAlterar
     */
    public boolean isMostarProAlterar() {
        return mostarProAlterar;
    }

    /**
     * @param mostarProAlterar the mostarProAlterar to set
     */
    public void setMostarProAlterar(boolean mostarProAlterar) {
        this.mostarProAlterar = mostarProAlterar;
    }

    /**
     * @return the mostarProAlterarErro
     */
    public boolean isMostarProAlterarErro() {
        return mostarProAlterarErro;
    }

    /**
     * @param mostarProAlterarErro the mostarProAlterarErro to set
     */
    public void setMostarProAlterarErro(boolean mostarProAlterarErro) {
        this.mostarProAlterarErro = mostarProAlterarErro;
    }

    /**
     * @return the mostarProAltErros
     */
    public boolean isMostarProAltErros() {
        return mostarProAltErros;
    }

    /**
     * @param mostarProAltErros the mostarProAltErros to set
     */
    public void setMostarProAltErros(boolean mostarProAltErros) {
        this.mostarProAltErros = mostarProAltErros;
    }

    /**
     * @return the mostarProExcluir
     */
    public boolean isMostarProExcluir() {
        return mostarProExcluir;
    }

    /**
     * @param mostarProExcluir the mostarProExcluir to set
     */
    public void setMostarProExcluir(boolean mostarProExcluir) {
        this.mostarProExcluir = mostarProExcluir;
    }

    /**
     * @return the mostarProExcErros
     */
    public boolean isMostarProExcErros() {
        return mostarProExcErros;
    }

    /**
     * @param mostarProExcErros the mostarProExcErros to set
     */
    public void setMostarProExcErros(boolean mostarProExcErros) {
        this.mostarProExcErros = mostarProExcErros;
    }

    /**
     * @return the validaPreco
     */
    public boolean isValidaPreco() {
        return validaPreco;
    }

    /**
     * @param validaPreco the validaPreco to set
     */
    public void setValidaPreco(boolean validaPreco) {
        this.validaPreco = validaPreco;
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
            if (p.getPreco() == 0.0) {

                setValidaPreco(true);
                setMostrarProCadastrado(false);
                setMostarProCadastroErro(false);
                setMostrarProCadastroExiste(false);
                setMostarProAlterar(false);
                setMostarProAlterarErro(false);
                setMostarProAltErros(false);
                setMostarProExcluir(false);
                setMostarProExcErros(false);

            } else {
                dao.create(p);
                p = new Produto();

                setValidaPreco(false);
                setMostrarProCadastrado(true);
                setMostarProCadastroErro(false);
                setMostrarProCadastroExiste(false);
                setMostarProAlterar(false);
                setMostarProAlterarErro(false);
                setMostarProAltErros(false);
                setMostarProExcluir(false);
                setMostarProExcErros(false);
            }

        } catch (EntityExistsException e) {

            setValidaPreco(false);
            setMostrarProCadastrado(false);
            setMostarProCadastroErro(false);
            setMostrarProCadastroExiste(true);
            setMostarProAlterar(false);
            setMostarProAlterarErro(false);
            setMostarProAltErros(false);
            setMostarProExcluir(false);
            setMostarProExcErros(false);


        } catch (RollbackException e) {

            setValidaPreco(false);
            setMostrarProCadastrado(false);
            setMostarProCadastroErro(true);
            setMostrarProCadastroExiste(false);
            setMostarProAlterar(false);
            setMostarProAlterarErro(false);
            setMostarProAltErros(false);
            setMostarProExcluir(false);
            setMostarProExcErros(false);
        }
        pesquisarAll();
    }

    public void alterar() {
        try {
            if (p.getPreco() == 0.0) {

                setValidaPreco(true);
                setMostrarProCadastrado(false);
                setMostarProCadastroErro(false);
                setMostrarProCadastroExiste(false);
                setMostarProAlterar(false);
                setMostarProAlterarErro(false);
                setMostarProAltErros(false);
                setMostarProExcluir(false);
                setMostarProExcErros(false);

            } else {

                dao.edit(p);
                p = new Produto();

                setValidaPreco(false);
                setMostrarProCadastrado(false);
                setMostarProCadastroErro(false);
                setMostrarProCadastroExiste(false);
                setMostarProAlterar(true);
                setMostarProAlterarErro(false);
                setMostarProAltErros(false);
                setMostarProExcluir(false);
                setMostarProExcErros(false);
            }

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProdutoMB.class.getName()).log(Level.SEVERE, null, ex);

            setValidaPreco(false);
            setMostrarProCadastrado(false);
            setMostarProCadastroErro(false);
            setMostrarProCadastroExiste(false);
            setMostarProAlterar(false);
            setMostarProAlterarErro(false);
            setMostarProAltErros(true);
            setMostarProExcluir(false);
            setMostarProExcErros(false);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoMB.class.getName()).log(Level.SEVERE, null, ex);

            setValidaPreco(false);
            setMostrarProCadastrado(false);
            setMostarProCadastroErro(false);
            setMostrarProCadastroExiste(false);
            setMostarProAlterar(false);
            setMostarProAlterarErro(true);
            setMostarProAltErros(false);
            setMostarProExcluir(false);
            setMostarProExcErros(false);
        }
        pesquisarAll();
    }

    public void excluir(Long id) {
        try {
            dao.destroy(id);

            setValidaPreco(false);
            setMostrarProCadastrado(false);
            setMostarProCadastroErro(false);
            setMostrarProCadastroExiste(false);
            setMostarProAlterar(false);
            setMostarProAlterarErro(false);
            setMostarProAltErros(false);
            setMostarProExcluir(true);
            setMostarProExcErros(false);

        } catch (NonexistentEntityException ex) {

            Logger.getLogger(ProdutoMB.class.getName()).log(Level.SEVERE, null, ex);

            setValidaPreco(false);
            setMostrarProCadastrado(false);
            setMostarProCadastroErro(false);
            setMostrarProCadastroExiste(false);
            setMostarProAlterar(false);
            setMostarProAlterarErro(false);
            setMostarProAltErros(false);
            setMostarProExcluir(false);
            setMostarProExcErros(true);
        }
        pesquisarAll();
    }

    public void limpar() {
        setP(new Produto());
        limpaMsg();
    }

    public void PesquisarPorNome() {
        //listaProduto = dao.pesquisarPorNome(pesquisa);
    }

    public void limpaMsg() {

        setValidaPreco(false);
        setMostrarProCadastrado(false);
        setMostarProCadastroErro(false);
        setMostrarProCadastroExiste(false);
        setMostarProAlterar(false);
        setMostarProAlterarErro(false);
        setMostarProAltErros(false);
        setMostarProExcluir(false);
        setMostarProExcErros(false);
    }
}
