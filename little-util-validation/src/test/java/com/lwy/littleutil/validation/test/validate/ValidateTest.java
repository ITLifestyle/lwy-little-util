package com.lwy.littleutil.validation.test.validate;

import com.lwy.littleutil.validation.model.ErrorInfo;
import com.lwy.littleutil.validation.model.ValidateResult;
import com.lwy.littleutil.validation.test.model.User;
import com.lwy.littleutil.validation.utils.ValidateUtils;
import org.junit.Test;

public class ValidateTest {
    @Test
    public void normalTest() throws Exception {
        ValidateResult validateResult = ValidateUtils.checkParam(new User());
        for (ErrorInfo errorInfo : validateResult.getErrorInfos()) {
            System.out.println(validateResult);
        }
    }
}
