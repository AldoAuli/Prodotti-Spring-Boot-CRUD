package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	private final CategoriaRepository CatRepo;
	
	public CategoriaServiceImpl(CategoriaRepository CatRepo) {
		this.CatRepo=CatRepo;
	}

	@Override
	public List<Categoria> findAll() {
		return CatRepo.findAll();
	}

	@Override
	public Categoria save(Categoria categoria) {
		return CatRepo.save(categoria);
	}

	@Override
	public Optional<Categoria> findById(int id) {
		// TODO Auto-generated method stub
		return CatRepo.findById(id);
	}

	@Override
	public void deleteById(int id) {
		CatRepo.deleteById(id);
	}
	
	

}
