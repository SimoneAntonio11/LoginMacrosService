package it.macros.app.repositories.entities;

import java.util.List;

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
@Table(name = "ruoli")
public class Ruolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_padre")
	private Ruolo ruolo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descrizione")
	private String descrizione;

	private transient List<Ruolo> ruoli;

	public Ruolo(Integer id) {
		super();
		this.id = id;
	}

	
}
