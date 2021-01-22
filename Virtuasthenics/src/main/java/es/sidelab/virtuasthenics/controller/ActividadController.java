package es.sidelab.virtuasthenics.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sidelab.virtuasthenics.model.Actividad;
import es.sidelab.virtuasthenics.model.Dieta;
import es.sidelab.virtuasthenics.model.Usuario;
import es.sidelab.virtuasthenics.repository.ActividadRepository;
import es.sidelab.virtuasthenics.repository.UsuarioRepository;
import es.sidelab.virtuasthenics.utils.Varios;

@Controller
public class ActividadController {

	@Autowired
	private ActividadRepository actividadRepository;

	@Autowired
	private UsuarioRepository userRepository;

	@RequestMapping("/insertarActividad")
	public String intertar(Actividad actividad, Model model) {
		try {
			actividadRepository.save(actividad);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "actividades";
	}

	@RequestMapping("/mostrarActividades")
	public String mostrar(Model model) {
		try {
			model.addAttribute("actividades", actividadRepository.findAll());
			model.addAttribute("showListaActividades", true);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaActividades";
	}

	@RequestMapping("/buscarActividades")
	public String buscarEntrenamientos(
			@RequestParam(value = "nombreActividad", required = false) String nombreActividad,
			@RequestParam(value = "nombreEntrenador", required = false) String nombreEntrenador,
			@RequestParam(value = "duracion", required = false) Long duracion, Model model) {
		try {
			if (nombreActividad != null && nombreActividad != "") {
				if (nombreEntrenador != null && nombreEntrenador != "") {
					if (duracion != null && duracion != 0) {
						model.addAttribute("actividades",
								actividadRepository.findByNombreActividadAndNombreEntrenadorAndDuracion(nombreActividad,
										nombreEntrenador, duracion));
					} else {
						model.addAttribute("actividades", actividadRepository
								.findByNombreActividadAndNombreEntrenador(nombreActividad, nombreEntrenador));
					}
				}
				if (duracion != null && duracion != 0) {
					model.addAttribute("actividades",
							actividadRepository.findByNombreActividadAndDuracion(nombreActividad, duracion));
				} else {
					model.addAttribute("actividades", actividadRepository.findByNombreActividad(nombreActividad));
				}
			} else if (nombreEntrenador != null && nombreEntrenador != "") {
				if (duracion != null && duracion != 0) {
					model.addAttribute("actividades",
							actividadRepository.findByNombreEntrenadorAndDuracion(nombreEntrenador, duracion));
				} else {
					model.addAttribute("actividades", actividadRepository.findByNombreEntrenador(nombreEntrenador));
				}
			} else if (duracion != null && duracion != 0) {
				model.addAttribute("actividades", actividadRepository.findByDuracion(duracion));
			} else {
				model.addAttribute("actividades", actividadRepository.findAll());
			}
			// Mostrar lista de entrenamientos
			model.addAttribute("showListaActividades", true);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaActividades";
	}
	
	@RequestMapping("/anadirActividadAFavoritos")
	public String anadirEntrenamientoAFavoritos(@RequestParam(value="idActividad", required=false) Long idActividad, Model model) {
		try {
			/*A*/
			Varios varios = new Varios();
			Usuario user = varios.getInfoUsuario(model, userRepository);
			Actividad actividad = actividadRepository.findOne(idActividad);
			//user.getActividadesFavoritas().add(actividad);
			
			model.addAttribute("respuesta", "NO IMPLEMENTADO TODAVÍA.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "respuesta";
	}
}