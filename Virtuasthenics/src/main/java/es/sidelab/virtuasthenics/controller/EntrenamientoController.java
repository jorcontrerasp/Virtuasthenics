package es.sidelab.virtuasthenics.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sidelab.virtuasthenics.model.Ejercicio;
import es.sidelab.virtuasthenics.model.Entrenamiento;
import es.sidelab.virtuasthenics.model.Usuario;
import es.sidelab.virtuasthenics.repository.EjercicioRepository;
import es.sidelab.virtuasthenics.repository.EntrenamientoRepository;
import es.sidelab.virtuasthenics.repository.UsuarioRepository;
import es.sidelab.virtuasthenics.utils.Varios;

@Controller
public class EntrenamientoController {
	
	@Autowired
	private EntrenamientoRepository entrenamientoRepository;
	@Autowired
	private EjercicioRepository ejercicioRepository;
	@Autowired
	private UsuarioRepository userRepository;
	
	@RequestMapping("/insertarEntrenamiento")
	public String intertar(Entrenamiento entrenamiento, Model model) {
		try {
			entrenamientoRepository.save(entrenamiento);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		}catch(Exception e){
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "entrenamientos";
	}
	
	@RequestMapping("/mostrarEntrenamientos")
	public String mostrar(Model model) {
		try {
			model.addAttribute("entrenamientos", entrenamientoRepository.findAll());
			model.addAttribute("showListaEntrenamientos", true);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaEntrenamientos";
	}
			
	@RequestMapping("/buscarEntrenamientos")
	public String buscarEntrenamientos(@RequestParam(value="nombre", required=false) String nombre, 
			@RequestParam(value="tipo", required=false) String tipo, 
			@RequestParam(value="duracion", required=false) Long duracion, Model model) {
		try {
			if (nombre != null && nombre != "") {
				if(tipo != null && tipo != "") {
					if(duracion != null && duracion != 0) {
						model.addAttribute("entrenamientos", entrenamientoRepository.findByNombreAndTipoAndDuracion(nombre, tipo, duracion));
					}else {
						model.addAttribute("entrenamientos", entrenamientoRepository.findByNombreAndTipo(nombre, tipo));
					}
				}if(duracion != null && duracion != 0) {
					model.addAttribute("entrenamientos", entrenamientoRepository.findByNombreAndDuracion(nombre, duracion));
				}else {
					model.addAttribute("entrenamientos", entrenamientoRepository.findByNombre(nombre));
				}
			}else if(tipo != null && tipo != "") {
				if(duracion != null && duracion != 0) {
					model.addAttribute("entrenamientos", entrenamientoRepository.findByTipoAndDuracion(tipo, duracion));
				}else {
					model.addAttribute("entrenamientos", entrenamientoRepository.findByTipo(tipo));
				}
			}else if(duracion != null && duracion != 0) {
				model.addAttribute("entrenamientos", entrenamientoRepository.findByDuracion(duracion));
			}else {
				model.addAttribute("entrenamientos", entrenamientoRepository.findAll());
			}
			
			//Mostrar lista de entrenamientos
			model.addAttribute("showListaEntrenamientos", true);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaEntrenamientos";
	}
	
	@RequestMapping("/mostrarEjerciciosEntrenamiento")
	public String mostrarEjerciciosEntrenamiento(@RequestParam(value="idEntrenamiento", required=false) Long idEntrenamiento, Model model) {
		try {
			model.addAttribute("ejercicios", ejercicioRepository.findByEntrenamientosId(idEntrenamiento));
			model.addAttribute("showListaEjercicios", true);
			//Identificador el entrenamiento.
			//Se utilizará a la hora de insertar nuevos ejercicios al entrenamiento seleccionado.
			model.addAttribute("idEntrenamientoEjercicios", idEntrenamiento);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaEjercicios";
	}
	
	@RequestMapping("/anadirEntrenamientoAFavoritos")
	public String anadirEntrenamientoAFavoritos(@RequestParam(value="idEntrenamiento", required=false) Long idEntrenamiento, Model model) {
		try {
			/*A*/
			Varios varios = new Varios();
			Usuario user = varios.getInfoUsuario(model, userRepository);
			Entrenamiento entrenamiento = entrenamientoRepository.findOne(idEntrenamiento);
			user.getEntrenamientosFavoritos().add(entrenamiento);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaEntrenamientos";
	}
	
	@RequestMapping("/modificarEntrenamiento")
	public String modificar(@RequestParam long id, 
							@RequestParam(value="nombre") String nombre, Model model) {
		try {
			Entrenamiento entrenamiento = entrenamientoRepository.findOne(id);
			if(nombre != null && !nombre.isEmpty() && nombre != "") {
				entrenamiento.setNombre(nombre);
			}
			entrenamientoRepository.save(entrenamiento);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "entrenamientos";
	}
}
