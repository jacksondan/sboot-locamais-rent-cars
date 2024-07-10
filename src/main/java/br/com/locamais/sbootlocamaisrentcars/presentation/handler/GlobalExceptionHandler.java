package br.com.locamais.sbootlocamaisrentcars.presentation.handler;

import br.com.locamais.rent.cars.representation.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleException(Exception e, WebRequest request){
        var response = new ResponseError()
                .error(e.getLocalizedMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .success(false);
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseError> hangleEntityNotFound(){
        var response = new ResponseError()
                .error("Registro não encontrado")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .success(false);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request){
        var response = new ResponseError()
                .error(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ResponseError> handleHttpClientErrorException(HttpClientErrorException e, WebRequest request){
        return new ResponseEntity<>(getResponseError(e,request),e.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ResponseError> handleHttpServerErrorException(HttpServerErrorException e, WebRequest request){
        return new ResponseEntity<>(getResponseError(e,request),e.getStatusCode());
    }

    private ResponseError getResponseError(@NotNull HttpStatusCodeException e, WebRequest request){
        return new ResponseError()
                .error(e.getMessage())
                .statusCode(e.getStatusCode().value())
                .success(false);
    }
}
