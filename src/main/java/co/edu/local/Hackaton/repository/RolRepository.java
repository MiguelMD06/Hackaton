package co.edu.local.Hackaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.local.Hackaton.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

	public Rol findByNombre(String nombre);
}
