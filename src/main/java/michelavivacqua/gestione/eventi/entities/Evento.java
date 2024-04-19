package michelavivacqua.gestione.eventi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String location;
    private int capienza;
//    @ManyToMany(mappedBy = "prenotazioni")
//    private List<Utente> utenti = new ArrayList<>();

    public Evento(String titolo, String descrizione, LocalDate data, String location, int capienza) {
        this.titolo=titolo;
        this.descrizione=descrizione;
        this.data=data;
        this.location=location;
        this.capienza=capienza;
    }

//    public Evento(String titolo, String descrizione, LocalDate data, String location, int capienza) {
//        this.titolo=titolo;
//        this.descrizione=descrizione;
//        this.data=data;
//        this.location=location;
//        this.capienza=capienza;
//        this.utenti = new ArrayList<>();
//    }



//    public List<Utente> getPrenotazioni() {
//        return utenti;
//    }

}
