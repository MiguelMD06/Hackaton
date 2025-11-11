package co.edu.local.Hackaton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.local.Hackaton.dto.TicketDTO;
import co.edu.local.Hackaton.model.Ticket;
import co.edu.local.Hackaton.services.TicketService;
import co.edu.local.Hackaton.services.UsuarioService;

@Controller
@RequestMapping("/jefe")
public class JefeController {
	
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ModelAttribute("ticket")
	public TicketDTO nuevoTicket() {
		return new TicketDTO();
	}
	
	@GetMapping
	public String mostrarForm(Model modelo) {

	    List<Ticket> asignaturas = ticketService.selectAll()
	            .stream()
	            .filter(a -> a.getEstado() != null &&
	                         a.getEstado().getNombre().equals("VALIDACION"))
	            .toList();
		modelo.addAttribute("tickets", asignaturas);
		modelo.addAttribute("operarios", usuarioService.listarOperadores());
		modelo.addAttribute("ticket", new TicketDTO());
		return "actualizaroperarios";
	}
	
	@PostMapping
	public String registrarUsuario(@ModelAttribute("ticket") TicketDTO ticket) {
			ticketService.updateOperario(ticket);
			return "redirect:/jefe?exito";
	}
	
}
