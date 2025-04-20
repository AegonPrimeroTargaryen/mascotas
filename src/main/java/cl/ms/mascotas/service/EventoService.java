package cl.ms.mascotas.service;

import cl.ms.mascotas.dto.EventoDto;
import cl.ms.mascotas.dto.EventoDtoRp;
import cl.ms.mascotas.dto.EventoDtoRq;
import cl.ms.mascotas.dto.ParticipantesDtoRq;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventoService {
    List<EventoDto> obtenerEventos();
    List<EventoDto> agregarEvento(EventoDtoRq rq);
    List<EventoDto> agregarParticipante(int idEvento, ParticipantesDtoRq rq);
    List<EventoDto> obtenerEventoById(int idEvento);
    List<EventoDto> updateEvento(int idEvento, EventoDtoRq rq);
    void eliminarEvento(int idEvento);
}
