package es.sidelab.virtuasthenics.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String passwordHash;
	
	private String correo;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@OneToMany
	private List<Entrenamiento> entrenamientosFavoritos;

	public Usuario() {
	}

	public Usuario(String name, String password, String correo, String... roles) {
		this.name = name;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.correo = correo;
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.entrenamientosFavoritos = new ArrayList<Entrenamiento>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Entrenamiento> getEntrenamientosFavoritos() {
		return entrenamientosFavoritos;
	}

	public void setEntrenamientosFavoritos(List<Entrenamiento> entrenamientosFavoritos) {
		this.entrenamientosFavoritos = entrenamientosFavoritos;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}