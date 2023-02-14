package com.biplav.projectmanager.validator;

import com.biplav.projectmanager.utility.RoleName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Set<String> roles = Stream
                .of(RoleName.values())
                .map(RoleName :: name)
                .collect(Collectors.toSet());
        return roles.contains(value);
    }
}
