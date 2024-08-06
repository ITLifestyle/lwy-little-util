package com.lwy.littleutil.validation.test.spel;

import com.lwy.littleutil.validation.annotation.NotEmpty;
import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelTest {
    @Test
    public void test() {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("name", "Alice");

        String greeting = parser.parseExpression("'Hello ' + #name").getValue(context, String.class);
        System.out.println(greeting);

        System.out.println(parser.parseExpression("true").getValue(context, Boolean.class));

        System.out.println(NotEmpty.class);


    }
}
