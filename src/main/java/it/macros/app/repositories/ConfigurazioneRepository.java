package it.macros.app.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.macros.app.repositories.entities.Configurazione;

public interface ConfigurazioneRepository extends JpaRepository<Configurazione, Integer> {

	@Query(value = "SELECT a FROM Configurazione a WHERE a.chiave LIKE :query% ORDER BY a.chiave ASC")
	public List<Configurazione> list(@Param("query") String query);
}