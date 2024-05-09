package io.metersphere.assertions;

import org.apache.jmeter.assertions.AssertionResult;

/**
 * @Author: jianxing
 * @CreateTime: 2024-05-08  16:33
 */
public class JSONPathAssertionResult extends AssertionResult {
    private String actualValue;


    public JSONPathAssertionResult() {
        super();
    }

    public JSONPathAssertionResult(String name) {
        super(name);
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getActualValue() {
        return actualValue;
    }
}
