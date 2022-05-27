package org.balicki.AccesoUsuario.model;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

public class Usuarios {
    public static int idUsuario = 0;
    public static List<Usuario> listadoUsuarios = new ArrayList<Usuario>();
    public static List<UsuarioDTO> listadoDTO = new ArrayList<UsuarioDTO>();
    public static List<UsuarioLoginDTO> listadoLogin = new ArrayList<UsuarioLoginDTO>();

    /**
     * @param id
     */
    public static void eliminarUsuario(int id) {
        if (id > listadoUsuarios.size()) {
            id = listadoUsuarios.size();
        }
        listadoDTO.remove(id - 1);
        listadoUsuarios.remove(id - 1);
        listadoLogin.remove(id - 1);
    }

    /**
     * @param usuario
     * @param id
     */
    public static void modificarUsuarioLoginDTO(Usuario usuario, int id) {
        listadoLogin.get(id).setUsuario(usuario.getUsuario());
        listadoLogin.get(id).setClave(usuario.getClave());
    }

    /**
     * @param usuario
     * @param id
     */
    public static void modificarUsuarioDTO(Usuario usuario, int id) {
        listadoDTO.get(id).setUsuario(usuario.getUsuario());
        listadoDTO.get(id).setRutaFotoPerfil(usuario.getRutaFotoPerfil());
        listadoDTO.get(id).setApellido1(usuario.getApellido1());
        listadoDTO.get(id).setApellido2(usuario.getApellido2());
        listadoDTO.get(id).setNombre(usuario.getNombre());
        listadoDTO.get(id).setSiglasGenero(usuario.getSiglasGenero());
        listadoDTO.get(id).setSiglasPais(usuario.getSiglasPais());
        listadoDTO.get(id).setFechaNacimiento(usuario.getFechaNacimiento());
        listadoDTO.get(id).setListaAficiones(usuario.getAficiones());
        listadoDTO.get(id).setListaMusicas(usuario.getMusicas());
    }

    /**
     * @param usuario
     * @param id
     */
    public static void modificarUsuario(Usuario usuario, int id) {
        listadoUsuarios.get(id).setUsuario(usuario.getUsuario());
        listadoUsuarios.get(id).setClave(usuario.getClave());
        listadoUsuarios.get(id).setConfirmaClave(usuario.getConfirmaClave());
        listadoUsuarios.get(id).setRutaFotoPerfil(usuario.getRutaFotoPerfil());
        listadoUsuarios.get(id).setApellido1(usuario.getApellido1());
        listadoUsuarios.get(id).setApellido2(usuario.getApellido2());
        listadoUsuarios.get(id).setNombre(usuario.getNombre());
        listadoUsuarios.get(id).setSiglasGenero(usuario.getSiglasGenero());
        listadoUsuarios.get(id).setSiglasPais(usuario.getSiglasPais());
        listadoUsuarios.get(id).setComentarios(usuario.getComentarios());
        listadoUsuarios.get(id).setFechaCreacion(LocalDate.now());
        listadoUsuarios.get(id).setFechaNacimiento(usuario.getFechaNacimiento());
        listadoUsuarios.get(id).setAficiones(usuario.getAficiones());
        listadoUsuarios.get(id).setMusicas(usuario.getMusicas());
    }

    /**
     * @param usuario
     */
    public static void creaUsuarioLoginDTO(Usuario usuario) {
        listadoLogin.add(new UsuarioLoginDTO(usuario.getUsuario(),
                usuario.getClave()));
    }

    /**
     * @param usuario
     */
    public static void creaUsuarioDTO(Usuario usuario) {
        listadoDTO.add(new UsuarioDTO(usuario.getIdUsuario(),
                usuario.getUsuario(),
                usuario.getRutaFotoPerfil(),
                usuario.getApellido1(),
                usuario.getApellido2(),
                usuario.getNombre(),
                usuario.getSiglasGenero(),
                usuario.getSiglasPais(),
                usuario.getFechaNacimiento(),
                usuario.getAficiones(),
                usuario.getMusicas()));
    }

    /**
     * @param usuario
     */
    public static void crearUsuario(Usuario usuario) {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        usuario.setIdUsuario(++idUsuario);
        usuario.setFechaCreacion(LocalDate.now());
        listadoUsuarios.add(usuario);
//        File json = new File("./src/main/resources/static/json/registros.json");
//        try {
//            mapper.writeValue(json, usuario);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @param archivo
     * @return
     */
    // Metodo para establecer la foto de perfil
    public static String establecerFoto(MultipartFile archivo) {
        try {
            String nombre_completo = archivo.getOriginalFilename();
            String path_archivo = ResourceUtils.getURL("classpath:").getPath() + "/static/img/";
            //UUID para no duplicar
            String nombre_archivo = UUID.randomUUID() + nombre_completo;
            nombre_completo = path_archivo + nombre_archivo;
            File archivo_destinatario = new File(nombre_completo);
            //Si no existe la carpeta la crea
            if (!archivo_destinatario.getParentFile().exists()) {
                archivo_destinatario.getParentFile().mkdirs();
            }
            archivo.transferTo(archivo_destinatario);
            return nombre_archivo;
        } catch (Exception e) {
            return "defecto.png";
        }
    }

    /**
     * @return
     */
    // Metodo para obtener la direccion MAC, creo que unicamente funciona en Ubuntu
    public static String direccionMAC() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while (networks.hasMoreElements()) {
                NetworkInterface network = networks.nextElement();
                byte[] mac = network.getHardwareAddress();
                if (mac != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    return String.valueOf(sb);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
