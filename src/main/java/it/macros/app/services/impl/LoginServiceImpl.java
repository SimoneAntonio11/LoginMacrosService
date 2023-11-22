package it.macros.app.services.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import it.macros.app.repositories.AnagraficaRepository;
import it.macros.app.repositories.ProfiloRepository;
import it.macros.app.repositories.UtenteRepository;
import it.macros.app.repositories.entities.Anagrafica;
import it.macros.app.repositories.entities.Profilo;
import it.macros.app.repositories.entities.Utente;
import it.macros.app.services.LoginService;
import it.macros.app.services.constants.ServiceMessages;
import it.macros.app.services.exceptions.ServiceException;
import it.macros.app.services.holders.EmailHolder;
import it.macros.app.services.utils.TokenGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private EmailHolder emailHolder;

	@Autowired
	private AnagraficaRepository anagraficaRepository;
	
	@Autowired
	private ProfiloRepository profiloRepository;

	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void ripristinaPassword(Anagrafica anagrafica) throws ServiceException {

		try {
			
			System.err.println(anagraficaRepository.findByEmail(anagrafica.getUtente().getEmail()));
			
			Anagrafica currentAnagrafica = anagraficaRepository.findByEmail(anagrafica.getUtente().getEmail());
			
			System.err.println(currentAnagrafica);

			if(currentAnagrafica != null) {

				String token = TokenGenerator.nextToken();

				Utente utente = utenteRepository.findById(currentAnagrafica.getUtente().getId()).get();
				
				System.err.println(utente);

				utente.setToken(token);
				
				utenteRepository.saveAndFlush(utente);

				emailHolder.sendMail(currentAnagrafica, token);

			} else {
				throw new NoSuchElementException();
			}

		} catch (NoSuchElementException ne) {
			log.info("Exception occurs {}", ne);
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			log.info("Exception occurs {}", de);
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			log.info("Exception occurs {}", e);
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	@Override
	public void ripristinaPassword(String token, Anagrafica anagrafica) throws ServiceException {

		try {
			Utente utenteCorrente = utenteRepository.findByToken(token);
			
			utenteCorrente.setPassword(passwordEncoder.encode(anagrafica.getUtente().getPassword()));
			
			utenteCorrente.setToken(null);
			
			System.err.println("La password è: " + anagrafica.getUtente());
			
			if(utenteCorrente != null) {
				utenteRepository.saveAndFlush(utenteCorrente);	
			}

		} catch (NoSuchElementException ne) {
			log.info("Exception occurs {}", ne);
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			log.info("Exception occurs {}", de);
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			log.info("Exception occurs {}", e);
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}
	
	@Override
	public Utente primoAccesso(String token) throws ServiceException {
		try {
			Utente utenteCorrente = utenteRepository.findByToken(token);
			
			if(utenteCorrente != null) {
				return utenteCorrente;
			} else {
				throw new NoSuchElementException();
			}
		}
		catch (NoSuchElementException ne) {
			log.info("Exception occurs {}", ne);
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			log.info("Exception occurs {}", de);
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			log.info("Exception occurs {}", e);
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	@Override
	@Transactional
	public void primoAccesso(Utente utente) throws ServiceException {
		try {
			Utente utenteCorrente = utenteRepository.findByToken(utente.getToken());
			
			if(utenteCorrente != null) {
				utente.setToken(null);
				utente.setAttivo(true);
				utente.setPassword(passwordEncoder.encode(utente.getPassword()));
				log.info(utente.toString());
				utenteRepository.saveAndFlush(utente);
			} else {
				throw new NoSuchElementException();
			}

		} catch (NoSuchElementException ne) {
			log.info("Exception occurs {}", ne);
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			log.info("Exception occurs {}", de);
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			log.info("Exception occurs {}", e);
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}
	
	@Override
	public Profilo trovaRuolo(String username) throws ServiceException{
		Profilo  profilo = new Profilo();
		try {
			profilo= profiloRepository.findRuoloByUsername(username);
		}catch(Exception e) {
			throw new ServiceException();
		}
		return profilo;
		
	}

}
