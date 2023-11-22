package it.macros.app.controllers.beans.responses;

import it.macros.app.repositories.entities.Utente;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UtenteResponse extends GenericResponse{
	private Utente utente;
}
