package io.metersphere.jmeter.mock.mapper;

/**
 * 三个参数的function
 */
@FunctionalInterface
public interface ExFunction<T, U1, U2, R>  {

    /**
     * 接收三个参数，返回一个结果
     */
    R apply(T t, U1 u1, U2 u2);
}
