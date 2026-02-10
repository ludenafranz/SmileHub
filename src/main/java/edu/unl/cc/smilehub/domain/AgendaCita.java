/*
@author Kleyner.ls
 */
package edu.unl.cc.smilehub.domain;

import edu.unl.cc.smilehub.gestion.Paciente;
import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Entity
public class AgendaCita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "agenda")
    private List<Cita> agenda;

    public AgendaCita() {
        this.agenda = new ArrayList<>();
    }

    public AgendaCita(List<Cita> agenda) {

        this.agenda = agenda;
    }


    public void agregarCita(Cita cita) {
        if (agenda == null) {
            this.agenda = new ArrayList<>();
        }
        if (!agenda.contains(cita)) {
            this.agenda.add(cita);
        }
    }

    public void eliminarCita(Cita cita) {
        if (agenda.contains(cita)) {
            this.agenda.remove(cita);
        }
    }


    public List<Cita> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Cita> agenda) {
        this.agenda = agenda;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AgendaCita{");
        sb.append("agenda=").append(agenda);
        sb.append('}');
        return sb.toString();
    }
}