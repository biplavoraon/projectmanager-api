package com.biplav.projectmanager.validator;

import com.biplav.projectmanager.repository.UserRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userRepo.existsByUsername(value);
    }
}
