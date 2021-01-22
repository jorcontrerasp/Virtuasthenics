package es.sidelab.virtuasthenics.repository;

import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import es.sidelab.virtuasthenics.model.Dieta;

@CacheConfig(cacheNames="virtuasthenics")
public interface DietaRepository extends JpaRepository<Dieta, Long> {
	
	@CacheEvict(allEntries = true)
	Dieta save(Dieta dieta);
	
	Dieta findByIdDieta(Long idDieta);
	
	@Cacheable
	List<Dieta> findByNombreDieta(String nombreDieta);
	
	@Cacheable
	List<Dieta> findByTipoDieta(String tipoDieta);
	
	@Cacheable
	List<Dieta> findByComida(String comida);
	
	@Cacheable
	List<Dieta> findByKcal(Long kcal);
	
	@Cacheable
	List<Dieta> findByAlimentos(Long idAlimento);
	
	@Cacheable
	List<Dieta> findByNombreDietaAndTipoDietaAndComidaAndKcal(String nombreDieta, String tipoDieta, String comida, Long kcal);
	
	@Cacheable
	List<Dieta> findByTipoDietaAndComidaAndKcal(String tipoDieta, String comida, Long kcal);
	
	@Cacheable
	List<Dieta> findByTipoDietaAndComida(String tipoDieta, String comida);
	
	@Cacheable
	List<Dieta> findByTipoDietaAndKcal(String tipoDieta, Long kcal);
	
	@Cacheable
	List<Dieta> findByComidaAndKcal(String comida, Long kcal);
	
}
