package org.balicki.AccesoUsuario.controller;

import org.balicki.AccesoUsuario.model.Colecciones;
import org.balicki.AccesoUsuario.model.Usuario;
import org.balicki.AccesoUsuario.model.UsuarioLoginDTO;
import org.balicki.AccesoUsuario.model.Usuarios;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    private boolean modificacion = false;
    private String ultimoUser = "";
    private int cont;
    private Cookie contador;
    private Cookie id;

    /**
     * @param solicitudHttp
     * @return
     * @throws UnknownHostException
     */
    @GetMapping("alta")
    public ModelAndView devuelveFormularioAltaUsuario(HttpServletRequest solicitudHttp) throws UnknownHostException {
        ModelAndView mAV = new ModelAndView();
        Map<String, String> listaErrores = new HashMap<String, String>();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String mac = Usuarios.direccionMAC();
        String navegador = solicitudHttp.getHeader("user-agent");
        mAV.addObject("direccionIP", ip);
        mAV.addObject("direccionMAC", mac);
        mAV.addObject("navegador", navegador);
        mAV.addObject("usuario", new Usuario());
        mAV.addObject("listaGenerosOrdenada", Colecciones.listaGenerosOrdenada);
        mAV.addObject("listaPaisesOrdenada", Colecciones.listaPaisesOrdenada);
        mAV.addObject("listaAficionesOrdenada", Colecciones.listaAficionesOrdenada);
        mAV.addObject("listaMusicasOrdenada", Colecciones.listaMusicasOrdenada);
        mAV.addObject("listaErrores", listaErrores);
        mAV.setViewName("alta");
        return mAV;
    }

    /**
     * @param usuario
     * @param br
     * @param fotoPerfil
     * @param solicitudHttp
     * @return
     * @throws UnknownHostException
     */
    @PostMapping("alta")
    public ModelAndView recibeParametrosAltaUsuario(@Valid Usuario usuario,
                                                    BindingResult br,
                                                    @RequestParam(required = false) MultipartFile fotoPerfil,
                                                    HttpServletRequest solicitudHttp) throws UnknownHostException {
        ModelAndView mAV = new ModelAndView();
        Map<String, String> listaErrores = new HashMap<String, String>();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String mac = Usuarios.direccionMAC();
        String navegador = solicitudHttp.getHeader("user-agent");
        // Validaciones de errores usuario
        if (br.hasErrors()) {
            // Clave
            if (!usuario.getClave().equals(usuario.getConfirmaClave())) {
                listaErrores.put("clave", "El campo clave y confirma clave deben ser identicos.");
            }
            // Si se inserta el format date en el application.properties
            // no se puede realizar estas comprobaciones
            // Fecha de nacimiento
//            try {
//                if (usuario.getFechaNacimiento().isAfter(LocalDate.now()) || usuario.getFechaNacimiento().isEqual(LocalDate.now())) {
//                    listaErrores.put("fechaNacimiento", "La fecha de nacimiento tiene que ser anterior a la actual.");
//                }
//                Period periodo = Period.between(usuario.getFechaNacimiento(), LocalDate.now());
//                if (periodo.getYears() < 18) {
//                    listaErrores.put("fechaNacimiento", "Debes tener una edad de 18 minimo para registrarte.");
//                }
//            } catch (DateTimeParseException e) {
//                listaErrores.put("fechaNacimiento", "Formato de fecha de nacimiento invalido.");
//            }
            mAV.addObject("direccionIP", ip);
            mAV.addObject("direccionMAC", mac);
            mAV.addObject("navegador", navegador);
            mAV.addObject("listaGenerosOrdenada", Colecciones.listaGenerosOrdenada);
            mAV.addObject("listaPaisesOrdenada", Colecciones.listaPaisesOrdenada);
            mAV.addObject("listaAficionesOrdenada", Colecciones.listaAficionesOrdenada);
            mAV.addObject("listaMusicasOrdenada", Colecciones.listaMusicasOrdenada);
            mAV.addObject("listaErrores", listaErrores);
            mAV.setViewName("alta");
        } else {
            // Comprobacion de asignacion de foto de perfil
            // Si quieres que agregar una foto distinta al
            // usuario debera estar en /img/
            if (fotoPerfil.isEmpty()) {
                usuario.setRutaFotoPerfil("/img/defecto.png");
            } else {
                usuario.setRutaFotoPerfil("/img/" + Usuarios.establecerFoto(fotoPerfil));
            }
            // Comprobacion de si se edita o da de alta un usuario
            if (modificacion) {
                Usuarios.modificarUsuario(usuario, usuario.getIdUsuario());
                Usuarios.modificarUsuarioDTO(usuario, usuario.getIdUsuario());
                Usuarios.modificarUsuarioLoginDTO(usuario, usuario.getIdUsuario());
                mAV.setViewName("redirect:/acceso/login");
                modificacion = false;
            } else {
                Usuarios.crearUsuario(usuario);
                Usuarios.creaUsuarioDTO(usuario);
                Usuarios.creaUsuarioLoginDTO(usuario);
                mAV.setViewName("redirect:/acceso/login");
            }
        }
        return mAV;
    }

    // Recuperar datos de un fichero json pero no funciona
