package it.macros.app.controllers.beans.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class RegisterRequest extends GenericRequest{
	private String username;
	private String email;
    private String password;
}
