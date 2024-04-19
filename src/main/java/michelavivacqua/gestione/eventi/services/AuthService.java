package michelavivacqua.gestione.eventi.services;

import michelavivacqua.gestione.eventi.entities.Utente;
import michelavivacqua.gestione.eventi.exceptions.UnauthorizedException;
import michelavivacqua.gestione.eventi.payloads.UtenteLoginDTO;
import michelavivacqua.gestione.eventi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtentiService utentiService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(UtenteLoginDTO payload){

        Utente utente = this.utentiService.findByEmail(payload.email());
        // Verifico se la password combacia con quella ricevuta nel payload
        if (bcrypt.matches(payload.password(), utente.getPassword())) {
            // Genero un token e lo torno
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non valide! Impossibile accedere");
        }


    }
}
