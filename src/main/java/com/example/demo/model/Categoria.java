package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_categoria;
	
	@NotBlank(message="il nome della categoria non può essere vuoto")
	private String nome;
	
	public Categoria() {}
	
	public Categoria(Integer id_categoria, String nome) {
		this.id_categoria=id_categoria;
		this.nome=nome;
	}

	public Integer getId() {
		return id_categoria;
	}

	public void setId(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
