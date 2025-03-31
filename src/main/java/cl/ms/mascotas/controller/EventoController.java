package cl.ms.mascotas.controller;

import cl.ms.mascotas.dto.EventoDtoRp;
import cl.ms.mascotas.dto.EventoDtoRq;
import cl.ms.mascotas.dto.ParticipantesDtoRq;
import cl.ms.mascotas.service.EventoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.evento.base.url}")
public class EventoController {
    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<EventoDtoRp> obtenerEventos() {
        return eventoService.obtenerEventos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDtoRp> obtenerEventoById(@Min(1) @PathVariable int id) {
        return eventoService.obtenerEventoById(id);
    }

    @PostMapping
    public ResponseEntity<EventoDtoRp> agregarEvento(@Valid @RequestBody EventoDtoRq rq) {
        return eventoService.agregarEvento(rq);
    }

    @PostMapping("/{id}")
    public ResponseEntity<EventoDtoRp> agregarParticipante(@Min(1) @PathVariable int id, @Valid @RequestBody ParticipantesDtoRq rq) {
        return eventoService.agregarParticipante(id, rq);
    }
}
