package es.sidelab.MailSender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/")
	public String inicio(Model model) {
		return "index";
	}
	
	@RequestMapping("/prueba2")
	public String prueba2(Model model) {
		System.out.println("PRUEBA");
		return "index";
	}
}
