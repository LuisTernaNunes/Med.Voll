package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TrataErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400 (MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors();
        return  ResponseEntity.badRequest().body(erros.stream().map(DadosErros::new).toList());
    }

    private record DadosErros(String campo, String mensagem){
        public DadosErros(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }
}
