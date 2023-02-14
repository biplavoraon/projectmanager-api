package com.biplav.projectmanager.validator;

import com.biplav.projectmanager.utility.TaskPriority;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriorityValidator implements ConstraintValidator<ValidPriority, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Set<String> priorities = Stream
                .of(TaskPriority.values())
                .map(TaskPriority :: name)
                .collect(Collectors.toSet());
        return priorities.contains(value);
    }
}
