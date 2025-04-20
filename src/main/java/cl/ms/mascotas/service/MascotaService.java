package cl.ms.mascotas.service;

import cl.ms.mascotas.dto.MascotaDto;
import cl.ms.mascotas.dto.MascotaDtoRq;

import java.util.List;

public interface MascotaService {
    List<MascotaDto> getMascotas();
    List<MascotaDto> getMascotaById(Integer id);
    List<MascotaDto> createMascota(MascotaDtoRq rq);
    List<MascotaDto> updateMascota(int id, MascotaDtoRq rq);
    void eliminarMascota(int id);
}
