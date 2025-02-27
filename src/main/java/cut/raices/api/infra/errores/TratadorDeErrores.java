package cut.raices.api.infra.errores;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treatarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }


    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionesDeNegoecio(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionesDeIntegridad(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Aquí puedes personalizar la respuesta de error según tus necesidades.
        String errorMessage = null;
        if (ex.getMostSpecificCause().getMessage().contains("email")){
             errorMessage = "[\n" +
                     "\t{\n" +
                     "\t\t\"campo\": \"mail\",\n" +
                     "\t\t\"error\": \"Este mail ya existe\"\n" +
                     "\t}\n" +
                     "]";
        }
        if (ex.getMostSpecificCause().getMessage().contains("documento")){
            errorMessage = "[\n" +
                    "\t{\n" +
                    "\t\t\"campo\": \"documento\",\n" +
                    "\t\t\"error\": \"Ya existe\"\n" +
                    "\t}\n" +
                    "]";
        }

        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }


}
