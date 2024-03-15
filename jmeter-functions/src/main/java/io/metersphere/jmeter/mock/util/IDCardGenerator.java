package io.metersphere.jmeter.mock.util;

import java.util.Random;

public class IDCardGenerator {
    // 随机生成地区代码
    private static String generateAreaCode() {
        Random random = new Random();
        int areaCode = random.nextInt(899999) + 100000; // 随机生成一个六位数地区码
        return String.valueOf(areaCode);
    }

    // 计算校验码
    private static char calculateCheckCode(String cardNumber) {
        int[] weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] checkCode = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (cardNumber.charAt(i) - '0') * weight[i];
        }
        return checkCode[sum % 11];
    }

    // 生成身份证号
    public static String generateIDCard(String birthdate) {
        // 假设地区码和顺序码可以随机生成
        String areaCode = generateAreaCode();
        String sequenceCode = String.format("%03d", new Random().nextInt(999));

        // 将生日从yyyy-MM-dd格式转换为yyyyMMdd格式
        String birthdateCode = birthdate.replace("-", "");

        // 组合前17位
        String cardNumber = areaCode + birthdateCode + sequenceCode;

        // 计算校验码
        char checkCode = calculateCheckCode(cardNumber);

        // 返回完整的身份证号
        return cardNumber + checkCode;
    }

}
