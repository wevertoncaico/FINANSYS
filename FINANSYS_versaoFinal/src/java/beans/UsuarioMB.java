package beans;

import dao.UsuarioJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import modelo.Usuario;
import util.EMF;

/**
 *
 * @author Monnalisa Christina
 */
@ManagedBean
@SessionScoped
public class UsuarioMB {

    private Usuario u = new Usuario();
    private UsuarioJpaController dao = new UsuarioJpaController(EMF.getEntityManagerFactory());
    private List<Usuario> listaUsuario = new ArrayList<Usuario>();
    private String pesquisa;
    private Integer validador = 0;
    private boolean mostrarMsgCadastrado = false;
    private boolean mostarMsgCadastroErro = false;
    private boolean mostrarMsgCadastroExiste = false;
    private boolean mostarMsgAlterar = false;
    private boolean mostarMsgAlterarErro = false;
    private boolean mostarMsgAltErros = false;
    private boolean mostarMsgExcluir = false;
    private boolean mostarMsgExcErros = false;
    private boolean msgValida = false;

    public UsuarioMB() {

        pesquisarAll();

    }

    /**
     * @return the mostrarMsgCadastrado
     */
    public boolean isMostrarMsgCadastrado() {
        return mostrarMsgCadastrado;
    }

    /**
     * @param mostrarMsgCadastrado the mostrarMsgCadastrado to set
     */
    public void setMostrarMsgCadastrado(boolean mostrarMsgCadastrado) {
        this.mostrarMsgCadastrado = mostrarMsgCadastrado;
    }

    /**
     * @return the mostarMsgAlterar
     */
    public boolean isMostarMsgAlterar() {
        return mostarMsgAlterar;
    }

    /**
     * @param mostarMsgAlterar the mostarMsgAlterar to set
     */
    public void setMostarMsgAlterar(boolean mostarMsgAlterar) {
        this.mostarMsgAlterar = mostarMsgAlterar;
    }

    /**
     * @return the mostarMsgExcluir
     */
    public boolean isMostarMsgExcluir() {
        return mostarMsgExcluir;
    }

    /**
     * @param mostarMsgExcluir the mostarMsgExcluir to set
     */
    public void setMostarMsgExcluir(boolean mostarMsgExcluir) {
        this.mostarMsgExcluir = mostarMsgExcluir;
    }

    /**
     * @return the mostarMsgCadastroErro
     */
    public boolean isMostarMsgCadastroErro() {
        return mostarMsgCadastroErro;
    }

    /**
     * @param mostarMsgCadastroErro the mostarMsgCadastroErro to set
     */
    public void setMostarMsgCadastroErro(boolean mostarMsgCadastroErro) {
        this.mostarMsgCadastroErro = mostarMsgCadastroErro;
    }

    /**
     * @return the mostrarMsgCadastroExiste
     */
    public boolean isMostrarMsgCadastroExiste() {
        return mostrarMsgCadastroExiste;
    }

    /**
     * @param mostrarMsgCadastroExiste the mostrarMsgCadastroExiste to set
     */
    public void setMostrarMsgCadastroExiste(boolean mostrarMsgCadastroExiste) {
        this.mostrarMsgCadastroExiste = mostrarMsgCadastroExiste;
    }

    /**
     * @return the mostarMsgAlterarErro
     */
    public boolean isMostarMsgAlterarErro() {
        return mostarMsgAlterarErro;
    }

    /**
     * @param mostarMsgAlterarErro the mostarMsgAlterarErro to set
     */
    public void setMostarMsgAlterarErro(boolean mostarMsgAlterarErro) {
        this.mostarMsgAlterarErro = mostarMsgAlterarErro;
    }

    /**
     * @return the mostarMsgAltErros
     */
    public boolean isMostarMsgAltErros() {
        return mostarMsgAltErros;
    }

    /**
     * @param mostarMsgAltErros the mostarMsgAltErros to set
     */
    public void setMostarMsgAltErros(boolean mostarMsgAltErros) {
        this.mostarMsgAltErros = mostarMsgAltErros;
    }

    /**
     * @return the mostarMsgExcErros
     */
    public boolean isMostarMsgExcErros() {
        return mostarMsgExcErros;
    }

    /**
     * @param mostarMsgExcErros the mostarMsgExcErros to set
     */
    public void setMostarMsgExcErros(boolean mostarMsgExcErros) {
        this.mostarMsgExcErros = mostarMsgExcErros;
    }

    /**
     * @return the msgValida
     */
    public boolean isMsgValida() {
        return msgValida;
    }

    /**
     * @param msgValida the msgValida to set
     */
    public void setMsgValida(boolean msgValida) {
        this.msgValida = msgValida;
    }

    /**
     * @return the u
     */
    public Usuario getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(Usuario u) {
        this.u = u;
    }

