package es.sidelab.virtuasthenics.utils;

import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sidelab.virtuasthenics.AppConfig;
import es.sidelab.virtuasthenics.model.Usuario;
import es.sidelab.virtuasthenics.repository.UsuarioRepository;

@Component
public class BBDDUsersLoader {
	
	private static Log log = LogFactory.getLog(BBDDUsersLoader.class);

	@Autowired
	private UsuarioRepository userRepository;

	@PostConstruct
	private void initUserBBDD() {
		String ip = null;
		Varios varios = new Varios();
		try {
			ip = varios.obtenerIP();
			log.info("Aplicación arrancada con la IP: " + ip);
		} catch (Exception e) {
			log.error("Error al obtener la IP de la máquina");
		}

		boolean inicializarBBDD = AppConfig.APP1_IP.equals(ip) && AppConfig.BD_INIT;
		if(inicializarBBDD) {
			userRepository.save(new Usuario("user", "pass", "user@gmail.es", "ROLE_USER"));
			userRepository.save(new Usuario("admin", "adminpass", "admin@yahoo.es", "ROLE_USER", "ROLE_ADMIN"));
			log.info("BBDD inicializada con éxito.");
		}
	}

}