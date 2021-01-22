package es.sidelab.virtuasthenics.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ALIMENTOS")
public class Alimento {
	
	@Id 
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long idAlimento;
	
	private String nombreAlimento;
	
	private long kcal;
	
	private long cantidad;
	
	private long hidratos;
	
	private long proteinas;
	
	private long grasas;
	
	@ManyToMany()
	private List<Dieta> dietas;

	public Alimento() {
		super();
	}

	public Alimento(String nombreAlimento, long kcal, long cantidad, long hidratos, long proteinas, long grasas) {
		super();
		this.nombreAlimento = nombreAlimento;
		this.kcal = kcal;
		this.cantidad = cantidad;
		this.hidratos = hidratos;
		this.proteinas = proteinas;
		this.grasas = grasas;
	}

	public long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public String getNombreAlimento() {
		return nombreAlimento;
	}

	public void setNombreAlimento(String nombreAlimento) {
		this.nombreAlimento = nombreAlimento;
	}

	public long getKcal() {
		return kcal;
	}

	public void setKcal(long kcal) {
		this.kcal = kcal;
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	public long getHidratos() {
		return hidratos;
	}

	public void setHidratos(long hidratos) {
		this.hidratos = hidratos;
	}

	public long getProteinas() {
		return proteinas;
	}

	public void setProteinas(long proteinas) {
		this.proteinas = proteinas;
	}

	public long getGrasas() {
		return grasas;
	}

	public void setGrasas(long grasas) {
		this.grasas = grasas;
	}

	public List<Dieta> getDietas() {
		return dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}

}
