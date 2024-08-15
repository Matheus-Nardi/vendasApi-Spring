package com.mafn.infra.exception_handler;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mafn.exception.NotFoundException;
import com.mafn.exception.RegraNegocioException;

@RestControllerAdvice
@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ApiErrors> handleRegrasNegocioException(RegraNegocioException ex) {
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
    private ResponseEntity<ApiErrors> handleNotFoundExcepion(NotFoundException ex) {
        ApiErrors apiErrors = ApiErrors.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.NOT_FOUND.value())
                .path(HttpStatus.NOT_FOUND.getReasonPhrase())
                .error(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ApiErrors> handleDataViolanetionException(DataIntegrityViolationException ex) {
        String msgError = "Já existe um cliente cadastrado com este CPF.";
        ApiErrors apiErrors = ApiErrors.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(msgError)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ApiErrors> handleNotValidException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getAllErrors().stream()
                .map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());
        ApiErrors apiErrors = ApiErrors.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error("Erros de validação")
                .errors(erros)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrors);
    }

}
