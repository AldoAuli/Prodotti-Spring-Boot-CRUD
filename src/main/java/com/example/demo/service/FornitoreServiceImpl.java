package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Fornitore;
import com.example.demo.repository.FornitoreRepository;

@Service
public class FornitoreServiceImpl implements FornitoreService{
	
	private final FornitoreRepository Fornitore_repo;
	public FornitoreServiceImpl(FornitoreRepository Fornitore_repo) {
		this.Fornitore_repo=Fornitore_repo;
	}
	@Override
	public List<Fornitore> findAll() {
		return Fornitore_repo.findAll();
	}
	@Override
	public Optional<Fornitore> findById(int id) {
		return Fornitore_repo.findById(id);
	}
	@Override
	public void deleteById(int id) {
		Fornitore_repo.deleteById(id);
	}
	@Override
	public Fornitore save(Fornitore fornitore) {
		return Fornitore_repo.save(fornitore);
	}
}