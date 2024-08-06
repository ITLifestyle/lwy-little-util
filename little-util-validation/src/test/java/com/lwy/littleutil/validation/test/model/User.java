package com.lwy.littleutil.validation.test.model;

import com.lwy.littleutil.validation.annotation.NotEmpty;
import com.lwy.littleutil.validation.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotEmpty
    private String userName;

    @NotNull
    private Integer age;
}
