package it.macros.app.controllers.beans.responses;

import lombok.*;

import it.macros.app.controllers.beans.Esito;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class GenericResponse {
	private Esito esito = new Esito();
}