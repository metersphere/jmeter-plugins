import io.metersphere.jmeter.mock.function.Base64Fun;
import io.metersphere.jmeter.mock.function.MD5Fun;
import io.metersphere.jmeter.mock.function.ShaFun;

public class FunTest {
    public static void main(String[] args) {
        // md5 test
        String input = "Hello, MD5!";
        try {
            String md5Hash = MD5Fun.calculate(input);
            System.out.println("MD5 Hash: " + md5Hash);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // base64 test
        // 示例字符串
        String originalString = "Hello, Base64!";

        // 进行Base64编码
        String encodedString = Base64Fun.base64Encode(originalString);
        System.out.println("Base64 Encoded: " + encodedString);

        // 进行Base64解码
        String decodedString = Base64Fun.base64Decode(encodedString);
        System.out.println("Base64 Decoded: " + decodedString);

        // subStr test

        // concat test

        // lconcat test

        // sha1 ...512
        ShaFun.sha1("Hello, SHA-1!");
    }

}
