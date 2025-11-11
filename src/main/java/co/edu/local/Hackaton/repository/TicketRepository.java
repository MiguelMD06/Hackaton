package co.edu.local.Hackaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.local.Hackaton.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
 
}
