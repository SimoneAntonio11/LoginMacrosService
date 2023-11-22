package it.macros.app.controllers.beans.responses;

import it.macros.app.repositories.entities.Profilo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ProfiloResponse extends GenericResponse{
	private Profilo profilo;
}
