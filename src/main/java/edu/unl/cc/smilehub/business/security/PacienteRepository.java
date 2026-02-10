package edu.unl.cc.smilehub.business.security;

import edu.unl.cc.smilehub.domain.Cita;
import edu.unl.cc.smilehub.faces.CrudGenericService;
import edu.unl.cc.smilehub.gestion.Paciente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Stateless
public class PacienteRepository implements Serializable {
    @Inject
    CrudGenericService crudGenericService;

    @PersistenceContext
    EntityManager em;

    public Paciente saver(Paciente paciente) {
        Paciente pacientesave = crudGenericService.create(paciente);
        return pacientesave;
    }



    public List<Paciente> findAll() {
        return em.createQuery("SELECT p FROM Paciente p ORDER BY p.nombres DESC", Paciente.class).
                getResultList();
    }
}
