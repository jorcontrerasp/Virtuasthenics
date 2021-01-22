package es.sidelab.virtuasthenics.utils;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sidelab.virtuasthenics.AppConfig;
import es.sidelab.virtuasthenics.model.Actividad;
import es.sidelab.virtuasthenics.model.Alimento;
import es.sidelab.virtuasthenics.model.Dieta;
import es.sidelab.virtuasthenics.model.Ejercicio;
import es.sidelab.virtuasthenics.model.Entrenamiento;
import es.sidelab.virtuasthenics.repository.ActividadRepository;
import es.sidelab.virtuasthenics.repository.AlimentoRepository;
import es.sidelab.virtuasthenics.repository.DietaRepository;
import es.sidelab.virtuasthenics.repository.EjercicioRepository;
import es.sidelab.virtuasthenics.repository.EntrenamientoRepository;

@Component
public class BBDDLoader {

	@Autowired
	private EjercicioRepository ejercicioRepository;

	@Autowired
	private EntrenamientoRepository entrenamientoRepository;

	@Autowired
	private AlimentoRepository alimentoRepository;

	@Autowired
	private DietaRepository dietaRepository;

	@Autowired
	private ActividadRepository actividadRepository;

	@PostConstruct
	public void inicializarActividades() {
		if (AppConfig.BD_INIT) {
			// Actividades
			Actividad actividad = new Actividad("Body Attack", "Rodolfo", 1, "10:00", "11:00");
			Actividad actividad2 = new Actividad("PowerLifting", "Anselmo", 2, "17:00", "19:00");
			Actividad actividad3 = new Actividad("Zumba", "Lidia", 1, "09:00", "10:00");

			// Guardar actividades
			actividadRepository.save(actividad);
			actividadRepository.save(actividad2);
			actividadRepository.save(actividad3);
		}
	}

	@PostConstruct
	public void inicializarAlimentos() {
		if (AppConfig.BD_INIT) {
			Alimento alimento = new Alimento("Merluza rebozada", 400, 1, 50, 30, 8);
			Alimento alimento2 = new Alimento("Pincho moruno", 200, 1, 150, 50, 15);
			alimentoRepository.save(alimento);
			alimentoRepository.save(alimento2);
		}
	}

	@PostConstruct
	public void inicializarDietas() {
		if (AppConfig.BD_INIT) {
			// Dietas
			Dieta dieta = new Dieta("Dieta1", "Hiperproteica", "Desayuno", 560);
			Dieta dieta2 = new Dieta("Dieta2", "Hiperproteica", "Comida", 750);
			Dieta dieta3 = new Dieta("Dieta3", "Hipoproteica", "Comida", 300);
			Dieta dieta4 = new Dieta("Dieta4", "Vegetariana", "Cena", 250);

			// Guardar dietas
			dietaRepository.save(dieta);
			dietaRepository.save(dieta2);
			dietaRepository.save(dieta3);
			dietaRepository.save(dieta4);

			// Alimentos
			Alimento alimento = new Alimento("Esparrago triguero", 100, 2, 50, 30, 8);
			Alimento alimento2 = new Alimento("Alcaparras", 200, 2, 150, 50, 15);

			alimento.setDietas(new ArrayList<Dieta>());
			alimento2.setDietas(new ArrayList<Dieta>());

			alimento.getDietas().add(dieta);
			alimento2.getDietas().add(dieta);

			// Guardar ejercicios
			alimentoRepository.save(alimento);
			alimentoRepository.save(alimento2);
		}
	}

