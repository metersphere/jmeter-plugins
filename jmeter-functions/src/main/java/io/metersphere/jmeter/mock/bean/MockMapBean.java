package io.metersphere.jmeter.mock.bean;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class MockMapBean extends MockBean<Map> {


    /**
     * 重写MockBean的方法，返回Map封装对象
     * @return
     */
    @Override
    public Map<String, Object> getObject(){
        //假字段集
        MockField[] fields = this.getFields();

        Map<String, Object> map = new LinkedHashMap<>();

        for (MockField field : fields) {
            map.merge(field.getFieldName(), field.getValue(), (old, val) -> throwingMerger());
        }

        return map;
    }


    /**
     * 构造方法
     *
     * @param fields
     */
    public MockMapBean(MockField[] fields) {
        super(Map.class, fields);
    }

    /**
     * 此方法来自 {@link Collectors#throwingMerger()}
     * @param <T>
     * @return
     */
    @SuppressWarnings("JavadocReference")
    protected static <T> BinaryOperator<T> throwingMerger() {
        return (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); };
    }

}
