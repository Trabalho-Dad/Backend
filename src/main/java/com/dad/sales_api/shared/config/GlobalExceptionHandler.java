package com.dad.sales_api.shared.config;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.dad.sales_api.shared.exceptions.SalesException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(SalesException.class)
  public ResponseEntity<Map<String, Object>> handleProductException(SalesException ex) {
    return ResponseEntity
      .status(ex.getStatus())
      .body(Map.of(
        "status", ex.getStatus().value(),
        "error", ex.getStatus().getReasonPhrase(),
        "message", ex.getMessage(),
        "timestamp", Instant.now().toString()
      ));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(Map.of(
        "status", HttpStatus.INTERNAL_SERVER_ERROR,
        "message", ex.getMessage(),
        "timestamp", Instant.now().toString()
      ));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(Map.of(
        "status", HttpStatus.INTERNAL_SERVER_ERROR,
        "message", ex.getMessage(),
        "timestamp", Instant.now().toString()
      ));
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(
    NoResourceFoundException ex, HttpServletRequest request) {

    Map<String, Object> body = Map.of(
      "status", 404,
      "error", "Not Found",
      "message", "Rota não encontrada.",
      "path", request.getRequestURI()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

   @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(f -> "Campo '" + f.getField() + "': " + f.getDefaultMessage())
        .toList();
 
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        new ErrorResponse(
            String.join(", ", errors),
            Instant.now().toString(),
            "400 BAD_REQUEST"
        )
    );
  }

  record ErrorResponse(String message, String timestamp, String status) {}
}
