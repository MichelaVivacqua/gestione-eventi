package michelavivacqua.gestione.eventi.repositories;

import michelavivacqua.gestione.eventi.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtentiDAO extends JpaRepository<Utente, Integer> {
    boolean existsByEmail(String email);
    Optional<Utente> findByEmail(String nome);
}
