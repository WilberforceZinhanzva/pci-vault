package zw.co.jugaad.pcivault.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<ExceptionBody> handleException(RuntimeException e){
        return new ResponseEntity<>(new ExceptionBody(e.getMessage(), HttpStatus.CONFLICT),HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = {AccountLockedException.class})
    public ResponseEntity<ExceptionBody> handleAuthenticationException(RuntimeException e){
        return new ResponseEntity<>(new ExceptionBody(e.getMessage(),HttpStatus.LOCKED),HttpStatus.LOCKED);
    }

    @ExceptionHandler(value = {UnprocessableContentException.class})
    public ResponseEntity<ExceptionBody> handleUnprocessableException(RuntimeException e){
        return new ResponseEntity<>(new ExceptionBody(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY),HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {PasswordNotSetException.class})
    public ResponseEntity<ExceptionBody> handlePasswordNotSetException(RuntimeException e){
        return new ResponseEntity<>(new ExceptionBody(e.getMessage(),HttpStatus.NOT_ACCEPTABLE),HttpStatus.NOT_ACCEPTABLE);
    }

}
