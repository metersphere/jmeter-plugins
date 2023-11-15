package io.metersphere.jmeter.mock.factory;

import io.metersphere.jmeter.mock.bean.*;

/**
 * MockObject对象工厂
 */
public class MockObjectFactory {

    /**
     * 创建一个普通mock对象
     */
    public static <T> MockObject<T> createNormalObj(MockBean<T> mockBean) {
        return new MockNormalObject<>(mockBean);
    }


    /**
     * 创建一个map类型的mock对象
     */
    public static MockMapObject createMapObj(MockMapBean mockMapBean) {
        return new MockMapObject(mockMapBean);
    }

}
