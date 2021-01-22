package es.sidelab.virtuasthenics.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sidelab.virtuasthenics.model.Ejercicio;
import es.sidelab.virtuasthenics.model.Entrenamiento;
import es.sidelab.virtuasthenics.repository.EjercicioRepository;
import es.sidelab.virtuasthenics.repository.EntrenamientoRepository;
import es.sidelab.virtuasthenics.repository.UsuarioRepository;
import es.sidelab.virtuasthenics.utils.Varios;

@Controller
public class EjercicioController {

	@Autowired
	private EjercicioRepository ejercicioRepository;
	@Autowired
	private EntrenamientoRepository entrenamientoRepository;
	@Autowired
	private UsuarioRepository userRepository;
	
	/*
	 * Host y puerto del servicio de generación de PDFs.
	 */
	private final int PORT_PDF_SERVICE = 9999;
	private final String HOST_PDF_SERVICE = "127.0.0.1";
	
	@RequestMapping("/insertarEjercicio")
	public String intertar(Ejercicio ejercicio, Model model) {
		try {
			ejercicioRepository.save(ejercicio);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		}catch(Exception e){
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		//return "busquedaEjercicios";
		return "entrenamientos";
	}
	
	@RequestMapping("/insertarEjercicioEntrenamientoAux")
	public String intertarEjercicioEntrenamientoAux(@RequestParam(value="idEntrenamientoEjercicios", required=false) Long idEntrenamientoEjercicios, Model model) {
		try {
			model.addAttribute("idEntrenamientoEjercicios", idEntrenamientoEjercicios);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		}catch(Exception e){
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "insertarEjercicio";
	}
	
	@RequestMapping("/insertarEjercicioEntrenamiento")
	public String intertarEjercicioEntrenamiento(@RequestParam(value="idEntrenamientoEjercicios", required=false) Long idEntrenamientoEjercicios, 
			@RequestParam(value="nombreEj", required=false) String nombreEj, @RequestParam(value="movimiento", required=false) String movimiento,
			@RequestParam(value="explicacion", required=false) String explicacion, Model model) {
		try {
			Entrenamiento entrenamiento = entrenamientoRepository.findById(idEntrenamientoEjercicios);
			if(entrenamiento!=null) {
				Ejercicio ejercicio = new Ejercicio(nombreEj, movimiento, explicacion);
				ejercicio.getEntrenamientos().add(entrenamiento);
				ejercicioRepository.save(ejercicio);
			}
			model.addAttribute("ejercicios", ejercicioRepository.findByEntrenamientosId(idEntrenamientoEjercicios));
			model.addAttribute("showListaEjercicios", true);
			//Identificador el entrenamiento.
			//Se utilizará a la hora de insertar nuevos ejercicios al entrenamiento seleccionado.
			model.addAttribute("idEntrenamientoEjercicios", idEntrenamientoEjercicios);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		}catch(Exception e){
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaEjercicios";
	}
	
	@RequestMapping("/buscarEjercicios")
	public String buscarEntrenamientos(@RequestParam(value="nombreEj", required=false) String nombreEj, 
			@RequestParam(value="movimiento", required=false) String movimiento, Model model) {
		try {
			if (nombreEj != null && nombreEj != "") {
				if(movimiento != null && movimiento != "") {
					model.addAttribute("ejercicios", ejercicioRepository.findByNombreEjAndMovimiento(nombreEj, movimiento));
				}else {
					model.addAttribute("ejercicios", ejercicioRepository.findByNombreEj(nombreEj));
				}
			}else if(movimiento != null && movimiento != "") {
				model.addAttribute("ejercicios", ejercicioRepository.findByMovimiento(movimiento));
			}else {
				model.addAttribute("ejercicios", ejercicioRepository.findAll());
			}
			
			//Mostrar lista de ejercicios
			model.addAttribute("showListaEjercicios", true);
			
			model.addAttribute("idEntrenamientoEjercicios", (long) 0);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaEjercicios";
	}
	
	@RequestMapping("/mostrarEjercicios")
	public String mostrar(Model model) {
		try {
			model.addAttribute("ejercicios", ejercicioRepository.findAll());
			model.addAttribute("showListaEjercicios", true);
			model.addAttribute("idEntrenamientoEjercicios", (long) 0);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaEjercicios";
	}
	
	@RequestMapping("/eliminarEjercicioEntrenamiento")
	public String eliminarEjercicioEntrenamiento(@RequestParam(value="idEjercicio", required=false) Long idEjercicio, 
			@RequestParam(value="idEntrenamientoEjercicios", required=false) Long idEntrenamientoEjercicios,
			Model model) {
		try {
			//?¿?¿
			Entrenamiento entrenamiento = entrenamientoRepository.findById(idEntrenamientoEjercicios);
			Ejercicio ejercicio = ejercicioRepository.findByIdEj(idEjercicio);
			
			if(entrenamiento != null) {
				entrenamiento.getEjercicios().remove(ejercicio);
				entrenamientoRepository.save(entrenamiento);
			}
			
			ejercicioRepository.delete(ejercicio);
			
			if(entrenamiento != null) {
				model.addAttribute("ejercicios", ejercicioRepository.findByEntrenamientosId(idEntrenamientoEjercicios));
				//Identificador el entrenamiento.
				//Se utilizará a la hora de insertar nuevos ejercicios al entrenamiento seleccionado.
				model.addAttribute("idEntrenamientoEjercicios", idEntrenamientoEjercicios);
			}else {
				model.addAttribute("ejercicios", ejercicioRepository.findAll());
				model.addAttribute("idEntrenamientoEjercicios", (long)0);
			}
			
			model.addAttribute("showListaEjercicios", true);
			
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return "busquedaEjercicios";
	}
	
	@RequestMapping("/imprimirEjercicioEntrenamiento")
	public String imprimirEjercicioEntrenamiento(
			@RequestParam(value = "idEntrenamientoEjercicios", required = false) Long idEntrenamientoEjercicios,
			Model model) {
		try {
			/*
			 * Llamar al servicio interno para generar PDF.
			 */
			Socket socket = null;
			BufferedReader brSocketIn = null;
			PrintWriter pwSocketOut = null;
			try {
				String host = HOST_PDF_SERVICE;
				int port = PORT_PDF_SERVICE;
				
				socket = new Socket(host, port);
				
				brSocketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pwSocketOut = new PrintWriter(socket.getOutputStream());
				
				// Envío y recepción de información
				
				String mensaje = "Prueba_01";
				pwSocketOut.println(mensaje);
				System.out.println(mensaje);
				String response = brSocketIn.readLine();
				System.out.println(response);
				
				
			} catch (UnknownHostException e) {
				System.err.println("Host desconocido");
			} catch (IOException e) {
				System.err.println("Error I/O");
			}finally {
				if(pwSocketOut != null) {
					pwSocketOut.close();
				}
				
				if(brSocketIn != null) {
					brSocketIn.close();
				}
				
				if(socket != null) {
					socket.close();
				}
			}
			model.addAttribute("showListaEjercicios", true);
			model.addAttribute("ejercicios", ejercicioRepository.findByEntrenamientosId(idEntrenamientoEjercicios));
			model.addAttribute("idEntrenamientoEjercicios", (long) 0);
			Varios varios = new Varios();
			varios.getInfoUsuario(model, userRepository);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "respuestaError";
		}
		return null;
	}
}
