package es.sidelab.virtuasthenics.repository;

import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import es.sidelab.virtuasthenics.model.Entrenamiento;

@CacheConfig(cacheNames="virtuasthenics")
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
	
	@CacheEvict(allEntries = true)
	Entrenamiento save(Entrenamiento entrenamiento);
	
	Entrenamiento findById(Long id);
	
	@Cacheable
	List<Entrenamiento> findByNombre(String nombre);
	
	@Cacheable
	List<Entrenamiento> findByTipo(String tipo);
	
	@Cacheable
	List<Entrenamiento> findByDuracion(long duracion);
	
	@Cacheable
	List<Entrenamiento> findByEjercicios(Long idEjercicio);
	
	@Cacheable
	List<Entrenamiento> findByNombreAndTipo(String nombre, String tipo);
	
	@Cacheable
	List<Entrenamiento> findByNombreAndDuracion(String nombre, long duracion);
	
	@Cacheable
	List<Entrenamiento> findByTipoAndDuracion(String tipo, long duracion);
	
	@Cacheable
	List<Entrenamiento> findByNombreAndTipoAndDuracion(String nombre, String tipo, long duracion);
}
