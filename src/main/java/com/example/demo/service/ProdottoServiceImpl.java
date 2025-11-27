package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Prodotto;
import com.example.demo.repository.ProdottoRepository;

@Service
public class ProdottoServiceImpl implements ProdottoService {
	private final ProdottoRepository repo;

	public ProdottoServiceImpl(ProdottoRepository repo) {
		this.repo=repo;
	}
	
	
	@Override
	public List<Prodotto> findAll() {
		
		return repo.findAll();
	}

	@Override
	public Prodotto save(Prodotto prodotto) {
		return repo.save(prodotto) ;
	}

	@Override
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	@Override
	public Optional<Prodotto> findById(int id) {
		return repo.findById(id);
	}


	@Override
	public List<Prodotto> findByNome(String cerca) {
		return repo.findByNomeContainingIgnoreCase(cerca);
		
	}


	@Override
	public List<Prodotto> findByQuantita(int quantita) {
		return repo.findByQuantitaLessThan(quantita);
	}


	@Override
	public List<Prodotto> findByFornitoreNomeContainingIgnoreCase(String nome) {
		return repo.findByFornitoriNomeContainingIgnoreCase(nome);
	}

}