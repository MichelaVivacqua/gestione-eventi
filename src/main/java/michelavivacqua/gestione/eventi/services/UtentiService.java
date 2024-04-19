package michelavivacqua.gestione.eventi.services;

import michelavivacqua.gestione.eventi.entities.Utente;
import michelavivacqua.gestione.eventi.exceptions.BadRequestException;
import michelavivacqua.gestione.eventi.exceptions.NotFoundException;
import michelavivacqua.gestione.eventi.payloads.NewUtenteDTO;
import michelavivacqua.gestione.eventi.repositories.UtentiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtentiService {

    @Autowired
    private UtentiDAO utentiDAO;


    public UtentiService(UtentiDAO utentiDAO) {
        this.utentiDAO = utentiDAO;
    }

    public List<Utente> getUtentiList() {
        return utentiDAO.findAll();
    }



    public Utente saveUtente(NewUtenteDTO newUtenteDTO) {

        if (utentiDAO.existsByEmail(newUtenteDTO.email())) {
            throw new BadRequestException("L'email " + newUtenteDTO.email() + " è già in uso, quindi l'utente ha già un account! Contatta l'assistenza se hai dimenticato la tua password");
        }

        Utente utente = new Utente(newUtenteDTO.username(), newUtenteDTO.nome(), newUtenteDTO.cognome(), newUtenteDTO.email(), newUtenteDTO.password(), newUtenteDTO.ruolo());
//        System.out.println(utente);
        return utentiDAO.save(utente);
    }


    public Utente findById(int id) {
        return utentiDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Utente findByIdAndUpdate(int id, Utente updatedUtente) {
        Utente found = findById(id);
        found.setNome(updatedUtente.getNome());
        found.setCognome(updatedUtente.getCognome());
        return utentiDAO.save(found);
    }

    public void findByIdAndDelete(int utenteId) {
       utentiDAO.deleteById(utenteId);
    }


    public Page<Utente> getUtenti(int page, int size, String sortBy){
        if(size > 70) size = 70;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utentiDAO.findAll(pageable);
    }

    public Utente findByEmail(String email) {
        return utentiDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }


}
