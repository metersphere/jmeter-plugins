package io.metersphere.jmeter.mock.mapper;

import java.util.function.IntFunction;

/**
 * 部分指定好的转化器类型
 * 接口类型，内部提供部分ArrayMapper实现类
 *
 * @since JDK21
 **/
public interface ArrayMapperType {

    /**
     * 保持String类型不变
     */
    class ToString implements ArrayMapper<String> {

        @Override
        public IntFunction<String[]> getArrayParseFunction() {
            return String[]::new;
        }

        @Override
        public String apply(String s) {
            return s;
        }
    }

    /**
     * 转化为Int类型
     */
    class ToInt implements ArrayMapper<Integer> {
        @Override
        public Integer apply(String s) {
            return Integer.parseInt(s);
        }

        @Override
        public IntFunction<Integer[]> getArrayParseFunction() {
            return Integer[]::new;
        }
    }

    /**
     * 转化为Double类型
     */
    class ToDouble implements ArrayMapper<Double> {
        @Override
        public IntFunction<Double[]> getArrayParseFunction() {
            return Double[]::new;
        }

        @Override
        public Double apply(String s) {
            return Double.parseDouble(s);
        }
    }

    /**
     * 转化为Long类型
     */
    class ToLong implements ArrayMapper<Long> {

        @Override
        public IntFunction<Long[]> getArrayParseFunction() {
            return Long[]::new;
        }

        @Override
        public Long apply(String s) {
            return Long.parseLong(s);
        }
    }


}
