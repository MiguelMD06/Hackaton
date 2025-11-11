package co.edu.local.Hackaton.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import co.edu.local.Hackaton.services.UsuarioService;


@Configuration
@EnableWebSecurity
public class SpringSecurity{

		@Autowired
		private UsuarioService usuarioServicio;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
			auth.setUserDetailsService(usuarioServicio);
			auth.setPasswordEncoder(passwordEncoder);
			return auth;
		}
		
		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authenticationProvider(authenticationProvider()) 
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/css/**", "/js/**").permitAll() 
	                .requestMatchers("/solicitante").hasRole("SOLICITANTE")
	                .requestMatchers("/mantenimiento/**").hasRole("ODM")
	                .requestMatchers("/jefe/**").hasRole("JODM")
	                .requestMatchers("/operador/**").hasRole("OPERARIO")
	                .anyRequest().authenticated()                                  
	            )
	            .formLogin(form -> form
	                    .loginPage("/login")
	                    .successHandler((request, response, authentication) -> {
	                        authentication.getAuthorities().forEach(auth -> {
	                            try {
	                                if (auth.getAuthority().equals("ROLE_SOLICITANTE")) {
	                                    response.sendRedirect("/solicitante");
	                                } else if (auth.getAuthority().equals("ROLE_ODM")) {
	                                    response.sendRedirect("/mantenimiento");
	                                } else if (auth.getAuthority().equals("ROLE_JODM"))  {
	                                     response.sendRedirect("/jefe");
	                                } else if (auth.getAuthority().equals("ROLE_OPERARIO")){
	                                    response.sendRedirect("/operador");
	                                }
	                            } catch (Exception e) {
	                                throw new RuntimeException(e);
	                            }
	                        });
	                    })
	                    .permitAll()
	            )
	            .logout(logout -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login?logout")
	                .permitAll()
	            ).exceptionHandling(exception -> exception.accessDeniedPage("/403"));

	        return http.build();
	    }
}
