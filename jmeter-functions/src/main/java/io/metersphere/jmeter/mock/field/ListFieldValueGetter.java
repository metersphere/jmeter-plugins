package io.metersphere.jmeter.mock.field;

import io.metersphere.jmeter.mock.exception.MockException;
import io.metersphere.jmeter.mock.invoker.Invoker;
import io.metersphere.jmeter.mock.util.MethodUtils;
import io.metersphere.jmeter.mock.util.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * List集合的字段值获取器
 * 有较大的可能出现一些类型异常，请注意一定按照规范填写
 */
public class ListFieldValueGetter implements FieldValueGetter<List> {


    /**
     * 方法执行者们
     * 期望中 只有一个执行者
     * 不排除多个执行者的情况，
     * 但是如果是多个执行者，List集合很大可能是String、Integer之类的基础数据类型
     */
    private final Invoker[] invokers;

    /**
     * 区间参数,重复最终输出,参数期望中长度为2，0索引为最小值，1为最大值
     * 默认值为[1,1],即为不重复
     *
     * @since 1.7.0 当前版本替换为获取函数
     */
    private final Supplier<Integer[]> integerIntervalSupplier;

    /**
     * 多余字符
     * 集合字段的指令类参数中可能出现多余字符，当有多于字符的时候，list集合的类型有很大的概率是Sting、Integer之类的基础数据类型
     */
    private final String[] moreStrs;


    /**
     * 获取字段值
     */
    @Override
    public List value() {
        Integer[] integerInterval = integerIntervalSupplier.get();
        //创建一个Object类型的List集合，用于保存数据
        List list = new ArrayList();
        //获取执行次数
        Integer min = integerInterval[0];
        Integer max = integerInterval[1];
        int num = (max == null ? min : RandomUtils.getNumberWithRight(min, max));
        //判断执行者的数量
        if (invokers.length > 1) {

            //当执行者数量大于1，执行方法
            getValueWhenInvokersMoreThan1(num, list);

        } else if (invokers.length <= 0) {
            /*
             * 没有执行者的情况，创建并返回一个空的字符串集合
             * 一般情况下不会出现空执行者的情况,就算是没有可执行方法也会有空值执行者
             */
//            return new ArrayList<String>();
            return list;
        } else {

            //当执行者的数量不大于1的时候，执行方法
            getValueWhenInvokerIs1(num, list);
        }
        //返回结果
        return list;
    }

    /**
     * 当执行者的数量超过1的时候
     */
    private void getValueWhenInvokersMoreThan1(int num, List list) {
        //执行者数量大于1的情况下，只能将全部执行结果的toString拼接，并尝试使用eval进行执行
        //如果eval可以执行，则保存eval中得到的结果，如果无法执行则返回拼接字符串
        StringBuilder sb = new StringBuilder();
        //开始遍历并执行
        try {
            for (int i = 0; i < num; i++) {
                //执行全部执行者
                for (int j = 0; j < invokers.length; j++) {
                    //如果有多余字符，先拼接多余字符
                    if (moreStrs != null) {
                        sb.append(moreStrs[j]);
                    }
                    //拼接执行结果
                    sb.append(invokers[j].invoke());
                }

                //如果有多余字符且多余字符的数量比执行者多1
                //只要多余字符比执行者数量大，则说明多余字符的数量为执行者的数量+1
                if (moreStrs != null && moreStrs.length > invokers.length) {
                    //拼接多余字符的最后值
                    sb.append(moreStrs[moreStrs.length - 1]);
                }
                String invokeStr = sb.toString();
                //尝试使用eval进行执行
                Object eval = MethodUtils.evalCache(invokeStr);
                //如果能执行成功，保存这个执行结果到集合
                list.add(eval);
            }
        } catch (Exception e) {
            throw new MockException(e);
        }
    }


