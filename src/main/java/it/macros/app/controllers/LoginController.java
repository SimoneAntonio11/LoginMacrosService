package it.macros.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.macros.app.controllers.beans.Esito;
import it.macros.app.controllers.beans.requests.LoginRequest;
import it.macros.app.controllers.beans.requests.UtenteRequest;
import it.macros.app.controllers.beans.responses.GenericResponse;
import it.macros.app.controllers.beans.responses.ProfiloResponse;
import it.macros.app.controllers.beans.responses.UtenteResponse;
import it.macros.app.controllers.constants.ControllerMaps;
import it.macros.app.repositories.entities.Profilo;
import it.macros.app.repositories.entities.Utente;
import it.macros.app.services.LoginService;
import it.macros.app.services.exceptions.ServiceException;
import it.macros.security.SecurityManager;
import it.macros.security.services.exceptions.SecurityException;


@Slf4j
@RestController
public class LoginController extends BaseController {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private LoginService loginService;

	/**
	 * @param loginRequest
	 * @param response
	 * @return HttpEntity
	 */
    @RequestMapping(value = "public/login", method = RequestMethod.POST, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<ProfiloResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {

		HttpEntity<ProfiloResponse> httpEntity;

		ProfiloResponse profiloResponse = new ProfiloResponse();
		
		Profilo profilo = new Profilo();

		try {
			log.info("START invocation login() of controller layer");

			securityManager.login(loginRequest.getUsername(), loginRequest.getPassword(), response);
			
			profilo=loginService.trovaRuolo(loginRequest.getUsername());
			
			profiloResponse.setEsito(new Esito());
			
			profiloResponse.setProfilo(profilo);

			httpEntity = new HttpEntity<ProfiloResponse>(profiloResponse);

			log.info("END invocation login() of controller layer");

		} catch(SecurityException e) {
			profiloResponse.setEsito(new Esito(e.getCode(), e.getMessage()));
			httpEntity = new HttpEntity<ProfiloResponse>(profiloResponse);
		} catch(ServiceException se) {
			profiloResponse.setEsito(new Esito(se.getCode(), se.getMessage()));
			httpEntity = new HttpEntity<ProfiloResponse>(profiloResponse);
		}

		return httpEntity;
	}

	/**
	 * @param request
	 * @param response
	 * @return HttpEntity
	 */
	@RequestMapping(value = "public/logout", method = RequestMethod.POST, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> logout(HttpServletRequest request, HttpServletResponse response) {

		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			log.info("START invocation logout() of controller layer");

			securityManager.logout(request, response);

			httpEntity = new HttpEntity<GenericResponse>(new GenericResponse());

			log.info("END invocation logout() of controller layer");

		} catch(SecurityException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage()));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	/*@RequestMapping(value = "public/ripristina-password", method = RequestMethod.POST, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> ripristinaPassword(@RequestBody AnagraficaRequest anagraficaRequest) {

		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			log.info("START invocation ripristinaPassword() of controller layer");

			loginService.ripristinaPassword(anagraficaRequest.getAnagrafica());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			log.info("END invocation ripristinaPassword() of controller layer");

		} catch(ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	@RequestMapping(value = "public/ripristina-password/{token}", method = RequestMethod.POST, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> ripristinaPassword(@PathVariable("token") String token, @RequestBody AnagraficaRequest anagraficaRequest) {

		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			log.info("START invocation ripristinaPassword() of controller layer");

			loginService.ripristinaPassword(token, anagraficaRequest.getAnagrafica());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			log.info("END invocation ripristinaPassword() of controller layer");

		} catch(ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}
	*/
	@RequestMapping(value = "/public/primo-accesso/{token}", method = RequestMethod.GET, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<UtenteResponse> primoAccesso(@PathVariable("token") String token) {

		HttpEntity<UtenteResponse> httpEntity;

		UtenteResponse utenteResponse = new UtenteResponse();

		try {
			log.info("START invocation primoAccesso() of controller layer");

			Utente utente = loginService.primoAccesso(token);

			utenteResponse.setUtente(utente);
			
			utenteResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<UtenteResponse>(utenteResponse);

			log.info("END invocation primoAccesso() of controller layer");

		} catch(ServiceException e) {
			utenteResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<UtenteResponse>(utenteResponse);
		}

		return httpEntity;
	}

	@RequestMapping(value = "/public/primo-accesso", method = RequestMethod.POST, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> primoAccesso(@RequestBody UtenteRequest utenteRequest) {

		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			log.info("START invocation primoAccesso() of controller layer");

			loginService.primoAccesso(utenteRequest.getUtente());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			log.info("END invocation primoAccesso() of controller layer");

		} catch(ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}
}