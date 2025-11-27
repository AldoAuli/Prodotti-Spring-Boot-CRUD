package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Prodotto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_prodotto;
	@ManyToMany
	@JoinTable(
	        name = "prodotto_fornitore", // Nome della tabella di giunzione che verrà creata
	        joinColumns = @JoinColumn(name = "id_prodotto"), // Colonna FK in questa tabella (che punta a Prodotto)
	        inverseJoinColumns = @JoinColumn(name = "id_fornitore"))// Colonna FK che punta all'altra entità (Fornitore)
	Set<Fornitore> fornitori;
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	@NotBlank (message="il nome del prodotto non può essere vuoto")
	private String nome;
	@NotNull(message="la quantità è obbligatoria")
	@Min(value= 1, message="la quanità minima è 1")
	private int quantita;
	@NotNull(message="il prezzo è obbligatorio")
	@Min(value= 1, message="il prezzo minimo  è 1")
	private double prezzo;
	
	public Prodotto() {}
	
	public Prodotto(Integer id_prodotto, String nome, int quantita,double prezzo) {
		this.id_prodotto=id_prodotto;
		this.nome=nome;
		this.quantita=quantita;
		this.prezzo=prezzo;
	}
	public Integer getId() {
		return id_prodotto;
	}

	public void setId(Integer id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<Fornitore> getFornitori() {
		return fornitori;
	}

	public void setFornitori(Set<Fornitore> fornitori) {
		this.fornitori = fornitori;
	}
	
}
