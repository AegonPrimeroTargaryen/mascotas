package cl.ms.mascotas.exception;

import cl.ms.mascotas.dto.ErrorDtoRp;
import cl.ms.mascotas.dto.ParamErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,NoResourceFoundException.class,HandlerMethodValidationException.class
            ,EventoNotFoundException.class,MascotaNotFoundException.class})
    protected ResponseEntity<ErrorDtoRp> handlerError(Exception e) {
        if (e instanceof MethodArgumentTypeMismatchException) return handleMethodArgumentTypeMismatchException();
        if (e instanceof HttpMessageNotReadableException || e instanceof HandlerMethodValidationException)
            return handleHttpMessageNotReadableException();
        if (e instanceof MethodArgumentNotValidException exp) return handleMethodArgumentNotValidException(exp);
        if (e instanceof NoResourceFoundException) return ResponseEntity.notFound().build();
        if (e instanceof EventoNotFoundException exception) return eventoNotFoundException(exception);
        if (e instanceof MascotaNotFoundException exception) return mascotaNotFoundException(exception);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDtoRp("01","NOK"));

    }

    private ResponseEntity<ErrorDtoRp> handleMethodArgumentTypeMismatchException() {
        return ResponseEntity.badRequest().body(new ErrorDtoRp("01","NOK"));
    }

    private ResponseEntity<ErrorDtoRp> handleHttpMessageNotReadableException() {
        return ResponseEntity.badRequest().body(new ErrorDtoRp("01","NOK"));
    }

    private ResponseEntity<ErrorDtoRp> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp){
        List<FieldError> errores = exp.getBindingResult().getFieldErrors();
        List<ParamErrorDto> errorDtos = new ArrayList<>();
        errores.forEach(error -> {
            ParamErrorDto paramErrorDto = new ParamErrorDto(error.getField(), error.getDefaultMessage());
            errorDtos.add(paramErrorDto);
        });
        return ResponseEntity.badRequest().body(new ErrorDtoRp("01", "NOK", errorDtos));
    }

    private ResponseEntity<ErrorDtoRp> eventoNotFoundException(EventoNotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("detalle", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDtoRp(exception.getCodigo(), exception.getStatus(), errors));
    }

    private ResponseEntity<ErrorDtoRp> mascotaNotFoundException(MascotaNotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("detalle", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDtoRp(exception.getCodigo(), exception.getStatus(), errors));
    }
}
