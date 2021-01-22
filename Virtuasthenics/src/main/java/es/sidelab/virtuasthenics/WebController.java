package es.sidelab.virtuasthenics;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import es.sidelab.virtuasthenics.repository.UsuarioRepository;
import es.sidelab.virtuasthenics.utils.Varios;

@Controller
public class WebController {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			return "inicio";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}

	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			if(token != null) {
				model.addAttribute("token", token.getToken());
			}
			
			return "login";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			return "logout";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}

	@GetMapping("/loginError")
	public String loginerror(Model model) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			return "loginError";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}
	
	@GetMapping("/inicio")
	public String home2(Model model) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			return "inicio";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}
	
	@GetMapping("/actividades")
	public String actividades(Model model) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			return "actividades";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}
	
	@GetMapping("/contacto")
	public String contacto(Model model) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			return "contacto";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}
	
	@GetMapping("/dietas")
	public String dietas(Model model) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			return "dietas";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}
	
	@GetMapping("/entrenamientos")
	public String entrenamientos(Model model) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			return "entrenamientos";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}
	
	@GetMapping("/usuario")
	public String usuario(Model model) {
		try {
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			return "usuarios";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}
}
