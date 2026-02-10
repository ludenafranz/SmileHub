package edu.unl.cc.smilehub.business.security;

import edu.unl.cc.smilehub.faces.CrudGenericService;
import edu.unl.cc.smilehub.gestion.Doctor;
import edu.unl.cc.smilehub.gestion.Paciente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class DoctorRepository {

    @Inject
    CrudGenericService crudGenericService;

    @PersistenceContext
    EntityManager em;

    public Doctor saver(Doctor doctor) {
        Doctor doctorsave = crudGenericService.create(doctor);
        return doctorsave;
    }

    public List<Doctor> findAll() {
        return em.createQuery("SELECT d FROM Doctor d ORDER BY d.nombres DESC", Doctor.class).
                getResultList();
    }

}
