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

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import io.metersphere.assertions.gui.JSONAssertionCondition;
import io.metersphere.assertions.util.VerifyUtils;
import org.apache.jmeter.assertions.Assertion;
import org.apache.jmeter.assertions.AssertionResult;
import org.apache.jmeter.assertions.LogErrorHandler;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.ThreadListener;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.function.BiConsumer;

/**
 * Checks if the result is a well-formed XML content using {@link XMLReader}
 *
 */
public class XMLAssertion extends AbstractTestElement implements Serializable, Assertion, ThreadListener {
    private static final Logger log = LoggerFactory.getLogger(XMLAssertion.class);
    private static final long serialVersionUID = 242L;
    public String getXmlPath() {
        return this.getPropertyAsString("XML_PATH");
    }

    public String getExpectedValue() {
        return this.getPropertyAsString("EXPECTED_VALUE");
    }

    public String getCondition() {
        return getPropertyAsString("ElementCondition");
    }

    private static DecimalFormat createDecimalFormat() {
        DecimalFormat decimalFormatter = new DecimalFormat("#.#");
        decimalFormatter.setMaximumFractionDigits(340);
        decimalFormatter.setMinimumFractionDigits(1);
        return decimalFormatter;
    }

    // one builder for all requests in a thread
    private static final ThreadLocal<XMLReader> XML_READER = new ThreadLocal<XMLReader>() {
        @Override
        protected XMLReader initialValue() {
            try {
                XMLReader reader = SAXParserFactory.newInstance()
                        .newSAXParser()
                        .getXMLReader();
                reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                return reader;
            } catch (SAXException | ParserConfigurationException e) {
                log.error("Error initializing XMLReader in XMLAssertion", e);
                return null;
            }
        }
    };

    /**
     * Returns the result of the Assertion.
     * Here it checks whether the Sample data is XML.
     * If so an AssertionResult containing a FailureMessage will be returned.
     * Otherwise the returned AssertionResult will reflect the success of the Sample.
     */
    @Override
    public AssertionResult getResult(SampleResult response) {
        // no error as default
        AssertionResult result = new AssertionResult(getName());
        String resultData = response.getResponseDataAsString();
        log.info("转成jsonobject的对象" + resultData);
        if (resultData.length() == 0) {
            return result.setResultForNull();
        }
        result.setFailure(false);
        XMLReader builder = XML_READER.get();
        log.info("转成jsonobject的对象" + resultData);
        if (builder != null) {
            try {
                builder.setErrorHandler(new LogErrorHandler());
                builder.parse(new InputSource(new StringReader(resultData)));
                try {
                    log.info("转成jsonobject的对象" + resultData);
                    JSONObject xmlJSONObj = XML.toJSONObject(resultData);
                    log.info("转成jsonobject的对象" + xmlJSONObj);
                    String jsonString = xmlJSONObj.toString(4);
                    log.info("测试的转成json的xml" + jsonString);
                    Object actualValue = JsonPath.read(jsonString, this.getXmlPath(), new Predicate[0]);
                    log.info("转化的对象是啥",actualValue.toString());
                    String jsonPathExpression = getXmlPath();
                    if (!JsonPath.isPathDefinite(jsonPathExpression)) {
                        // 没有勾选匹配值，只检查表达式是否正确
                        throw new IllegalStateException("JSONPath is indefinite");
                    }
                    JSONAssertionCondition condition = JSONAssertionCondition.valueOf(getCondition());

                    VerifyUtils.jsonPathValue.set(jsonPathExpression);
                    BiConsumer<Object, String> assertMethod = condition.getAssertMethod();
                    if (assertMethod != null) {
                        assertMethod.accept(actualValue, getExpectedValue());
                    }
                } catch (Exception e) {
                    result.setError(true);
                    result.setFailure(true);
                    result.setFailureMessage(e.getMessage());
                }
            } catch (SAXException | IOException e) {
                result.setError(true);
                result.setFailure(true);
                result.setFailureMessage(e.getMessage());
            }
        } else {
            result.setError(true);
            result.setFailureMessage("Cannot initialize XMLReader in element:" + getName() + ", check jmeter.log file");
        }

        return result;
    }

    @Override
    public void threadStarted() {
        // nothing to do on thread start
    }

    @Override
    public void threadFinished() {
        XML_READER.remove();
    }
}
