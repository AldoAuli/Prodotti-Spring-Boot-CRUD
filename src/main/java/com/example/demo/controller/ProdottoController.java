package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Categoria;
import com.example.demo.model.Fornitore;
import com.example.demo.model.Prodotto;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.FornitoreService;
import com.example.demo.service.ProdottoService;

import jakarta.validation.Valid;

@Controller
public class ProdottoController {

	
	private final ProdottoService Service;
	private final CategoriaService Service_categoria;
	private final FornitoreService Service_fornitore;
	
	public ProdottoController(ProdottoService Service,CategoriaService Service_categoria,FornitoreService Service_fornitore) {
		this.Service=Service;
		this.Service_categoria=Service_categoria;
		this.Service_fornitore=Service_fornitore;
	}
	
	@GetMapping("/")
	public String index (Model model, @RequestParam(required=false) String cerca, @RequestParam(required=false) Integer quantita,@RequestParam(required=false) String nome) {
		List<Prodotto> ListaProdotti;
		List<Categoria> ListaCategoria=Service_categoria.findAll();
		List<Fornitore> ListaFornitori=Service_fornitore.findAll();
		if(cerca!= null && !cerca.isEmpty()) {
			ListaProdotti=Service.findByNome(cerca);
		}else {
			if(quantita!=null) {
				ListaProdotti=Service.findByQuantita(quantita);
			}else {
				if(nome!=null && !nome.isEmpty()) {
					ListaProdotti=Service.findByFornitoreNomeContainingIgnoreCase(nome);
				}else {
					ListaProdotti=Service.findAll();	
				}
			}
		}
		model.addAttribute("listaProdotti", ListaProdotti);
		model.addAttribute("nuovoProdotto", new Prodotto());
		model.addAttribute("listaCategorie",ListaCategoria);
		model.addAttribute("listaFornitori",ListaFornitori);
		
		return "prodotto";
	}
	@PostMapping("/salvaProdotto")
	public String salvaProdotto( @ModelAttribute("nuovoProdotto") @Valid Prodotto prodotto,BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
	        // Se ci sono errori di validazione (es. nome vuoto o prezzo <= 0)
	        
	        // Ricarichiamo la lista prodotti per la tabella (necessario per non far crashare Thymeleaf!)
	        model.addAttribute("listaProdotti", Service.findAll()); 
	        
	        // Non facciamo un redirect, ma torniamo direttamente al template "prodotto".
	        // L'oggetto 'prodotto' (che contiene gli errori) viene mantenuto nel model.
	        return "prodotto";
	    }else {
		Service.save(prodotto);
		return "redirect:/";
	    }
	    
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