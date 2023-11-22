package it.macros.app.services;

import it.macros.app.repositories.entities.Anagrafica;
import it.macros.app.repositories.entities.Profilo;
import it.macros.app.repositories.entities.Utente;
import it.macros.app.services.exceptions.ServiceException;

public interface LoginService {

	public void ripristinaPassword(Anagrafica anagrafica) throws ServiceException;
	public void ripristinaPassword(String token, Anagrafica anagrafica) throws ServiceException;
	public Utente primoAccesso(String token) throws ServiceException;
	public void primoAccesso(Utente utente) throws ServiceException;
	public Profilo trovaRuolo(String username) throws ServiceException;

	
}
