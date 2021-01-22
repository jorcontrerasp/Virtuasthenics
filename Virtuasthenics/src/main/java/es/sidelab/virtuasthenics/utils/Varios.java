package es.sidelab.virtuasthenics.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import es.sidelab.virtuasthenics.model.Usuario;
import es.sidelab.virtuasthenics.repository.UsuarioRepository;

public class Varios {
	
	public Varios() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * Método que obtiene la información del usuario de la sesión.
	 */
	public Usuario getInfoUsuario(Model model, UsuarioRepository userRepository) throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("admin", false);
		model.addAttribute("isUser", false);
		model.addAttribute("user", auth.getName());
		
		Usuario user = userRepository.findByName(auth.getName());
		
		if(auth.getName() != null && !auth.getName().equals("anonymousUser")) {
			model.addAttribute("showInfoUser", true);
			
			if(user != null) {
				model.addAttribute("correo", user.getCorreo());
			}
			
			//Obtener roles del usuario.
			List<String> roles = user.getRoles();
			//Comprobar si es administrador
			//model.addAttribute("admin", request.isUserInRole("ADMIN"));
			for(String rol : roles){
				if(rol.equals("ROLE_USER")) {
					model.addAttribute("isUser", true);
				}
				if(rol.equals("ROLE_ADMIN")) {
					model.addAttribute("admin", true);
				}
			}
		}
		
		return user;
	}
	
	public String obtenerIP() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }

}
