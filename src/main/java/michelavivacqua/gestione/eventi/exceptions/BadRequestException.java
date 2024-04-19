package michelavivacqua.gestione.eventi.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException{
    private List<ObjectError> errorsList;
    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(List<ObjectError> errorsList){
        super("Errore! Tutti i campi sono obbligatori; Username, nome e cognome devono avere tra i 3 e gli 8 caratteri, e il ruolo può essere ORGANIZZATORE o PARTECIPANTE");
        this.errorsList = errorsList;
    }
}
