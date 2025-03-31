package cl.ms.mascotas.service;

import cl.ms.mascotas.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EventoService {
    private int idEvento = 1;

    private final List<EventoDto> eventos = new ArrayList<>();

    private final MascotaService mascotaService;

    public EventoService(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    public ResponseEntity<EventoDtoRp> obtenerEventos() {
        return ResponseEntity.ok(new EventoDtoRp("00","OK",eventos));
    }

    public ResponseEntity<EventoDtoRp> agregarEvento(EventoDtoRq rq) {
        int generarId = idEvento++;
        EventoDto eventoDto = new EventoDto();
        eventoDto.setId(generarId);
        eventoDto.setNombre(rq.getNombre());
        eventoDto.setTipoEvento(rq.getTipoEvento());
        eventoDto.setFecha(rq.getFecha());
        eventoDto.setCantParticipantes(rq.getCantParticipantes());
        eventoDto.setDireccion(rq.getDireccion());
        eventos.add(eventoDto);
        List<EventoDto> eventosDto = new ArrayList<>();
        eventosDto.add(eventoDto);

        return ResponseEntity.ok(new EventoDtoRp("00","OK",eventosDto));
    }

    public ResponseEntity<EventoDtoRp> agregarParticipante(int idEvento, ParticipantesDtoRq rq) {
        EventoDtoRp eventoDto = new EventoDtoRp();
        if (eventos.stream().noneMatch(evento -> evento.getId() == idEvento)){
            eventoDto.setCodigo("00");
            eventoDto.setDetalle("Evento no encontrado");
        } else {
            List<MascotaDto> mascotasDto = new ArrayList<>();
            List<EventoDto> eventosDto = new ArrayList<>();
            eventos.forEach(evento -> {
               if (evento.getId() == idEvento){
                   rq.getParticipantes().forEach(id ->
                       Objects.requireNonNull(mascotaService.getMascotas().getBody()).getData().stream()
                               .filter(dato -> dato.getId() == id).findFirst().ifPresent(mascotasDto::add)
                   );
                   evento.setParticipantes(mascotasDto);
                   eventosDto.add(evento);
               }
            });
            eventoDto.setCodigo("00");
            eventoDto.setDetalle("OK");
            eventoDto.setEventos(eventosDto);
        }

        return ResponseEntity.ok(eventoDto);
    }

    public ResponseEntity<EventoDtoRp> obtenerEventoById(int idEvento) {
        List<EventoDto> eventosDto = new ArrayList<>();
        EventoDtoRp eventoDtoRp = new EventoDtoRp();
        eventos.stream().filter(evento -> evento.getId() == idEvento).findFirst().ifPresent(eventosDto::add);
        eventoDtoRp.setCodigo("00");
        eventoDtoRp.setDetalle("OK");
        eventoDtoRp.setEventos(eventosDto);
        return ResponseEntity.ok(eventoDtoRp);
    }
}
