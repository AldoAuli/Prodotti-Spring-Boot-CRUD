package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Movimento;


@Service
public interface MovimentoService {
	Movimento save (Movimento movimento);
	List<Movimento> findAll();
	Optional<Movimento> findById(int id);
	
}
