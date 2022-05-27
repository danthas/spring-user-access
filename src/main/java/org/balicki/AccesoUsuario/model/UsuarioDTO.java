package org.balicki.AccesoUsuario.model;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDTO {
    int idUsuario;
    String usuario,
            rutaFotoPerfil,
            apellido1,
            apellido2,
            nombre,
            siglasGenero,
            siglasPais;
    LocalDate fechaNacimiento;
    List<String> listaAficiones;
    List<String> listaMusicas;

    /**
     *
     */
    public UsuarioDTO() {
    }

    /**
     * @param idUsuario
     * @param usuario
     * @param rutaFotoPerfil
     * @param apellido1
     * @param apellido2
     * @param nombre
     * @param siglasGenero
     * @param siglasPais
     * @param fechaNacimiento
     * @param listaAficiones
     * @param listaMusicas
     */
    public UsuarioDTO(int idUsuario,
                      String usuario,
                      String rutaFotoPerfil,
                      String apellido1,
                      String apellido2,
                      String nombre,
                      String siglasGenero,
                      String siglasPais,
                      LocalDate fechaNacimiento,
                      List<String> listaAficiones,
                      List<String> listaMusicas) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.rutaFotoPerfil = rutaFotoPerfil;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.siglasGenero = siglasGenero;
        this.siglasPais = siglasPais;
        this.fechaNacimiento = fechaNacimiento;
        this.listaAficiones = listaAficiones;
        this.listaMusicas = listaMusicas;
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
    public List<String> getListaAficiones() {
        return listaAficiones;
    }

    /**
     * @param listaAficiones
     */
    public void setListaAficiones(List<String> listaAficiones) {
        this.listaAficiones = listaAficiones;
    }

    /**
     * @return
     */
    public List<String> getListaMusicas() {
        return listaMusicas;
    }

    /**
     * @param listaMusicas
     */
    public void setListaMusicas(List<String> listaMusicas) {
        this.listaMusicas = listaMusicas;
    }
}
