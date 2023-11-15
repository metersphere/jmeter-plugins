package io.metersphere.jmeter.mock.factory;

import java.util.function.Supplier;

/**
 * 就是一个类的一层封装类
 */
public class SimpleBean<T> implements Supplier<T> {

    private T bean = null;

    public SimpleBean(){ }
    public SimpleBean(T bean){
        this.bean = bean;
    }

    public void set(T bean){
        this.bean = bean;
    }

    /**
     * Gets a result.
     * @return a result
     */
    @Override
    public T get() {
        return bean;
    }
}
