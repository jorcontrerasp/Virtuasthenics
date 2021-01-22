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
@Table(name = "DIETAS")
public class Dieta {
	
	@Id 
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long idDieta;
	
	private String nombreDieta;
	
	/*
	 * TIPOS DE DIETA:
	 * 	- Hiperproteica.
	 * 	- Hipoproteica
	 * 	- Vegetariana.
	 */
	private String tipoDieta;
	
	/*
	 *	- Desayuno.
	 *	- Almuerzo.
	 *	- Comida.
	 *	- Merienda.
	 *	- Cena.
	 */
	private String comida;
	
	private long kcal;
	
	@ManyToMany(mappedBy="dietas")
	private List<Alimento> alimentos;

	public Dieta() {
		super();
	}
	
	public Dieta(String nombreDieta, String tipoDieta, String comida, long kcal) {
		super();
		this.nombreDieta = nombreDieta;
		this.tipoDieta = tipoDieta;
		this.comida = comida;
		this.kcal = kcal;
	}

	public long getIdDieta() {
		return idDieta;
	}

	public void setIdDieta(long idDieta) {
		this.idDieta = idDieta;
	}
	

	public String getNombreDieta() {
		return nombreDieta;
	}

	public void setNombreDieta(String nombreDieta) {
		this.nombreDieta = nombreDieta;
	}

	public String getTipoDieta() {
		return tipoDieta;
	}

	public void setTipoDieta(String tipoDieta) {
		this.tipoDieta = tipoDieta;
	}

	public String getComida() {
		return comida;
	}

	public void setComida(String comida) {
		this.comida = comida;
	}

	public long getKcal() {
		return kcal;
	}

	public void setKcal(long kcal) {
		this.kcal = kcal;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}
	
}
