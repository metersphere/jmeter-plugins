
import io.metersphere.jmeter.mock.Mock;

public class MockTest {
    public static void main(String[] args) {
        // 基本变量
        System.out.println(Mock.parser("@boolean", "@cname"));

        System.out.println(Mock.parser("@boolean", "@bool"));
        System.out.println(Mock.parser("@natural", "@natural")); // 待补充
        System.out.println(Mock.parser("@natural(1,101)", "@natural(1,101)"));// 待补充
        System.out.println(Mock.parser("@integer", "@integer"));
        System.out.println(Mock.parser("@integer(1,100)", "@integer(1,100)"));
        System.out.println(Mock.parser("@floatNumber( 1, 10, 2, 5 )", "@floatNumber(1,10,2,5)"));
        System.out.println(Mock.parser("@character(pool)", "@character(abc)"));
        System.out.println(Mock.parser("@character('lower')", "@character('lower')"));
        System.out.println(Mock.parser("@character('upper')", "@character('upper')"));
        System.out.println(Mock.parser("@character('symbol')", "@character('symbol')"));
        System.out.println(Mock.parser("@string(pool,1,10)", "@string(1,10)"));
        System.out.println(Mock.parser("@range( 1, 100, 1 )", "@range"));

        // 日期变量
        System.out.println(Mock.parser("@date('yyyy-MM-dd')", "@date('yyyy-MM-dd')"));
        System.out.println(Mock.parser("@time('HH:mm:ss')", "@time('HH:mm:ss')"));
        System.out.println(Mock.parser("@dateTime('yyyy-MM-dd HH:mm:ss')", "@dateTime('yyyy-MM-dd HH:mm:ss')"));
        System.out.println(Mock.parser("@now('yyyy-MM-dd HH:mm:ss')", "@now('yyyy-MM-dd HH:mm:ss')"));
        // 主键
        System.out.println(Mock.parser("@guid", "@uuid"));
        System.out.println(Mock.parser("@increment(1)", "@increment(1)"));
        // web 变量
        System.out.println(Mock.parser("@url('http')", "@url('http')"));
        System.out.println(Mock.parser("@protocol", "@protocol"));
        System.out.println(Mock.parser("@domain", "@domain"));
        System.out.println(Mock.parser("@tld", "@tld"));
        System.out.println(Mock.parser("@email", "@email"));
        System.out.println(Mock.parser("@ip", "@ip"));
        // 地区
        System.out.println(Mock.parser("@region", "@region"));
        System.out.println(Mock.parser("@province", "@province"));
        System.out.println(Mock.parser("@city", "@city"));
        System.out.println(Mock.parser("@county", "@county"));
        System.out.println(Mock.parser("@county(true)", "@county(true)"));
        // 邮编变量
        System.out.println(Mock.parser("@zip", "@zip"));
        // 身份证号码
        System.out.println(Mock.parser("@idCard", "@idCard"));
        // 手机号
        System.out.println(Mock.parser("@phoneNumber", "@phoneNumber"));
        // 人名变量
        System.out.println(Mock.parser("@first", "@first"));
        System.out.println(Mock.parser("@last", "@last"));
        System.out.println(Mock.parser("@name", "@name"));
        System.out.println(Mock.parser("@cfirst", "@cfirst"));
        System.out.println(Mock.parser("@clast", "@clast"));
        System.out.println(Mock.parser("@cname", "@cname"));
        // 颜色变量
        System.out.println(Mock.parser("@color", "@color"));
        System.out.println(Mock.parser("@rgb", "@rgb"));
        System.out.println(Mock.parser("@rgba", "@rgba"));
        System.out.println(Mock.parser("@hsl", "@hsl"));
        // 文本变量
        System.out.println(Mock.parser("@paragraph", "@paragraph"));
        System.out.println(Mock.parser("@cparagraph", "@cparagraph"));
        System.out.println(Mock.parser("@sentence", "@sentence"));
        System.out.println(Mock.parser("@csentence", "@csentence"));
        System.out.println(Mock.parser("@word", "@word"));
        System.out.println(Mock.parser("@cword", "@cword"));
        System.out.println(Mock.parser("@title", "@title"));
        System.out.println(Mock.parser("@ctitle", "@ctitle"));

        // 正则表达式
        System.out.println(Mock.parser("@regexp( regexp )", "@regexp('\\$(\\\\d+\\\\.\\\\d{2})')"));

    }
}
