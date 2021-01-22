package es.sidelab.virtuasthenics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import es.sidelab.virtuasthenics.AppConfig;
import es.sidelab.virtuasthenics.model.Dieta;
import es.sidelab.virtuasthenics.model.Email;
import es.sidelab.virtuasthenics.model.Usuario;
import es.sidelab.virtuasthenics.repository.AlimentoRepository;
import es.sidelab.virtuasthenics.repository.DietaRepository;
import es.sidelab.virtuasthenics.repository.UsuarioRepository;
import es.sidelab.virtuasthenics.utils.Varios;

@Controller
public class DietaController {
	
	@Autowired
	private DietaRepository dietaRepository;
	
	@Autowired
	private AlimentoRepository alimentoRepository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	private String servicePath = "http://" + AppConfig.MS1_IP + ":8080/";
	
	@RequestMapping("/insertarDieta")
	public String intertar(Dieta dieta, Model model) {
		try {
			dietaRepository.save(dieta);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		}catch(Exception e){
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "clases";
	}
	
	@RequestMapping("/mostrarDietas")
	public String mostrar(Model model) {
		try {
			model.addAttribute("dietas", dietaRepository.findAll());
			model.addAttribute("showListaDietas", true);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaDietas";
	}
	
	@RequestMapping("/buscarDietas")
	public String buscarEntrenamientos(@RequestParam(value="nombreDieta", required=false) String nombreDieta,
			@RequestParam(value="tipoDieta", required=false) String tipoDieta, 
			@RequestParam(value="comida", required=false) String comida, 
			@RequestParam(value="kcal", required=false) Long kcal, Model model) {
		try {
			if(nombreDieta != null && nombreDieta != "") {
				if (tipoDieta != null && tipoDieta != "") {
					if(comida != null && comida != "") {
						if(kcal != null && kcal != 0) {
							model.addAttribute("dietas", dietaRepository.findByNombreDietaAndTipoDietaAndComidaAndKcal(nombreDieta, tipoDieta, comida, kcal));
						}
					}
				}
			}else if (tipoDieta != null && tipoDieta != "") {
				if(comida != null && comida != "") {
					if(kcal != null && kcal != 0) {
						model.addAttribute("dietas", dietaRepository.findByTipoDietaAndComidaAndKcal(tipoDieta, comida, kcal));
					}else {
						model.addAttribute("dietas", dietaRepository.findByTipoDietaAndComida(tipoDieta, comida));
					}
				}if(kcal != null && kcal != 0) {
					model.addAttribute("dietas", dietaRepository.findByTipoDietaAndKcal(tipoDieta, kcal));
				}else {
					model.addAttribute("dietas", dietaRepository.findByTipoDieta(tipoDieta));
				}
			}else if(comida != null && comida != "") {
				if(kcal != null && kcal != 0) {
					model.addAttribute("dietas", dietaRepository.findByComidaAndKcal(comida, kcal));
				}else {
					model.addAttribute("dietas", dietaRepository.findByComida(comida));
				}
			}else if(kcal != null && kcal != 0) {
				model.addAttribute("dietas", dietaRepository.findByKcal(kcal));
			}else {
				model.addAttribute("dietas", dietaRepository.findAll());
			}
			
			//Mostrar lista de dietas
			model.addAttribute("showListaDietas", true);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaDietas";
	}
	
	@RequestMapping("/mostrarAlimentosDieta")
	public String mostrarAlimentosDieta(@RequestParam(value="idDieta", required=false) Long idDieta, Model model) {
		try {
			model.addAttribute("alimentos", alimentoRepository.findByDietasIdDieta(idDieta));
			model.addAttribute("showListaAlimentos", true);
			//Identificador de la dieta.
			//Se utilizará a la hora de insertar nuevos alimentos a la dieta seleccionada.
			model.addAttribute("idDietaAlimentos", idDieta);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaAlimentos";
	}
	
	@RequestMapping("/anadirDietaAFavoritos")
	public String anadirEntrenamientoAFavoritos(@RequestParam(value="idDieta", required=false) Long idDieta, Model model) {
		try {
			/*A*/
			Varios varios = new Varios();
			Usuario user = varios.getInfoUsuario(model, userRepository);
			Dieta dieta = dietaRepository.findOne(idDieta);
			//user.getDietasFavoritas().add(dieta);
			
			model.addAttribute("respuesta", "NO IMPLEMENTADO TODAVÍA.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "respuesta";
	}
	
	@GetMapping("/sendMail")
	public String sendMail(Model model) {
		try {
			System.out.println("Enviando mail");

			RestTemplate rt = new RestTemplate();
			String url=servicePath+"mail";
			
			System.out.println("Llamando a " + url);

			String to = "virtuasthenics@gmail.com";
			String msgTo = "Virtuasthenics";
			String from = /*SecurityContextHolder.getContext().getAuthentication().getName()*/ "prbs.0395@gmail.com";
			String data = "Hola, me gustaría obtener un plan de dieta personalizado. Un saludo.";
			Email email = new Email(from, to, data);
			try {
				rt.postForLocation(url, email);
			}catch(Exception e) {
				System.out.println("Servicio de envío de correos no encontrado.");
				return "respuestaError";
			}
			
			String respuesta = "Correo enviado correctamente. Próximamente un entrenador se pondrá en contacto con usted. Gracias por su confianza.";
			model.addAttribute("respuesta", respuesta);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
			return "respuesta";
		}catch(Exception e) {
			e.printStackTrace();
			return "respuestaError";
		}
	}

}
