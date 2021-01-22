package es.sidelab.virtuasthenics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

import es.sidelab.virtuasthenics.controller.UsuarioController;

@EnableCaching
@SpringBootApplication
@EnableHazelcastHttpSession
public class App {
	
	private static Log log = LogFactory.getLog(App.class);
	
	/*MÁQUINAS DOCKER*/
	private String ip1 = "172.20.128.3";
	private String ip2 = "172.20.128.4";
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public Config config(){
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(false);
		
		List<String> listaIPs = new ArrayList<String>();
		//listaIPs.add("127.0.0.1");
		
		/*Máquinas Docker*/
		listaIPs.add(ip1);
		listaIPs.add(ip2);
		
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(/*Collections.singletonList("127.0.0.1")*/listaIPs);

		return config;
	}
	
	@Bean
	public CacheManager cacheManager() {
		log.info("Activating cache...");
		return new ConcurrentMapCacheManager("virtuasthenics");
	}
	
}
