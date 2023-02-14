package com.biplav.projectmanager.advice;

import com.biplav.projectmanager.exception.TaskNotFoundException;
import com.biplav.projectmanager.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleBusinessExceptionUser(UserNotFoundException ex) {
        Map<String, String> errorMap2 = new HashMap<>();
        errorMap2.put("errorMessage", ex.getMessage());
        return errorMap2;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TaskNotFoundException.class)
    public Map<String, String> handleBusinessExceptionTask(TaskNotFoundException ex) {
        Map<String, String> errorMap3 = new HashMap<>();
        errorMap3.put("errorMessage", ex.getMessage());
        return errorMap3;
    }
}
