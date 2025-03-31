package cl.ms.mascotas.service;

import cl.ms.mascotas.dto.MascotaDto;
import cl.ms.mascotas.dto.MascotaDtoRp;
import cl.ms.mascotas.dto.MascotaDtoRq;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    private int generarId = 4;

    private List<MascotaDto> mascotas = new ArrayList<>();

    public MascotaService(){
        this.mascotas.add(new MascotaDto(1, "Betoven", "Perro", 15, "Camila"));
        this.mascotas.add(new MascotaDto(2, "Rocky", "Perro", 5, "Alonso"));
        this.mascotas.add(new MascotaDto(3, "Thanos", "Perro", 4, "David"));
    }

    public ResponseEntity<MascotaDtoRp> getMascotas() {
        return ResponseEntity.ok(new MascotaDtoRp("00","OK",mascotas));
    }

    public ResponseEntity<MascotaDtoRp> getMascotaById(Integer id) {
        List<MascotaDto> newList = new ArrayList<>();
        Optional<MascotaDto> result = mascotas.stream().filter(m -> m.getId() == id).findFirst();
        result.ifPresent(newList::add);
        return ResponseEntity.ok(new MascotaDtoRp("00","OK",newList));
    }

    public ResponseEntity<MascotaDtoRp> agregarMascota(MascotaDtoRq rq) {
        int idMascota = generarId++;
        MascotaDto mascotaDto = new MascotaDto(idMascota, rq.getNombre(), rq.getTipo(), rq.getEdad(), rq.getTipo());
        mascotas.add(mascotaDto);
        List<MascotaDto> newList = new ArrayList<>();
        newList.add(mascotaDto);
        return ResponseEntity.ok(new MascotaDtoRp("00","OK",newList));
    }
}
