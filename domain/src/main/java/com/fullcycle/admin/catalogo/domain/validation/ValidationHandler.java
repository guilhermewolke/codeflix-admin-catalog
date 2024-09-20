package com.fullcycle.admin.catalogo.domain.validation;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;

import java.util.List;

public interface ValidationHandler {
    ValidationHandler append(Error error) throws DomainException;
    ValidationHandler append(ValidationHandler handler);
    ValidationHandler validate(Validation validation);

    List<Error> getErrors();

    default boolean hasErrors() {
        return getErrors() != null && !getErrors().isEmpty();
    }
}
