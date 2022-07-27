package app.socialmedia.Exception;

import app.socialmedia.model.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handleMethodArgsNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> response=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            response.put(fieldName,message);
        });

        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleLogoutException(UserNotFoundException e) {
        String exceptionMessage = e.getMessage();
        Response logoutResponse = new Response();
        logoutResponse.setStatus(false);
        logoutResponse.setMessage(exceptionMessage);
        return new ResponseEntity<Response>(logoutResponse, HttpStatus.BAD_REQUEST);
    }
}
