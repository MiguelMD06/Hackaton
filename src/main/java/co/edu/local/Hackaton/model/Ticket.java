package co.edu.local.Hackaton.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Nonnull
	@Column(name = "tk_descripcion", nullable = false)
	private String descripcion;
	
	@Nonnull
	@Column(name = "tk_ubicacion", nullable = false)
	private String ubicacion;
	
	@Nonnull
	@Column(name = "tk_dependencia", nullable = false)
	private String dependencia;
	
	@Nonnull
	@Column(name = "fch_solicitud", nullable = false)
	private LocalDateTime fechaSolicitud;
	
	@Column(name = "fch_cierre")
	private LocalDateTime fechaCierre;
	
	@Column(name = "tk_evidencias")
	private String evidencia;
	
	@Column(name = "tk_prioridad")
	private int prioridad;
		
	@Nonnull
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH},
    fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category categoria;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH},
	fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@Nonnull
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH},
	fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_id")
	private Estado estado;

	public Ticket(String descripcion, String ubicacion, String dependencia, String evidencia, Category categoria) {
		super();
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
		this.dependencia = dependencia;
		this.evidencia = evidencia;
		this.categoria = categoria;
	}
	
	

	public Ticket(String descripcion, String ubicacion, String dependencia, LocalDateTime fechaSolicitud,
			LocalDateTime fechaCierre, String evidencia, Category categoria, Estado estado) {
		super();
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
		this.dependencia = dependencia;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaCierre = fechaCierre;
		this.evidencia = evidencia;
		this.categoria = categoria;
		this.estado = estado;
	}



	public Ticket(Long id, Estado estado) {
		super();
		this.id = id;
		this.estado = estado;
	}
	
	
}
