/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.metersphere.assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.metersphere.assertions.util.VerifyUtils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.assertions.Assertion;
import org.apache.jmeter.assertions.AssertionResult;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.ThreadListener;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.oro.text.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;

/**
 * JSONPath Assertion
 */
public class JsonPathAssertion extends AbstractTestElement implements Serializable, Assertion, ThreadListener {
    private static final Logger log = LoggerFactory.getLogger(JsonPathAssertion.class);

    @Serial
    private static final long serialVersionUID = 2L;
    public static final String JSONPATH = "JSON_PATH";
    public static final String JSONPATHVALUE = "JSONPATH_VALUE";
    public static final String EXPECTEDVALUE = "EXPECTED_VALUE";
    public static final String JSONVALIDATION = "JSONVALIDATION";
    public static final String EXPECT_NULL = "EXPECT_NULL";
    public static final String INVERT = "INVERT";
    public static final String ISREGEX = "ISREGEX";
    public static final String GREATERTHAN = "GREATERTHAN";
    public static final String GREATERTHANOREQUAL = "GREATERTHANOREQUAL";
    public static final String LESSTHAN = "LESSTHAN";
    public static final String LESSTHANOREQUAL = "LESSTHANOREQUAL";
    public static final String CONTAINS = "CONTAINS";

    private static final boolean USE_JAVA_REGEX = !JMeterUtils.getPropDefault("jmeter.regex.engine", "oro").equalsIgnoreCase("oro");

    private static final ThreadLocal<DecimalFormat> decimalFormatter = ThreadLocal.withInitial(JsonPathAssertion::createDecimalFormat);

    private static DecimalFormat createDecimalFormat() {
        DecimalFormat decimalFormatter = new DecimalFormat("#.#");
        decimalFormatter.setMaximumFractionDigits(340); // java.text.DecimalFormat.DOUBLE_FRACTION_DIGITS == 340
        decimalFormatter.setMinimumFractionDigits(1);
        return decimalFormatter;
    }

    public String getJsonPath() {
        return getPropertyAsString(JSONPATH);
    }

    public void setJsonPath(String jsonPath) {
        setProperty(JSONPATH, jsonPath);
    }

    public String getJsonPathValue() {
        return getPropertyAsString(JSONPATHVALUE);
    }

    public void setJsonPathValue(String jsonPathValue) {
        setProperty(JSONPATHVALUE, jsonPathValue);
    }

    public String getExpectedValue() {
        return getPropertyAsString(EXPECTEDVALUE);
    }

    public void setExpectedValue(String expectedValue) {
        setProperty(EXPECTEDVALUE, expectedValue);
    }

    public void setJsonValidationBool(boolean jsonValidation) {
        setProperty(JSONVALIDATION, jsonValidation);
    }

    public void setExpectNull(boolean val) {
        setProperty(EXPECT_NULL, val);
    }

    public boolean isExpectNull() {
        return getPropertyAsBoolean(EXPECT_NULL);
    }

    public boolean isJsonValidationBool() {
        return getPropertyAsBoolean(JSONVALIDATION);
    }

    public void setInvert(boolean invert) {
        setProperty(INVERT, invert);
    }

    public boolean isInvert() {
        return getPropertyAsBoolean(INVERT);
    }

    public void setGreaterThan(boolean greaterThan) {
        setProperty(GREATERTHAN, greaterThan);
    }

    public boolean isGreaterThan() {
        return getPropertyAsBoolean(GREATERTHAN);
    }

    public void setGreaterThanOrEqual(boolean greaterThanOrEqual) {
        setProperty(GREATERTHANOREQUAL, greaterThanOrEqual);
    }

    public boolean isGreaterThanOrEqual() {
        return getPropertyAsBoolean(GREATERTHANOREQUAL);
    }

    public void setLessThan(boolean lessThan) {
        setProperty(LESSTHAN, lessThan);
    }

    public boolean isLessThan() {
        return getPropertyAsBoolean(LESSTHAN);
    }

    public void setLessThanOrEqual(boolean lessThanOrEqual) {
        setProperty(LESSTHANOREQUAL, lessThanOrEqual);
    }

    public boolean isLessThanOrEqual() {
        return getPropertyAsBoolean(LESSTHANOREQUAL);
    }

    public void setContains(boolean contains) {
        setProperty(CONTAINS, contains);
    }

    public boolean isContains() {
        return getPropertyAsBoolean(CONTAINS);
    }

    public void setIsRegex(boolean flag) {
        setProperty(ISREGEX, flag);
    }

    public boolean isUseRegex() {
        return getPropertyAsBoolean(ISREGEX, true);
    }

    @Override
    public AssertionResult getResult(SampleResult samplerResult) {
        AssertionResult result = new AssertionResult(getName());
        String responseData = samplerResult.getResponseDataAsString();
        if (responseData.isEmpty()) {
            return result.setResultForNull();
        }

        result.setFailure(false);
        result.setFailureMessage("");

        if (!isInvert()) {
            try {
                doAssert(responseData);
            } catch (Exception e) {
                log.debug("Assertion failed", e);
                result.setFailure(true);
                result.setFailureMessage(e.getMessage());
            }
        } else {
            try {
                doAssert(responseData);
                result.setFailure(true);
                if (isJsonValidationBool()) {
                    if (isExpectNull()) {
                        result.setFailureMessage("Failed that JSONPath " + getJsonPath() + " not matches null");
                    } else {
                        result.setFailureMessage("Failed that JSONPath " + getJsonPath() + " not matches " + getExpectedValue());
                    }
                } else {
                    result.setFailureMessage("Failed that JSONPath not exists: " + getJsonPath());
                }
            } catch (Exception e) {
                log.debug("Assertion failed, as expected", e);
            }
        }
        return result;
    }

