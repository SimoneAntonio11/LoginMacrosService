package it.macros.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.macros.app.repositories.entities.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	@Query(value = "SELECT a FROM Utente a WHERE a.token=:token")
	public Utente findByToken(@Param("token")String token);
	
	@Query(value = "SELECT a FROM Utente a WHERE a.username=:username")
	public Utente findByUsername(@Param("username")String username);

}
