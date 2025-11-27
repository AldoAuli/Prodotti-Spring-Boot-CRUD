package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Fornitore;

public interface FornitoreService {
	List<Fornitore> findAll();
	Optional<Fornitore> findById(int id);
	void deleteById(int id);
	Fornitore save(Fornitore fornitore);
}