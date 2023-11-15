package io.metersphere.jmeter.mock.function;

import java.util.Base64;

public class Base64Fun {

    public static String base64Encode(String input) {
        // 将字符串转换为字节数组
        byte[] inputBytes = input.getBytes();

        // 使用Base64编码
        byte[] encodedBytes = Base64.getEncoder().encode(inputBytes);

        // 将字节数组转换为字符串
        return new String(encodedBytes);
    }

    public static String base64Decode(String input) {
        // 将字符串转换为字节数组
        byte[] inputBytes = input.getBytes();

        // 使用Base64解码
        byte[] decodedBytes = Base64.getDecoder().decode(inputBytes);

        // 将字节数组转换为字符串
        return new String(decodedBytes);
    }

}
