package pl.rwalski.managed.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

@ManagedBean(name = "language")
@SessionScoped
public class LocaleChanger {

    @PostConstruct
    private void init() {
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    private Locale locale;

    private static final Logger logger = Logger.getLogger(LocaleChanger.class.toString());


    private static Map<String, Locale> locales;

    static {
        locales = new HashMap<>();
        locales.put("English", new Locale("en"));
        locales.put("Polski", new Locale("pl"));
    }

    public Map<String, Locale> getLocales() {
        return locales;
    }

    public Locale getLocale() {
        logger.info(FacesContext.getCurrentInstance().getViewRoot().getLocale().toString());
        FacesContext.getCurrentInstance()
                .getViewRoot().setLocale(locale);
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e){

        Locale newValue = (Locale)e.getNewValue();

        FacesContext.getCurrentInstance()
                .getViewRoot().setLocale(newValue);


    }

}
