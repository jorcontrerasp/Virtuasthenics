package es.sidelab.virtuasthenics.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sidelab.virtuasthenics.model.Usuario;
import es.sidelab.virtuasthenics.repository.UsuarioRepository;
import es.sidelab.virtuasthenics.utils.Varios;

@Controller
public class UsuarioController {
	
	private static Log log = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@RequestMapping("/registrarse")
	public String registrarse(@RequestParam(value="username_REG", required=true) String nombre,
			@RequestParam(value="email_REG", required=true) String email,
			@RequestParam(value="password_REG", required=true) String pass1,
			@RequestParam(value="password2_REG", required=true) String pass2,
			@RequestParam(value="accept", required=false) String accept,
			Model model) {
		try {
			log.info("Dando de alta usuario '" + nombre + "'");
			if(!pass1.equals(pass2)) {
				model.addAttribute("respuesta", "La contraseña 1 debe coincidir con la 2.");
				return "respuesta";
			}
			Usuario user = new Usuario(nombre, pass1, email, "ROLE_USER");
			userRepository.save(user);
			model.addAttribute("respuesta", "Usuario dado de alta correctamente. Entra en la página de login para acceder.");
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		}catch(Exception e){
			// TODO: handle exception
			log.error(e);
			return "respuestaError";
		}
		return "respuesta";
	}

}
