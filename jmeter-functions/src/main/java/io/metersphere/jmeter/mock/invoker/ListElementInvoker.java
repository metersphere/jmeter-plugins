package io.metersphere.jmeter.mock.invoker;

import io.metersphere.jmeter.mock.util.RandomUtils;

import java.util.List;

/**
 *
 * list类型的{@link ElementInvoker}
 */
public class ListElementInvoker<T> extends ElementInvoker<T> {
    /** 集合参数 */
    private List<T> list;



    /**
     * 集合构造
     * @param list
     */
    public ListElementInvoker(List<T> list){
        this.list = list;
    }

    @Override
    public T getRandomElement() {
        return RandomUtils.getRandomElement(list);
    }
}
