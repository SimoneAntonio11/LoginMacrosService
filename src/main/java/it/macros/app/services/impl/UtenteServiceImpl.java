package it.macros.app.services.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.macros.app.repositories.UtenteRepository;
import it.macros.app.repositories.entities.Utente;
import it.macros.app.services.UtenteService;
import it.macros.app.services.constants.ServiceMessages;
import it.macros.app.services.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public Utente getUtente(String username) throws ServiceException {
		
		Utente utente = null;

		try {
			utente = utenteRepository.findByUsername(username);
		} catch (NoSuchElementException ne) {
			log.info("Exception occurs {}, ID {}", ne);
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (Exception e) {
			log.error("Exception occurs {}", e);
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
		return utente;
	}

}
