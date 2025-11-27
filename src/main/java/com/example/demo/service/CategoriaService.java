package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Categoria;

public interface CategoriaService {
	List<Categoria> findAll();
	Categoria save(Categoria categoria);
	Optional<Categoria> findById(int id);
	void deleteById(int id);
}
