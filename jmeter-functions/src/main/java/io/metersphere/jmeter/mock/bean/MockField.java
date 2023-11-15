package io.metersphere.jmeter.mock.bean;

import io.metersphere.jmeter.mock.field.FieldValueGetter;
import io.metersphere.jmeter.mock.util.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 假字段值的字段封装对象的抽象类
 * 假的字段有几种类型：
 * <p>
 * 字符串
 * 整数
 * 浮点数
 * 集合
 * 数组
 * 引用对象
 */
public class MockField<T> {

    private final Class<T> objType;

    /**
     * 字段值获取器
     */
    private FieldValueGetter valueGetter;

    /**
     * 字段名称
     */
    private final String fieldName;

    /**
     * 字段类型
     */
    private final Class fieldType;


    private final Field field;


    private final Method setterMethod;


    /**
     * 为传入的对象的对应的参数赋值
     * 通过FieldUtils工具类使用setter方法赋值
     *
     * @param
     * @see FieldUtils
     */
    public void setValue(Object bean) throws Exception {
        FieldUtils.objectSetter(bean, fieldName, getValue());
    }

    /**
     * 获取字段的值
     *
     * @return 字段的值
     */
    public Object getValue() {
        return valueGetter.value();
    }

    /**
     * 获取字段的名称
     *
     * @return 字段名
     */
    public String getFieldName() {
        return this.fieldName;
    }


    public Class getFieldType() {
        return this.fieldType;
    }

    public Class<T> getObjType() {
        return objType;
    }

    public FieldValueGetter getValueGetter() {
        return valueGetter;
    }

    public Field getField() {
        return field;
    }

    public Method getSetterMethod() {
        return setterMethod;
    }

    public void setValueGetter(FieldValueGetter valueGetter) {
        this.valueGetter = valueGetter;
    }

    /**
     * 构造
     */
    public MockField(Class<T> objType, String fieldName, FieldValueGetter fieldValueGetter, Class fieldType) {
        this.objType = objType;
        this.fieldName = fieldName;
        this.valueGetter = fieldValueGetter;
        this.fieldType = fieldType;

        // 获取field
        if (objType != null) {
            this.field = FieldUtils.getField(objType, fieldName);
            this.field.setAccessible(true);
            this.setterMethod = FieldUtils.getFieldSetter(objType, this.field);
        } else {
            // 当类型为Map类型的时候，objType为null，因此field为null。
            this.field = null;
            this.setterMethod = null;
        }
    }

    @Override
    public String toString() {
        return "MockField(StringName='" + fieldName + "')";
    }
}
