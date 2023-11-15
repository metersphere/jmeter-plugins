package io.metersphere.jmeter.mock.util;

import io.metersphere.jmeter.mock.exception.MockException;
import io.metersphere.jmeter.mock.invoker.ElementInvoker;
import io.metersphere.jmeter.mock.invoker.Invoker;
import io.metersphere.jmeter.mock.invoker.MethodInvoker;
import org.apache.commons.beanutils.ConvertUtils;

import javax.script.ScriptException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * 方法执行工具
 */
public class MethodUtils {
    /**
     * 执行一个方法，可以为基本的数据类型进行转化
     */
    public static Object invoke(Object obj, Object[] args, Method method) throws InvocationTargetException, IllegalAccessException {
        //获取参数的数据类型数组，准备转化数据类型
        Parameter[] parameters = method.getParameters();
        //如果传入参数与方法参数数量不符 ，抛出异常
        //不知道是否能识别 String... args 这种参数
        if (args.length != parameters.length) {
            throw new MockException();
        }
        //创建一个新的Object数组保存转化后的参数，如果使用原数组的话会抛异常：ArrayStoreException
        Object[] newArr = new Object[args.length];
        //遍历参数并转化
        for (int i = 0; i < parameters.length; i++) {
            //使用BeanUtils的数据类型器对参数的数据类型进行转化
            //保存至新的参数集
            Class<?> paramType = parameters[i].getType();
            Object arg = args[i];
            if (arg.getClass().equals(paramType)) {
                newArr[i] = arg;
            } else {
                newArr[i] = ConvertUtils.convert(arg, paramType);
            }
        }

        //返回方法的执行结果
        return method.invoke(obj, newArr);
    }

    /**
     * 执行一个方法，可以为基本的数据类型进行转化
     */
    public static Object invoke(Object obj, Object[] args, String methodName) throws NoSuchMethodException {
        //通过反射获取此方法
        Method[] methods = Arrays.stream(obj.getClass().getMethods()).filter(m -> m.getName().equals(methodName) && m.getParameters().length == args.length).toArray(Method[]::new);
        for (Method m : methods) {
            try {
                return invoke(obj, args, m);
            } catch (Exception e) {
                throw new MockException(e);
            }
        }

        throw new NoSuchMethodException();
    }

    /**
     * js中的eval函数，应该是只能进行简单的计算
     * 利用js脚本完成
     *
     * @param str 需要进行eval执行的函数
     *            执行后的结果
     */
    public static Object eval(String str) throws ScriptException {
        return str;
    }

    /**
     * js中的eval函数，应该是只能进行简单的计算
     * 利用js脚本完成
     *
     * @param str 需要进行eval执行的函数
     *            执行后的结果
     */
    public static Object evalCache(String str) {
        try {
            return eval(str);
        } catch (ScriptException ignore) {
            return str;
        }
    }

    /**
     * 创建一个方法执行者
     */
    public static Invoker createMethodInvoker(Object obj, Object[] args, Method method) {
        return MethodInvoker.getInstance(obj, args, method);
    }

    /**
     * 创建一个方法为空的方法执行者，代表没有方法，将会输出指定的字符串
     */
    public static Invoker createNullMethodInvoker(Object nullValue) {
        return MethodInvoker.getInstance(nullValue);
    }

    /**
     * 创建一个数组元素获取执行者
     */
    public static Invoker createArrayElementInvoker(Object[] arr) {
        return ElementInvoker.getInstance(arr);
    }

    /**
     * 创建一个集合元素获取执行者
     */
    public static Invoker createListElementInvoker(List<?> list) {
        return ElementInvoker.getInstance(list);
    }

}


