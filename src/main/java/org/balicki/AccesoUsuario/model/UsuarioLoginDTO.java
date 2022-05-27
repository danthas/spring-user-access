package org.balicki.AccesoUsuario.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class UsuarioLoginDTO {
    @NotNull(message = "{usuario.notnull}")
    @NotBlank(message = "{usuario.notblank}")
    @Email(regexp = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+", message = "{usuario.email}")
    private String usuario;
    @NotNull(message = "{clave.notnull}")
    @NotBlank(message = "{clave.notblank}")
    @Pattern(regexp = "^(?=(?:.*\\d))(?=.*[A-Z])(?=.*[a-z])(?=.*[.,*!?¿¡/#$%&_])\\S{6,12}$", message = "{clave.pattern}")
    private String clave;

    /**
     *
     */
    public UsuarioLoginDTO() {
    }

    /**
     * @param nombre
     * @param clave
     */
    public UsuarioLoginDTO(String nombre,
                           String clave) {
        this.usuario = nombre;
        this.clave = clave;
    }

    /**
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
}