    /**
     * @return the listaUsuario
     */
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
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
        listaUsuario = dao.findUsuarioEntities();

    }

    public void pesquisarNome() {
        listaUsuario = new ArrayList<Usuario>();
        for (Usuario us : dao.findUsuarioEntities()) {
            if ((us.getNome().toLowerCase().contains(pesquisa))) {
                listaUsuario.add(us);
            }

        }
        setPesquisa("");
    }

    public void cadastrar() {
        try {

            if (validaLogin(u.getLogin()) > 0) {

                setMsgValida(true);
                setMostrarMsgCadastrado(false);
                setMostarMsgCadastroErro(false);
                setMostrarMsgCadastroExiste(false);
                setMostarMsgAlterar(false);
                setMostarMsgAlterarErro(false);
                setMostarMsgAltErros(false);
                setMostarMsgExcluir(false);
                setMostarMsgExcErros(false);

            } else {
                dao.create(u);
                u = new Usuario();
                
                setMsgValida(false);
                setMostrarMsgCadastrado(true);
                setMostarMsgCadastroErro(false);
                setMostrarMsgCadastroExiste(false);
                setMostarMsgAlterar(false);
                setMostarMsgAlterarErro(false);
                setMostarMsgAltErros(false);
                setMostarMsgExcluir(false);
                setMostarMsgExcErros(false);
            }

        } catch (EntityExistsException e) {

            setMsgValida(false);
            setMostrarMsgCadastrado(false);
            setMostarMsgCadastroErro(false);
            setMostrarMsgCadastroExiste(true);
            setMostarMsgAlterar(false);
            setMostarMsgAlterarErro(false);
            setMostarMsgAltErros(false);
            setMostarMsgExcluir(false);
            setMostarMsgExcErros(false);


        } catch (RollbackException e) {
            
            setMsgValida(false);
            setMostrarMsgCadastrado(false);
            setMostarMsgCadastroErro(true);
            setMostrarMsgCadastroExiste(false);
            setMostarMsgAlterar(false);
            setMostarMsgAlterarErro(false);
            setMostarMsgAltErros(false);
            setMostarMsgExcluir(false);
            setMostarMsgExcErros(false);

        }
        pesquisarAll();
    }

    public void alterar() {
        try {

            if (validaLogin(u.getLogin()) > 0) {
                
                setMsgValida(true);
                setMostrarMsgCadastrado(false);
                setMostarMsgCadastroErro(false);
                setMostrarMsgCadastroExiste(false);
                setMostarMsgAlterar(false);
                setMostarMsgAlterarErro(false);
                setMostarMsgAltErros(false);
                setMostarMsgExcluir(false);
                setMostarMsgExcErros(false);


            } else {

                dao.edit(u);
                u = new Usuario();
                
                setMsgValida(false);
                setMostrarMsgCadastrado(false);
                setMostarMsgCadastroErro(false);
                setMostrarMsgCadastroExiste(false);
                setMostarMsgAlterar(true);
                setMostarMsgAlterarErro(false);
                setMostarMsgAltErros(false);
                setMostarMsgExcluir(false);
                setMostarMsgExcErros(false);

            }

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            
            setMsgValida(false);
            setMostrarMsgCadastrado(false);
            setMostarMsgCadastroErro(false);
            setMostrarMsgCadastroExiste(false);
            setMostarMsgAlterar(false);
            setMostarMsgAlterarErro(false);
            setMostarMsgAltErros(true);
            setMostarMsgExcluir(false);
            setMostarMsgExcErros(false);


        } catch (Exception ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            
            setMsgValida(false);
            setMostrarMsgCadastrado(false);
            setMostarMsgCadastroErro(false);
            setMostrarMsgCadastroExiste(false);
            setMostarMsgAlterar(false);
            setMostarMsgAlterarErro(true);
            setMostarMsgAltErros(false);
            setMostarMsgExcluir(false);
            setMostarMsgExcErros(false);

        }
        pesquisarAll();
    }

    public void excluir(Long id) {
        try {
            dao.destroy(id);
            
            setMsgValida(false);
            setMostrarMsgCadastrado(false);
            setMostarMsgCadastroErro(false);
            setMostrarMsgCadastroExiste(false);
            setMostarMsgAlterar(false);
            setMostarMsgAlterarErro(false);
            setMostarMsgAltErros(false);
            setMostarMsgExcluir(false);
            setMostarMsgExcErros(false);


        } catch (NonexistentEntityException ex) {

            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);

            setMsgValida(false);
            setMostrarMsgCadastrado(false);
            setMostarMsgCadastroErro(false);
            setMostrarMsgCadastroExiste(false);
            setMostarMsgAlterar(false);
            setMostarMsgAlterarErro(false);
            setMostarMsgAltErros(false);
            setMostarMsgExcluir(false);
            setMostarMsgExcErros(true);

        }
        pesquisarAll();
    }

    public void limpar() {
        setU(new Usuario());
        limpaMsg();
    }

    public void limpaMsg() {

        setMsgValida(false);
        setMostrarMsgCadastrado(false);
        setMostarMsgCadastroErro(false);
        setMostrarMsgCadastroExiste(false);
        setMostarMsgAlterar(false);
        setMostarMsgAlterarErro(false);
        setMostarMsgAltErros(false);
        setMostarMsgExcluir(false);
        setMostarMsgExcErros(false);
    }

    private Integer validaLogin(String login) {
        for (Usuario u : dao.findUsuarioEntities()) {
            if (u.getLogin().toUpperCase().contains(login.toUpperCase())) {

                validador += 1;
            }
        }
        return validador;
    }
}
