package com.arias.profile.exceptions;

import com.arias.profile.dtos.response.GeneralResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = {BusinessRuleException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<GeneralResponseDTO<Object>> handleBusinessRuleExceptions(
            BusinessRuleException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(GeneralResponseDTO.builder()
                        .code(-1)
                        .message("no podemos procesar tu solicitud en este momento")
                        .error(Collections.singletonList(ex.getMessage()))
                        .build()
                );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<GeneralResponseDTO<Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(GeneralResponseDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .error(errors)
                        .data(null)
                        .build());
    }
}
