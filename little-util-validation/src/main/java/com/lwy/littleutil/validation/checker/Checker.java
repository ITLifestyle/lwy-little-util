package com.lwy.littleutil.validation.checker;

import com.lwy.littleutil.validation.context.ValidateContext;

public interface Checker<T> {
    Boolean check(T t, ValidateContext validateContext);
}
