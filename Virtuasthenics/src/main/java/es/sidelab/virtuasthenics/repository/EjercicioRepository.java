package es.sidelab.virtuasthenics.repository;

import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import es.sidelab.virtuasthenics.model.Ejercicio;

@CacheConfig(cacheNames="virtuasthenics")
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
	
	@CacheEvict(allEntries = true)
	Ejercicio save(Ejercicio ejercicio);
	
	Ejercicio findByIdEj(Long idEj);
	
	@Cacheable
	List<Ejercicio> findByNombreEj(String nombreEj);
	
	@Cacheable
	List<Ejercicio> findByMovimiento(String tipoMovimiento);
	
	@Cacheable
	List<Ejercicio> findByExplicacion(String explicacion);
	
	@Cacheable
	List<Ejercicio> findByNombreEjAndMovimiento(String nombreEj, String explicacion);
	
	@Cacheable
	List<Ejercicio> findByEntrenamientosId(Long idEntrenamiento);
}
