package org.gabrielbarrilli.webfluxcourse.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class ValidationError extends StandardError{

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<FieldError> errors = new ArrayList<>();

    ValidationError(LocalDateTime timeStamp, String path, Integer status, String error, String message) {
        super(timeStamp, path, status, error, message);
    }

//    public void addError(String fieldName, String message) {
//        this.errors.add(new FieldError(fieldName, message));
//    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldError(fieldName, message));
        this.errors.sort(Comparator.comparing(FieldError::getFieldName)); // Ordena os erros sempre que um novo erro é adicionado
    }

    @Getter
    @AllArgsConstructor
    private static final class FieldError {
        private String fieldName;
        private String message;
    }
}
