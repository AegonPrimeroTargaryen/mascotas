package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDtoRp extends ResponseDto{

    @JsonProperty("error")
    private Object error;

    public ErrorDtoRp(String codigo, String status, Object errors) {
        super(codigo, status);
        this.error = errors;
    }

    public ErrorDtoRp(String codigo, String status) {
        super(codigo, status);
    }
}
