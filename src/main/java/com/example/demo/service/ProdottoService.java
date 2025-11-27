package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Prodotto;

public interface ProdottoService {
	List<Prodotto> findAll();
	Prodotto save(Prodotto prodotto);
	void deleteById(int id);
	Optional<Prodotto> findById(int id);
	List<Prodotto> findByNome(String cerca);
	List<Prodotto> findByQuantita(int quantita);
	List<Prodotto> findByFornitoreNomeContainingIgnoreCase(String nome);
}
