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

package io.metersphere.jmeter.gui;

import io.metersphere.jmeter.JsonPathAssertion;
import net.miginfocom.swing.MigLayout;
import org.apache.jmeter.assertions.gui.AbstractAssertionGui;
import org.apache.jmeter.gui.GUIMenuSortOrder;
import org.apache.jmeter.gui.util.JSyntaxTextArea;
import org.apache.jmeter.gui.util.JTextScrollPane;
import org.apache.jmeter.testelement.TestElement;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.Serial;

/**
 * Java class representing GUI for the {@link JsonPathAssertion} component in JMeter
 *
 * @since 4.0
 */
@GUIMenuSortOrder(2)
public class JsonPathAssertionGui extends AbstractAssertionGui implements ChangeListener {

    @Serial
    private static final long serialVersionUID = -6008018002423594040L;
    private static final String JSON_ASSERTION_PATH = "JSONPath表达式";
    private static final String JSON_ASSERTION_VALIDATION = "等于";
    private static final String JSON_ASSERTION_GREATERTHAN = "大于";
    private static final String JSON_ASSERTION_GREATERTHANOREQUAL = "大于等于";
    private static final String JSON_ASSERTION_LESSTHAN = "小于";
    private static final String JSON_ASSERTION_LESSTHANOREQUAL = "小于等于";
    private static final String JSON_ASSERTION_CONTAINS = "包含";
    private static final String JSON_ASSERTION_REGEX = "匹配正则表达式";
    private static final String JSON_ASSERTION_EXPECTED_VALUE = "预期值";
    private static final String JSON_PATH_VALUE = "选择以下操作符:";
    private static final String JSON_ASSERTION_NULL = "预期值为空";
    private static final String JSON_ASSERTION_INVERT = "断言结果取反";
    private static final String JSON_ASSERTION_TITLE = "JSON断言";

    protected JTextField jsonPath = null;
    protected JSyntaxTextArea jsonValue = null;
    protected JRadioButton jsonValidation = null;
    protected JCheckBox jsonChecked = null;
    protected JRadioButton expectNull = null;
    protected JRadioButton invert = null;
    protected JRadioButton isRegex;
    protected JRadioButton greaterThan = null;
    protected JRadioButton greaterThanOrEqual = null;
    protected JRadioButton lessThan = null;
    protected JRadioButton lessThanOrEqual = null;
    protected JRadioButton contains = null;

    @Override
    public String getStaticLabel() {
        return "MS JSON Assertion";
    }

    public JsonPathAssertionGui() {
        init();
    }

    void init() {
        setLayout(new BorderLayout());
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);

        JPanel panel = buildPanel();
        add(panel, BorderLayout.CENTER);

