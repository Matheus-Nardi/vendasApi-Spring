package com.mafn.validation;

import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyCollectionValidator implements ConstraintValidator<NotEmptyCollection, Set> {

    @Override
    public boolean isValid(Set value, ConstraintValidatorContext context) {
       return value != null && !value.isEmpty();
    }
}
