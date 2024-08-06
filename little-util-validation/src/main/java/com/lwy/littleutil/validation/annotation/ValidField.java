package com.lwy.littleutil.validation.annotation;

import com.lwy.littleutil.validation.checker.Checker;
import com.lwy.littleutil.validation.checker.SpelChecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validateBy = SpelChecker.class)
public @interface ValidField {
    String judgeValidExp () default "true";
    String checkExp ();
    String msg() default  "{fieldName} valid failed!";
    Class<Checker> check() default Checker.class;
}
