package es.sidelab.virtuasthenics.repository;

import org.springframework.data.repository.CrudRepository;
import es.sidelab.virtuasthenics.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Usuario findByName(String name);
	
	Usuario findByCorreo(String correo);
}