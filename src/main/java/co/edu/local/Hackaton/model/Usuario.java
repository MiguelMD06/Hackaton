package co.edu.local.Hackaton.model;

import java.util.Collection;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Nonnull
	@Column(name = "user_nombre", unique = true, nullable = false)
	private String nombre;
	
	@Nonnull
	@Column(name = "user_contrasena", nullable = false)
	private String contrasena;
	
	@Column(name = "user_habilidad")
	private String habilidad;
	
	@Column(name = "user_ubicacion")
	private String ubicacion;
	
	
	@Nonnull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "usuarios_roles",
				joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
			)
	private Collection<Rol> rol;


	public Usuario(String nombre, String contrasena, Collection<Rol> rol) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.rol = rol;
	}


	public Usuario(String nombre, String contrasena) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
	}
	
	
	
}
