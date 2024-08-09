package com.mafn.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyCollectionValidator.class)
public @interface NotEmptyCollection {
    String message() default "A coleção não pode ser vazia.";

    Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
