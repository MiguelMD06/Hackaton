package co.edu.local.Hackaton.servicesImpl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.local.Hackaton.dto.TicketDTO;
import co.edu.local.Hackaton.model.Ticket;
import co.edu.local.Hackaton.repository.CategoryRepository;
import co.edu.local.Hackaton.repository.EstadoRepository;
import co.edu.local.Hackaton.repository.TicketRepository;
import co.edu.local.Hackaton.repository.UsuarioRepository;
import co.edu.local.Hackaton.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public Ticket crear(Ticket ticketDetails) {
		Ticket ticket = new Ticket(ticketDetails.getDescripcion(), ticketDetails.getUbicacion(), ticketDetails.getDependencia(), LocalDateTime.now() , null, ticketDetails.getEvidencia(), ticketDetails.getCategoria(), estadoRepository.findByNombre("NUEVO"));
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket updateValidar(TicketDTO ticketDetails) {
		Ticket ticketNuevo  = ticketRepository.findById(ticketDetails.getId()).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
		ticketNuevo.setEstado(estadoRepository.findById(ticketDetails.getEstado()).orElseThrow());
		ticketNuevo.setFechaCierre(LocalDateTime.now());
		return ticketRepository.save(ticketNuevo);
	}
	
	@Override
	public Ticket updateEstado(TicketDTO ticketDetails) {
		Ticket ticketNuevo  = ticketRepository.findById(ticketDetails.getId()).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
		ticketNuevo.setEstado(estadoRepository.findById(ticketDetails.getEstado()).orElseThrow());
		return ticketRepository.save(ticketNuevo);
	}
	
	@Override
	public Ticket updatePrioridad(TicketDTO ticketDetails) {
		Ticket ticketNuevo  = ticketRepository.findById(ticketDetails.getId()).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
		ticketNuevo.setPrioridad(ticketDetails.getPrioridad());
		ticketNuevo.setEstado(estadoRepository.findByNombre("VALIDACION"));
		return ticketRepository.save(ticketNuevo);
	}

	@Override
	public List<Ticket> selectAll() {
		return ticketRepository.findAll();
	}
	@Override
	public Ticket updateOperario(TicketDTO ticketDetails) {
		Ticket ticketNuevo  = ticketRepository.findById(ticketDetails.getId()).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
		ticketNuevo.setUsuario(usuarioRepository.findById(ticketDetails.getUsuario()).orElseThrow());
		ticketNuevo.setEstado(estadoRepository.findByNombre("ASIGNADO"));
		return ticketRepository.save(ticketNuevo);
}
	@Override
	public Ticket updateOperarioEstado(TicketDTO ticketDetails) {
		Ticket ticketNuevo  = ticketRepository.findById(ticketDetails.getId()).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
		ticketNuevo.setEstado(estadoRepository.findById(ticketDetails.getEstado()).orElseThrow());
		ticketNuevo.setEvidencia(ticketDetails.getEvidencia());
		return ticketRepository.save(ticketNuevo);
	}

	@Override
	public Ticket buscarPorId(Long id) {
		return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
	}
	
	@Override
	public Ticket update(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
