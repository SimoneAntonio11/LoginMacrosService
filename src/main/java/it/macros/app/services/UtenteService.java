package it.macros.app.services;

import it.macros.app.repositories.entities.Utente;
import it.macros.app.services.exceptions.ServiceException;

public interface UtenteService {

	public Utente getUtente(String username) throws ServiceException;

}