    private void doAssert(String jsonString) {
        Object value = JsonPath.read(jsonString, getJsonPath());
        // 大于
        if (isGreaterThan()) {
            if (VerifyUtils.isGreaterThan(value, getExpectedValue())) {
                return;
            } else {
                String msg = "Value expected greaterThan to be '%s', but found '%s'";
                throw new IllegalStateException(String.format(msg, getExpectedValue(), objectToString(value)));
            }
        }

        // 大于等于
        if (isGreaterThanOrEqual()) {
            if (VerifyUtils.isGreaterThanOrEqual(value, getExpectedValue())) {
                return;
            } else {
                String msg = "Value expected greaterThanOrEqual to be '%s', but found '%s'";
                throw new IllegalStateException(String.format(msg, getExpectedValue(), objectToString(value)));
            }
        }

        // 小于
        if (isLessThan()) {
            if (VerifyUtils.isLessThan(value, getExpectedValue())) {
                return;
            } else {
                String msg = "Value expected lessThan to be '%s', but found '%s'";
                throw new IllegalStateException(String.format(msg, getExpectedValue(), objectToString(value)));
            }
        }

        // 小于等于
        if (isLessThanOrEqual()) {
            if (VerifyUtils.isLessThanOrEqual(value, getExpectedValue())) {
                return;
            } else {
                String msg = "Value expected lessThanOrEqual to be '%s', but found '%s'";
                throw new IllegalStateException(String.format(msg, getExpectedValue(), objectToString(value)));
            }
        }

        // 包含
        if (isContains()) {
            if (VerifyUtils.isContains(Objects.toString(value), getExpectedValue())) {
                return;
            } else {
                String msg = "Value expected contains to be '%s', but found '%s'";
                throw new IllegalStateException(String.format(msg, getExpectedValue(), objectToString(value)));
            }
        }

        if (!isJsonValidationBool()) {
            if (value instanceof JSONArray arrayValue) {
                if (arrayValue.isEmpty() && !JsonPath.isPathDefinite(getJsonPath())) {
                    throw new IllegalStateException("JSONPath is indefinite and the extracted Value is an empty Array." + " Please use an assertion value, to be sure to get a correct result. " + getExpectedValue());
                }
            }
            return;
        }

        if (value instanceof JSONArray) {
            if (arrayMatched((JSONArray) value)) {
                return;
            }
        } else {
            if ((isExpectNull() && value == null) || isEquals(value)) {
                return;
            }
        }

        if (isExpectNull()) {
            throw new IllegalStateException(String.format("Value expected to be: null, but found: \n %s", value));
        } else {
            String msg;
            if (isUseRegex()) {
                msg = "Value expected to match regexp '%s', but it did not match: '%s'";
            } else {
                msg = "Value expected to be: \n %s \n but found: \n %s";
            }
            throw new IllegalStateException(String.format(msg, getExpectedValue(), objectToString(value)));
        }
    }

    private boolean arrayMatched(JSONArray value) {
        if (value.isEmpty() && "[]".equals(getExpectedValue())) {
            return true;
        }

        for (Object subj : value.toArray()) {
            if ((subj == null && isExpectNull()) || isEquals(subj)) {
                return true;
            }
        }

        return isEquals(value);
    }

    private boolean isEquals(Object actual) {
        if (isUseRegex()) {
            String str = objectToString(actual);
            if (USE_JAVA_REGEX) {
                return JMeterUtils.compilePattern(getExpectedValue()).matcher(str).matches();
            } else {
                Pattern pattern = JMeterUtils.getPatternCache().getPattern(getExpectedValue());
                return JMeterUtils.getMatcher().matches(str, pattern);
            }
        } else {
            Object expected = JSONValue.parse(getExpectedValue());
            String jsonPathValue = getJsonPathValue();
            if (StringUtils.isBlank(jsonPathValue)) {
                return Objects.equals(expected, actual);
            }
            try {
                String expectedJson = toJsonString(expected);
                String actualJson = toJsonString(actual);
                String[] split = StringUtils.split(jsonPathValue, ",");
                for (String path : split) {
                    expectedJson = removeByJsonPath(expectedJson, path);
                    actualJson = removeByJsonPath(actualJson, path);
                }

                boolean equals = compare(expectedJson, actualJson);
                if (!equals) {
                    String msg = "Value expected to be: \n %s \n but found: \n %s";
                    throw new IllegalStateException(String.format(msg, expectedJson, actualJson));
                }
                return true;
            } catch (Exception e) {
                return Objects.equals(expected, actual);
            }
        }
    }

    public static String objectToString(Object subj) {
        String str;
        if (subj == null) {
            str = "null";
        } else if (subj instanceof Map) {
            //noinspection unchecked
            str = new JSONObject((Map<String, ?>) subj).toJSONString();
        } else if (subj instanceof Double || subj instanceof Float) {
            str = decimalFormatter.get().format(subj);
        } else {
            str = subj.toString();
        }
        return str;
    }

    @Override
    public void threadStarted() {
        // nothing to do on thread start
    }

    @Override
    public void threadFinished() {
        decimalFormatter.remove();
    }

    private String removeByJsonPath(String json, String path) {
        DocumentContext documentContext = JsonPath.parse(json);
        return documentContext.delete(path).jsonString();
    }

    private String toJsonString(Object json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(json);
    }

    private boolean compare(String source, String target) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode1 = objectMapper.readTree(source);
        JsonNode jsonNode2 = objectMapper.readTree(target);
        return jsonNode1.equals(jsonNode2);
    }
}