package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Fornitore {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_fornitore;
	@NotBlank(message="il nome non può essere vuoto")
	private String nome;
	@NotBlank(message="l'email non può essere vuota")
	private String email;
	
	public Fornitore() {}
	public Fornitore(Integer id_fornitore, String nome, String email) {
		this.id_fornitore= id_fornitore;
		this.nome=nome;
		this.email=email;
	}
	public Integer getId() {
		return id_fornitore;
	}
	public void setId(Integer id_fornitore) {
		this.id_fornitore = id_fornitore;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
