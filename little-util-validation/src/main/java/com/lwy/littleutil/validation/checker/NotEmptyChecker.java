package com.lwy.littleutil.validation.checker;

import com.lwy.littleutil.validation.context.ValidateContext;

public class NotEmptyChecker implements Checker<String> {
    @Override
    public Boolean check(String val, ValidateContext validateContext) {
        return val != null && val.trim().length() > 0;
    }
}
