package org.balicki.AccesoUsuario.model;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {
    private int idUsuario;
    @NotNull(message = "{usuario.notnull}")
    @NotBlank(message = "{usuario.notblank}")
    @Email(regexp = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+", message = "{usuario.email}")
    private String usuario;
    @NotNull(message = "{clave.notnull}")
    @NotBlank(message = "{clave.notblank}")
    @Pattern(regexp = "^(?=(?:.*\\d))(?=.*[A-Z])(?=.*[a-z])(?=.*[.,*!?¿¡/#$%&_])\\S{6,12}$", message = "{clave.pattern}")
    private String clave;
    @NotNull(message = "{confirmaClave.notnull}")
    @NotBlank(message = "{confirmaClave.notblank}")
    @Pattern(regexp = "^(?=(?:.*\\d))(?=.*[A-Z])(?=.*[a-z])(?=.*[.,*!?¿¡/#$%&_])\\S{6,12}$", message = "{clave.pattern}")
    private String confirmaClave;
    private String rutaFotoPerfil;
    @NotNull(message = "{apellido1.notnull}")
    @NotBlank(message = "{apellido1.notblank}")
    private String apellido1;
    @NotNull(message = "{apellido2.notnull}")
    @NotBlank(message = "{apellido2.notblank}")
    private String apellido2;
    @NotNull(message = "{nombre.notnull}")
    @NotBlank(message = "{nombre.notblank}")
    private String nombre;
    @NotNull(message = "{siglasGenero.notnull}")
    private String siglasGenero;
    @NotNull(message = "{siglasPais.notnull}")
    private String siglasPais;
    private String comentarios;
    private LocalDate fechaCreacion;
    @NotNull(message = "{fechaNacimiento.notnull}")
    private LocalDate fechaNacimiento;
    @NotNull(message = "{aficiones.notnull}")
    private List<String> aficiones;
    @NotNull(message = "{musicas.notnull}")
    private List<String> musicas;

    /**
     *
     */
    public Usuario() {
    }

    /**
     * @param idUsuario
     * @param usuario
     * @param clave
     * @param confirmaClave
     * @param rutaFotoPerfil
     * @param apellido1
     * @param apellido2
     * @param nombre
     * @param siglasGenero
     * @param siglasPais
     * @param comentarios
     * @param fechaCreacion
     * @param fechaNacimiento
     * @param aficiones
     * @param musicas
     */
    public Usuario(int idUsuario,
                   String usuario,
                   String clave,
                   String confirmaClave,
                   String rutaFotoPerfil,
                   String apellido1,
                   String apellido2,
                   String nombre,
                   String siglasGenero,
                   String siglasPais,
                   String comentarios,
                   LocalDate fechaCreacion,
                   LocalDate fechaNacimiento,
                   List<String> aficiones,
                   List<String> musicas) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
        this.confirmaClave = confirmaClave;
        this.rutaFotoPerfil = rutaFotoPerfil;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.siglasGenero = siglasGenero;
        this.siglasPais = siglasPais;
        this.comentarios = comentarios;
        this.fechaCreacion = fechaCreacion;
        this.fechaNacimiento = fechaNacimiento;
        this.aficiones = aficiones;
        this.musicas = musicas;
    }

    /**
     * @return
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    /**
     * @return
     */
    public String getConfirmaClave() {
        return confirmaClave;
    }

    /**
     * @param confirmaClave
     */
    public void setConfirmaClave(String confirmaClave) {
        this.confirmaClave = confirmaClave;
    }

    /**
     * @return
     */
    public String getRutaFotoPerfil() {
        return rutaFotoPerfil;
    }

    /**
     * @param rutaFotoPerfil
     */
    public void setRutaFotoPerfil(String rutaFotoPerfil) {
        this.rutaFotoPerfil = rutaFotoPerfil;
    }

    /**
     * @return
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return
     */
    public String getSiglasGenero() {
        return siglasGenero;
    }

    /**
     * @param siglasGenero
     */
    public void setSiglasGenero(String siglasGenero) {
        this.siglasGenero = siglasGenero;
    }

    /**
     * @return
     */
    public String getSiglasPais() {
        return siglasPais;
    }

    /**
     * @param siglasPais
     */
    public void setSiglasPais(String siglasPais) {
        this.siglasPais = siglasPais;
    }

    /**
     * @return
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion
     */
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return
     */
    public List<String> getAficiones() {
        return aficiones;
    }

    /**
     * @param aficiones
     */
    public void setAficiones(List<String> aficiones) {
        this.aficiones = aficiones;
    }

    /**
     * @return
     */
    public List<String> getMusicas() {
        return musicas;
    }

    /**
     * @param musicas
     */
    public void setMusicas(List<String> musicas) {
        this.musicas = musicas;
    }
}
