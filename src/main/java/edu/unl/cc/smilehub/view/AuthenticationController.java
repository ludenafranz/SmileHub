/**
 * @author FrancisEngine(Francisco Chamba)
 */
package edu.unl.cc.smilehub.view;


import edu.unl.cc.smilehub.business.SecurityFacade;
import edu.unl.cc.smilehub.domain.admin.Usuario;
import edu.unl.cc.smilehub.exception.CredentialInvalidException;
import edu.unl.cc.smilehub.exception.EntityNotFoundException;
import edu.unl.cc.smilehub.faces.FacesUtil;
import edu.unl.cc.smilehub.view.security.UserSession;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.logging.Logger;

@Named
@ViewScoped
public class AuthenticationController implements java.io.Serializable{

    private static Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    @NotNull @NotEmpty @Size(min=4, message = "Nombre de usuario muy corto")
    private String username;

    @NotNull @NotEmpty @Size(min=4, message = "Contraseña muy corta")
    private String password;

    @Inject
    private SecurityFacade securityFacade;

    @Inject
    private UserSession userSession;
    
    @Inject
    private FacesUtil facesUtil;

    private Usuario user;


    public String login(){
        logger.info("Login attempt for user: " + username);
        logger.info("Password: " + password);
        try {
            user = securityFacade.authenticate(username, password);
            setHttpSession(user);
            facesUtil.addSuccessMessageAndKeep("Aviso", "Bienvenido " + user.getName());
            /*
            FacesMessage fcm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso",
                    "Bienvenido " + user.getName());
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fcm);
            fc.getExternalContext().getFlash().setKeepMessages(true);
            */
            userSession.postLogin(user);
            //metodo provicionar por tiempo
            String homePage = userSession.getHomePage();
            return homePage;

        } catch (CredentialInvalidException | EntityNotFoundException e) {
            logger.warning("Login failed for user " + username + ": " + e.getMessage());
            facesUtil.addErrorMessage("Error de Autenticación", "Credenciales incorrectas");
            return null;
        }
    }

    public String username(){
        return user.getIdentificacion();
    }

    /**
     * Establece la session de usuario en el contexto HTTTP de la aplicación
     * @param user
     */
    private void setHttpSession(Usuario user){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("user", user);
    }

    public String logout() throws ServletException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();
        ((jakarta.servlet.http.HttpServletRequest) facesContext.getExternalContext().getRequest()).logout();
        return "/index.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}




