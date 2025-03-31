package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDto{
    @JsonProperty("id")
    private int id;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("tipoEvento")
    private String tipoEvento;

    @JsonProperty("fecha")
    private String fecha;

    @JsonProperty("cantParticipantes")
    private int cantParticipantes;

    @JsonProperty("participantes")
    private List<MascotaDto> participantes;

    @JsonProperty("direccion")
    private DireccionDto direccion;
}
