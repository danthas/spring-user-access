package org.balicki.AccesoUsuario.controller;

import org.balicki.AccesoUsuario.model.UsuarioLoginDTO;
import org.balicki.AccesoUsuario.model.Usuarios;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

@Controller
@RequestMapping("acceso")
public class AccesoController {
    /**
     * @param solicitudHttp
     * @return
     * @throws UnknownHostException
     */
    @GetMapping("login")
    public ModelAndView devuelveFormularioLogin(HttpServletRequest solicitudHttp) throws Exception {
        ModelAndView mAV = new ModelAndView();
        UsuarioLoginDTO usuarioLoginDTO;
        String ip = InetAddress.getLocalHost().getHostAddress();
        String mac = Usuarios.direccionMAC();
        String navegador = solicitudHttp.getHeader("user-agent");
        // Guardo el usuario de login en la sesion
        if (solicitudHttp.getSession().getAttribute("usuarioLoginDTO") != null) {
            usuarioLoginDTO = (UsuarioLoginDTO) solicitudHttp.getSession().getAttribute("usuarioLoginDTO");
        } else {
            usuarioLoginDTO = new UsuarioLoginDTO();
        }
        mAV.addObject("usuarioLoginDTO", usuarioLoginDTO);
        mAV.addObject("direccionIP", ip);
        mAV.addObject("direccionMAC", mac);
        mAV.addObject("navegador", navegador);
        mAV.setViewName("login");
        return mAV;
    }

    /**
     * @param usuarioLoginDTO
     * @param br
     * @param solicitudHttp
     * @return
     * @throws UnknownHostException
     */
    @PostMapping("login")
    public ModelAndView recibeCredencialesLogin(@Valid UsuarioLoginDTO usuarioLoginDTO, BindingResult br,
                                                HttpServletRequest solicitudHttp) throws UnknownHostException {
        ModelAndView mAV = new ModelAndView();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String mac = Usuarios.direccionMAC();
        String navegador = solicitudHttp.getHeader("user-agent");
        boolean listado = false;
        // Validaciones de errores login
        if (br.hasErrors()) {
            mAV.addObject("otroError", "No se encuentra ningun usuario con los campos dados");
            mAV.addObject("direccionIP", ip);
            mAV.addObject("direccionMAC", mac);
            mAV.addObject("navegador", navegador);
            mAV.addObject("usuarioLoginDTO", usuarioLoginDTO);
            mAV.setViewName("login");
        } else {
            for (int i = 0; i < Usuarios.listadoLogin.size(); i++) {
                if (Usuarios.listadoLogin.get(i).getUsuario().equals(usuarioLoginDTO.getUsuario()) &&
                        Usuarios.listadoLogin.get(i).getClave().equals(usuarioLoginDTO.getClave())) {
                    listado = true;
                }
            }
            if (listado) {
                if (solicitudHttp.getSession().getAttribute("usuarioLoginDTO") != null) {
                    solicitudHttp.getSession().removeAttribute("usuarioLoginDTO");
                    solicitudHttp.getSession().setAttribute("usuarioLoginDTO", usuarioLoginDTO);
                } else {
                    solicitudHttp.getSession().setAttribute("usuarioLoginDTO", usuarioLoginDTO);
                }
                mAV.setViewName("redirect:/usuario/listado");
            } else {
                mAV.addObject("otroError", "No se encuentra ningun usuario con los campos dados");
                mAV.addObject("direccionIP", ip);
                mAV.addObject("direccionMAC", mac);
                mAV.addObject("navegador", navegador);
                mAV.addObject("usuarioLoginDTO", usuarioLoginDTO);
                mAV.setViewName("login");
            }
        }
        return mAV;
    }

    /**
     * @param solicitudHttp
     * @param respuestaHttp
     * @return
     */
    @GetMapping("logout")
    public ModelAndView desconexion(HttpServletRequest solicitudHttp, HttpServletResponse respuestaHttp) {
        ModelAndView mAV = new ModelAndView();
        solicitudHttp.getSession().invalidate();
        mAV.setViewName("redirect:login");
        return mAV;
    }
}
