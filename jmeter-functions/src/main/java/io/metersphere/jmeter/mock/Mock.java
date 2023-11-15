package io.metersphere.jmeter.mock;


import io.metersphere.jmeter.mock.bean.*;
import io.metersphere.jmeter.mock.exception.MockException;
import io.metersphere.jmeter.mock.factory.MockMapperFactory;
import io.metersphere.jmeter.mock.factory.MockObjectFactory;
import io.metersphere.jmeter.mock.function.FunctionApply;
import io.metersphere.jmeter.mock.parser.ParameterParser;
import io.metersphere.jmeter.mock.util.MockUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * javaBean假数据生成工具
 */
public class Mock {

    /* 静态代码块加载资源 */
    static {
        //创建线程安全的map集合,保存全部映射记录
        MOCK_OBJECT = new ConcurrentHashMap<>(4);
        MOCK_MAP = new ConcurrentHashMap<>(4);

        //创建map，这里的map理论上不需要线程同步
        Map<String, Method> mockUtilMethods;

        //加载这些方法，防止每次都使用反射去调用方法。
        //直接调用的话无法掌控参数，所以必须使用反射的形式进行调用
        Class<MockUtils> mockUtilClass = MockUtils.class;
        //只获取公共方法
        Method[] methods = mockUtilClass.getMethods();
        /*
            过滤Object中的方法、
            将MockUtil中的全部方法名格式化 格式：方法名(参数类型class地址，参数类型class地址.....)、
            转化为<方法名：方法>的map集合
         */
        mockUtilMethods =
                Arrays.stream(methods)
                        //过滤掉Object中继承过来的方法
                        .filter(m -> Arrays.stream(Object.class.getMethods()).noneMatch(om -> om.equals(m)))
                        //格式化方法名，格式：方法名(参数类型class地址，参数类型class地址.....)
                        .flatMap(m -> {
                            Map<String, Method> methodMap = new HashMap<>();
                            //格式化方法名，并作为key
                            String key = m.getName() + "("
                                    + Arrays.stream(m.getParameterTypes())
                                    .map(Class::getName)
                                    .collect(Collectors.joining(",")) +
                                    ")";
                            methodMap.put(key, m);
                            return methodMap.entrySet().stream();
                        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        //保存MockUtil中的全部方法
        MOCK_METHOD = mockUtilMethods;
    }

    /**
     * 保存全部记录的class与其对应的假对象{@link MockBean}
     */
    private static final Map<Class, MockNormalObject> MOCK_OBJECT;

    /**
     * Map类型假对象
     * TODO 后期考虑合并MOCK_OBJECT 和 MOCK_MAP两个字段
     */
    private static final Map<String, MockMapObject> MOCK_MAP;


    /**
     * MockUtil中的全部方法
     */
    private static final Map<String, Method> MOCK_METHOD;


    /**
     * 添加一个数据映射
     *
     * @param objClass 映射类型
     * @param map      映射对应值
     * @return 映射结果表
     */
    public static <T> MockBean<T> setResult(Class<T> objClass, Map<String, Object> map, boolean reset) {
        //如果不是重新设置且此映射已经存在，并且objClass对象存在，将会抛出异常
        if (!reset && MOCK_OBJECT.get(objClass) != null) {
            throw new MockException("此映射已存在！");
        }

        MockBean<T> parser;

        //使用参数解析器进行解析
        parser = ParameterParser.parser(objClass, map);

        //如果类型不是Map类型，添加
        MOCK_OBJECT.put(objClass, new MockNormalObject<>(parser));

        //提醒系统的垃圾回收
        System.gc();

        return parser;
    }

    /**
     * 添加一个map类型的映射
     *
     * @param resultName 映射名
     * @param map        映射值
     * @param reset      是否覆盖
     */
    public static MockMapBean setResult(String resultName, Map<String, Object> map, boolean reset) {
        //如果不是重新设置且此映射已经存在，并且objClass对象存在，将会抛出异常
        if (!reset && MOCK_MAP.get(resultName) != null) {
            throw new MockException("此映射已存在！this mock result has already exists.");
        }

        MockMapBean parser;

        //使用参数解析器进行解析
        parser = ParameterParser.parser(map);
        MOCK_MAP.put(resultName, MockObjectFactory.createMapObj(parser));

        //提醒系统的垃圾回收
        System.gc();

        return parser;
    }


    /**
     * 添加数据记录，如果要添加的映射已存在，则会抛出异常
     */
    public static <T> void set(Class<T> objClass, Map<String, Object> map) {
        //设置并保存映射，不可覆盖
        setResult(objClass, map, false);
    }

    /**
     * 通过注解来获取映射
     */
    public static <T> void set(Class<T> objClass) {
        //获取映射Map
        Map<String, Object> mapper = MockMapperFactory.getMapper(objClass);
        setResult(objClass, mapper, false);
    }

    /**
     * 添加数据记录，如果要添加的映射已存在，则会抛出异常
     */
    public static void set(String resultName, Map<String, Object> map) {
        //设置并保存映射，不可覆盖
        setResult(resultName, map, false);
    }

    /**
     * 获取一个实例对象
     */
    public static <T> MockObject<T> get(Class<T> objClass) {
        return Optional.ofNullable(MOCK_OBJECT.get(objClass)).orElse(null);
    }

    /**
     * 获取一个实例对象
     */
    public static <T> MockObject<Map> get(String resultName) {
        return MOCK_MAP.get(resultName);
    }

    /**
     * 获取Mock方法集合
     *
     * @return 全部已被加载的映射方法
     */
    public static Map<String, Method> getMockMethods() {
        return new HashMap<>(MOCK_METHOD);
    }


    /**
     * 根据过滤条件寻找指定的string-method
     */
    public static Map.Entry<String, Method> getMockMethodByFilter(Predicate<? super Map.Entry<String, Method>> predicate) {
        for (Map.Entry<String, Method> entry : MOCK_METHOD.entrySet()) {
            if (predicate.test(entry)) {
                return entry;
            }
        }
        return null;
    }

    public static Object calculate(Object itemValue) {
        if (ObjectUtils.isEmpty(itemValue)) {
            return itemValue;
        }
        try {
            String[] func = itemValue.toString().split("\\|");
            Object value = ParameterParser.parser(func[0].trim(), func[0].trim());
            if (func.length == 1) {
                return value;
            }
            value = FunctionApply.apply(value.toString(), func[1].trim());
            return value;
        } catch (Exception e) {
            return itemValue;
        }
    }
}
