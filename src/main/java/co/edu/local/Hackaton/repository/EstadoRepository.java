package co.edu.local.Hackaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.local.Hackaton.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	public Estado findByNombre(String nombre);
}
