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
    private List<Usuario> listaUsuario;
    private String pesquisa;

    public UsuarioMB() {
        pesquisarAll();
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
            dao.create(u);
            u = new Usuario();

        } catch (EntityExistsException e) {
        } catch (RollbackException e) {
        }
        pesquisarAll();
    }

    public void alterar() {
        try {

            dao.edit(u);
            u = new Usuario();

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
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
        setU(new Usuario());
    }
}
