package it.macros.app.controllers.beans.requests;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class LoginRequest extends GenericRequest {
	private String username;
    private String password;
}