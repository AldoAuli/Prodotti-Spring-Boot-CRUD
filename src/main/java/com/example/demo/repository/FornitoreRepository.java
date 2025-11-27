package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Fornitore;

@Repository
public interface FornitoreRepository extends JpaRepository<Fornitore,Integer> {

}
