package it.macros.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.macros.app.repositories.entities.Anagrafica;

public interface AnagraficaRepository extends JpaRepository<Anagrafica, Integer> {

	@Query(value = "SELECT a FROM Anagrafica a WHERE a.utente.email = :email")
	public Anagrafica findByEmail(@Param("email") String email);
}