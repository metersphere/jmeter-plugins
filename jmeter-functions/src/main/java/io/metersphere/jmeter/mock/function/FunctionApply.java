package io.metersphere.jmeter.mock.function;

public enum FunctionApply {
    MD5,
    BASE64,
    UN_BASE64,
    SUBSTR,
    CONCAT,
    L_CONCAT,
    SHA1,
    SHA256,
    SHA512,
    SHA384,
    SHA224,
    LOWER,
    UPPER,
    LENGTH,
    NUMBER;

    public static String apply(String value, String funcStr) {
        String[] args = funcStr.split(":");
        switch (args[0]) {
            case "md5":
                return MD5Fun.calculate(value);
            case "base64":
                return Base64Fun.base64Encode(value);
            case "unbase64":
                return Base64Fun.base64Decode(value);
            case "substr":
                return subStr(value, args[1]);
            case "concat":
                return concat(value, args[1], false);
            case "lconcat":
                return concat(value, args[1], true);
            case "sha1":
                return ShaFun.sha1(value);
            case "sha256":
                return ShaFun.sha256(value);
            case "sha512":
                return ShaFun.sha512(value);
            case "sha384":
                return ShaFun.sha384(value);
            case "sha224":
                return ShaFun.sha224(value);
            case "lower":
                return value.toLowerCase();
            case "upper":
                return value.toUpperCase();
            case "length":
                return String.valueOf(value.length());
            case "number":
                return number(value);
            default:
                return null;
        }
    }

    public static String number(String value) {
        try {
            return String.valueOf(Double.parseDouble(value));
        } catch (Exception e) {
            return value;
        }
    }

    public static String concat(String value, String func, boolean isPrefix) {
        try {
             if (isPrefix) {
                return func + value;
            } else {
                return value + func;
            }
        } catch (Exception e) {
            return value;
        }
    }

    public static String subStr(String value, String func) {
        try {
            String[] args = func.split(",");
             if (args.length <= 1) {
                return value;
            }
            return value.substring(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (Exception e) {
            return value;
        }
    }
}
