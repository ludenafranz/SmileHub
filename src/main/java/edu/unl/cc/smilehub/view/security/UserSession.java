package edu.unl.cc.smilehub.view.security;

import edu.unl.cc.smilehub.business.SecurityFacade;
import edu.unl.cc.smilehub.domain.admin.Role;
import edu.unl.cc.smilehub.domain.admin.Usuario;
import edu.unl.cc.smilehub.exception.EntityNotFoundException;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Set;
import java.util.logging.Logger;

@Named
@SessionScoped
public class UserSession implements java.io.Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(UserSession.class.getName());

    @Inject
    private SecurityFacade securityFacade;

    @Inject
    private RoleNavigationService roleNavigationService;

    private Usuario user;

    public void postLogin(@NotNull Usuario user) throws EntityNotFoundException {
        logger.info("User logged in: " + user.getName());
        this.user = user;
        if (user.getRole() == null) {
            logger.info("Usuario sin roles, buscando en BD para user ID: " + this.user.getId());
            Role roles = securityFacade.findRoleNamesByUser(this.user.getId());
            user.setRole(roles);
        } else {
            logger.info("Usuario ya tiene roles: " + user.getRole());
            user.getRole();
        }
    }

    public Usuario regresarUser(){
        return user;
    }

    //metodo provicionar por tiempo
    public String getHomePage() {
        logger.info("getHomePage() llamado para usuario: " + (user != null ? user.getName() : "null"));
        if (user != null && user.getRole() != null) {
            logger.info("Roles del usuario en getHomePage: " + user.getRole());
            return roleNavigationService.getHomePageByRoles(user.getRole());
        }
        return "/dashboard.xhtml?faces-redirect=true";
    }



    public boolean hasPermissionForPage(String pagePath) {
        return this.hasPermission(pagePath);
    }

    public boolean hasPermission(String resource) {
        if (resource.equals("/menuDiario.xhtml")){
            return hasRole("ADMIN");
        }
        return false;
    }

    public boolean hasRole(@NotNull String roleName){
        if (user == null){
            return false;
        }
        return user.getRole().getName().equals(roleName);
    }

    public Usuario getUser() {
        return user;
    }
}