//    @GetMapping("/listado")
//    public ModelAndView devuelveListadoUsuarios() {
//        ModelAndView mAV = new ModelAndView();
//        List<Usuario> lista = Usuarios.recuperaUsuarios();
//        List<UsuarioDTO> envia = new ArrayList<UsuarioDTO>();
//        for (int i = 0; i < lista.size(); i++) {
//            envia.add(new ModelMapper().map(lista.get(i), UsuarioDTO.class));
//        }
//        mAV.addObject("listaUsuarios", envia);
//        mAV.setViewName("listado");
//        return mAV;
//    }

    /**
     * @param _id
     * @param solicitudHttp
     * @param respuestaHttp
     * @return
     * @throws UnknownHostException
     */
    @GetMapping("listado")
    public ModelAndView devuelveListadoUsuarios(@CookieValue(value = "_id", required = false) String _id,
                                                HttpServletRequest solicitudHttp,
                                                HttpServletResponse respuestaHttp) throws UnknownHostException {
        ModelAndView mAV = new ModelAndView();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String mac = Usuarios.direccionMAC();
        String navegador = solicitudHttp.getHeader("user-agent");
        UsuarioLoginDTO usuarioLoginDTO;
        usuarioLoginDTO = (UsuarioLoginDTO) solicitudHttp.getSession().getAttribute("usuarioLoginDTO");
        // Comprobacion para las cookies
        // Si el ultimo usuario que incia sesion no coincide con
        // el usuario que acaba de iniciar sesion,
        // se sobreescriben las cookies anteriores
        if (_id != null && ultimoUser.equals(usuarioLoginDTO.getUsuario())) {
            id = new Cookie("_id", solicitudHttp.getCookies()[0].getValue());
            cont = Integer.parseInt(solicitudHttp.getCookies()[1].getValue());
            cont++;
            contador = new Cookie("_contador", String.valueOf(cont));
            respuestaHttp.addCookie(id);
            respuestaHttp.addCookie(contador);
            mAV.addObject("bienvenida", "Bienvenido de nuevo");
        } else {
            cont = 1;
            id = new Cookie("_id", generaCadenaAlfanumericaAleatoria(64));
            contador = new Cookie("_contador", String.valueOf(cont));
            respuestaHttp.addCookie(id);
            respuestaHttp.addCookie(contador);
            mAV.addObject("bienvenida", "Bienvenido por primera vez");
        }
        // Guardo el ultimo usuario que ha iniciado sesion
        ultimoUser = usuarioLoginDTO.getUsuario();
        mAV.addObject("textoID", ultimoUser);
        mAV.addObject("textoCONT", contador.getValue());
        mAV.addObject("direccionIP", ip);
        mAV.addObject("direccionMAC", mac);
        mAV.addObject("navegador", navegador);
        // Recupero los datos de los usuarios directamente de la coleccion
        mAV.addObject("listaUsuarios", Usuarios.listadoDTO);
        mAV.setViewName("listado");
        return mAV;
    }

    /**
     * @param idUsuario
     * @param solicitudHttp
     * @return
     * @throws UnknownHostException
     */
    @GetMapping("modificacion/{idUsuario}")
    public ModelAndView devuelveFormularioModificacionUsuario(@PathVariable("idUsuario") int idUsuario,
                                                              HttpServletRequest solicitudHttp) throws UnknownHostException {
        ModelAndView mAV = new ModelAndView();
        Map<String, String> listaErrores = new HashMap<String, String>();
        Usuario usuario = Usuarios.listadoUsuarios.get((idUsuario - 1));
        String ip = InetAddress.getLocalHost().getHostAddress();
        String mac = Usuarios.direccionMAC();
        String navegador = solicitudHttp.getHeader("user-agent");
        mAV.addObject("direccionIP", ip);
        mAV.addObject("direccionMAC", mac);
        mAV.addObject("navegador", navegador);
        mAV.addObject("usuario", usuario);
        mAV.addObject("listaGenerosOrdenada", Colecciones.listaGenerosOrdenada);
        mAV.addObject("listaPaisesOrdenada", Colecciones.listaPaisesOrdenada);
        mAV.addObject("listaAficionesOrdenada", Colecciones.listaAficionesOrdenada);
        mAV.addObject("listaMusicasOrdenada", Colecciones.listaMusicasOrdenada);
        mAV.addObject("listaErrores", listaErrores);
        mAV.setViewName("alta");
        modificacion = true;
        return mAV;
    }

    /**
     * @param idUsuario
     * @return
     */
    @GetMapping("baja/{idUsuario}")
    public ModelAndView bajaUsuario(@PathVariable("idUsuario") int idUsuario) {
        ModelAndView mAV = new ModelAndView();
        Usuarios.eliminarUsuario((idUsuario));
        mAV.setViewName("redirect:/acceso/login");
        return mAV;
    }

    private String generaCadenaAlfanumericaAleatoria(int longitudCadena) {
        int leftLimit = 48; // Numero 0
        int rightLimit = 122; // Letra z
        Random random = new Random();

        String cadenaAleatoriaGenerada = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(longitudCadena)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return (cadenaAleatoriaGenerada);
    }
}
