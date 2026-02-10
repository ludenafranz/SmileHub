package edu.unl.cc.smilehub.faces;

/**
 * @author wduck (Wilman Chamba Z)
 */

import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Stateless
public class FacesUtil {

    public static void addSuccessMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    }

    public static void addSuccessMessageAndKeep(String summary, String detail) {
        addMessageAndKeep(FacesMessage.SEVERITY_INFO, summary, detail);
    }

    public static void addErrorMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    public static void addErrorMessageAndKeep(String summary, String detail) {
        addMessageAndKeep(FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    private static void addMessage(
            FacesMessage.Severity severity,
            String summary,
            String detail) {

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }

    private static void addMessageAndKeep(
            FacesMessage.Severity severity,
            String summary,
            String detail) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, summary, detail));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
}

