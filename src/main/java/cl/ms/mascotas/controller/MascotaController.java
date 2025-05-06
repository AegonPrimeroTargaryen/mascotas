package cl.ms.mascotas.controller;

import cl.ms.mascotas.dto.MascotaDto;
import cl.ms.mascotas.dto.MascotaDtoRp;
import cl.ms.mascotas.dto.MascotaDtoRq;
import cl.ms.mascotas.service.MascotaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/mascotas-api/v1")
public class MascotaController {

    private final MascotaService mascotaService;

    @Autowired
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public EntityModel<MascotaDtoRp> getMascotas() {
        log.info("Controller - getMascotas()");
//        return ResponseEntity.ok(new MascotaDtoRp("00","OK",mascotaService.getMascotas()));
        return EntityModel.of(
                new MascotaDtoRp("00","OK",mascotaService.getMascotas()),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMascotas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public EntityModel<MascotaDtoRp> getMascotaById(@Min(1) @PathVariable int id) {
        log.info("Controller - getMascotasById()");
//        return ResponseEntity.ok(new MascotaDtoRp("00","OK",mascotaService.getMascotaById(id)));
        return EntityModel.of(
                new MascotaDtoRp("00","OK",mascotaService.getMascotaById(id)),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMascotaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMascotas()).withRel("all-mascotas")
        );
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public EntityModel<MascotaDtoRp> createMascota(@Valid @RequestBody MascotaDtoRq rq) {
        log.info("Controller - createMascota()");
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(new MascotaDtoRp("00","OK",mascotaService.createMascota(rq)));
        return EntityModel.of(
                new MascotaDtoRp("00","OK",mascotaService.createMascota(rq)),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).createMascota(rq)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMascotas()).withRel("all-mascotas")
        );
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public EntityModel<MascotaDtoRp> actualizarMascota(@Min(1) @PathVariable int id, @Valid @RequestBody MascotaDtoRq rq) {
        log.info("Controller - actualizarMascota()");
//        return ResponseEntity.ok(new MascotaDtoRp("00","OK",mascotaService.updateMascota(id,rq)));
        return EntityModel.of(
                new MascotaDtoRp("00","OK",mascotaService.updateMascota(id,rq)),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).actualizarMascota(id,rq)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMascotas()).withRel("all-mascotas")
        );
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MascotaDto> eliminarMascota(@Min(1) @PathVariable int id) {
        log.info("Controller - eliminarMascota()");
        mascotaService.eliminarMascota(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
