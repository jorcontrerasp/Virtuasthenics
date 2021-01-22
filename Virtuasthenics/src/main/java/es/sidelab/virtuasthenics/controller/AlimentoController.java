package es.sidelab.virtuasthenics.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sidelab.virtuasthenics.model.Alimento;
import es.sidelab.virtuasthenics.model.Dieta;
import es.sidelab.virtuasthenics.repository.AlimentoRepository;
import es.sidelab.virtuasthenics.repository.DietaRepository;
import es.sidelab.virtuasthenics.repository.UsuarioRepository;
import es.sidelab.virtuasthenics.utils.Varios;

@Controller
public class AlimentoController {

	@Autowired
	private AlimentoRepository alimentoRepository;
	
	@Autowired
	private DietaRepository dietaRepository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@RequestMapping("/insertarAlimento")
	public String intertar(Alimento alimento, Model model) {
		try {
			alimentoRepository.save(alimento);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		}catch(Exception e){
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaAlimentos";
	}
	
	@RequestMapping("/insertarAlimentoDietaAux")
	public String intertarAlimentoDietaAux(@RequestParam(value="idDietaAlimentos", required=false) Long idDietaAlimentos, Model model) {
		try {
			model.addAttribute("idDietaAlimentos", idDietaAlimentos);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		}catch(Exception e){
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "insertarAlimento";
	}
	
	@RequestMapping("/insertarAlimentoDieta")
	public String intertarAlimentoDieta(@RequestParam(value="idDietaAlimentos", required=false) Long idDietaAlimentos, 
			@RequestParam(value="nombreAlimento", required=false) String nombreAlimento, @RequestParam(value="kcal", required=false) long kcal,
			@RequestParam(value="cantidad", required=false) long cantidad, @RequestParam(value="hidratos", required=false) long hidratos,
			@RequestParam(value="proteinas", required=false) long proteinas, @RequestParam(value="grasas", required=false) long grasas,
			Model model) {
		try {
			Dieta dieta = dietaRepository.findByIdDieta(idDietaAlimentos);
			if(dieta!=null) {
				Alimento alimento = new Alimento(nombreAlimento, kcal, cantidad, hidratos, proteinas, grasas);
				if(alimento.getDietas() == null) {
					List<Dieta> listaDietas = new ArrayList<Dieta>();
					alimento.setDietas(listaDietas);
				}
				alimento.getDietas().add(dieta);
				alimentoRepository.save(alimento);
			}
			model.addAttribute("alimentos", alimentoRepository.findByDietasIdDieta(idDietaAlimentos));
			model.addAttribute("showListaAlimentos", true);
			//Identificador de la dieta.
			//Se utilizará a la hora de insertar nuevos alimentos a la dieta seleccionada.
			model.addAttribute("idDietaAlimentos", idDietaAlimentos);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		}catch(Exception e){
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaAlimentos";
	}
	
	@RequestMapping("/buscarAlimentos")
	public String buscarDietas(@RequestParam(value="nombreAlimento", required=false) String nombreAlimento, 
			@RequestParam(value="kcal", required=false) Long kcal, Model model) {
		try {
			if (nombreAlimento != null && nombreAlimento != "") {
				if(kcal != null && kcal != 0) {
					model.addAttribute("alimentos", alimentoRepository.findByNombreAlimentoAndKcal(nombreAlimento, kcal));
				}else {
					model.addAttribute("alimentos", alimentoRepository.findByNombreAlimento(nombreAlimento));
				}
			}else if(kcal != null && kcal != 0) {
				model.addAttribute("alimentos", alimentoRepository.findByKcal(kcal));
			}else {
				model.addAttribute("alimentos", alimentoRepository.findAll());
			}
			
			//Mostrar lista de ejercicios
			model.addAttribute("showListaAlimentos", true);
			
			model.addAttribute("idDietaAlimentos", (long) 0);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaAlimentos";
	}
	
	@RequestMapping("/mostrarAlimentos")
	public String mostrar(Model model) {
		try {
			model.addAttribute("alimentos", alimentoRepository.findAll());
			model.addAttribute("showListaAlimentos", true);
			model.addAttribute("idDietaAlimentos", (long) 0);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaAlimentos";
	}
	
	@RequestMapping("/eliminarAlimentoDieta")
	public String eliminarAlimentoDieta(@RequestParam(value="idAlimento", required=false) Long idAlimento, 
			@RequestParam(value="idDietaAlimentos", required=false) Long idDietaAlimentos,
			Model model) {
		try {
			//?¿?¿
			Dieta dieta = dietaRepository.findByIdDieta(idDietaAlimentos);
			Alimento alimento = alimentoRepository.findByIdAlimento(idAlimento);
			
			if(dieta != null) {
				dieta.getAlimentos().remove(alimento);
				dietaRepository.save(dieta);
			}
			
			alimentoRepository.delete(alimento);
			
			if(dieta != null) {
				model.addAttribute("alimentos", alimentoRepository.findByDietasIdDieta(idDietaAlimentos));
				//Identificador del alimento.
				//Se utilizará a la hora de insertar nuevos alimentos a la dieta seleccionada.
				model.addAttribute("idDietaAlimentos", idDietaAlimentos);
			}else {
				model.addAttribute("alimentos", alimentoRepository.findAll());
				model.addAttribute("idDietaAlimentos", (long)0);
			}
			
			model.addAttribute("showListaAlimentos", true);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaAlimentos";
	}
}
