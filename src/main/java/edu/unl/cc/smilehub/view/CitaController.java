package edu.unl.cc.smilehub.view;

import edu.unl.cc.smilehub.business.security.AgendaCitaRepository;
import edu.unl.cc.smilehub.business.security.DoctorRepository;
import edu.unl.cc.smilehub.business.security.PacienteRepository;
import edu.unl.cc.smilehub.domain.*;
import edu.unl.cc.smilehub.domain.admin.TipoIdentificacion;
import edu.unl.cc.smilehub.faces.FacesUtil;
import edu.unl.cc.smilehub.gestion.Doctor;
import edu.unl.cc.smilehub.gestion.Paciente;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CitaController implements Serializable {

    private static Logger logger = Logger.getLogger(CitaController.class.getName());
    @Inject
    private DoctorRepository doctorRepository;

    @Inject
    private PacienteRepository pacienteRepository;

    @Inject
    private AgendaCitaRepository agendaCitaRepository;

    @Inject
    private FacesUtil facesUtil;

    private Cita cita;
    private Paciente paciente;

    private Doctor doctor;
    private TipoAtencion tipoAtencion;
    private TipoIdentificacion tipoIdentificacion;
    private LocalDate fecha;
    private LocalTime hora;
    private Integer turno;
    private String razonSocial;;
    private String nombres;
    private String apellidos;
    private String numeroIdentificacion;
    private String telefono;
    private String correo;
    private EstadoCita estadoCita;
    private AgendaCita agendaCita;

    private List<Paciente> pacientes;
    private List<Doctor> doctors;
    private List<Cita> citas;


    @PostConstruct
    private void init(){
        this.cita = new Cita();
        this.paciente = new Paciente();
        this.doctor = new Doctor();
        this.agendaCita = new AgendaCita();
        allPacientes();
        alldocteres();
        iniciarAgenda();
    }

    public void iniciarAgenda(){
        citas = agendaCitaRepository.findAll();
    }

    public void doctor(){
        try{
            doctor.setRazonSocial(razonSocial);
            doctor.setApellidos(apellidos);
            doctor.setNumeroIdentificacion(numeroIdentificacion);
            doctor.setTelefono(telefono);
            doctor.setCorreo(correo);
            doctor.setNombres(nombres);
            doctor.setTipoIdentificacion(tipoIdentificacion);
            doctorRepository.saver(doctor);
            logger.info("Doctor "+doctor.getNombres()+" creado");
        } catch (Exception e) {
            logger.severe("Error al guardar doctor: "+doctor.getNombres());
        } finally {
            alldocteres();
            limpiar();
        }

    }

    public void asignarPaciente(Paciente paciente){
        this.paciente = paciente;
    }
    public void asignarDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    public void paciente(){
        try{
            paciente.setRazonSocial(razonSocial);
            paciente.setApellidos(apellidos);
            paciente.setNumeroIdentificacion(numeroIdentificacion);
            paciente.setTelefono(telefono);
            paciente.setCorreo(correo);
            paciente.setNombres(nombres);
            paciente.setTipoIdentificacion(tipoIdentificacion);

            pacienteRepository.saver(paciente);
            logger.info("Doctor " + doctor.getNombres() + " creado");
        }catch (Exception e){
            logger.severe("Error al guardar doctor: "+doctor.getNombres());
        }finally {
            allPacientes();
            limpiar();
        }

    }

    public void alldocteres(){
        doctors =  doctorRepository.findAll();
    }

    public void allPacientes(){
        pacientes =  pacienteRepository.findAll();
    }

    public void NuevaCita() {
        if(nombres == null){
            throw new IllegalArgumentException("nombres no puede ser nulo");
        }
        if(apellidos == null){
            throw new IllegalArgumentException("apellidos no puede ser nulo");
        }
        if(paciente.getNumeroIdentificacion() == null){
            throw new IllegalArgumentException("tipoIdentificacion no puede ser nulo");
        }
        if(paciente.getTelefono() == null){
            throw new IllegalArgumentException("telefono no puede ser nulo");
        }
        if(paciente.getTelefono() == null){
            throw new IllegalArgumentException("correo no puede ser nulo");
        }
        try{
            cita.setTipoAtencion(
                    TipoAtencion.CONTROL_SEGUIMIENTO);
            cita.setPaciente(paciente);
            cita.setDoctor(doctor);
            cita.setFecha(fecha);
            cita.setHora(hora);
            cita.setTurno(turno);
            cita.setEstadoCita(estadoCita);
            if(agendaCita == null){
                agendaCita.agregarCita(cita);
                agendaCitaRepository.guardar(agendaCita);
            } else {
                agendaCita.agregarCita(cita);
                agendaCitaRepository.actualizar(agendaCita);
            }
        } catch (Exception e) {
            logger.severe("Error al guardar doctor: "+doctor.getNombres());
        }finally {
            limpiarcita();
        }
    }

    public void limpiarcita(){
        cita = null;
    }

    public void limpiar(){
        doctor = null;
        paciente = null;
        this.apellidos = "";
        this.numeroIdentificacion = "";
        tipoIdentificacion = TipoIdentificacion.CEDULA;
        this.razonSocial = "";
        this.correo = "";
        this.fecha = null;
        this.hora = null;
        this.turno = 1;
        this.estadoCita = EstadoCita.PENDIENTE;
        limpiarcita();
    }

    public void actualizarCita(){
        if(cita == null){
            throw new IllegalArgumentException("cita no puede ser nulo");
        }
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public AgendaCita getAgendaCita() {
        return agendaCita;
    }

    public void setAgendaCita(AgendaCita agendaCita) {
        this.agendaCita = agendaCita;
    }

    public TipoAtencion getTipoAtencion() {
        return tipoAtencion;
    }

    public void setTipoAtencion(TipoAtencion tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}