    /**
     * 当执行者数量不大于1的时候
     *
     * @param num
     * @param list
     */
    private void getValueWhenInvokerIs1(int num, List list) {
        //执行者数量不大于1，即只有一个
        Invoker invoker = invokers[0];
        //尽管只有一个方法执行者，但是仍然可能存在多余字符
        //所以分两种情况
        //在有多余字符的情况下，处理方式类似于上面的多执行者
        if (moreStrs != null) {
            //如果存在多余字符
            //准备拼接结果
            StringBuilder sb = new StringBuilder();
            //遍历num次数
            try {
                for (int i = 0; i < num; i++) {
                    //先拼接多余字符，再拼接方法执行结果
                    sb.append(moreStrs[i]);
                    //方法的执行结果
                    sb.append(invoker.invoke());
                    //如果多余字符有结尾，拼接
                    //由于只有一个执行者，所以如果多余字符数量大于1就说明有尾部多余
                    if (moreStrs.length > 1) {
                        //这里的元素索引不出意外的话，必定是2
                        sb.append(moreStrs[moreStrs.length - 1]);
                    }
                    String invokeData = sb.toString();
                    //尝试对结果进行eval
                    //如果执行成功，保存执行结果
                    Object eval = MethodUtils.evalCache(invokeData);
                    list.add(eval);
                }
            } catch (Exception e) {
                throw new MockException(e);
            }
        } else {
            //没有多余字符
            //只有一个方法执行者、且没有多余字符的情况是最稳定的情况。
            //指令参数类型情况下，这种类型只有及低的可能会出现类型异常（只要你的list类型没有填写错误）
            //遍历num次数，执行执行者并将结果保存至list集合
            try {
                for (int i = 0; i < num; i++) {
                    //执行结果
                    list.add(invoker.invoke());
                }
            } catch (Exception e) {
                throw new MockException(e);
                //如果执行出现错误，保存一个空值 null
//                list.add(null);
            }
        }
    }


    /**
     * 获取一个固定值的区间获取函数
     */
    static Supplier<Integer[]> normalIntegerIntervalSupplier() {
        Integer[] intervalsNew = new Integer[]{1, 1};
        return () -> intervalsNew;
    }

    /**
     * 获取一个固定值的区间获取函数
     *
     * @param intervals 固定区间
     */
    static Supplier<Integer[]> normalIntegerIntervalSupplier(Integer[] intervals) {
        Integer[] intervalsNew = Arrays.copyOf(intervals, intervals.length);
        return () -> intervalsNew;
    }

    /**
     * 根据两个区间来随机获取其中一个区间的函数
     *
     * @param intervals1 第一个区间
     * @param intervals2 第二个区间
     * @return 获取函数
     */
    static Supplier<Integer[]> normalIntegerIntervalSupplier(Integer[] intervals1, Integer[] intervals2) {
        Integer[] intervals1New = Arrays.copyOf(intervals1, intervals1.length);
        Integer[] intervals2New = Arrays.copyOf(intervals2, intervals2.length);
        return () -> ThreadLocalRandom.current().nextBoolean() ? intervals1New : intervals2New;
    }


    /**
     * 构造方法
     *
     * @param invokers        方法执行者
     * @param integerInterval 区间参数
     * @param moreStrs        多余字符
     */
    public ListFieldValueGetter(Invoker[] invokers, Integer[] integerInterval, String[] moreStrs) {
        this.invokers = invokers;
        //如果多余字符长度为0，则赋值为null
        this.moreStrs = moreStrs.length == 0 ? null : moreStrs;
        //如果为true，则使用默认的数组
        boolean isNull = integerInterval == null || integerInterval.length > 2 || integerInterval[0] == null || integerInterval[1] == null;
        if (isNull) {
            this.integerIntervalSupplier = normalIntegerIntervalSupplier();
        } else {
            this.integerIntervalSupplier = normalIntegerIntervalSupplier(integerInterval);
        }
    }

