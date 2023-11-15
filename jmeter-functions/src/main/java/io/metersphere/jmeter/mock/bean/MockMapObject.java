package io.metersphere.jmeter.mock.bean;

import java.util.Map;

/**
 * Map类型的结果集合
 */
public class MockMapObject implements MockObject<Map> {

    private final MockMapBean mockMapBean;

    @Override
    public MockBean<Map> getMockBean() {
        return mockMapBean;
    }


    /**
     * @return
     */
    @Override
    public Map<String, Object> getOne() {
        return mockMapBean.getObject();
    }

    /**
     * 唯一构造
     *
     * @param mockMapBean
     */
    public MockMapObject(MockMapBean mockMapBean) {
        this.mockMapBean = mockMapBean;
    }

}
