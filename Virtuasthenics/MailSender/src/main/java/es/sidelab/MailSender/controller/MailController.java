package es.sidelab.MailSender.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import es.sidelab.MailSender.model.Email;
import es.sidelab.MailSender.utils.SmtpMailSender;

@RestController
public class MailController {
	
	@Autowired
	private SmtpMailSender smtpMailSender;
	
	String body;

	public MailController() {
		smtpMailSender = new SmtpMailSender();
	}
	
	@RequestMapping("/test")
	public String test() {
		System.out.println("Correo de prueba");
		
		String from = "prbs.0395@gmail.com";
		String to = "virtuasthenics@gmail.com";
		String msg= "Hola. Esto es un mensaje de prueba. Un saludo.";
		
		Gson gson = new Gson();
		String jsonInString = gson.toJson(new Email(msg, to, msg));
		
		Email mail = new Email();
		mail.setFrom(from);
		mail.setTo(to);
		mail.setMsg(msg);
		
		System.out.println("Trying to contact : " + mail.getTo());
		try {
			sendEmail(mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonInString;
	}

	@PostMapping("/mail")
	public void reciever(Model model, @RequestBody Email mail) throws MessagingException {
		System.out.println("Trying to contact : " + mail.getTo());
		sendEmail(mail);
	}
	
	@GetMapping("/mail")
	public void reciever2(Model model, @RequestBody Email mail) throws MessagingException {
		System.out.println("Trying to contact : " + mail.getTo());
		sendEmail(mail);
	}

	private void sendEmail(Email mail) throws MessagingException {
		smtpMailSender.send(mail.getTo(), mail.getFrom() + " is trying to contact you", mail.getMsg());
	}
}