	@PostConstruct
	public void inicializarEjercicios() {
		if (AppConfig.BD_INIT) {
			Ejercicio ej1 = new Ejercicio("Ej pectoral 1", "Press Banca",
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi enim ante, convallis id nisl nec, volutpat posuere nunc. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus congue convallis sem.");
			Ejercicio ej2 = new Ejercicio("Ej pectoral 2", "Elevación poleas",
					"Nam id rhoncus ipsum. Vivamus in rutrum odio. Interdum et malesuada fames ac ante ipsum primis in faucibus. Suspendisse luctus neque vel nisi vulputate venenatis. Mauris egestas hendrerit erat. Mauris pharetra suscipit massa nec imperdiet.");
			Ejercicio ej3 = new Ejercicio("A1", "B1", "C1");
			Ejercicio ej4 = new Ejercicio("A2", "B2", "C2");
			Ejercicio ej5 = new Ejercicio("A3", "B3", "C3");
			Ejercicio ej6 = new Ejercicio("A4", "B4", "C4");
			Ejercicio ej7 = new Ejercicio("A5", "B5", "C5");
			Ejercicio ej8 = new Ejercicio("A6", "B6", "C6");
			Ejercicio ej9 = new Ejercicio("A7", "B7", "C7");
			Ejercicio ej10 = new Ejercicio("A8", "B8", "C8");
			Ejercicio ej11 = new Ejercicio("A9", "B9", "C9");
			ejercicioRepository.save(ej1);
			ejercicioRepository.save(ej2);
		}
	}

	@PostConstruct
	public void inicializarEntrenamientos() {
		if (AppConfig.BD_INIT) {
			// Entrenamientos
			Entrenamiento entrenamiento1 = new Entrenamiento("M1", "Musculacion", 120);
			Entrenamiento entrenamiento2 = new Entrenamiento("Entrenamiento Sala (I)", "Futbol", 120);
			Entrenamiento entrenamiento3 = new Entrenamiento("Entrenamiento F7 (I)", "Futbol", 120);
			Entrenamiento entrenamiento4 = new Entrenamiento("Entrenamiento F11 (I)", "Futbol", 120);
			Entrenamiento entrenamiento5 = new Entrenamiento("E1", "Estiramientos", 120);
			Entrenamiento entrenamiento6 = new Entrenamiento("S1", "Spinning", 120);
			Entrenamiento entrenamiento7 = new Entrenamiento("C1", "Calistenia", 60);
			Entrenamiento entrenamiento8 = new Entrenamiento("C2", "Calistenia", 60);
			Entrenamiento entrenamiento9 = new Entrenamiento("C3", "Calistenia", 120);
			Entrenamiento entrenamiento10 = new Entrenamiento("C4", "Calistenia", 120);
			Entrenamiento entrenamiento11 = new Entrenamiento("C5", "Calistenia", 120);
			Entrenamiento entrenamiento12 = new Entrenamiento("W1", "Weightlifting", 60);
			Entrenamiento entrenamiento13 = new Entrenamiento("W2", "Weightlifting", 90);
			Entrenamiento entrenamiento14 = new Entrenamiento("W3", "Weightlifting", 120);
			Entrenamiento entrenamiento15 = new Entrenamiento("W4", "Weightlifting", 120);
			Entrenamiento entrenamiento16 = new Entrenamiento("W5", "Weightlifting", 120);

			// Guardar entrenamientos
			entrenamientoRepository.save(entrenamiento1);
			entrenamientoRepository.save(entrenamiento2);
			entrenamientoRepository.save(entrenamiento3);
			entrenamientoRepository.save(entrenamiento4);
			entrenamientoRepository.save(entrenamiento5);
			entrenamientoRepository.save(entrenamiento6);
			entrenamientoRepository.save(entrenamiento7);
			entrenamientoRepository.save(entrenamiento8);
			entrenamientoRepository.save(entrenamiento9);
			entrenamientoRepository.save(entrenamiento10);
			entrenamientoRepository.save(entrenamiento11);
			entrenamientoRepository.save(entrenamiento12);
			entrenamientoRepository.save(entrenamiento13);
			entrenamientoRepository.save(entrenamiento14);
			entrenamientoRepository.save(entrenamiento15);
			entrenamientoRepository.save(entrenamiento16);

			// Ejercicios
			Ejercicio ejercicio1 = new Ejercicio("Remo espalda", "Remo", "AAA");
			Ejercicio ejercicio2 = new Ejercicio("Remo espalda 2", "Remo", "BBB");
			Ejercicio ejercicio3 = new Ejercicio("Remo espalda 3", "Remo", "CCC");
			Ejercicio ejercicio4 = new Ejercicio("Remo espalda 4", "Remo", "DDD");
			Ejercicio ejercicio5 = new Ejercicio("Pectoral 1", "Press Banca", "EEE");

			ejercicio1.getEntrenamientos().add(entrenamiento1);
			ejercicio2.getEntrenamientos().add(entrenamiento1);
			ejercicio3.getEntrenamientos().add(entrenamiento1);
			ejercicio4.getEntrenamientos().add(entrenamiento1);
			ejercicio5.getEntrenamientos().add(entrenamiento1);

			// Guardar ejercicios
			ejercicioRepository.save(ejercicio1);
			ejercicioRepository.save(ejercicio2);
			ejercicioRepository.save(ejercicio3);
			ejercicioRepository.save(ejercicio4);
			ejercicioRepository.save(ejercicio5);
		}
	}

}
