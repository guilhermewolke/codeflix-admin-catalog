package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.validation.Error;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import com.fullcycle.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {
    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 255;
    private final Category category;

    protected CategoryValidator(final Category category, ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        String name = this.category.getName();


        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("O nome não pode ser nulo"));
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("O nome não pode ser vazio"));
        }

        final int length = name.length();

        if (length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("O nome precisa ter no mínimo " + NAME_MIN_LENGTH + " caracteres"));
        }

        if (length > NAME_MAX_LENGTH) {
            this.validationHandler().append(new Error("O nome precisa ter no máximo " + NAME_MAX_LENGTH + " caracteres"));
        }
    }
}
