package edu.unl.cc.smilehub.business;

import edu.unl.cc.smilehub.business.security.RoleRepository;
import edu.unl.cc.smilehub.business.security.UserRepository;
import edu.unl.cc.smilehub.domain.admin.Usuario;
import edu.unl.cc.smilehub.exception.AlreadyEntityException;
import edu.unl.cc.smilehub.exception.CredentialInvalidException;
import edu.unl.cc.smilehub.exception.EncryptorException;
import edu.unl.cc.smilehub.exception.EntityNotFoundException;
import edu.unl.cc.smilehub.util.EncryptorManager;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class SecurityFacade implements Serializable {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    public Usuario createUser(Usuario user) throws EncryptorException, AlreadyEntityException {
        String pwdEncripted = EncryptorManager.encrypt(user.getPassword());
        user.setPassword(pwdEncripted);
        Usuario userPersisted = userRepository.save(user);
        return userPersisted;
    }

    public Usuario updateUser(Usuario user) throws AlreadyEntityException, EncryptorException {
        if (user.getId() == null){
            return createUser(user);
        }
        String pwdEncrypted = EncryptorManager.encrypt(user.getPassword());
        user.setPassword(pwdEncrypted);
        try {
            Usuario userFound = userRepository.find(user.getName());
            if  (!userFound.getId().equals(user.getId())){
                throw new AlreadyEntityException("Ya existe otro usuario con ese nombre");
            }
        } catch (EntityNotFoundException ignored) {
        }
        return userRepository.save(user);
    }

    public Usuario authenticate(String name, String password) throws CredentialInvalidException {
        try {
            Usuario userFound = userRepository.findWithRoles(name);
            String pwdEncrypted = EncryptorManager.encrypt(password);
            if (pwdEncrypted.equals(userFound.getPassword())){
                return userFound;
            }
            throw new CredentialInvalidException();
        } catch (EntityNotFoundException e) {
            throw new CredentialInvalidException();
        } catch (EncryptorException e) {
            throw new CredentialInvalidException("Credenciales incorrectas", e);
        }
    }



    public Set<Role> findAllRolesWithPermission(){
        return roleRepository.findAllWithPermissions();
    }
    public edu.unl.cc.smilehub.domain.admin.Role findRoleNamesByUser(Long userId) throws EntityNotFoundException {
        Usuario user = userRepository.findWithRoles(userId);
        return user.getRole();
    }

    public Set<Role> findRolesWithPermissionByUser(Long userId) throws EntityNotFoundException {
        Usuario user = userRepository.find(userId);
        // Simulación de usuarios con rol ADMIN
        Role  role = roleRepository.find("ADMIN");
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(role);
        return roles;
    }

    public List<Usuario> findUsers(String criteria) throws EntityNotFoundException {
        return userRepository.findWithLike(criteria);
    }

    public Usuario findUser(Long userId) throws EntityNotFoundException {
        return  userRepository.find(userId);
    }
}