package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaDtoRq {
    @NotNull(message = "No puede ser nullo")
    @NotEmpty(message = "No puede ser vacio")
    @JsonProperty("nombre")
    private String nombre;

    @NotNull(message = "No puede ser nullo")
    @NotEmpty(message = "No puede ser vacio")
    @Pattern(regexp = "^(Perro|Gato|Otro)$", message = "Solo se permiten: Perro|Gato|Otro")
    @JsonProperty("tipo")
    private String tipo;

    @Min(value = 1, message = "Rango minimo de 1")
    @Max(value = 30, message = "Rango maximo de 30")
    @JsonProperty("edad")
    private int edad;

    @NotNull(message = "No puede ser nullo")
    @NotEmpty(message = "No puede ser vacio")
    @JsonProperty("duenio")
    private String  duenio;
}
