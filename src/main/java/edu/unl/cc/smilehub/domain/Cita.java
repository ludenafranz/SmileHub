package edu.unl.cc.smilehub.domain;

import edu.unl.cc.smilehub.domain.admin.TipoIdentificacion;
import edu.unl.cc.smilehub.gestion.Doctor;
import edu.unl.cc.smilehub.gestion.EntidadLegal;
import edu.unl.cc.smilehub.gestion.Paciente;
import jakarta.persistence.*;

import javax.print.attribute.standard.NumberOfDocuments;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Cita implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate fecha;


    private LocalTime hora;


    private Integer turno;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cita")
    private EstadoCita estadoCita;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private TipoAtencion tipoAtencion;


    public Cita() {

    }

    public Cita(LocalDate fecha, LocalTime hora, Integer turno,
                EstadoCita estadoCita, Paciente paciente, Doctor doctor,
                TipoAtencion atenciones) {
        this.fecha = fecha;
        this.hora = hora;
        this.turno = turno;
        this.estadoCita = estadoCita;
        this.paciente = paciente;
        this.doctor = doctor;
        this.tipoAtencion = atenciones;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() { return hora; }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public TipoAtencion getTipoAtencion() {
        return tipoAtencion;
    }

    public void setTipoAtencion(TipoAtencion tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
    }
}