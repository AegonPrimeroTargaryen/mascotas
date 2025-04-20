package cl.ms.mascotas.repository;

import cl.ms.mascotas.entity.MascotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<MascotaEntity, Long> {
}
