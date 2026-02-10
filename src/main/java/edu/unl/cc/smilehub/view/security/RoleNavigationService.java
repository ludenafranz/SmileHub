package edu.unl.cc.smilehub.view.security;


import edu.unl.cc.smilehub.domain.admin.Usuario;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//clase provicionar por tiempo
@Stateless
public class RoleNavigationService implements Serializable {
    private static final Logger logger = Logger.getLogger(RoleNavigationService.class.getName());

    // Mapeo directo de roles a páginas
    private static final Map<String, String> ROLE_HOME_PAGES = Map.of(
            "SECRETARIA", "/menuDiario.xhtml",
            "PACIENTE", "/mesero.xhtml",
            "DOCTOR", "/cocina.xhtml"
    );

    // Prioridad de roles (si un usuario tiene múltiples roles)
    private static final List<String> ROLE_PRIORITY = List.of(
            "SECRETARIA",
            "PACIENTE",
            "DOCTOR"
    );

    /**
     * Determina la página principal basada únicamente en roles
     */
    public String getHomePageByRoles(edu.unl.cc.smilehub.domain.admin.Role roles) {
        if (roles == null || roles.getName().isEmpty()) {
            logger.warning("Usuario sin roles asignados");
            return "/dashboard.xhtml?faces-redirect=true";
        }


        logger.info("Rol del usuario: " + roles.getName());

        // Buscar por prioridad
        for (String priorityRole : ROLE_PRIORITY) {
            if (roles.equals(priorityRole)) {
                String homePage = ROLE_HOME_PAGES.get(priorityRole);
                logger.info("Redirigiendo a: " + homePage + " por rol: " + priorityRole);
                return homePage + "?faces-redirect=true";
            }
        }

        logger.warning("Rol no configurado: " + roles.getName());
        return "/dashboard.xhtml?faces-redirect=true";
    }

    /**
     * Verifica si un usuario tiene un rol específico
     */
    public boolean hasRole(Usuario user, String roleName) {
        if (user == null || user.getRole() == null) return false;

        return user.getRole().equals(roleName);
    }
}
