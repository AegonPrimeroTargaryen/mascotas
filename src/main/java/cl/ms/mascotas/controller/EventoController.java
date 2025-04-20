package cl.ms.mascotas.controller;

import cl.ms.mascotas.dto.EventoDtoRp;
import cl.ms.mascotas.dto.EventoDtoRq;
import cl.ms.mascotas.dto.ParticipantesDtoRq;
import cl.ms.mascotas.service.EventoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("${api.evento.base.url}")
public class EventoController {
    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<EventoDtoRp> obtenerEventos() {
        log.info("Controller - obtenerEventos()");
        return ResponseEntity.ok(new EventoDtoRp("00","OK",eventoService.obtenerEventos()));
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EventoDtoRp> obtenerEventoById(@Min(1) @PathVariable int id) {
        log.info("Controller - obtenerEventoById()");
        return ResponseEntity.ok(new EventoDtoRp("00","OK",eventoService.obtenerEventoById(id)));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<EventoDtoRp> agregarEvento(@Valid @RequestBody EventoDtoRq rq) {
        log.info("Controller - agregarEvento()");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new EventoDtoRp("00","OK",eventoService.agregarEvento(rq)));
    }

    @PostMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EventoDtoRp> agregarParticipante(@Min(1) @PathVariable int id, @Valid @RequestBody ParticipantesDtoRq rq) {
        log.info("Controller - agregarParticipante()");
        return ResponseEntity.ok(new EventoDtoRp("00","OK",eventoService.agregarParticipante(id, rq)));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EventoDtoRp> actualizarEvento(@Min(1) @PathVariable int id, @Valid @RequestBody EventoDtoRq rq) {
        log.info("Controller - actualizarEvento()");
        return ResponseEntity.ok(new EventoDtoRp("00","OK",eventoService.updateEvento(id,rq)));
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EventoDtoRp> eliminarEvento(@Min(1) @PathVariable int id) {
        log.info("Controller - eliminarEvento()");
        eventoService.eliminarEvento(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
