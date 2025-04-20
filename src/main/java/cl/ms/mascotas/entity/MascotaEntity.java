package cl.ms.mascotas.entity;

import cl.ms.mascotas.dto.MascotaDto;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MASCOTA")
public class MascotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MASCOTA")
    private Long idMascota;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "EDAD")
    private int edad;

    @Column(name = "DUENIO")
    private String duenio;

    public MascotaDto toDto() {
        MascotaDto mascotaDto = new MascotaDto();
        mascotaDto.setId(idMascota==null?0:idMascota.intValue());
        mascotaDto.setNombre(nombre);
        mascotaDto.setTipo(tipo);
        mascotaDto.setEdad(edad);
        mascotaDto.setDuenio(duenio);
        return mascotaDto;
    }
}
