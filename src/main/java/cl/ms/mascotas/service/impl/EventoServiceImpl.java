package cl.ms.mascotas.service.impl;

import cl.ms.mascotas.dto.*;
import cl.ms.mascotas.entity.EventoEntity;
import cl.ms.mascotas.entity.MascotaEntity;
import cl.ms.mascotas.exception.EventoNotFoundException;
import cl.ms.mascotas.repository.EventoRepository;
import cl.ms.mascotas.service.EventoService;
import cl.ms.mascotas.service.MascotaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EventoServiceImpl implements EventoService {
    private final EventoRepository eventoRepository;

    private final MascotaService mascotaService;

    @Autowired
    public EventoServiceImpl(EventoRepository eventoRepository, MascotaService mascotaService) {
        this.eventoRepository = eventoRepository;
        this.mascotaService = mascotaService;
    }

    @Override
    public List<EventoDto> obtenerEventos() {
        log.info("Service - obtenerEventos() - init");
        List<EventoDto> eventosDto = new ArrayList<>();
        eventoRepository.findAll().forEach(evento -> eventosDto.add(evento.toDto()));
        setLogSizeEventos(eventosDto.size());
        log.info("Service - obtenerEventos() - end");
        return eventosDto;
    }

    @Override
    public List<EventoDto> agregarEvento(EventoDtoRq rq) {
        log.info("Service - agregarEvento() - init");
        List<EventoDto> eventosDto = new ArrayList<>();
        eventosDto.add(eventoRepository.save(rq.toEntity()).toDto());
        log.info("Service - agregarEvento() - end");
        return eventosDto;
    }

    @Override
    public List<EventoDto> agregarParticipante(int idEvento, ParticipantesDtoRq rq) {
        Optional<EventoEntity> eventoEntity = eventoRepository.findById((long) idEvento);
        List<EventoDto> eventosDto = new ArrayList<>();
        if (eventoEntity.isEmpty()) {
            throw new EventoNotFoundException("Evento no encontrado", "01", "NOK");
        } else {
            List<MascotaEntity> mascotaEntities = new ArrayList<>();
            rq.getParticipantes().forEach(item -> {
                List<MascotaDto> result = mascotaService.getMascotaById(item);
                if (!result.isEmpty()) mascotaEntities.add(result.getFirst().toEntityUpdate());
            });
            if (!mascotaEntities.isEmpty()){
                eventoEntity.get().setMascotas(mascotaEntities);
                eventoRepository.save(eventoEntity.get());
            }
            eventosDto.add(eventoEntity.get().toDto());
        }

        return eventosDto;
    }

    @Override
    public List<EventoDto> obtenerEventoById(int idEvento) {
        log.info("Service - obtenerEventoById() - init");
        List<EventoDto> eventosDto = new ArrayList<>();
        eventoRepository.findById((long) idEvento).ifPresent(evento -> eventosDto.add(evento.toDto()));
        setLogSizeEventos(eventosDto.size());
        log.info("Service - obtenerEventoById() - end");
        return eventosDto;
    }

    @Override
    public List<EventoDto> updateEvento(int idEvento, EventoDtoRq rq) {
        log.info("Service - updateEvento() - init");
        Optional<EventoEntity> result = eventoRepository.findById((long) idEvento);
        if (result.isEmpty()) {
            throw new EventoNotFoundException("Evento no encontrado", "01", "NOK");
        }
        EventoEntity eventoEntity = result.get();
        List<EventoDto> eventosDto = new ArrayList<>();
        EventoEntity eventoEntityUpdate = rq.updateEntity(eventoEntity);
        eventosDto.add(eventoRepository.save(eventoEntityUpdate).toDto());
        log.info("Service - updateEvento() - end");
        return eventosDto;
    }

    @Override
    public void eliminarEvento(int idEvento){
        log.info("Service - eliminarEvento() - init");
        Optional<EventoEntity> eventoEntity = eventoRepository.findById((long) idEvento);
        if (eventoEntity.isEmpty()) throw new EventoNotFoundException("Evento no encontrado", "01", "NOK");
        eventoRepository.delete(eventoEntity.get());
        log.info("Service - eliminarEvento() - end");
    }

    private void setLogSizeEventos(int size) {
        log.info("registros consultados: {}",size);
    }
}
