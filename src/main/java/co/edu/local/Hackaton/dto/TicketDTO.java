package co.edu.local.Hackaton.dto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.local.Hackaton.model.Category;
import co.edu.local.Hackaton.model.Estado;
import co.edu.local.Hackaton.model.Usuario;
import co.edu.local.Hackaton.repository.EstadoRepository;
import co.edu.local.Hackaton.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
	

	private Long id;
	
	private String descripcion;
	
	private String ubicacion;
	
	private String dependencia;
	
	private LocalDateTime fechaSolicitud;
	
	private LocalDateTime fechaCierre;
	
	private String evidencia;
	
	private int prioridad;
		
	private Category categoria;
	
	private Long usuario;
	
	private Long estado;

	public TicketDTO(Long id, int prioridad) {
		super();
		this.id = id;
		this.prioridad = prioridad;
	}

	public TicketDTO(Long id, Long usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}

	public TicketDTO(Long estado) {
		super();
		this.estado = estado;
	}
	
	
	
	
}
