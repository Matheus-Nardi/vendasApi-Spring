package com.mafn.infra;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mafn.exception.NotFoundException;
import com.mafn.exception.RegraNegocioException;

@RestControllerAdvice
@ControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ApiErrors> handleRegrasNegocioException(RegraNegocioException ex){
        ApiErrors apiErrors = ApiErrors.builder()
                    .timestamp(LocalDate.now())
                    .status(HttpStatus.BAD_REQUEST.value())
                    .path(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .error(ex.getMessage())
                    .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrors);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ApiErrors> handleNotFoundExcepion(NotFoundException ex){
        ApiErrors apiErrors = ApiErrors.builder()
                    .timestamp(LocalDate.now())
                    .status(HttpStatus.NOT_FOUND.value())
                    .path(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .error(ex.getMessage())
                    .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrors);
    }
}
