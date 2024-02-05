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

package io.metersphere.assertions.gui;

import io.metersphere.assertions.XMLAssertion;
import io.metersphere.assertions.constants.JSONAssertionCondition;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.StringUtils;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Java class representing GUI for the {} component in JMeter
 *
 * @since 4.0
 */
@GUIMenuSortOrder(2)
public class XMLAssertionGui extends AbstractAssertionGui implements ChangeListener {

    @Serial
    private static final long serialVersionUID = -6008018002423594040L;
    private static final String JSON_ASSERTION_PATH = "XML表达式";
    private static final String JSON_ASSERTION_EXPECTED_VALUE = "预期值";
    private static final String JSON_PATH_VALUE = "选择以下操作符:";

    private static final String JSON_ASSERTION_TITLE = "XML断言";

    protected JTextField jsonPath = null;
    protected JSyntaxTextArea jsonValue = null;
    protected JCheckBox jsonChecked = null;

    protected Map<JSONAssertionCondition, JRadioButton> conditionButtonMap = new HashMap<>();

    @Override
    public String getStaticLabel() {
        return "MS XML Assertion";
    }

    public XMLAssertionGui() {
        init();
    }

    void init() {
        setLayout(new BorderLayout());
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);

        JPanel panel = buildPanel();
        add(panel, BorderLayout.CENTER);

        jsonChecked.addChangeListener(this);

        conditionButtonMap.values()
                .forEach(button -> button.addChangeListener(this));
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

        ButtonGroup buttonGroup = new ButtonGroup();
        JSONAssertionCondition[] conditions = JSONAssertionCondition.values();
        for (JSONAssertionCondition condition : conditions) {
            JRadioButton jRadioButton = new JRadioButton();
            panel.add(new JLabel(condition.getLabel()));
            panel.add(jRadioButton, "span");
            buttonGroup.add(jRadioButton);
            conditionButtonMap.put(condition, jRadioButton);
        }

        jsonValue = JSyntaxTextArea.getInstance(5, 60);
        JLabel json_assertion_expected_value = new JLabel(JSON_ASSERTION_EXPECTED_VALUE);
        panel.add(json_assertion_expected_value);
        panel.add(JTextScrollPane.getInstance(jsonValue));

        return panel;
    }

    @Override
    public void clearGui() {
        super.clearGui();
        jsonPath.setText("");
        jsonValue.setText("");
        jsonChecked.setSelected(true);

        conditionButtonMap.values()
                .forEach(button -> button.setSelected(false));
    }

    @Override
    public TestElement createTestElement() {
        XMLAssertion jpAssertion = new XMLAssertion();
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
        if (element instanceof XMLAssertion jpAssertion) {
            jpAssertion.setJsonPath(jsonPath.getText());
            jpAssertion.setExpectedValue(jsonValue.getText());
            conditionButtonMap.forEach((condition, button) -> {
                if (button.isSelected()) {
                    jpAssertion.setCondition(condition.name());
                }
            });
        }
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof XMLAssertion jpAssertion) {
            jsonPath.setText(jpAssertion.getJsonPath());
            jsonValue.setText(jpAssertion.getExpectedValue());
            jpAssertion.setJsonValidationBool(jsonChecked.isSelected());

            String condition = jpAssertion.getCondition();
            if (StringUtils.isBlank(condition)) {
                condition = JSONAssertionCondition.REGEX.name();
            }
            conditionButtonMap.get(JSONAssertionCondition.valueOf(condition))
                    .setSelected(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }
}
