package io.metersphere.jmeter.mock.function;

import java.security.MessageDigest;

public class ShaFun {

    private static String digest(String input, String algorithm) {
        // 将字节数组转换为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest shaDigest = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = shaDigest.digest(input.getBytes());
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (Exception ignored) {
        }

        return hexString.toString();
    }

    public static String sha1(String input) {
        return digest(input, "SHA-1");
    }

    public static String sha224(String input) {
        return digest(input, "SHA-224");
    }

    public static String sha256(String input) {
        return digest(input, "SHA-256");
    }

    public static String sha384(String input) {
        return digest(input, "SHA-384");
    }

    public static String sha512(String input) {
        return digest(input, "SHA-512");
    }

}
