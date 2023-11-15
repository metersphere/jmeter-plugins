package io.metersphere.jmeter.mock.field;

import io.metersphere.jmeter.mock.exception.MockException;
import io.metersphere.jmeter.mock.invoker.Invoker;


/**
 * 整数类型字段值获取器
 * 
 */
public class IntegerFieldValueGetter implements FieldValueGetter<Integer> {

    /**
     * 方法执行者用于获取整数类型的字段值
     * 执行者必然只有一个
     */
    private Invoker invoker;


    /**
     * 获取一个整数类型的字段值
     * @return
     */
    @Override
    public Integer value() {
        try {
            return (Integer) invoker.invoke();
        } catch (Exception e) {
            throw new MockException(e);
        }
    }


    /**
     * 构造方法，只需要一个方法执行者
     */
    public IntegerFieldValueGetter(Invoker invoker) {
        this.invoker = invoker;
    }


}
