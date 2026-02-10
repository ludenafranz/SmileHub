package edu.unl.cc.smilehub.domain;


public class Gmail {

    private String correo;
    private String asunto;
    private String contenido;

    public Gmail(String correo, String asunto, String contenido) {
        this.correo = correo;
        this.asunto = asunto;
        this.contenido = contenido;
    }

    private void propiedades(){

    }

    public void envairGmail() {

    }


    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}
