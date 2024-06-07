package com.lwy.littleutil.comparefield.test;

import com.lwy.littleutil.comparefield.model.CompareResult;
import com.lwy.littleutil.comparefield.test.model.UserInfo;
import com.lwy.littleutil.comparefield.utils.CompareUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompareUtilsTest {
    @Test
    public void testCompare1() throws Exception {
        List<UserInfo> oriUserInfos = new ArrayList<>();
        oriUserInfos.add(new UserInfo(1L, "李哈雷", "男", new BigDecimal(7000)));
        oriUserInfos.add(new UserInfo(2L, "李明兰", "女", new BigDecimal(8000)));
        oriUserInfos.add(new UserInfo(3L, "李子木", "男", new BigDecimal(9000)));

        List<UserInfo> targetUserInfos = new ArrayList<>();
        targetUserInfos.add(new UserInfo(2L, "李明溪", "女", new BigDecimal(9000)));
        targetUserInfos.add(new UserInfo(3L, "李子木", "男", new BigDecimal(8000)));
        targetUserInfos.add(new UserInfo(4L, "周秋兰", "男", new BigDecimal(9000)));

        List<CompareResult> compareResults = CompareUtils.compareField(oriUserInfos, targetUserInfos);
        for (CompareResult compareResult : compareResults) {
            System.out.println(compareResult.getChangeContent());
        }
    }
}
