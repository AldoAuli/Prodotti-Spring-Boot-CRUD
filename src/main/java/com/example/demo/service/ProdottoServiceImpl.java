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
		if(prodotto.getPrezzo()>0) {
		return repo.save(prodotto) ;
		}else {
			throw new IllegalArgumentException("Il prezzo del prodotto deve essere un valore positivo.");
		}
	}

	@Override
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	@Override
	public Optional<Prodotto> findById(int id) {
		return repo.findById(id);
	}

}