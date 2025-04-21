package cl.ms.mascotas.entity;

import cl.ms.mascotas.dto.EventoDto;
import cl.ms.mascotas.dto.MascotaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "EVENTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TIPO_EVENTO")
    private String tipoEvento;

    @Column(name = "FECHA")
    private String fecha;

    @Column(name = "DETALLE")
    private String detalle;

    @Column(name = "CANTIDAD_PARTICIPANTES")
    private int cantidadParticipantes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_DIRECCION")
    private DireccionEntity direccion;

    @ManyToMany
    @JoinTable(
            name = "EVENTO_MASCOTA",
            joinColumns = @JoinColumn(name = "ID_EVENTO"),
            inverseJoinColumns = @JoinColumn(name = "ID_MASCOTA")
    )
    private List<MascotaEntity> mascotas;

    public EventoDto toDto() {
        List<MascotaDto> mascotasDto = new ArrayList<>();
        if(this.mascotas != null) mascotas.forEach(mascota -> mascotasDto.add(mascota.toDto()));
        return new EventoDto(id==null?0:id.intValue(),nombre,tipoEvento,fecha,detalle
                ,cantidadParticipantes,mascotasDto,direccion.toDto());
    }
}
