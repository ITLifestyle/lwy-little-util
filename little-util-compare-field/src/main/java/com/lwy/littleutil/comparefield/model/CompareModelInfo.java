package com.lwy.littleutil.comparefield.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CompareModelInfo {
    private Object key;

    private Object label;

    private HashMap<String, CompareFieldInfo> fieldsInfo = new HashMap<>();

    public void addFieldInfo(String fieldName, String fieldLabel, Object fieldVal) {
        fieldsInfo.put(fieldName, new CompareFieldInfo(fieldName, fieldLabel, fieldVal));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompareFieldInfo {
        private String fieldName;

        private String fieldLabel;

        private Object fieldVal;
    }
}
