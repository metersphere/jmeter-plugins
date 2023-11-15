package io.metersphere.jmeter.mock.field;

/**
 * 字段值获取器
 *
 * 
 */
@FunctionalInterface
public interface FieldValueGetter<T> {

    /**
     * 获取这个字段的参数
     */
    T value();

}
