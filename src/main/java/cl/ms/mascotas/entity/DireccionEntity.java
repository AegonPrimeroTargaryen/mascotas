package cl.ms.mascotas.entity;

import cl.ms.mascotas.dto.DireccionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "DIRECCION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DIRECCION")
    private long id;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "COMUNA")
    private String comuna;

    @Column(name = "REGION")
    private String region;

    public DireccionDto toDto() {
        return new DireccionDto(direccion, comuna, region);
    }
}
