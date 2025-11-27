package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Categoria;
import com.example.demo.service.CategoriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categorie")
public class CategoriaController {
	
	private final CategoriaService service_cat;
	
	public CategoriaController(CategoriaService service_cat) {
		this.service_cat=service_cat;
	}
	@GetMapping
	public String index(Model model) {
		List<Categoria> ListaCategorie=service_cat.findAll();
		model.addAttribute("listaCategorie",ListaCategorie);
		model.addAttribute("nuovaCategoria",new Categoria());
		return "categorie";
	}
	@PostMapping("/salva")
	public String salvaCategoria(@Valid Categoria categoria) {
		service_cat.save(categoria);
		return "redirect:/categorie";
	}
	@GetMapping("/elimina/{id}")
	public String eliminaCategoria(@PathVariable int id) {
		service_cat.deleteById(id);
		return "redirect:/categorie";
		
	}
	
	@GetMapping("/modifica/{id}")
	public String modificaCategoria(@PathVariable int id, Model model) {
		Optional<Categoria> CategoriaModificare=service_cat.findById(id);
		
		if(CategoriaModificare.isPresent()) {
		model.addAttribute("nuovaCategoria",CategoriaModificare.get());
		}
		List<Categoria> ListaCategorie=service_cat.findAll();
		model.addAttribute("listaCategorie",ListaCategorie);
		return "categorie";
	}

}
