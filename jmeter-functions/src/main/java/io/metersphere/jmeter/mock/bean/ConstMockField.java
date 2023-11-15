package io.metersphere.jmeter.mock.bean;

public class ConstMockField<T> extends MockField<T> {
    /**
     * 构造
     * @param fieldName 字段名称
     * @param fieldConstValue 字段值获取器
     * @param fieldType 字段类型
     */
    public ConstMockField(Class<T> objType, String fieldName, Object fieldConstValue, Class fieldType) {
        super(objType, fieldName, () -> fieldConstValue, fieldType);
    }
}
