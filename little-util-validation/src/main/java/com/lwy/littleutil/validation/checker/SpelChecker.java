package com.lwy.littleutil.validation.checker;

import com.lwy.littleutil.validation.context.ValidateContext;
import com.lwy.littleutil.validation.utils.SpelUtils;

public class SpelChecker implements Checker<String> {
    @Override
    public Boolean check(String expression, ValidateContext validateContext) {
        return SpelUtils.judgeBoolean(expression, validateContext.getValidateObj());
    }
}
