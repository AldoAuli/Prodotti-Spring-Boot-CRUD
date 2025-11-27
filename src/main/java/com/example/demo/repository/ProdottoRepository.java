package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Prodotto;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto,Integer> {
	
	List<Prodotto> findByNomeContainingIgnoreCase(String cerca);
	List<Prodotto> findByQuantitaLessThan(int quantita);
	List<Prodotto> findByFornitoriNomeContainingIgnoreCase(String nome);
	
}
