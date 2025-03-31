package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantesDtoRq {
    @NotNull(message = "No puede ser nullo")
    @NotEmpty(message = "No puede ser vacio")
    @JsonProperty("participantes")
    private List<Integer> participantes;
}
