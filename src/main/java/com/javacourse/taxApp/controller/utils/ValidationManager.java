package com.javacourse.taxApp.controller.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidationManager {
    private static Validator getValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return  factory.getValidator();
    }

    public static  <T>  boolean isValid(T object){
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        return constraintViolations.isEmpty();
    }

    public static   <T>  String getFirstErrorMessage(T object){
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        return constraintViolations.iterator().next().getMessage();
    }

}
