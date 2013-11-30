/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import static util.LocaleMessage.getClassLoader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 *
 * @author weverton
 */
public class LocaleMessage {
    
    protected static ClassLoader getClassLoader(Object defaultObject) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = defaultObject.getClass().getClassLoader();
        }
        return loader;
    }

   // public static String getLocaleString( String bundle,String key,Object parameters[],Locale locale) {
     public static String getLocaleString( String key,Object parameters[],Locale locale) {    
        String message ;
       
        //ResourceBundle resourceBundle = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle(), locale);
        try {
             message = key;
        } catch (MissingResourceException e) {
            message = "Erro na mensagem!";
        }
        if (parameters != null) {
            StringBuffer stringBuffer = new StringBuffer();
            MessageFormat messageFormat = new MessageFormat(message,locale);
            message = messageFormat.format(parameters, stringBuffer,null).toString();
        }
       return message;
    }
}
