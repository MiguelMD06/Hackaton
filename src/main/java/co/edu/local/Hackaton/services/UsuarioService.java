package co.edu.local.Hackaton.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import co.edu.local.Hackaton.model.Usuario;


public interface UsuarioService extends UserDetailsService{
	
	public List<Usuario> listarOperadores();
	

}
