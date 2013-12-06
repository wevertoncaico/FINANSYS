
package beans;

import dao.UsuarioJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Usuario;
import util.EMF;
import util.FacesUtil;

/**
 *
 * @author Monnalisa Christina
 */
@ManagedBean
@SessionScoped
public class LoginMB {

   
    private Usuario usuario = new Usuario();
    private String login ="";
    private String senha="";
    private UsuarioJpaController dao = new UsuarioJpaController(EMF.getEntityManagerFactory());
    private boolean logado = false;
   
    
    public LoginMB() {
        
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the logado
     */
    public boolean isLogado() {
        return logado;
    }

    /**
     * @param logado the logado to set
     */
    public void setLogado(boolean logado) {
        this.logado = logado;
    }

     public boolean validarLogin(){
         
        criarUsuarioAdmin();
        Usuario u = dao.verificarUsuario(login , senha);
        if (u != null){
            usuario = u;
            UsuarioMB umb = FacesUtil.getUsuarioMB();
            umb.setU(u);
            
            return true;
        } else {
            return false;
        }
    }
     
    
    public String logar(){
       boolean aux = validarLogin(); 
        if (aux == true){
            return "/BemVindo.xhtml";
        } else {
            return null;
        }
    }
    
    public String deslogar(){
        setLogado(false);
        usuario = new Usuario();
        login = null;        
        senha = null;
        return "/Inicio.xhtml";
    }
    
    public void criarUsuarioAdmin(){
    
       
        Usuario u = dao.verificarUsuario("admin" , "123456");
        if(u == null){
        
            usuario.setNome("Administrador");
            usuario.setLogin("admin");
            usuario.setSenha("123456");
            dao.create(usuario);
        }
        usuario = new Usuario();
    
    }
     
}
