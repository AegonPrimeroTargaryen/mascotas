package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("nombre")
    private String nombre;


    @JsonProperty("tipo")
    private String tipo;

    @JsonProperty("edad")
    private int edad;

    @JsonProperty("duenio")
    private String  duenio;
}
