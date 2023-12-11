package io.metersphere.jmeter.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class VerifyUtils {
    /**
     * 大于
     * 支持整数，小数
     *
     * @param subj 实际结果
     * @return boolean
     */
    public static boolean isGreaterThan(Object subj, String expectedValue) {
        return getCompareResult(subj, expectedValue) > 0;
    }

    /**
     * 大于等于
     * 支持整数，小数
     *
     * @param subj 实际结果
     * @return boolean
     */
    public static boolean isGreaterThanOrEqual(Object subj, String expectedValue) {
        return getCompareResult(subj, expectedValue) >= 0;
    }

    /**
     * 小于
     * 支持整数，小数
     *
     * @param subj 实际结果
     * @return boolean
     */
    public static boolean isLessThan(Object subj, String expectedValue) {
        return getCompareResult(subj, expectedValue) < 0;
    }

    /**
     * 小于等于
     * 支持整数，小数
     *
     * @param subj 实际结果
     * @return boolean
     */
    public static boolean isLessThanOrEqual(Object subj, String expectedValue) {
        return getCompareResult(subj, expectedValue) <= 0;
    }

    /**
     * 包含
     * 支持字符串
     *
     * @param subj 实际结果
     * @return boolean
     */
    public static boolean isContains(Object subj, String expectedValue) {
        return StringUtils.contains(Objects.toString(subj), expectedValue);
    }

    /**
     * 获取两个数字比较的结果
     *
     * @param subj 实际结果
     * @return >0：大于；=0：等于；<0:小于
     */
    private static int getCompareResult(Object subj, String expectedValue) {
        if (subj == null || StringUtils.isBlank(expectedValue)) {
            String msg = "expectedValue or actualValue is null, expectedValue='%s', actualValue='%s'";
            throw new IllegalStateException(String.format(msg, expectedValue, subj));
        }

        String actualValue = subj.toString();
        if (validateNumber(actualValue) || validateNumber(expectedValue)) {
            String msg = "expectedValue or actualValue is not number, expectedValue='%s', actualValue='%s'";
            throw new IllegalStateException(String.format(msg, expectedValue, actualValue));
        }

        return Double.valueOf(actualValue).compareTo(Double.valueOf(expectedValue));
    }

    /**
     * 判断是否是整数或者是小数
     *
     * @param str 目标字符串
     * @return boolean
     */
    public static boolean validateNumber(String str) {
        if (StringUtils.isBlank(str)) {
            return true;
        }
        return !str.matches("[+-]?[0-9]+(\\.[0-9]{1,10})?");
    }

}
