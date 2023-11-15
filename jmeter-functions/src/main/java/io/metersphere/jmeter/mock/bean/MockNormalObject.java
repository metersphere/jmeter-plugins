package io.metersphere.jmeter.mock.bean;

/**
 * 将{@link MockBean}封装并返回
 **/
public class MockNormalObject<T> implements MockObject<T> {

    private final MockBean<T> mockBean;

    @Override
    public MockBean<T> getMockBean() {
        return mockBean;
    }


    @Override
    public T getOne() {
        return mockBean.getObject();
    }

    /**
     * 唯一构造
     */
    public MockNormalObject(MockBean<T> mockBean) {
        this.mockBean = mockBean;
    }
}
