package cl.ms.mascotas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MascotaDtoRp extends ResponseDto{
    @JsonProperty("data")
    private List<MascotaDto> data;

    public MascotaDtoRp(String codigo, String status, List<MascotaDto> data) {
        super(codigo, status);
        this.data = data;
    }
}
