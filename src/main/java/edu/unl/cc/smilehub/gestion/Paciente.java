package edu.unl.cc.smilehub.gestion;

import edu.unl.cc.smilehub.domain.admin.TipoIdentificacion;
import edu.unl.cc.smilehub.domain.admin.Usuario;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Paciente extends EntidadLegal implements Serializable {

    public Paciente() {
        super();
    }

    public Paciente(Long id, Usuario usuario,
                    String razonSocial,
                    String nombres,
                    String apellidos,
                    TipoIdentificacion tipoIdentificacion,
                    String numeroIdentificacion,
                    String telefono,
                    String correo) {

        super(id,razonSocial, nombres, apellidos,
                tipoIdentificacion, numeroIdentificacion,
                telefono, correo);
    }
}
