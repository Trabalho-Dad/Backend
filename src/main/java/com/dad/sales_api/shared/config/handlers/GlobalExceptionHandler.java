package com.dad.sales_api.shared.config.handlers;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.dad.sales_api.shared.config.handlers.dto.output.ExceptionOutputDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.dad.sales_api.shared.exceptions.SalesException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(SalesException.class)
  public ResponseEntity<ExceptionOutputDTO> handleProductException(SalesException ex) {
    return new ResponseEntity<>(
        new ExceptionOutputDTO(
            ex.getMessage(),
            Instant.now().toString(),
            ex.getStatus().value(),
            ex.getStatus().getReasonPhrase()
        ),
        ex.getStatus()
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionOutputDTO> handleException(Exception ex) {
    return new ResponseEntity<>(
        new ExceptionOutputDTO(
            ex.getMessage() != null ? ex.getMessage() : "Erro inesperado.",
            Instant.now().toString(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
        ),
        HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<ExceptionOutputDTO> handleMethodValidation(
      HandlerMethodValidationException ex) {

    String message = ex.getAllErrors()
        .stream()
        .map(error -> error.getDefaultMessage())
        .collect(Collectors.joining(", "));

    return new ResponseEntity<>(
        new ExceptionOutputDTO(
            message,
            Instant.now().toString(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase()
        ),
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ExceptionOutputDTO> handleRuntimeException(RuntimeException ex) {
      return new ResponseEntity<>(
          new ExceptionOutputDTO(
              ex.getMessage() != null ? ex.getMessage() : "Erro inesperado.",
              Instant.now().toString(),
              HttpStatus.INTERNAL_SERVER_ERROR.value(),
              HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
          ),
          HttpStatus.INTERNAL_SERVER_ERROR
      );
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ExceptionOutputDTO> handleNotFound(
    NoResourceFoundException ex, HttpServletRequest request) {
    return new ResponseEntity<>(
        new ExceptionOutputDTO(
            "Rota não encontrada",
            Instant.now().toString(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase()
        ),
        HttpStatus.NOT_FOUND
    );
  }

   @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionOutputDTO> handleValidation(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(f -> "Campo '" + f.getField() + "': " + f.getDefaultMessage())
        .toList();
 
    return new ResponseEntity(
        new ExceptionOutputDTO(
            String.join(", ", errors),
            Instant.now().toString(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase()
        ),
        HttpStatus.BAD_REQUEST
    );
  }
}
