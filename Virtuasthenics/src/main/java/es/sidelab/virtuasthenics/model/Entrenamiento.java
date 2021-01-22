package es.sidelab.virtuasthenics.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ENTRENAMIENTOS")
public class Entrenamiento {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	private String tipo;
	
	private long duracion;
	
	@ManyToMany(mappedBy="entrenamientos")
	private List<Ejercicio> ejercicios;
	
	public Entrenamiento() {}
	
	public Entrenamiento(String nombre, String tipo, long duracion) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.duracion = duracion;
		this.ejercicios = new ArrayList<Ejercicio>();
	}

	public Entrenamiento(String nombre, String tipo, long duracion, List<Ejercicio> ejercicios) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.duracion = duracion;
		this.ejercicios = ejercicios;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public List<Ejercicio> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(List<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}

	@Override
	public String toString() {
		return "Entrenamiento [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", duracion=" + duracion
				+ ", ejercicios=" + ejercicios + "]";
	}
	
}
