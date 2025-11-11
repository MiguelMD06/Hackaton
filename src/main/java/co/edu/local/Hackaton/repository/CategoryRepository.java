package co.edu.local.Hackaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.local.Hackaton.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	public Category findByNombre(String nombre);
}
