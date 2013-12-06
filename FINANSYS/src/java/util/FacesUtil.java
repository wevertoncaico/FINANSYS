/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.LoginMB;
import beans.UsuarioMB;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

/**
 *
 * @author Monnalisa Christina
 */
public class FacesUtil {

    public static UsuarioMB getUsuarioMB() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        UsuarioMB usuarioMB = (UsuarioMB) app.evaluateExpressionGet(facesContext, "#{usuarioMB}", UsuarioMB.class);
        return usuarioMB;
    }

   /* public static LoginMB getLoginMB() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        LoginMB loginMB = (LoginMB) app.evaluateExpressionGet(facesContext, "#{loginMB}", LoginMB.class);
        return loginMB;
    }*/
}
