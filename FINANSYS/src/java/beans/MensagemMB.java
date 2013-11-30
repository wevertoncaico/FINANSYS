/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.tomcat.jni.Local;
import util.LocaleMessage;

/**
 *
 * @author weverton
 */
@ManagedBean
@RequestScoped
public class MensagemMB {

    /**
     * Creates a new instance of MensagemMB
     */
        
     public void adicionarMsg(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        String textoMensagem = carregarMensagemDeArquivo(key);
       // String textoMensagem = carregarMensagemDeArquivo(key);
        FacesMessage m = new FacesMessage(textoMensagem);
        context.addMessage(null, m);
    }
    public String chave;

    public MensagemMB() {
    }

    public String carregarMensagemDeArquivo(String key) {
        chave = key;
        FacesContext context = FacesContext.getCurrentInstance();
        Locale myLoc = context.getViewRoot().getLocale();

       //String message = LocaleMessage.getLocaleString(context.getApplication().getMessageBundle(), chave, null, myLoc);
        String message = LocaleMessage.getLocaleString(chave, null, myLoc);
        return message;
    }

    /**
     * @return the chave
     */
    public String getChave() {
        return chave;
    }

    /**
     * @param chave the chave to set
     */
    public void setChave(String chave) {
        this.chave = chave;
    }

   
}
