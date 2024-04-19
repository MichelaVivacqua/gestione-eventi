package michelavivacqua.gestione.eventi.entities;

import jakarta.persistence.*;
import lombok.*;
import michelavivacqua.gestione.eventi.enums.Ruolo;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Entity
    public class Utente {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String username;
        private String nome;
        private String cognome;
        private String email;
        private String password;
        @Enumerated(EnumType.STRING)
        private Ruolo ruolo;

        public Utente(String username, String nome, String cognome, String email, String password, Ruolo ruolo) {
            this.username=username;
            this.nome=nome;
            this.cognome=cognome;
            this.email=email;
            this.password=password;
            this.ruolo=ruolo;
        }
    }
