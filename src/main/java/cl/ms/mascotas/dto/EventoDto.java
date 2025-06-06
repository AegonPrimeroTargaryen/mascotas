package cl.ms.mascotas.dto;

import cl.ms.mascotas.entity.EventoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Nombre no puede ser nullo")
    @NotEmpty(message = "Nombre no puede estar vacio")
    @Size(min = 5,message = "Nombre demaciado corto")
    @JsonProperty("nombre")
    private String nombre;

    @NotNull(message = "Tipo Evento no puede ser nullo")
    @NotEmpty(message = "Tipo Evento no puede estar vacio")
    @Size(min = 5,message = "Tipo Evento demaciado corto")
    @JsonProperty("tipoEvento")
    private String tipoEvento;

    @NotNull(message = "Fecha no puede ser nullo")
    @NotEmpty(message = "Fecha no puede estar vacio")
    @Pattern(regexp = "^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/(\\d{4}) ([01]?\\d|2[0-3]):(00|30)$"
            ,message = "Formato fecha incorrecta: dd/mm/aaaa hh:mm")
    @Size(min = 16, max = 16, message = "Fecha incorrecta")
    @JsonProperty("fecha")
    private String fecha;

    @NotNull(message = "Detalle no puede ser nullo")
    @NotEmpty(message = "Detalle no puede estar vacio")
    @Size(min = 5,message = "Detalle demaciado corto")
    @JsonProperty("detalle")
    private String detalle;

    @NotNull(message = "Cantidad Participantes no puede ser nullo")
    @Min(value = 1, message = "Cantida Participantes no puede ser menor a 1")
    @Max(value = 200, message = "Cantida Participantes no puede ser mayor a 25")
    @JsonProperty("cantParticipantes")
    private int cantParticipantes;

    @JsonProperty("participantes")
    private List<MascotaDto> participantes;

    @NotNull(message = "Direccion no puede ser nullo")
    @Valid
    @JsonProperty("direccion")
    private DireccionDto direccion;

    public EventoEntity toEntity() {
        EventoEntity eventoEntity = new EventoEntity();
        eventoEntity.setNombre(this.nombre);
        eventoEntity.setTipoEvento(this.tipoEvento);
        eventoEntity.setFecha(this.fecha);
        eventoEntity.setDetalle(this.detalle);
        eventoEntity.setCantidadParticipantes(this.cantParticipantes);
        eventoEntity.setDireccion(this.direccion.toEntity());
        return eventoEntity;
    }

    public EventoEntity updateEntity(EventoEntity eventoEntity) {
        eventoEntity.setNombre(this.nombre);
        eventoEntity.setTipoEvento(this.tipoEvento);
        eventoEntity.setFecha(this.fecha);
        eventoEntity.setDetalle(this.detalle);
        eventoEntity.setCantidadParticipantes(this.cantParticipantes);
        eventoEntity.setDireccion(this.direccion.updateDireccionEntity(eventoEntity.getDireccion()));
        return eventoEntity;
    }

}
