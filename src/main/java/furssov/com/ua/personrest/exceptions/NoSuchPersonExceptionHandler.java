package furssov.com.ua.personrest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoSuchPersonExceptionHandler {
    @ExceptionHandler({NoSuchPersonException.class})
    public ResponseEntity handler(Exception e)
    {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
