package com.lwy.littleutil.validation.context;

public class StandardValidateContext implements ValidateContext {
    private Object validateObj;

    @Override
    public Object getValidateObj() {
        return validateObj;
    }

    @Override
    public void setValidateObj(Object obj) {
        this.validateObj = obj;
    }
}
