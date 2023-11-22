package it.macros.app.controllers.beans.requests;

import lombok.*;

import it.macros.app.repositories.entities.Utente;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UtenteRequest extends GenericRequest {
	private Utente utente;
}