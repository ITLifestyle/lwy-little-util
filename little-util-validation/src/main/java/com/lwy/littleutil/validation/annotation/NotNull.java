package com.lwy.littleutil.validation.annotation;

import com.lwy.littleutil.validation.checker.NotNullChecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validateBy = NotNullChecker.class)
public @interface NotNull {
    String value () default "";
    String judgeValidExp () default "true";
    String msg() default  "{fieldName} not be null!";
}
