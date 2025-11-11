package co.edu.local.Hackaton.servicesImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.local.Hackaton.model.Rol;
import co.edu.local.Hackaton.model.Usuario;
import co.edu.local.Hackaton.repository.RolRepository;
import co.edu.local.Hackaton.repository.UsuarioRepository;
import co.edu.local.Hackaton.services.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private PasswordEncoder passwordEncode;
	
	@Override
	public List<Usuario> listarOperadores() {
	    return usuarioRepository.findAll()
	            .stream()
	            .filter(usuario -> usuario.getRol()
	                    .stream()
	                    .anyMatch(rol -> rol.getNombre().equals("ROLE_OPERARIO")))
	            .toList();
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNombre(username);
		System.out.println(usuario);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o Password Inválidos");
		}
		
		return new User(usuario.getNombre(),usuario.getContrasena(), mapearAutoridadRoles(usuario.getRol()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
}
