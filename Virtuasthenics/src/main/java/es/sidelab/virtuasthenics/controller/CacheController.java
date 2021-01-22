package es.sidelab.virtuasthenics.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController{
	
	@Autowired
	private CacheManager cacheManager;
	
	@GetMapping(value="/cache")
	public Map<Object, Object> getCacheContent(){
		/*Para inspeccionar qué hay en la CACHÉ*/
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager.getCache("virtuasthenics");
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("virtuasthenics");
		return cache.getNativeCache();
	}
}
