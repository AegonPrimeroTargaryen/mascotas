package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
public class EventoDtoRp extends ResponseDto {
    @JsonProperty("data")
    private List<EventoDto> eventos;

    public EventoDtoRp(String codigo, String status, List<EventoDto> eventos) {
        super(codigo, status);
        this.eventos = eventos;
    }

    public EventoDtoRp(String codigo, String status) {
        super(codigo, status);
    }
}
