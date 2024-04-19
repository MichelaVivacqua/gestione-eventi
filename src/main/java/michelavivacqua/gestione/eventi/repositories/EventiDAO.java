package michelavivacqua.gestione.eventi.repositories;

import michelavivacqua.gestione.eventi.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventiDAO extends JpaRepository<Evento, Integer> {
    boolean existsByTitolo(String titolo);
    Optional<Evento> findByTitolo(String titolo);
//    @Query("SELECT e FROM Evento e JOIN e.prenotazioni p WHERE p.utente.id = :utenteId")
//    List<Evento> findEventiByUtenteId(int utenteId);
}
