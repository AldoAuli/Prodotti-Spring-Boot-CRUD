package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Movimento;
import com.example.demo.model.Prodotto;
import com.example.demo.repository.MovimentoRepository;
import com.example.demo.util.TipoMovimento;

import jakarta.transaction.Transactional;
@Service
public class MovimentoServiceImpl implements MovimentoService {
	
	private final MovimentoRepository Movimento_Repo;
	private final ProdottoService Prodotto_service;
	
	public MovimentoServiceImpl(MovimentoRepository Movimento_Repo,ProdottoService Prodotto_service) {
		this.Movimento_Repo=Movimento_Repo;
		this.Prodotto_service=Prodotto_service;
	}
	@Override
	@Transactional // Garantisce che l'aggiornamento del Movimento e del Prodotto avvenga insieme o fallisca tutto
	public Movimento save(Movimento movimento) {
		// 1. Carica l'oggetto Prodotto dal database usando l'ID fornito nel Movimento
		Prodotto prodotto= Prodotto_service.findById(movimento.getProdotto().getId()).orElseThrow(() -> new RuntimeException("Prodotto con ID " + movimento.getProdotto().getId() + " non trovato."));;
		int quantitaMovimentata = movimento.getQuantita();
        int nuovaQuantitaMagazzino = prodotto.getQuantita();
        
        // 2. Determina l'operazione in base al TipoMovimento (utilizzando l'Enum)
        if (TipoMovimento.ENTRATA.equals(movimento.getTipo())) {
            
            // Logica ENTRATA: Aumenta la quantità
            nuovaQuantitaMagazzino += quantitaMovimentata;
            
        } else if (TipoMovimento.USCITA.equals(movimento.getTipo())) {
            
            // Logica USCITA: Diminuisce la quantità
            if (nuovaQuantitaMagazzino < quantitaMovimentata) {
                // Preveniamo quantità negative (Logica di Validazione a livello di Service)
                throw new IllegalArgumentException("Impossibile effettuare l'uscita: quantità in magazzino insufficiente (" + prodotto.getQuantita() + ").");
            }
            nuovaQuantitaMagazzino -= quantitaMovimentata;
            
        } else {
            // Caso teoricamente impossibile con l'Enum, ma buona pratica di sicurezza
            throw new IllegalArgumentException("Tipo di movimento non valido: " + movimento.getTipo());
        }

        // 3. Aggiorna l'oggetto Prodotto e salvalo
        prodotto.setQuantita(nuovaQuantitaMagazzino);
        Prodotto_service.save(prodotto); 
        
        // 4. Salva il Movimento nel database per la tracciabilità
        return Movimento_Repo.save(movimento); 
        
        // Se il salvataggio del prodotto o del movimento fallisce, @Transactional fa il rollback di entrambi.
    }
    @Override
    public List<Movimento> findAll() {
        return Movimento_Repo.findAll();
    }
    
    @Override
    public Optional<Movimento> findById(int id) {
        return Movimento_Repo.findById(id);
    }
		
	}