        jsonValidation.addChangeListener(this);
        jsonChecked.addChangeListener(this);
        expectNull.addChangeListener(this);
        isRegex.addChangeListener(this);
        greaterThan.addChangeListener(this);
        greaterThanOrEqual.addChangeListener(this);
        lessThan.addChangeListener(this);
        lessThanOrEqual.addChangeListener(this);
        contains.addChangeListener(this);
    }

    protected JPanel buildPanel() {
        JPanel panel = new JPanel(new MigLayout("fillx, wrap 2, insets 0", "[][fill,grow]"));

        jsonPath = new JTextField();
        JLabel path = new JLabel(JSON_ASSERTION_PATH);
        panel.add(path);
        panel.add(jsonPath, "span, growx");

        // 选项
        jsonChecked = new JCheckBox();
        JLabel checkedLabel = new JLabel("是否匹配值");
        panel.add(checkedLabel);
        panel.add(jsonChecked, "span");

        JLabel json_path_value = new JLabel(JSON_PATH_VALUE);
        panel.add(json_path_value, "span");

        jsonValidation = new JRadioButton();
        JLabel json_assertion_validation = new JLabel(JSON_ASSERTION_VALIDATION);
        panel.add(json_assertion_validation);
        panel.add(jsonValidation, "span");

        greaterThan = new JRadioButton();
        JLabel json_assertion_gt = new JLabel(JSON_ASSERTION_GREATERTHAN);
        panel.add(json_assertion_gt);
        panel.add(greaterThan, "span");

        greaterThanOrEqual = new JRadioButton();
        JLabel json_assertion_gte = new JLabel(JSON_ASSERTION_GREATERTHANOREQUAL);
        panel.add(json_assertion_gte);
        panel.add(greaterThanOrEqual, "span");

        lessThan = new JRadioButton();
        JLabel json_assertion_lt = new JLabel(JSON_ASSERTION_LESSTHAN);
        panel.add(json_assertion_lt);
        panel.add(lessThan, "span");

        lessThanOrEqual = new JRadioButton();
        JLabel json_assertion_lte = new JLabel(JSON_ASSERTION_LESSTHANOREQUAL);
        panel.add(json_assertion_lte);
        panel.add(lessThanOrEqual, "span");

        contains = new JRadioButton();
        JLabel json_assertion_contains = new JLabel(JSON_ASSERTION_CONTAINS);
        panel.add(json_assertion_contains);
        panel.add(contains, "span");

        isRegex = new JRadioButton();
        JLabel json_assertion_regex = new JLabel(JSON_ASSERTION_REGEX);
        panel.add(json_assertion_regex);
        panel.add(isRegex, "span");

        expectNull = new JRadioButton();
        JLabel json_assertion_null = new JLabel(JSON_ASSERTION_NULL);
        panel.add(json_assertion_null);
        panel.add(expectNull, "span");

        invert = new JRadioButton();
        JLabel json_assertion_invert = new JLabel(JSON_ASSERTION_INVERT);
        panel.add(json_assertion_invert);
        panel.add(invert, "span");

        jsonValue = JSyntaxTextArea.getInstance(5, 60);
        JLabel json_assertion_expected_value = new JLabel(JSON_ASSERTION_EXPECTED_VALUE);
        panel.add(json_assertion_expected_value);
        panel.add(JTextScrollPane.getInstance(jsonValue));


        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jsonValidation);
        buttonGroup.add(greaterThan);
        buttonGroup.add(greaterThanOrEqual);
        buttonGroup.add(lessThan);
        buttonGroup.add(lessThanOrEqual);
        buttonGroup.add(contains);
        buttonGroup.add(isRegex);
        buttonGroup.add(expectNull);
        buttonGroup.add(invert);

        return panel;
    }

    @Override
    public void clearGui() {
        super.clearGui();
        jsonPath.setText("");
        jsonValue.setText("");
        jsonValidation.setSelected(false);
        jsonChecked.setSelected(true);
        expectNull.setSelected(false);
        invert.setSelected(false);
        isRegex.setSelected(true);
        greaterThan.setSelected(false);
        greaterThanOrEqual.setSelected(false);
        lessThan.setSelected(false);
        lessThanOrEqual.setSelected(false);
        contains.setSelected(false);

    }

    @Override
    public TestElement createTestElement() {
        JsonPathAssertion jpAssertion = new JsonPathAssertion();
        modifyTestElement(jpAssertion);
        return jpAssertion;
    }

    @Override
    public String getLabelResource() {
        return JSON_ASSERTION_TITLE;
    }

    @Override
    public void modifyTestElement(TestElement element) {
        super.configureTestElement(element);
        if (element instanceof JsonPathAssertion jpAssertion) {
            jpAssertion.setJsonPath(jsonPath.getText());
            jpAssertion.setExpectedValue(jsonValue.getText());
            jpAssertion.setJsonValidationBool(jsonValidation.isSelected());
            jpAssertion.setJsonValidationBool(jsonChecked.isSelected());
            jpAssertion.setExpectNull(expectNull.isSelected());
            jpAssertion.setInvert(invert.isSelected());
            jpAssertion.setIsRegex(isRegex.isSelected());
            jpAssertion.setGreaterThan(greaterThan.isSelected());
            jpAssertion.setGreaterThanOrEqual(greaterThanOrEqual.isSelected());
            jpAssertion.setLessThan(lessThan.isSelected());
            jpAssertion.setLessThanOrEqual(lessThanOrEqual.isSelected());
            jpAssertion.setContains(contains.isSelected());
        }
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof JsonPathAssertion jpAssertion) {
            jsonPath.setText(jpAssertion.getJsonPath());
            jsonValue.setText(jpAssertion.getExpectedValue());
            jsonValidation.setSelected(jpAssertion.isJsonValidationBool());
            jsonChecked.setSelected(jpAssertion.isJsonValidationBool());
            expectNull.setSelected(jpAssertion.isExpectNull());
            invert.setSelected(jpAssertion.isInvert());
            isRegex.setSelected(jpAssertion.isUseRegex());
            greaterThan.setSelected(jpAssertion.isGreaterThan());
            greaterThanOrEqual.setSelected(jpAssertion.isGreaterThanOrEqual());
            lessThan.setSelected(jpAssertion.isLessThan());
            lessThanOrEqual.setSelected(jpAssertion.isLessThanOrEqual());
            contains.setSelected(jpAssertion.isContains());
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }
}
