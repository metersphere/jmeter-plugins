package io.metersphere.jmeter.mock.invoker;

import io.metersphere.jmeter.mock.util.RandomUtils;

/**
 *
 * 数组类型的{@link ElementInvoker}
 */
public class ArrayElementInvoker<T> extends ElementInvoker<T> {

    /** 集合参数 */
    private T[] arr;
    /**
     * 数组构造
     * @param arr
     */
    public ArrayElementInvoker(T[] arr){
        this.arr = arr;
    }

    @Override
    public T getRandomElement() {
        return RandomUtils.getRandomElement(arr);
    }
}
