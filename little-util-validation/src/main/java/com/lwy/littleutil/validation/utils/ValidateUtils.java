package com.lwy.littleutil.validation.utils;

import com.lwy.littleutil.validation.annotation.Constraint;
import com.lwy.littleutil.validation.checker.Checker;
import com.lwy.littleutil.validation.context.StandardValidateContext;
import com.lwy.littleutil.validation.context.ValidateContext;
import com.lwy.littleutil.validation.model.ErrorInfo;
import com.lwy.littleutil.validation.model.ValidateResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ValidateUtils {
    public static ValidateResult checkParam(Object object) throws Exception {
        if (object == null) {
            throw new Exception("检查对象不能为空!");
        }

        ValidateResult validateResult = new ValidateResult();
         if (object instanceof Iterable<?>) {
            Iterable iterable = (Iterable) object;
            for (Object item : iterable) {
                validateResult.getErrorInfos().addAll(singleObjectCheck(item));
            }
        } else {
             validateResult.getErrorInfos().addAll(singleObjectCheck(object));
        }
        return validateResult;
    }

    private static List<ErrorInfo> singleObjectCheck(Object obj) throws Exception {
        ValidateContext validateContext = new StandardValidateContext();
        List<ErrorInfo> errorInfos = new ArrayList<>();
        validateContext.setValidateObj(obj);

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Annotation[] annotations = declaredField.getAnnotations();
            declaredField.setAccessible(true);
            Object value = declaredField.get(obj);
            String fieldName = declaredField.getName();

            for (Annotation annotation : annotations) {
                Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
                if (constraint == null) continue;
                Checker checker = constraint.validateBy().getDeclaredConstructor().newInstance();
                Boolean check = checker.check(value, validateContext);
                Method msg = annotation.getClass().getMethod("msg");
                if (msg == null) continue;

                if (!check) {
                    errorInfos.add(new ErrorInfo(fieldName, msg.invoke(annotation).toString()));
                }
            }
        }

        return errorInfos;
    }
}
