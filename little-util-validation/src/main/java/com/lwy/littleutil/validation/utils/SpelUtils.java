package com.lwy.littleutil.validation.utils;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelUtils {
    public static Boolean judgeBoolean(String expression, Object data) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("data", data);
        return parser.parseExpression(expression).getValue(context, Boolean.class);
    }
}
