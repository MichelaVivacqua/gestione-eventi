package michelavivacqua.gestione.eventi.payloads;

import jakarta.validation.constraints.*;
import michelavivacqua.gestione.eventi.enums.Ruolo;

import java.time.LocalDate;

public record NewEventoDTO(
        @NotEmpty(message = "Il titolo è obbligatorio")
        @Size(min = 3, max = 30, message = "Il titolo deve essere compreso tra gli 3 e i 30 caratteri")
        String titolo,
        @NotEmpty(message = "La descrizione è obbligatoria")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra i 3 e i 30 caratteri")
        String descrizione,
        @NotNull(message = "La data è obbligatoria")
        LocalDate data,
        @NotEmpty(message = "La location è obbligatoria")
        String location,
//        @NotEmpty(message = "La capienza è obbligatoria, ha un valore minimo di 10 e massimo di 1000")
        @Min(10)
        @Max(1000)
        int capienza
) {
}
