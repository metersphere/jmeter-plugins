package io.metersphere.jmeter.mock.factory;

import io.metersphere.jmeter.mock.bean.MockBean;
import io.metersphere.jmeter.mock.bean.MockField;
import io.metersphere.jmeter.mock.bean.MockMapBean;

/**
 * MockBean的工厂
 **/
public class MockBeanFactory {

    /**
     * 创建一个MockBean
     */
    public static <T> MockBean<T> createMockBean(Class<T> objectClass, MockField[] fields) {
        return new MockBean<>(objectClass, fields);
    }

    /**
     * 创建一个MockMapBean
     */
    public static MockMapBean createMockMapBean(MockField[] fields) {
        return new MockMapBean(fields);
    }

}
