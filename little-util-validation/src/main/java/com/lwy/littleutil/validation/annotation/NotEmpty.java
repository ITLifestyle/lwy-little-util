package com.lwy.littleutil.validation.annotation;

import com.lwy.littleutil.validation.checker.NotEmptyChecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validateBy = NotEmptyChecker.class)
public @interface NotEmpty {
    String judgeValidExp () default "true";
    String msg() default  "{fieldName} not be empty!";
}
