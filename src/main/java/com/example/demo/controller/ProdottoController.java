package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Prodotto;
import com.example.demo.service.ProdottoServiceImpl;

@Controller
public class ProdottoController {

	
	private final ProdottoServiceImpl Service;
	
	public ProdottoController(ProdottoServiceImpl Service) {
		this.Service=Service;
	}
	
	@GetMapping("/")
	public String index (Model model) {
		List<Prodotto> ListaProdotti=Service.findAll();
		model.addAttribute("listaProdotti", ListaProdotti);
		model.addAttribute("nuovoProdotto", new Prodotto());
		return "prodotto";
	}
	@PostMapping("/salvaProdotto")
	public String salvaProdotto( @ModelAttribute Prodotto prodotto) {
		
		Service.save(prodotto);
		
		return "redirect:/";
	}
	@GetMapping("/modificaProdotto/{id}")
	public String modificaProdotto(@PathVariable int id, Model model ) {
		Prodotto ProdottoModificare= Service.findById(id).orElse(null);
		
		if(!(ProdottoModificare==null)) {
			model.addAttribute("nuovoProdotto",ProdottoModificare);
			List<Prodotto> Prodotti = Service.findAll();
			model.addAttribute("listaProdotti", Prodotti);
			return "prodotto";
		}else {
			return "redirect:/";
		}
		
	}
	@GetMapping("/eliminaProdotto/{id}")
	public String eliminaProdotto(@PathVariable int id) {
		Service.deleteById(id);
		return "redirect:/";
	}
}