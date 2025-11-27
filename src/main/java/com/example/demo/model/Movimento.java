package com.example.demo.model;

import java.time.LocalDateTime;

import com.example.demo.util.TipoMovimento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Movimento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Integer id_movimento;
	@Enumerated(EnumType.STRING)
	private TipoMovimento tipo;
	@NotNull
	@Min(value=1, message="la quantità non può essere minore di 1")
	private  int quantita;
	private LocalDateTime dataMovimento = LocalDateTime.now();
	@ManyToOne
	@JoinColumn(name="id_prodotto")
	private  Prodotto prodotto;
	
	public Movimento() {}
	
	public Movimento(Integer id_movimento, TipoMovimento tipo,int quantita, LocalDateTime dataMovimento) {
		this.id_movimento=id_movimento;
		this.tipo=tipo;
		this.quantita=quantita;
		this.dataMovimento=dataMovimento;
	}

	public Integer getId_movimento() {
		return id_movimento;
	}

	public void setId_movimento(Integer id_movimento) {
		this.id_movimento = id_movimento;
	}
	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public TipoMovimento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimento tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(LocalDateTime dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
}
