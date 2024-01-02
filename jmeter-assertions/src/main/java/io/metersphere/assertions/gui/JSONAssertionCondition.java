package io.metersphere.assertions.gui;

import io.metersphere.assertions.util.VerifyUtils;

import java.util.function.BiConsumer;

/**
 * @Author: jianxing
 * @CreateTime: 2023-12-29  16:26
 */
public enum JSONAssertionCondition {
    CONTAINS("包含", VerifyUtils::containsAssert),
    NOT_CONTAINS("不包含", VerifyUtils::notContainsAssert),
    EQUALS("等于", VerifyUtils::equalsAssert),
    NOT_EQUALS("不等于", VerifyUtils::notEqualsAssert),
    GT("大于", VerifyUtils::greaterThanAssert),
    GT_OR_EQUALS("大于等于", VerifyUtils::greaterThanOrEqualAssert),
    LT("小于", VerifyUtils::lessThanAssert),
    LT_OR_EQUALS("小于等于", VerifyUtils::lessThanOrEqualAssert),
    START_WITH("以...开头", VerifyUtils::startsWithAssert),
    END_WITH("以...结束", VerifyUtils::endsWithAssert),
    EMPTY("为空", VerifyUtils::emptyAssert),
    NOT_EMPTY("不为空", VerifyUtils::notEmptyAssert),
    REGEX("匹配正则表达式", VerifyUtils::regexAssert),
    LENGTH_EQUALS("长度等于", VerifyUtils::lengthEqualsAssert),
    LENGTH_NOT_EQUALS("长度不等于", VerifyUtils::lengthNotEqualsAssert),
    LENGTH_GT("长度大于", VerifyUtils::lengthGreaterThanAssert),
    LENGTH_GT_OR_EQUALS("长度大于等于", VerifyUtils::lengthGreaterThanOrEqualAssert),
    LENGTH_LT("长度小于", VerifyUtils::lengthLessThanAssert),
    LENGTH_LT_OR_EQUALS("长度小于等于", VerifyUtils::lengthLessThanOrEqualAssert);


    JSONAssertionCondition(String label, BiConsumer<Object, String> assertMethod) {
        this.label = label;
        this.assertMethod = assertMethod;
    }

    private String label;
    private BiConsumer<Object, String> assertMethod;

    public BiConsumer<Object, String> getAssertMethod() {
        return assertMethod;
    }

    public String getLabel() {
        return label;
    }
}
