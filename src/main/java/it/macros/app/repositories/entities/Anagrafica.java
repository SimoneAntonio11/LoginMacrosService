package it.macros.app.repositories.entities;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "anagrafica")
public class Anagrafica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_utente")
	private Utente utente;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "data_nascita")
	private Date dataNascita;
	
	@Column(name = "peso")
	private Double peso;
		
	@Column(name = "altezza")
	private Double altezza;
	
	@Column(name = "cancellato")
	private Boolean cancellato;
}
