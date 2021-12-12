package pl.rwalski.managed.beans;

import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.exceptions.UserNotFoundException;
import pl.edu.agh.service.DashboardService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class PasswordChange {

    @EJB(mappedName = "ejb:/ejbs/DashboardServiceImpl!pl.edu.agh.service.DashboardService")
    private DashboardService dashboardService;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String repeatPassword;

    @Getter
    private String errorMessage;

    @Getter
    private String successMessage;

    @Getter
    @Setter
    private boolean forOtherUser;

    @Getter
    @Setter
    private String userName;

    private static final Logger logger = Logger.getLogger(PasswordChange.class.toString());


    public String changePassword() {
        errorMessage = null;
        successMessage = null;

        Locale locale = FacesContext.getCurrentInstance()
                .getViewRoot().getLocale();

        logger.info(locale.toString());
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);


        if (!password.equals(repeatPassword)) {
            errorMessage = messages.getString("password-change.mismatch-error");
            return "passwordChange";
        }
        if (forOtherUser) {

            try {
                dashboardService.changeOtherUserPassword(userName, password);
                successMessage = messages.getString("password-change.success");
            } catch (UserNotFoundException e) {
                errorMessage = messages.getString("password-change.user-not-exist-error");
                return "passwordChange";
            }
        } else {
            dashboardService.changePassword(password);
            successMessage = messages.getString("password-change.success");
        }
        return "passwordChange";
    }



}
