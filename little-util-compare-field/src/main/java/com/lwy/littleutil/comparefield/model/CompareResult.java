package com.lwy.littleutil.comparefield.model;

import com.lwy.littleutil.comparefield.enums.OptTypeEnum;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CompareResult {
    private Object key;

    private Object label;

    private OptTypeEnum optType;

    private List<CompareResultItem> compareResultItemList;

    @Data
    public static class CompareResultItem {
        private String fieldName;

        private String fieldLabel;

        private Object oriVal;

        private Object newVal;

        private String content;
    }

    public String getChangeContent() {
        return switch (optType) {
            case ADD -> String.format("添加了%s", label);
            case DELETE -> String.format("删除了%s", label);
            case UPDATE -> String.format("更新了%s: ", label)
                    + compareResultItemList.stream()
                    .map(item -> String.format("%s由%s变更%s", item.getFieldLabel(), item.getOriVal(), item.getNewVal()))
                    .collect(Collectors.joining("; "));
        };
    }
}
