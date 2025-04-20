package cl.ms.mascotas.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MascotaNotFoundException extends RuntimeException {
    private final String codigo;
    private final String status;

    public MascotaNotFoundException(String msj, String codigo, String status) {
        super(msj);
        this.codigo = codigo;
        this.status = status;
    }
}
