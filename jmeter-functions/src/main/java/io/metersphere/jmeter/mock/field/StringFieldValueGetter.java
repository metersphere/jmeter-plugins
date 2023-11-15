package io.metersphere.jmeter.mock.field;


import io.metersphere.jmeter.mock.exception.MockException;
import io.metersphere.jmeter.mock.invoker.Invoker;
import io.metersphere.jmeter.mock.util.MethodUtils;
import io.metersphere.jmeter.mock.util.RandomUtils;

import java.util.Collections;
import java.util.function.Supplier;

/**
 * 字符串类型字段值的获取者
 */
public class StringFieldValueGetter implements FieldValueGetter<String> {

    /**
     * 方法执行者，顺序与解析出来的方法和多余字符之间的顺序一致
     */
    private final Invoker[] invokers;

    /**
     * 多余字符，如果不为null，则长度必然是 方法执行者invokers 的数量的+1或者相同
     */
    private final String[] moreStr;


    /**
     * 获取重复次数的获取函数。
     */
    private final Supplier<Integer> timeSupplier;


    /**
     * 获取字段值
     */
    @Override
    public String value() {
        StringBuilder sb = new StringBuilder(32);
        //同时遍历方法与多余字符,使用methods遍历
        int i = 0;
        int invokerLength = invokers.length;
        try {
            for (; i < invokerLength; i++) {
                //如果有多余字符，先存多余字符，后存执行结果
                if (moreStr != null) {
                    sb.append(moreStr[i]);
                }

                sb.append(invokers[i].invoke());
            }
        } catch (Exception e) {
            throw new MockException(e);
        }
        //如果多余字符不为空
        //判断多余字符的数量：
        // 如果数量相等，说明在最后的方法后面没有多余参数，
        // 如果数量多1，则说明在最后的方法后面还有多余字符
        //如果尾部有多余字符，添加
        if (moreStr != null && moreStr.length > invokerLength) {
            sb.append(moreStr[i]);
        }

        //重复输出，次数为integerInterval的参数
        //如果没有右参数，重复次数则为左参数
//        int times;
        int times = timeSupplier.get();
//        if(integerInterval[1] == null){
//            times = integerInterval[0];
//        }else{
//            int min = integerInterval[0];
//            int max = integerInterval[1];
//            times = RandomUtil.getNumberWithRight(min , max);
//        }

        //有些少数情况，end中拼接后的字符串是可以作为简单JS代码执行的，在此处重复字符串之前，尝试使用eval进行执行
        String end = String.valueOf(MethodUtils.evalCache(sb.toString()));


        //重复次数并返回
        if (times <= 1) {
            return end;
        } else {
            return String.join("", Collections.nCopies(times, end));
        }
    }


    /**
     * 构造
     */
    public StringFieldValueGetter(Invoker[] invokers, String[] moreStr, Integer[] integerInterval) {
        this.invokers = invokers;
        this.moreStr = moreStr;
        //判断：数组为null || 长度大于2 || 左参数为null || 左右参数都为null
        //如果为true，则使用默认的数组
        boolean isNull = integerInterval == null || integerInterval.length > 2 || integerInterval[0] == null || integerInterval[1] == null;
        if (isNull) {
            this.timeSupplier = () -> 1;
        } else {
            int min = integerInterval[0];
            int max = integerInterval[1];
            this.timeSupplier = () -> RandomUtils.getNumberWithRight(min, max);
        }
    }

    /**
     * 构造，区间参数默认为[1-1]
     */
    public StringFieldValueGetter(Invoker[] invokers, String[] moreStr) {
        this.invokers = invokers;
        this.moreStr = moreStr;
        //区间为默认值
        this.timeSupplier = () -> 1;
    }

}
