package edu.unl.cc.smilehub.domain.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class Role implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Validaciones a nivel de vista y modelo
    @Column(unique = true, nullable = false, length = 50)
    private String name;


    public Role() {
    }

    public Role(Long id, @NotNull @NotEmpty String name) {
        this();
        this.id = id;
        this.setName(name);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull @NotEmpty String name) {
        this.name = name.trim();
    }




    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) && Objects.equals(getName(), role.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
