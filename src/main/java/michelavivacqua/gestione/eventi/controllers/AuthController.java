package michelavivacqua.gestione.eventi.controllers;

import michelavivacqua.gestione.eventi.exceptions.BadRequestException;
import michelavivacqua.gestione.eventi.payloads.NewUtenteDTO;
import michelavivacqua.gestione.eventi.payloads.NewUtenteRespDTO;
import michelavivacqua.gestione.eventi.payloads.UtenteLoginDTO;
import michelavivacqua.gestione.eventi.payloads.UtenteLoginRespDTO;
import michelavivacqua.gestione.eventi.services.AuthService;
import michelavivacqua.gestione.eventi.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UtentiService utentiService;

    @PostMapping("/login")
    public UtenteLoginRespDTO login(@RequestBody UtenteLoginDTO payload){
        return new UtenteLoginRespDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO saveUser(@RequestBody @Validated NewUtenteDTO body, BindingResult validation){
        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
       return new NewUtenteRespDTO(this.utentiService.saveUtente(body).getId());
    }

}
