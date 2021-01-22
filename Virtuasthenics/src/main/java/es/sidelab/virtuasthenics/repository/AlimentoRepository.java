package es.sidelab.virtuasthenics.repository;

import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import es.sidelab.virtuasthenics.model.Alimento;

@CacheConfig(cacheNames="virtuasthenics")
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
	
	@CacheEvict(allEntries = true)
	Alimento save(Alimento alimento);
	
	Alimento findByIdAlimento(Long idAlimento);
	
	@Cacheable
	List<Alimento> findByNombreAlimento(String nombreAlimento);
	
	@Cacheable
	List<Alimento> findByKcal(long kcal);
	
	@Cacheable
	List<Alimento> findByCantidad(long cantidad);
	
	@Cacheable
	List<Alimento> findByHidratos(long hidratos);
	
	@Cacheable
	List<Alimento> findByProteinas(long proteinas);
	
	@Cacheable
	List<Alimento> findByGrasas(long grasas);
	
	@Cacheable
	List<Alimento> findByDietasIdDieta(long idDietas);
	
	@Cacheable
	List<Alimento> findByNombreAlimentoAndCantidad(String nombreAlimento, long cantidad);
	
	@Cacheable
	List<Alimento> findByNombreAlimentoAndKcal(String nombreAlimento, long kcal);
}
