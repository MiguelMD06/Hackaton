package co.edu.local.Hackaton.services;


import java.time.LocalDateTime;
import java.util.List;

import co.edu.local.Hackaton.dto.TicketDTO;
import co.edu.local.Hackaton.model.Ticket;

public interface TicketService {

	public Ticket crear(Ticket ticket);
	public Ticket update(Ticket ticket);
	public Ticket updateEstado(TicketDTO ticket);
	public Ticket updateValidar(TicketDTO ticket);
	public Ticket updatePrioridad(TicketDTO ticket);
	public Ticket updateOperario(TicketDTO ticket);
	public Ticket updateOperarioEstado(TicketDTO ticket);
	public List<Ticket> selectAll();
	public Ticket buscarPorId(Long id);
	
}
