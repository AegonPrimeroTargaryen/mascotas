package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaDtoRp extends ResponseDto{
    @JsonProperty("data")
    private List<MascotaDto> data;

    public MascotaDtoRp(String codigo, String detalle,List<MascotaDto> data) {
        super(codigo, detalle);
        this.data = data;
    }
}
