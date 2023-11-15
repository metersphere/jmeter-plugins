package io.metersphere.jmeter.mock.bean;

import io.metersphere.jmeter.mock.exception.MockException;

import java.util.Arrays;

public class ParallelMockBean<T> extends MockBean<T> {


    /**
     * 获取对象一个对象
     * @return
     */
    public T getObject() {
        //先创建一个实例
        T instance;
        try {
            instance = objectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
        Arrays.stream(fields).parallel().forEach(field -> {
            try {
                field.setValue(instance);
            } catch (Exception e) {
                // ignored ?
                throw new MockException(e);
            }
        });
        //返回这个实例
        return instance;
    }


    public MockBean<T> parallel(){
        return this;
    }


    public MockBean<T> sequential(){
        return new MockBean<>(objectClass, Arrays.copyOf(fields, fields.length));
    }


    /**
     * 构造方法
     */
    public ParallelMockBean(Class<T> objectClass, MockField[] fields) {
        super(objectClass, fields);
    }
}
