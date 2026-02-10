package edu.unl.cc.smilehub.domain;

import jakarta.persistence.*;
import java.io.Serializable;


public class AtencionMedica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private double costoAtencion;

    @Column(name = "descripcion_medica")
    private String descripcionMedica;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_atencion")
    private TipoAtencion tipoAtencion;

    public AtencionMedica() {}

    public AtencionMedica(double costoAtencion, String descripcionMedica, TipoAtencion tipoAtencion) {
        this.costoAtencion = costoAtencion;
        this.descripcionMedica = descripcionMedica;
        this.tipoAtencion = tipoAtencion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public double getCostoAtencion() { return costoAtencion; }
    public void setCostoAtencion(double costoAtencion) { this.costoAtencion = costoAtencion; }
    public String getDescripcionMedica() { return descripcionMedica; }
    public void setDescripcionMedica(String descripcionMedica) { this.descripcionMedica = descripcionMedica; }
    public TipoAtencion getTipoAtencion() { return tipoAtencion; }
    public void setTipoAtencion(TipoAtencion tipoAtencion) { this.tipoAtencion = tipoAtencion; }
}