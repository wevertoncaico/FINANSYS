package locale;

import java.util.Locale;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author weverton
 */

public class LocaleController {
 private Locale currentLocale = new Locale("pt", "BR");

 public void inglesLocale() {
  UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
  currentLocale = new Locale("en");
  viewRoot.setLocale(currentLocale);
 }

 public void portugueseLocale() {
  UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
  currentLocale = new Locale("pt", "BR");
  viewRoot.setLocale(currentLocale);
 }

 public Locale getCurrentLocale() {
  return currentLocale;
 }
}
