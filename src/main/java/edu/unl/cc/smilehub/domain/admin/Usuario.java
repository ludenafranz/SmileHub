package edu.unl.cc.smilehub.domain.admin;

import jakarta.persistence.*;

import java.io.Serializable;
import edu.unl.cc.smilehub.domain.admin.Role;

@Entity
@Table(name = "user_")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String identificacion;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificacion")
    private TipoIdentificacion tipoIdentificacion;

    @Column(nullable = false)
    private String password;

    @OneToOne
    private Role role;

    // El constructor protegido está perfecto para JPA
    protected Usuario() {
    }

    public Usuario(String identificacion,
                   TipoIdentificacion tipoIdentificacion,
                   String password,
                   Role role) {
        this.identificacion = identificacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.password = password;
        this.role = role;
    }

    // --- GETTERS Y SETTERS (Indispensables para que JPA funcione) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}