package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DireccionDto {
    @NotNull(message = "Direccion no puede ser nullo")
    @NotEmpty(message = "Direccion no puede estar vacio")
    @Size(min = 5,message = "Direccion demaciado corto")
    @JsonProperty("direccion")
    private String direccion;


    @NotNull(message = "Comuna no puede ser nullo")
    @NotEmpty(message = "Comuna no puede estar vacio")
    @Size(min = 5,message = "Comuna demaciado corto")
    @JsonProperty("comuna")
    private String comuna;


    @NotNull(message = "Region no puede ser nullo")
    @NotEmpty(message = "Region no puede estar vacio")
    @Size(min = 5,message = "Region demaciado corto")
    @JsonProperty("region")
    private String region;
}
