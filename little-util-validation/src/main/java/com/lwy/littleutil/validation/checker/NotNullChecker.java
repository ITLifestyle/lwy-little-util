package com.lwy.littleutil.validation.checker;

import com.lwy.littleutil.validation.context.ValidateContext;

public class NotNullChecker implements Checker<Object> {
    @Override
    public Boolean check(Object obj, ValidateContext validateContext) {
        return obj != null;
    }
}
