package ru.restaurant_voting.error;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.restaurant_voting.error.AppException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

//https://habr.com/ru/post/675716/
@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    private final ErrorAttributes errorAttributes;



    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> appException(WebRequest request, AppException ex) {
        log.error("ApplicationException: {}", ex.getMessage());
        return createResponseEntity(request, ex.getOptions(), null, ex.getStatus());
    }

    @ExceptionHandler(DeadLineRuntimeEx.class)
    public ResponseEntity<?> DeadLineRuntimeExeption(WebRequest request, DeadLineRuntimeEx ex) {
        log.error("ApplicationException: {}", ex.getMessage());
        return createResponseEntity(request, ErrorAttributeOptions.of(MESSAGE), ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException(WebRequest request, EntityNotFoundException ex) {
        log.error("EntityNotFoundException: {}", ex.getMessage());
        return createResponseEntity(request, ErrorAttributeOptions.of(MESSAGE), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(WebRequest request, ConstraintViolationException ex) {
        log.error("ConstraintViolationException: {}", ex.getMessage());
        String msg = ex.getConstraintViolations().stream()
                .map(cv -> String.format("[%s] %s", cv.getPropertyPath(), cv.getMessage()))
                .collect(Collectors.joining("\n"));
        return createResponseEntity(request, ErrorAttributeOptions.defaults(), msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @SuppressWarnings("unchecked")
    private <T> ResponseEntity<T> createResponseEntity(WebRequest request, ErrorAttributeOptions options, String msg, HttpStatus status) {
        Map<String, Object> body = errorAttributes.getErrorAttributes(request, options);
        if (msg != null) {
            body.put("message", msg);
        }
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        return (ResponseEntity<T>) ResponseEntity.status(status).body(body);
    }
}
