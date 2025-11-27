package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Movimento;
import com.example.demo.model.Prodotto;
import com.example.demo.service.MovimentoService;
import com.example.demo.service.ProdottoService;
import com.example.demo.util.TipoMovimento;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/movimenti")
public class MovimentoController {
	
	private final MovimentoService serviceMovimento;
	private final ProdottoService serviceProdotto;
	
	public MovimentoController(MovimentoService serviceMovimento,ProdottoService serviceProdotto) {
		 this.serviceMovimento=serviceMovimento;
		 this.serviceProdotto=serviceProdotto;
	}
	@GetMapping
	public String index (Model model) {
		List<Movimento> ListaMovimento= serviceMovimento.findAll();
		model.addAttribute("listaMovimenti",ListaMovimento);
		
		List<Prodotto> ListaProdotti= serviceProdotto.findAll();
		model.addAttribute("listaProdotti",ListaProdotti);
		model.addAttribute("tipiMovimento",TipoMovimento.values());
		model.addAttribute("nuovoMovimento", new Movimento());
		
		return "movimenti";
	}
	
	@PostMapping("/salva")
    public String salvaMovimento(
        @ModelAttribute("nuovoMovimento") @Valid Movimento movimento,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes,
        Model model // Necessario se la validazione fallisce
    ) {
        if (bindingResult.hasErrors()) {
            // Se fallisce, dobbiamo ricaricare i dati necessari per il form
            model.addAttribute("listaProdotti", serviceProdotto.findAll());
            model.addAttribute("tipiMovimento", TipoMovimento.values());
            model.addAttribute("listaMovimenti", serviceMovimento.findAll());
            return "movimenti";
        }
        
        try {
        	serviceMovimento.save(movimento);
            redirectAttributes.addFlashAttribute("successMessage", "Movimento registrato e quantità prodotto aggiornata con successo!");
        } catch (IllegalArgumentException e) {
             // Cattura l'errore se la quantità in uscita è insufficiente
             redirectAttributes.addFlashAttribute("errorMessage", "Errore: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Si è verificato un errore durante il salvataggio.");
        }
        
        return "redirect:/movimenti";
    }
}
