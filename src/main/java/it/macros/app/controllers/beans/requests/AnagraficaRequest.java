package it.macros.app.controllers.beans.requests;

import lombok.*;

import it.macros.app.repositories.entities.Anagrafica;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class AnagraficaRequest extends GenericRequest {
	private Anagrafica anagrafica;
}