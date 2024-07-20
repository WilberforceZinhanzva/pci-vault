package zw.co.jugaad.pcivault.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ExceptionBody {
    private String message;
    private HttpStatus status;
    private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"));

    public ExceptionBody(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }
}
