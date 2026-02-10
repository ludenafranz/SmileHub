package edu.unl.cc.smilehub.business.security;

import edu.unl.cc.smilehub.domain.AgendaCita;
import edu.unl.cc.smilehub.domain.Cita;
import edu.unl.cc.smilehub.domain.EstadoCita;
import edu.unl.cc.smilehub.faces.CrudGenericService;
import edu.unl.cc.smilehub.gestion.Doctor;
import edu.unl.cc.smilehub.gestion.Paciente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class AgendaCitaRepository {

    @PersistenceContext
    private EntityManager em;

    @Inject
    CrudGenericService crudGenericService;

    public AgendaCita actualizar(AgendaCita agendaCita) {
        crudGenericService.update(agendaCita);
        return agendaCita;
    }

    public AgendaCita guardar(AgendaCita agendaCita) {
        agendaCita = crudGenericService.create(agendaCita);
        return agendaCita;
    }

    public List<Paciente> obtenerPacientes() {
        return em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
    }

    public List<Doctor> obtenerDoctores() {
        return em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
    }


    public List<Cita> findAll() {
        return em.createQuery("SELECT c FROM Cita c ORDER BY c.fecha DESC, c.hora DESC", Cita.class)
                .getResultList();
    }

    public List<Cita> findByPaciente(String nuemroidentificacion) {
        return em.createQuery("SELECT c FROM Cita c WHERE c.paciente.numeroIdentificacion = :nuemroidentificacion ORDER BY c.fecha DESC", Cita.class)
                .setParameter("nuemroidentificacion", nuemroidentificacion)
                .getResultList();
    }

    public long countCitasHoy() {
        return em.createQuery("SELECT COUNT(c) FROM Cita c WHERE c.fecha = CURRENT_DATE", Long.class)
                .getSingleResult();
    }

    public long countPendientes() {
        return em.createQuery("SELECT COUNT(c) FROM Cita c WHERE c.estadoCita = :estado", Long.class)
                .setParameter("estado", EstadoCita.PENDIENTE)
                .getSingleResult();
    }
}

