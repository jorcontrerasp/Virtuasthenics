package es.sidelab.virtuasthenics.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "EJERCICIOS")
public class Ejercicio {

	@Id 
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long idEj;
	
	private String nombreEj;
	private String movimiento;
	private String explicacion;
	//private Musculo musculo;
	@ManyToMany()
	private List<Entrenamiento> entrenamientos;
	
	public Ejercicio() {}
	
	public Ejercicio(String nombreEj, String movimiento, String explicacion) {
		super();
		this.nombreEj = nombreEj;
		this.movimiento = movimiento;
		this.explicacion = explicacion;
		this.entrenamientos = new ArrayList<Entrenamiento>();
	}
	
	public Ejercicio(String nombreEj, String movimiento, String explicacion, /*Musculo musculo,*/
			List<Entrenamiento> entrenamientos) {
		super();
		this.nombreEj = nombreEj;
		this.movimiento = movimiento;
		this.explicacion = explicacion;
		//this.musculo = musculo;
		this.entrenamientos = entrenamientos;
	}
	
	public long getIdEj() {
		return idEj;
	}
	
	public void setIdEj(long idEj) {
		this.idEj = idEj;
	}
	
	public String getNombreEj() {
		return nombreEj;
	}
	
	public void setNombreEj(String nombreEj) {
		this.nombreEj = nombreEj;
	}
	
	public String getMovimiento() {
		return movimiento;
	}
	
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	
	public String getExplicacion() {
		return explicacion;
	}
	
	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}
	
//	public Musculo getMusculo() {
//		return musculo;
//	}
//	
//	public void setMusculo(Musculo musculo) {
//		this.musculo = musculo;
//	}
//	
	public List<Entrenamiento> getEntrenamientos() {
		return entrenamientos;
	}
	
	public void setEntrenamientos(List<Entrenamiento> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}

	@Override
	public String toString() {
		return "Ejercicio [idEj=" + idEj + ", nombreEj=" + nombreEj + ", movimiento=" + movimiento + ", explicacion="
				+ explicacion + ", entrenamientos=" + entrenamientos + "]";
	}
}
