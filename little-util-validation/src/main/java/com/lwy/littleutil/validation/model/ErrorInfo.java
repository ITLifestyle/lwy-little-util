package com.lwy.littleutil.validation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ErrorInfo {
    private String fieldName;

    private String errorMessage;
}
