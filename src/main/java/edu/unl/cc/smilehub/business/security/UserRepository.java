package edu.unl.cc.smilehub.business.security;


import edu.unl.cc.smilehub.domain.admin.Usuario;
import edu.unl.cc.smilehub.exception.EntityNotFoundException;
import edu.unl.cc.smilehub.faces.CrudGenericService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class UserRepository {

    @Inject
    private CrudGenericService crudService;
    @PersistenceContext//(name = "JbrewPU", unitName = "JbrewPU")
    private EntityManager em;

    public Usuario save(Usuario  user){
        return crudService.create(user);
    }

    public Usuario  find(@NotNull Long id) throws EntityNotFoundException {
        Usuario  user = crudService.find(Usuario .class, id);
        if (user == null){
            throw new EntityNotFoundException("User no encontrado con [" + id + "]");
        }
        return user;
    }

    public Usuario  find(@NotNull String name) throws EntityNotFoundException {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        Usuario  userFound = crudService.findSingleResultOrNullWithNamedQuery("User.findLikeName", params);
        if (userFound == null){
            throw new EntityNotFoundException("User no encontrado con [" + name + "]");
        }
        return userFound;
    }

    public List<Usuario > findWithLike(@NotNull String name) throws EntityNotFoundException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", "%" + name + "%");
        return crudService.findWithNamedQuery("User.findLikeName", params);
    }

    //metodo provicionar por tiempo
    public Usuario  findWithRoles(@NotNull String name) throws EntityNotFoundException {
        Map<String, Object> params = new HashMap<>();
        params.put("username", name);
        Usuario  userFound = crudService.findSingleResultOrNullWithNamedQuery("User.findWithRoles", params);
        if (userFound == null) {
            throw new EntityNotFoundException("Usuario no encontrado: " + name);
        }
        return userFound;
    }

    //metodo provicionar por tiempo
    public Usuario  findWithRoles(Long userId) throws EntityNotFoundException {
        String jpql = "SELECT DISTINCT u FROM Usuario  u " +
                "LEFT JOIN FETCH u.role " +
                "WHERE u.id = :userId";

        try {
            return em.createQuery(jpql, Usuario .class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Usuario no encontrado ID: " + userId);
        }
    }


}
