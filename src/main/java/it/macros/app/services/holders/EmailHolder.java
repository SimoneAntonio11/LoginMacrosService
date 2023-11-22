package it.macros.app.services.holders;

import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.macros.app.repositories.ConfigurazioneRepository;
import it.macros.app.repositories.entities.Anagrafica;
import it.macros.app.repositories.entities.Configurazione;



@Slf4j
@Component
public class EmailHolder extends BaseHolder {

	@Autowired
	private ConfigurazioneRepository configurazioneRepository;

	public void sendMail(Anagrafica anagrafica, String token) throws Exception {

		HashMap<String, String> configurazioni = new HashMap<>();

		List<Configurazione> list = configurazioneRepository.list("mail");
	
		for (Configurazione c : list) {
			configurazioni.put(c.getChiave(), c.getValore());
		}

		String server, port, username, password, subject, auth, tls, body, protocols;

		server = configurazioni.get("mail.smtp.host");
		port = configurazioni.get("mail.smtp.port");
		username = configurazioni.get("mail.smtp.username");
		password = configurazioni.get("mail.smtp.password");
		subject = configurazioni.get("mail.smtp.email.subject");
		auth = configurazioni.get("mail.smtp.auth");
		tls = configurazioni.get("mail.smtp.email.tls");
		body = configurazioni.get("mail.smtp.email.first.access");
		protocols = configurazioni.get("mail.smtp.ssl.protocols");
		
		body = body.replace("{0}", anagrafica.getNome()).replace("{1}", anagrafica.getCognome()).replace("{2}", token);

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", server);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.starttls.enable", tls);
		properties.put("mail.smtp.ssl.trust", server);
		properties.put("mail.smtp.ssl.protocols", protocols);

		Session session = Session.getDefaultInstance(properties);

		try {
			Message msg = new MimeMessage(session);
			InternetAddress[] toAddresses = { new InternetAddress(anagrafica.getUtente().getEmail()) };

			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(body + token);

			Transport t = session.getTransport("smtp");

			t.connect(username, password);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();

		} catch (MessagingException e) {
			log.info("Messaging Exception {}", e);
			throw e;
		}
	}
}