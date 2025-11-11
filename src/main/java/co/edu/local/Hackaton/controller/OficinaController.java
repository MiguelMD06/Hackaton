package co.edu.local.Hackaton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.local.Hackaton.dto.TicketDTO;
import co.edu.local.Hackaton.model.Ticket;
import co.edu.local.Hackaton.repository.CategoryRepository;
import co.edu.local.Hackaton.services.TicketService;

@Controller
@RequestMapping("/mantenimiento")
public class OficinaController {
	
	
	@Autowired
	private TicketService ticketService;
	
	@ModelAttribute("ticket")
	public TicketDTO nuevoTicket() {
		return new TicketDTO();
	}
	@GetMapping
	public String mostrarInd() {
		return "indexoficina";
	}
	
	@GetMapping("/actualizar")
	public String mostrarForm(Model modelo) {

	    List<Ticket> asignaturas = ticketService.selectAll()
	            .stream()
	            .filter(a -> a.getEstado() != null &&
	                         a.getEstado().getNombre().equals("NUEVO"))
	            .toList();
		modelo.addAttribute("tickets", asignaturas);
		modelo.addAttribute("ticket", new TicketDTO());
		return "actualizartickets";
	}
	
	
	@PostMapping("/actualizar")
	public String registrarUsuario(@ModelAttribute("ticket") TicketDTO ticket) {
			ticketService.updatePrioridad(ticket);
			return "redirect:/mantenimiento/actualizar?exito";
	}
	
	@GetMapping("/validar")
	public String mostrarValidar(Model modelo) {

	    List<Ticket> asignaturas = ticketService.selectAll()
	            .stream()
	            .filter(a -> a.getEstado() != null &&
	                         a.getEstado().getNombre().equals("SOLUCIONADO"))
	            .toList();
		modelo.addAttribute("tickets", asignaturas);
		modelo.addAttribute("ticket", new TicketDTO());
		return "mantenimientoValidar";
	}
	
	@GetMapping("/validar/{id}")
	public String registrarUsuario(@PathVariable Long id) {
			TicketDTO ticket = new TicketDTO(7L);
			ticket.setId(id);
			ticketService.updateValidar(ticket);
			return "redirect:/mantenimiento/validar?exito";
	}
	
	@GetMapping("/reabrir")
	public String mostrarAbrir(Model modelo) {
		
		List<Ticket> asignaturas = ticketService.selectAll()
				.stream()
				.filter(a -> a.getEstado() != null &&
				a.getEstado().getNombre().equals("CERRADO"))
				.toList();
		modelo.addAttribute("tickets", asignaturas);
		modelo.addAttribute("ticket", new TicketDTO());
		return "mantenimientoAbrir";
	}
	
	@GetMapping("/reabrir/{id}")
	public String reAbrir(@PathVariable Long id) {
		TicketDTO ticket = new TicketDTO(1L);
		ticket.setId(id);
		ticketService.updateEstado(ticket);
		return "redirect:/mantenimiento/reabrir?exito";
	}
	
}
