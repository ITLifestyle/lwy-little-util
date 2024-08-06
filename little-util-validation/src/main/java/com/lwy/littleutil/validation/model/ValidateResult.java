package com.lwy.littleutil.validation.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidateResult {
    private List<ErrorInfo> errorInfos = new ArrayList<>();

    public boolean hasError() {
        return errorInfos.size() > 0;
    }
}
