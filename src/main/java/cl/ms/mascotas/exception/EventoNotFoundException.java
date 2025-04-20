package cl.ms.mascotas.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EventoNotFoundException extends RuntimeException {
    private final String codigo;
    private final String status;

    public EventoNotFoundException(String msg, String codigo, String status) {
        super(msg);
        this.codigo = codigo;
        this.status = status;
    }
}
