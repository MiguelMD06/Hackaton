package co.edu.local.Hackaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.local.Hackaton.model.Ticket;
import co.edu.local.Hackaton.repository.CategoryRepository;
import co.edu.local.Hackaton.services.TicketService;

@Controller
@RequestMapping("/solicitante")
public class SolicitanteController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TicketService ticketService;
	
	@ModelAttribute("ticket")
	public Ticket nuevoTicket() {
		return new Ticket();
	}
	
	@GetMapping
	public String mostrarForm(Model modelo) {
		modelo.addAttribute("categoria", categoryRepository.findAll());
		modelo.addAttribute("ticket", new Ticket());
		return "creartickets";
	}
	
	@PostMapping
	public String registrarUsuario(@ModelAttribute("ticket") Ticket ticket) {
			ticketService.crear(ticket);
			return "redirect:/solicitante?exito";
	}
	
	
}
