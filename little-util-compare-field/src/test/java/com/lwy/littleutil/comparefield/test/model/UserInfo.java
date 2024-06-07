package com.lwy.littleutil.comparefield.test.model;

import com.lwy.littleutil.comparefield.annotation.CompareField;
import com.lwy.littleutil.comparefield.annotation.CompareKey;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserInfo {
    @CompareKey
    private Long idCard;

    @CompareField(fieldLabel = "用户名", isModelLabel = true)
    private String username;

    @CompareField(fieldLabel = "性别")
    private String sex;

    @CompareField(fieldLabel = "薪水")
    private BigDecimal salary;
}
