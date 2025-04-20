package cl.ms.mascotas.repository;

import cl.ms.mascotas.entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
}
