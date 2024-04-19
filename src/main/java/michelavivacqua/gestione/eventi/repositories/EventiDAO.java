package michelavivacqua.gestione.eventi.repositories;

import michelavivacqua.gestione.eventi.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventiDAO extends JpaRepository<Evento, Integer> {
    boolean existsByTitolo(String titolo);
    Optional<Evento> findByTitolo(String titolo);

}
