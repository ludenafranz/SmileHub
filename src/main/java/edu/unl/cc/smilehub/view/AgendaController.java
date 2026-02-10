package edu.unl.cc.smilehub.view;

import edu.unl.cc.smilehub.business.security.AgendaCitaRepository;
import edu.unl.cc.smilehub.business.security.PacienteRepository;
import edu.unl.cc.smilehub.domain.Cita;
import edu.unl.cc.smilehub.domain.admin.Usuario;
import edu.unl.cc.smilehub.gestion.Paciente;
import edu.unl.cc.smilehub.view.security.UserSession;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jdk.jfr.Name;

import java.io.Serializable;
import java.util.List;

@Named
public class AgendaController implements Serializable {
    @Inject
    AgendaCitaRepository agendaCitaRepository;

    @Inject
    UserSession userSession;

    @Inject
    PacienteRepository pacienteRepository;

    private List<Cita> citas;

    private Paciente paciente;

    @PostConstruct
    public void init(){
        recojerCitas();
        encontrarPaciente();
    }

    private void encontrarPaciente(){
        List<Paciente> pacientelist = pacienteRepository.findAll();
        Usuario user = userSession.regresarUser();
        for (Paciente pacientes: pacientelist ){
            if(user.getIdentificacion().equals(pacientes.getNumeroIdentificacion())){
                paciente = pacientes;
            }
        }
    }

    public void recojerCitas(){
        citas = agendaCitaRepository.findByPaciente(userSession.regresarUser().getIdentificacion());
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
