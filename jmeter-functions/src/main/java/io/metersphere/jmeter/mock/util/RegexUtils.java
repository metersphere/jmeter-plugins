package io.metersphere.jmeter.mock.util;

import io.metersphere.jmeter.mock.exception.RegexpIllegalException;
import io.metersphere.jmeter.mock.exception.TypeNotMatchException;
import io.metersphere.jmeter.mock.exception.UninitializedException;
import io.metersphere.jmeter.mock.util.regex.OrdinaryNode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配两个字符串之间的内容
 * 代码来源于网络
 */
public class RegexUtils {

    public static List<String> getMatcher(String source, String regex) {
        /*
            Pattern： 一个Pattern是一个正则表达式经编译后的表现模式。
            Matcher： 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
         */
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        //记录匹配的数量并创建数组
        List<String> end = new ArrayList<>();
        //遍历并保存结果集
        while (matcher.find()) {
            end.add(matcher.group());
        }
        //返回结果
        return end;
    }

    public static String generator(String expression) {
        try {
            return new OrdinaryNode(expression).random();
        } catch (RegexpIllegalException | TypeNotMatchException | UninitializedException e) {
            return expression;
        }
    }
}
