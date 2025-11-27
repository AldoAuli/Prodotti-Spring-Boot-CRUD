package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Fornitore;
import com.example.demo.service.FornitoreService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/fornitori")
public class FornitoreController {

	
	private final FornitoreService serviceFornitore;
	
	public FornitoreController(FornitoreService serviceFornitore) {
		this.serviceFornitore=serviceFornitore;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Fornitore> ListaFornitori= serviceFornitore.findAll();
		
		model.addAttribute("listaFornitori", ListaFornitori);
		model.addAttribute("nuovoFornitore", new Fornitore());
		return "fornitore";
	}
	
	@PostMapping("/salva")
	public String salvaFornitore(@Valid Fornitore fornitore) {
		serviceFornitore.save(fornitore);
		return "redirect:/fornitori";
	}
	
	@PostMapping("/elimina/{id}")
	public String eliminaFornitore(@PathVariable int id) {
		serviceFornitore.deleteById(id);
		return "redirect:/fornitori";
	}
	@PostMapping("/modifica/{id}")
	public String modificaFornitore(@PathVariable int id, Model model) {
		Optional<Fornitore> modificaFornitore= serviceFornitore.findById(id);
		if(modificaFornitore.isPresent()) {
			model.addAttribute("nuovoFornitore",modificaFornitore);
		}
		List<Fornitore> ListaFornitori=serviceFornitore.findAll();
		model.addAttribute("listaFornitori", ListaFornitori);
		return "redirect/:fornitori";
	}
}