    /**
     * 构造方法
     *
     * @param invokers         方法执行者
     * @param integerInterval1 区间参数
     * @param integerInterval2 区间参数
     * @param moreStrs         多余字符
     */
    public ListFieldValueGetter(Invoker[] invokers, Integer[] integerInterval1, Integer[] integerInterval2, String[] moreStrs) {
        this.invokers = invokers;
        //如果多余字符长度为0，则赋值为null
        this.moreStrs = moreStrs.length == 0 ? null : moreStrs;
        //如果为true，则使用默认的数组
        boolean isNull1 = integerInterval1 == null || integerInterval1.length > 2 || integerInterval1[0] == null || integerInterval1[1] == null;
        boolean isNull2 = integerInterval2 == null || integerInterval2.length > 2 || integerInterval2[0] == null || integerInterval2[1] == null;

        Integer[] integerInterval1New = isNull1 ? new Integer[]{1, 1} : integerInterval1;
        Integer[] integerInterval2New = isNull2 ? integerInterval1New : integerInterval2;

        this.integerIntervalSupplier = normalIntegerIntervalSupplier(integerInterval1New, integerInterval2New);
    }

    /**
     * 构造方法，区间参数默认为[1,1]
     *
     * @param invokers 方法执行者
     * @param moreStrs 多余字符
     */
    public ListFieldValueGetter(Invoker[] invokers, String[] moreStrs) {
        this.invokers = invokers;
        this.integerIntervalSupplier = normalIntegerIntervalSupplier();
        //如果多余字符长度为0，则赋值为null
        this.moreStrs = moreStrs.length == 0 ? null : moreStrs;
    }

    /**
     * 构造方法，没有多余字符
     *
     * @param invokers        方法执行者
     * @param integerInterval 区间参数
     */
    public ListFieldValueGetter(Invoker[] invokers, Integer[] integerInterval) {
        this.invokers = invokers;
        //判断：数组为null || 长度大于2 || 左参数为null || 左右参数都为null
        //如果为true，则使用默认的数组
        boolean isNull = integerInterval == null || integerInterval.length > 2 || integerInterval[0] == null || integerInterval[1] == null;
        if (isNull) {
            this.integerIntervalSupplier = normalIntegerIntervalSupplier();
        } else {
            this.integerIntervalSupplier = normalIntegerIntervalSupplier(integerInterval);
        }
        //多余字符赋值为null
        this.moreStrs = null;
    }

    /**
     * 构造方法，没有多余字符
     *
     * @param invokers         方法执行者
     * @param integerInterval1 区间参数1
     * @param integerInterval2 区间参数2
     */
    public ListFieldValueGetter(Invoker[] invokers, Integer[] integerInterval1, Integer[] integerInterval2) {
        this.invokers = invokers;
        //判断：数组为null || 长度大于2 || 左参数为null || 左右参数都为null
        //如果为true，则使用默认的数组
        boolean isNull1 = integerInterval1 == null || integerInterval1.length > 2 || integerInterval1[0] == null || integerInterval1[1] == null;
        boolean isNull2 = integerInterval2 == null || integerInterval2.length > 2 || integerInterval2[0] == null || integerInterval2[1] == null;

        Integer[] integerInterval1New = isNull1 ? new Integer[]{1, 1} : integerInterval1;
        Integer[] integerInterval2New = isNull2 ? integerInterval1New : integerInterval2;

        this.integerIntervalSupplier = normalIntegerIntervalSupplier(integerInterval1New, integerInterval2New);

        //多余字符赋值为null
        this.moreStrs = null;
    }

    /**
     * 构造方法，没有多余字符，区间参数默认为[1,1]
     *
     * @param invokers 方法执行者
     */
    public ListFieldValueGetter(Invoker[] invokers) {
        this.invokers = invokers;
        this.integerIntervalSupplier = normalIntegerIntervalSupplier();
        //多余字符赋值为null
        this.moreStrs = null;
    }


}
