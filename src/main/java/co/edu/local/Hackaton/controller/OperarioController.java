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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.local.Hackaton.dto.TicketDTO;
import co.edu.local.Hackaton.model.Ticket;
import co.edu.local.Hackaton.services.TicketService;
import co.edu.local.Hackaton.services.UsuarioService;

@Controller
@RequestMapping("/operador")
public class OperarioController {

	@Autowired
	private TicketService ticketService;
	
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ModelAttribute("ticket")
	public TicketDTO nuevoTicket() {
		return new TicketDTO();
	}
	
	@GetMapping
	public String mostrarIndx() {
		return "indexoperarios";
	}
	
	@GetMapping("/asignados")
	public String mostrarForm(Model modelo, Authentication authentication) {

		String nombreUsuario = authentication.getName(); 
		
	    List<Ticket> ticketsEj = ticketService.selectAll()
	            .stream()
	            .filter(a -> a.getEstado() != null &&
	                         a.getEstado().getNombre().equals("ASIGNADO"))
	            .toList();
	    
	    List<Ticket> ticketsFilt = ticketsEj
	            .stream()
	            .filter(a -> a.getUsuario() != null &&
	                         a.getUsuario().getNombre().equals(nombreUsuario))
	            .toList();
		modelo.addAttribute("tickets", ticketsFilt);
		return "operarios";
	}
	
	@GetMapping("/actualizar/{id}")
	public String registrarUsuario(@PathVariable Long id) {
			TicketDTO ticket = new TicketDTO(4L);
			ticket.setId(id);
			ticketService.updateEstado(ticket);
			return "redirect:/operador/trabajos";
	}
	
	@GetMapping("/trabajos")
	public String mostrarFormulario(Model modelo, Authentication authentication) {

		String nombreUsuario = authentication.getName(); 
		
	    List<Ticket> ticketsEj = ticketService.selectAll()
	            .stream()
	            .filter(a -> a.getEstado() != null && (
	                         a.getEstado().getNombre().equals("EJECUCION") || a.getEstado().getNombre().equals("ESPERA")))
	            .toList();
	    
	    List<Ticket> ticketsFilt = ticketsEj
	            .stream()
	            .filter(a -> a.getUsuario() != null &&
	                         a.getUsuario().getNombre().equals(nombreUsuario))
	            .toList();
		modelo.addAttribute("tickets", ticketsFilt);
		modelo.addAttribute("ticket", new TicketDTO());
		
		return "operarios2";
	}
	
	@PostMapping("/trabajos")
	public String registrarUsuario(@ModelAttribute TicketDTO ticketDTO) {
			ticketService.updateOperarioEstado(ticketDTO);
			return "redirect:/operador/trabajos?exito";
	}
	
	
	
	
}
