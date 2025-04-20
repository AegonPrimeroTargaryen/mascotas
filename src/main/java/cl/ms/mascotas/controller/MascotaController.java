package cl.ms.mascotas.controller;

import cl.ms.mascotas.dto.MascotaDto;
import cl.ms.mascotas.dto.MascotaDtoRp;
import cl.ms.mascotas.dto.MascotaDtoRq;
import cl.ms.mascotas.service.MascotaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("${api.mascota.base.url}")
public class MascotaController {

    private final MascotaService mascotaService;

    @Autowired
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<MascotaDtoRp> getMascotas() {
        log.info("Controller - getMascotas()");
        return ResponseEntity.ok(new MascotaDtoRp("00","OK",mascotaService.getMascotas()));
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MascotaDtoRp> getMascotaById(@Min(1) @PathVariable int id) {
        log.info("Controller - getMascotasById()");
        return ResponseEntity.ok(new MascotaDtoRp("00","OK",mascotaService.getMascotaById(id)));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<MascotaDtoRp> createMascota(@Valid @RequestBody MascotaDtoRq rq) {
        log.info("Controller - createMascota()");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MascotaDtoRp("00","OK",mascotaService.createMascota(rq)));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MascotaDtoRp> actualizarMascota(@Min(1) @PathVariable int id, @Valid @RequestBody MascotaDtoRq rq) {
        log.info("Controller - actualizarMascota()");
        return ResponseEntity.ok(new MascotaDtoRp("00","OK",mascotaService.updateMascota(id,rq)));
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MascotaDto> eliminarMascota(@Min(1) @PathVariable int id) {
        log.info("Controller - eliminarMascota()");
        mascotaService.eliminarMascota(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
