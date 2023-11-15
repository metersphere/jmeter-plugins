package io.metersphere.jmeter.mock.field;

import io.metersphere.jmeter.mock.exception.MockException;
import io.metersphere.jmeter.mock.invoker.Invoker;


/**
 * Double类型字段值获取器
 * 既然使用了此字段值获取器，则说明已经确定了字段的类型，则必定不会出现多个执行者。
 *
 * 
 */
public class DoubleFieldValueGetter implements FieldValueGetter<Double> {

    /**
     * 方法执行者，用于获取double值
     * 方法执行者必定为1个
     */
    private Invoker invoker;

    @Override
    public Double value() {
        //直接返回执行结果
        try {
            return (Double) invoker.invoke();
        } catch (Exception e) {
            throw new MockException(e);
        }
    }


    /**
     * 构造方法，只需要一个方法执行者
     */
    public DoubleFieldValueGetter(Invoker invoker) {
        this.invoker = invoker;
    }

}
