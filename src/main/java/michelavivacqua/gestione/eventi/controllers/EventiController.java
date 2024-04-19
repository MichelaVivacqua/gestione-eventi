package michelavivacqua.gestione.eventi.controllers;

import michelavivacqua.gestione.eventi.entities.Evento;
import michelavivacqua.gestione.eventi.exceptions.BadRequestException;
import michelavivacqua.gestione.eventi.payloads.NewEventoDTO;
import michelavivacqua.gestione.eventi.payloads.NewEventoRespDTO;
import michelavivacqua.gestione.eventi.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventiController {
    @Autowired
    private EventiService eventiService;

    //    1. POST http://localhost:3001/eventi (+ body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewEventoRespDTO saveEvento(@RequestBody @Validated NewEventoDTO body, BindingResult validation){

        if(validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
//        System.out.println(body);
        return new NewEventoRespDTO(this.eventiService.saveEvento(body).getId());}




    // 2. GET http://localhost:3001/eventi/{{eventoId}}
    @GetMapping("/{eventoId}")
    private Evento findEventoById(@PathVariable int eventoId){
        return this.eventiService.findById(eventoId);
    }

    //    3. GET http://localhost:3001/eventi
    @GetMapping
    private List<Evento> getAllEvento(){
        return this.eventiService.getEventiList();
    }

    //    3.1 Paginazione e ordinamento http://localhost:3001/eventi/page
    @GetMapping("/page")
    public Page<Evento> getAllEventi(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "1") int size,
                                          @RequestParam(defaultValue = "id") String sortBy) {
        return this.eventiService.getDispositivi(page, size, sortBy);
    }

    // 4. PUT http://localhost:3001/eventi/{{eventoId}} (+ body)
    @PutMapping("/{eventoId}")
    private Evento findByIdAndUpdate(@PathVariable int eventoId, @RequestBody Evento body){
        return this.eventiService.findByIdAndUpdate(eventoId, body);
    }



    // 5. DELETE http://localhost:3001/eventi/{eventoId}
    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEventoById(@PathVariable int eventoId) {
        this.eventiService.findByIdAndDelete(eventoId);
    }
}
