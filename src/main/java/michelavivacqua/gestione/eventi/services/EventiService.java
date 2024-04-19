package michelavivacqua.gestione.eventi.services;

import jakarta.transaction.Transactional;
import michelavivacqua.gestione.eventi.entities.Evento;
import michelavivacqua.gestione.eventi.entities.Utente;
import michelavivacqua.gestione.eventi.exceptions.NotFoundException;
import michelavivacqua.gestione.eventi.payloads.NewEventoDTO;
import michelavivacqua.gestione.eventi.repositories.EventiDAO;
import michelavivacqua.gestione.eventi.repositories.UtentiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventiService {
    @Autowired
    private EventiDAO eventiDAO;

    @Autowired
    private UtentiDAO utentiDAO;

    public EventiService(EventiDAO eventiDAO) {
        this.eventiDAO = eventiDAO;
    }

    public List<Evento> getEventiList() {
        return eventiDAO.findAll();
    }



    public Evento saveEvento(NewEventoDTO newEventoDTO) {
        Evento evento = new Evento(newEventoDTO.titolo(),newEventoDTO.descrizione(),newEventoDTO.data(),newEventoDTO.location(),newEventoDTO.capienza());
//        System.out.println(dispositivo);
        return eventiDAO.save(evento);
    }



    public Evento findById(int id) {
        return eventiDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Evento findByIdAndUpdate(int id, Evento updatedEvento) {
        Evento found = findById(id);
        found.setTitolo(updatedEvento.getTitolo());
        found.setDescrizione(updatedEvento.getDescrizione());
        found.setData(updatedEvento.getData());
        found.setLocation(updatedEvento.getLocation());
        found.setCapienza(updatedEvento.getCapienza());
        return eventiDAO.save(found);
    }

    public void findByIdAndDelete(int dispositivoId) {
        eventiDAO.deleteById(dispositivoId);
    }


    public Page<Evento> getDispositivi(int page, int size, String sortBy){
        if(size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.eventiDAO.findAll(pageable);
    }

    @Transactional
    public boolean prenotaPosto(int eventoId) {
        Optional<Evento> optionalEvento = eventiDAO.findById(eventoId);
        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();
            if (evento.getCapienza() > 0) {
                evento.setCapienza(evento.getCapienza() - 1);
                eventiDAO.save(evento);
                return true; // Prenotazione riuscita
            }
        }
        return false; // Prenotazione non riuscita
    }

//    @Transactional
//    public boolean prenotaPosto(int eventoId, Utente utente) {
//        Optional<Evento> optionalEvento = eventiDAO.findById(eventoId);
//        if (optionalEvento.isPresent()) {
//            Evento evento = optionalEvento.get();
//            if (evento.getCapienza() > 0) {
////                evento.getPrenotazioni().add(utente);
//                utente.getPrenotazioni().add(evento);
//                evento.setCapienza(evento.getCapienza() - 1);
//                eventiDAO.save(evento);
//                return true; // Prenotazione riuscita
//            }
//        }
//        return false; // Prenotazione non riuscita
//    }

//    public List<Evento> getEventiPrenotatiDaUtente(int utenteId) {
//        return eventiDAO.findEventiByUtenteId(utenteId);
//    }
//    public void annullaPrenotazione(int utenteId, int eventoId) {
//        Utente utente = utentiDAO.findById(utenteId).orElseThrow(() -> new NotFoundException("Utente non trovato"));
//        Evento evento = eventiDAO.findById(eventoId).orElseThrow(() -> new NotFoundException("Evento non trovato"));
//
//        // Rimuovi la prenotazione dell'utente per l'evento
//        evento.setCapienza(evento.getCapienza() + 1);
//        eventiDAO.save(evento);
//    }

}
