package com.biplav.projectmanager.validator;

import com.biplav.projectmanager.utility.TaskStates;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StateValidator implements ConstraintValidator<ValidState, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Set<String> states = Stream
                .of(TaskStates.values())
                .map(TaskStates :: name)
                .collect(Collectors.toSet());
        return states.contains(value);
    }
}
