package cl.ms.mascotas.controller;

import cl.ms.mascotas.dto.MascotaDtoRp;
import cl.ms.mascotas.dto.MascotaDtoRq;
import cl.ms.mascotas.service.MascotaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.mascota.base.url}")
public class MascotaController {

    private final MascotaService mascotaService;

    @Autowired
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public ResponseEntity<MascotaDtoRp> getMascotas() {
        return mascotaService.getMascotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDtoRp> getMascotaById(@Min(1) @PathVariable int id) {
        return mascotaService.getMascotaById(id);
    }

    @PostMapping
    public ResponseEntity<MascotaDtoRp> agregarMascota(@Valid @RequestBody MascotaDtoRq rq) {
        return mascotaService.agregarMascota(rq);
    }
}
