package it.macros.app.repositories.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "profili")
public class Profilo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_ruolo")
	private Ruolo ruolo;
	
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;
	
	@Column(name = "data_inizio")
	private Date dataInizio;
	
	@Column(name = "data_fine")
	private Date dataFine;
	
	@Column(name = "data_aggiornamento")
	private Date dataAggiornamento;
	
	@Column(name = "utente_aggiornamento")
	private String utenteAggiornamento;

}
