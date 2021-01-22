package es.sidelab.virtuasthenics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVIDADES")
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idActividad;

	private String nombreActividad;
	private String nombreEntrenador;
	private long duracion;
	private String horaInicio;
	private String horaFin;

	public Actividad() {
		super();
	}

	public Actividad(String nombreActividad, String nombreEntrenador, long duracion, String horaInicio,
			String horaFin) {
		super();
		this.nombreActividad = nombreActividad;
		this.nombreEntrenador = nombreEntrenador;
		this.duracion = duracion;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(long idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getNombreEntrenador() {
		return nombreEntrenador;
	}

	public void setNombreEntrenador(String nombreEntrenador) {
		this.nombreEntrenador = nombreEntrenador;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	@Override
	public String toString() {
		return "Actividad [idActividad=" + idActividad + ", nombreActividad=" + nombreActividad + ", nombreEntrenador="
				+ nombreEntrenador + ", duracion=" + duracion + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin
				+ "]";

	}

}