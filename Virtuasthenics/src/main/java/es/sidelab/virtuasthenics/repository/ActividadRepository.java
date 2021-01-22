package es.sidelab.virtuasthenics.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import es.sidelab.virtuasthenics.model.Actividad;

@CacheConfig(cacheNames="virtuasthenics")
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
	
	@CacheEvict(allEntries = true)
	Actividad save(Actividad actividad);
	
	Actividad findByIdActividad(Long idActividad);

	@Cacheable
	List<Actividad> findByNombreActividad(String nombreActividad);

	@Cacheable
	List<Actividad> findByNombreEntrenador(String nombreEntrenador);

	@Cacheable
	List<Actividad> findByDuracion(long duracion);

	@Cacheable
	List<Actividad> findByHoraInicio(String horaInicio);

	@Cacheable
	List<Actividad> findByHoraFin(String horaFin);

	@Cacheable
	List<Actividad> findByNombreActividadAndDuracion(String nombreActividad, long duracion);

	@Cacheable
	List<Actividad> findByNombreActividadAndNombreEntrenador(String nombreActividad, String nombreEntrenador);

	@Cacheable
	List<Actividad> findByNombreActividadAndNombreEntrenadorAndDuracion(String nombreActividad, String nombreEntrenador,
			long duracion);

	@Cacheable
	List<Actividad> findByNombreEntrenadorAndDuracion(String nombreEntrenador, long duracion);
}