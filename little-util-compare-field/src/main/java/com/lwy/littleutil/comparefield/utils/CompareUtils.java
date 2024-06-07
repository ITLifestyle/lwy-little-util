package com.lwy.littleutil.comparefield.utils;

import com.lwy.littleutil.comparefield.annotation.CompareField;
import com.lwy.littleutil.comparefield.annotation.CompareKey;
import com.lwy.littleutil.comparefield.enums.OptTypeEnum;
import com.lwy.littleutil.comparefield.exception.NoCompareKeyException;
import com.lwy.littleutil.comparefield.model.CompareModelInfo;
import com.lwy.littleutil.comparefield.model.CompareResult;

import java.lang.reflect.Field;
import java.util.*;

public class CompareUtils {
    public static <T, E> List<CompareResult> compareField(List<T> oriModels, List<E> targetModels) throws Exception {
        if (oriModels == null || targetModels == null) {
            throw new RuntimeException("对比列表不能为空!");
        }

        Map<Object, CompareModelInfo> oriModelInfos = readModelsInfo(oriModels);
        Map<Object, CompareModelInfo> targetModelInfos = readModelsInfo(targetModels);

        Set<Object> keys = new HashSet<>(oriModelInfos.keySet());
        keys.addAll(targetModelInfos.keySet());

        List<CompareResult> compareResults = new ArrayList<>();
        for (Object key : keys) {
            compareResults.add(compareModel(oriModelInfos.get(key), targetModelInfos.get(key)));
        }

        return compareResults;
    }

    private static CompareResult compareModel(CompareModelInfo oriModelInfo, CompareModelInfo targetModelInfo) {
        if (oriModelInfo == null && targetModelInfo == null) {
            return null;
        }

        CompareResult compareResult = new CompareResult();
        if (oriModelInfo == null) {
            compareResult.setOptType(OptTypeEnum.ADD);
            compareResult.setKey(targetModelInfo.getKey());
            compareResult.setLabel(targetModelInfo.getLabel());
        } else if (targetModelInfo == null) {
            compareResult.setOptType(OptTypeEnum.DELETE);
            compareResult.setKey(oriModelInfo.getKey());
            compareResult.setLabel(oriModelInfo.getLabel());
        } else {
            compareResult.setOptType(OptTypeEnum.UPDATE);
            compareResult.setKey(oriModelInfo.getKey());
            compareResult.setLabel(oriModelInfo.getLabel());
            compareResult.setCompareResultItemList(compareFieldInfos(oriModelInfo.getFieldsInfo(), targetModelInfo.getFieldsInfo()));
        }

        return compareResult;
    }

    private static List<CompareResult.CompareResultItem> compareFieldInfos(HashMap<String, CompareModelInfo.CompareFieldInfo> oriFieldInfos, HashMap<String, CompareModelInfo.CompareFieldInfo> targetFieldInfos) {
        List<CompareResult.CompareResultItem> compareResultItems = new ArrayList<>();
        Set<String> oriFieldNames = oriFieldInfos.keySet();
        Set<String> targetFieldNames = oriFieldInfos.keySet();

        Set<String> commonFieldNames = new HashSet<>(oriFieldNames);
        commonFieldNames.retainAll(targetFieldNames);
        for (String fieldName : commonFieldNames) {
            CompareResult.CompareResultItem compareResultItem = compareFieldInfo(oriFieldInfos.get(fieldName), targetFieldInfos.get(fieldName));
            if (compareResultItem != null) {
                compareResultItems.add(compareResultItem);
            }
        }
        return compareResultItems;
    }

    private static CompareResult.CompareResultItem compareFieldInfo(CompareModelInfo.CompareFieldInfo origFieldInfo, CompareModelInfo.CompareFieldInfo targetFieldInfo) {
        if (!equalsVal(origFieldInfo.getFieldVal(), targetFieldInfo.getFieldVal())) {
            CompareResult.CompareResultItem compareResultItem = new CompareResult.CompareResultItem();
            compareResultItem.setFieldName(origFieldInfo.getFieldName());
            compareResultItem.setFieldLabel(origFieldInfo.getFieldLabel());
            compareResultItem.setOriVal(origFieldInfo.getFieldVal());
            compareResultItem.setNewVal(targetFieldInfo.getFieldVal());
            return compareResultItem;
        }
        return null;
    }

    private static boolean equalsVal(Object oriFieldVal, Object targetFieldVal) {
        if (oriFieldVal instanceof Number && oriFieldVal instanceof Comparable && targetFieldVal instanceof Number && targetFieldVal instanceof Comparable) {
            return ((Comparable) oriFieldVal).compareTo(targetFieldVal) == 0;
        }
        return oriFieldVal.equals(targetFieldVal);
    }

    private static <T> Map<Object, CompareModelInfo> readModelsInfo(List<T> models) throws IllegalAccessException {
        Map<Object, CompareModelInfo> modelInfos = new HashMap<>();
        for (Object oriModel : models) {
            CompareModelInfo compareModelInfo = readModelInfo(oriModel);

            if (compareModelInfo.getKey() == null) {
                throw new NoCompareKeyException();
            }

            modelInfos.put(compareModelInfo.getKey(), compareModelInfo);
        }

        return modelInfos;
    }

    private static CompareModelInfo readModelInfo(Object model) throws IllegalAccessException {
        CompareModelInfo compareModelInfo = new CompareModelInfo();

        Field[] fields = model.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            CompareKey compareKey = field.getAnnotation(CompareKey.class);
            if (compareKey != null) {
                Object fieldVal = field.get(model);
                compareModelInfo.setKey(fieldVal);
            }

            CompareField compareField = field.getAnnotation(CompareField.class);
            if (compareField != null) {
                String fieldName = stringIsEmpty(compareField.fieldName()) ? field.getName() : compareField.fieldName();
                String fieldLabel = compareField.fieldLabel();
                Object fieldVal = field.get(model);
                compareModelInfo.addFieldInfo(fieldName, fieldLabel, fieldVal);

                if (compareField.isModelLabel()) {
                    compareModelInfo.setLabel(fieldVal);
                }
            }
        }

        return compareModelInfo;
    }

    private static boolean stringIsEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
