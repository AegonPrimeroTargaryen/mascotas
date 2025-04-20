package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("status")
    private String status;
}
