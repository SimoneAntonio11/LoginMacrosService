package it.macros.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.macros.app.repositories.entities.Profilo;

public interface ProfiloRepository extends JpaRepository<Profilo, Integer>{

	@Query(value = "SELECT a FROM Profilo a INNER JOIN Utente b on a.utente.id=b.id WHERE b.username=:username")
	public Profilo findRuoloByUsername(@Param("username")String username);
}
