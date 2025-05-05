package cl.ms.mascotas.service.impl;

import cl.ms.mascotas.dto.MascotaDto;
import cl.ms.mascotas.dto.MascotaDtoRq;
import cl.ms.mascotas.entity.MascotaEntity;
import cl.ms.mascotas.exception.MascotaNotFoundException;
import cl.ms.mascotas.repository.MascotaRepository;
import cl.ms.mascotas.service.MascotaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;

    @Autowired
    public MascotaServiceImpl(MascotaRepository mascotaRepository){
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public List<MascotaDto> getMascotas() {
        log.info("Service - getMascotas() - init");
        List<MascotaDto> mascotaDtoList = new ArrayList<>();
        mascotaRepository.findAll(Sort.by(Sort.Direction.ASC,"idMascota"))
                .forEach(mascota -> mascotaDtoList.add(mascota.toDto()));
        setLogSizeMascotas(mascotaDtoList.size());
        log.info("Service - getMascotas() - end");
        return mascotaDtoList;
    }

    @Override
    public List<MascotaDto> getMascotaById(Integer id) {
        log.info("Service - getMascotasById() - int");
        List<MascotaDto> mascotaDtoList = new ArrayList<>();
        mascotaRepository.findById((long) id).ifPresent(mascota -> mascotaDtoList.add(mascota.toDto()));
        setLogSizeMascotas(mascotaDtoList.size());
        log.info("Service - getMascotasById() - end");
        return mascotaDtoList;
    }

    @Override
    public List<MascotaDto> createMascota(MascotaDtoRq rq) {
        log.info("Service - agregarMascota() - init");
        List<MascotaDto> mascotaDtoList = new ArrayList<>();
        mascotaDtoList.add(mascotaRepository.save(rq.toEntityInsert()).toDto());
        log.info("Service - agregarMascota() - end");
        return mascotaDtoList;
    }

    @Override
    public List<MascotaDto> updateMascota(int id, MascotaDtoRq rq){
        log.info("Service - updateMascota() - init");
        Optional<MascotaEntity> result = mascotaRepository.findById((long) id);
        if (result.isEmpty()) throw new MascotaNotFoundException("Mascota no encontrada","01","NOK");
        rq.setId(Math.toIntExact(result.get().getIdMascota()));
        List<MascotaDto> mascotaDtoList = new ArrayList<>();
        mascotaDtoList.add(mascotaRepository.save(rq.toEntityUpdate()).toDto());
        log.info("Service - updateMascota() - end");
        return mascotaDtoList;
    }

    @Override
    public void eliminarMascota(int id) {
        log.info("Service - deleteMascota() - init");
        Optional<MascotaEntity> result = mascotaRepository.findById((long) id);
        if (result.isEmpty()) throw new MascotaNotFoundException("Mascota no encontrada","01","NOK");
        mascotaRepository.deleteById((long) id);
        log.info("Service - deleteMascota() - end");
    }

    private void setLogSizeMascotas(int size) {
        log.info("registros consultados: {}",size);
    }
}
