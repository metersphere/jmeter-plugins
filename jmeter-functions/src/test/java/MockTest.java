
import io.metersphere.jmeter.mock.Mock;

public class MockTest {
    public static void main(String[] args) {
        // 基本变量
        System.out.println("@boolean= " + Mock.calculate("@cname@bool@natural"));
        System.out.println("@natural= " + Mock.calculate("@natural")); // 待补充
        System.out.println("@natural(1,101)= " + Mock.calculate("@natural(1,101)"));// 待补充
        System.out.println("@integer= " + Mock.calculate("@integer"));
        System.out.println("@integer(1,100)= " + Mock.calculate("@integer(1,100)"));
        System.out.println("@floatNumber( 1, 10, 2, 5 )= " + Mock.calculate("@floatNumber(1,10,2,5)"));
        System.out.println("@character(pool)= " + Mock.calculate("@character(pool)"));
        System.out.println("@character('lower')= " + Mock.calculate("@character('lower')"));
        System.out.println("@character('upper')= " + Mock.calculate("@character('upper')"));
        System.out.println("@character('symbol')= " + Mock.calculate("@character('symbol')"));
        System.out.println("@string(pool,1,10)= " + Mock.calculate("@string(pool,1,10)"));
        System.out.println("@range( 1, 100, 1 )= " + Mock.calculate("@range(1,100,1)"));

        // 日期变量
        System.out.println("@date('yyyy-MM-dd')= " + Mock.calculate("@date('yyyy-MM-dd')"));
        System.out.println("@time('HH:mm:ss')= " + Mock.calculate("@time('HH:mm:ss')"));
        System.out.println("@dateTime('yyyy-MM-dd HH:mm:ss')= " + Mock.calculate("@dateTime('yyyy-MM-dd HH:mm:ss')"));
        System.out.println("@now('yyyy-MM-dd HH:mm:ss')= " + Mock.calculate("@now('yyyy-MM-dd HH:mm:ss')"));
        // 主键
        System.out.println("@guid= " + Mock.calculate("@guid"));
        System.out.println("@increment(1)= " + Mock.calculate("@increment(1)"));
        // web 变量
        System.out.println("@url('http')= " + Mock.calculate("@url('http')"));
        System.out.println("@protocol= " + Mock.calculate("@protocol"));
        System.out.println("@domain= " + Mock.calculate("@domain"));
        System.out.println("@tld= " + Mock.calculate("@tld"));
        System.out.println("@email= " + Mock.calculate("@email"));
        System.out.println("@ip= " + Mock.calculate("@ip"));
        // 地区
        System.out.println("@region= " + Mock.calculate("@region"));
        System.out.println("@province= " + Mock.calculate("@province"));
        System.out.println("@city= " + Mock.calculate("@city"));
        System.out.println("@county= " + Mock.calculate("@county"));
        System.out.println("@county(true)= " + Mock.calculate("@county(true)"));
        // 邮编变量
        System.out.println("@zip= " + Mock.calculate("@zip"));
        // 身份证号码
        System.out.println("@idCard= " + Mock.calculate("@idCard"));
        // 手机号
        System.out.println("@phoneNumber= " + Mock.calculate("@phoneNumber"));
        // 人名变量
        System.out.println("@first= " + Mock.calculate("@first"));
        System.out.println("@last= " + Mock.calculate("@last"));
        System.out.println("@name= " + Mock.calculate("@name"));
        System.out.println("@cfirst= " + Mock.calculate("@cfirst"));
        System.out.println("@clast= " + Mock.calculate("@clast"));
        System.out.println("@cname= " + Mock.calculate("@cname"));
        // 颜色变量
        System.out.println("@color= " + Mock.calculate("@color"));
        System.out.println("@rgb= " + Mock.calculate("@rgb"));
        System.out.println("@rgba= " + Mock.calculate("@rgba"));
        System.out.println("@hsl= " + Mock.calculate("@hsl"));

        // 文本变量
        System.out.println("@paragraph= " + Mock.calculate("@paragraph"));
        System.out.println("@cparagraph= " + Mock.calculate("@cparagraph"));
        System.out.println("@sentence= " + Mock.calculate("@sentence"));
        System.out.println("@csentence= " + Mock.calculate("@csentence"));
        System.out.println("@word= " + Mock.calculate("@word"));
        System.out.println("@cword= " + Mock.calculate("@cword"));
        System.out.println("@title= " + Mock.calculate("@title"));
        System.out.println("@ctitle= " + Mock.calculate("@ctitle"));

        // 正则表达式
        System.out.println("@regexp( regexp )= " + Mock.calculate("@regexp('\\$(\\\\d+\\\\.\\\\d{2})')"));

        System.out.println("开始测试函数处理 ============ ");
        System.out.println("@ctitle= " + Mock.calculate("@string|md5"));
        System.out.println("@ctitle= " + Mock.calculate("@string|base64"));
        System.out.println("@ctitle= " + Mock.calculate("@string|unbase64"));
        System.out.println("@ctitle= " + Mock.calculate("@string|substr:1,3"));
        System.out.println("@ctitle= " + Mock.calculate("@string|concat:0000000"));
        System.out.println("@ctitle= " + Mock.calculate("@string|lconcat:0000000"));
        System.out.println("@ctitle= " + Mock.calculate("@string|sha1"));
        System.out.println("@ctitle= " + Mock.calculate("@string|sha224"));
        System.out.println("@ctitle= " + Mock.calculate("@string|sha256"));
        System.out.println("@ctitle= " + Mock.calculate("@string|sha384"));
        System.out.println("@ctitle= " + Mock.calculate("@string|sha512"));
        System.out.println("@ctitle= " + Mock.calculate("@string|lower"));
        System.out.println("@ctitle= " + Mock.calculate("@string|upper"));
        System.out.println("@ctitle= " + Mock.calculate("@string|length"));
        System.out.println("@ctitle= " + Mock.calculate("@string|number"));

        System.out.println(Mock.buildFunctionCallString("@integer@md5:123456"));

    }
}
