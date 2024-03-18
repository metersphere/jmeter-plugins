package io.metersphere.jmeter.mock.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static io.metersphere.jmeter.mock.util.ChineseUtils.getFamilyName;

/**
 * <p>
 * 随机数据助手,可能会用到的所有随机方法<br>
 * 此类所有的方法，只要在方法名前加上'@' 即可在Mock中作为映射指令
 * 注意：此类在进行方法重载的时候不应出现参数数量相同的重载方法
 */
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class MockUtils {

    /**
     * 构造私有化
     */
    private MockUtils() {
    }

    /* —————————— 默认参数 ———————————— */
    /**
     * {@link #randomDateTime()}默认使用的格式化参数
     */
    private static final String DATE_FORMAT;

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT;


    /**
     * {@link #time()}默认使用的格式化参数
     */
    private static final String TIME_FORMAT;

    private static final SimpleDateFormat SIMPLE_DATETIME_FORMAT;

    /**
     * {@link #toDateTime()}默认使用的格式化参数
     */
    private static final String DATETIME_FORMAT;

    private static final SimpleDateFormat SIMPLE_TIME_FORMAT;

    /**
     * 顶级域名合集
     */
    private static final String[] DOMAINS;


    //静态代码块加载资源
    static {
        // 加载定义域名合集
        String domainStr = "top,xyz,xin,vip,win,red,net,org,wang,gov,edu,mil,biz,name,info,mobi,pro,travel,club,museum,int,aero,post,rec,asia";
        DOMAINS = domainStr.split(",");

        // 日期格式化
        DATE_FORMAT = "yyyy-dd-MM";
        SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);

        DATETIME_FORMAT = "yyyy-dd-MM HH:mm:ss";
        SIMPLE_DATETIME_FORMAT = new SimpleDateFormat(DATE_FORMAT);

        TIME_FORMAT = "HH:mm:ss";
        SIMPLE_TIME_FORMAT = new SimpleDateFormat(DATE_FORMAT);

    }


    /* —————————— name/chinese/cname —————————— */

    /**
     * 获取一个随机中文名称
     */
    public static String cname() {
        return ChineseUtils.getName();
    }


    /**
     * 获取指定数量区间[min , max]个随机中文名
     *
     * @param min 最小数量
     * @param max 最大数量
     */
    public static String[] cnames(Integer min, Integer max) {
        //获取随机数量
        int num = RandomUtils.getNumberWithRight(min, max);
        String[] names = new String[num];
        //遍历并获取
        for (int i = 0; i < num; i++) {
            names[i] = cname();
        }
        //返回结果
        return names;
    }

    /**
     * 获取指定数量个随机中文名
     */
    public static String[] cnames(Integer num) {
        return cnames(num, num);
    }

    /**
     * 随机获取一个中文姓氏 - 百家姓中获取
     */
    public static String cfirst() {
        return getFamilyName();
    }

    /**
     * 获取一个随机英文姓名-两个开头大写的英文字母(title(2,7)+" "+title(2,7))
     */
    public static String name() {
        int min = 2, max = 7;
        return title(min, max) + " " + title(min, max);
    }

    /**
     * 获取指定数量区间[min, max]个随机英文姓名
     *
     * @param min 最少数量
     * @param max 最大数量
     */
    public static String[] names(Integer min, Integer max) {
        //获取随机数量
        int num = RandomUtils.getNumberWithRight(min, max);
        String[] names = new String[num];
        //遍历并获取
        for (int i = 0; i < num; i++) {
            names[i] = name();
        }
        //返回结果
        return names;
    }

    /**
     * 获取指定数量num个随机英文姓名
     *
     * @param num 获取数量
     */
    public static String[] names(Integer num) {
        return names(num, num);
    }

    /**
     * 获取3-5个随机汉字
     */
    public static String ctitle() {
        return ctitle(3, 5);
    }


    /**
     * 获取指定数量个随机汉字
     */
    public static String ctitle(Integer num) {
        return ChineseUtils.getChinese(num);
    }

    /**
     * 获取指定数量区间个随机汉字，区间[min,max]
     *
     * @param min 最少数量
     * @param max 最大数量
     */
    public static String ctitle(Integer min, Integer max) {
        return ChineseUtils.getChinese(RandomUtils.getNumberWithRight(min, max));
    }


    /**
     * 获取5-10长度的英文字符串，开头大写
     */
    public static String title() {
        return title(5, 10);
    }

    /**
     * 获取指定长度的英文字符串，开头大写
     */
    public static String title(Integer num) {
        return title(num, num);
    }

    /**
     * 获取指定长度的英文字符串，开头大写
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String title(Integer min, Integer max) {
        int num = RandomUtils.getNumberWithRight(min, max);
        String title = RandomUtils.getRandomString(num, false);
        //全部小写，开头大写
        return FieldUtils.headUpper(title);
    }

    /**
     * 获取指定长度的英文字符串，纯小写
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String string(Integer min, Integer max) {
        return word(min, max);
    }

    /**
     * 获取指定长度的英文字符串，纯小写
     */
    public static String string(Integer num) {
        return string(num, num);
    }

    /**
     * 获取5-10长度的英文字符串，纯小写
     */
    public static String string() {
        return string(5, 10);
    }

    /**
     * 获取指定长度的英文字符串，纯大写
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String stringUpper(Integer min, Integer max) {
        int num = RandomUtils.getNumberWithRight(min, max);
        return RandomUtils.getRandomUpperString(num);
    }

    /**
     * 获取指定长度的英文字符串，纯大写
     */
    public static String stringUpper(Integer num) {
        return stringUpper(num, num);
    }

    /**
     * 获取5-10长度的英文字符串，纯大写
     */
    public static String stringUpper() {
        return stringUpper(5, 10);
    }


    /**
     * 获取一个UUID
     */
    public static String UUID() {
        return RandomUtils.getUUID();
    }

    public static String uuid() {
        return RandomUtils.getUUID();
    }


    /* —————————— date —————————— */

    /**
     * 获取随机日期
     * 时间：1990 - 现在
     */
    public static Date randomDateTime() {
        Calendar calendar = Calendar.getInstance();
        //设置年份等参数
        int nowYear = calendar.get(Calendar.YEAR);
        int nowDay = calendar.get(Calendar.DAY_OF_YEAR);

        //设置随机年份
        calendar.set(Calendar.YEAR, RandomUtils.getNumberWithRight(1990, nowYear));
        //设置随机日期
        calendar.set(Calendar.DAY_OF_YEAR, RandomUtils.getNumberWithRight(1, nowDay));
        //设置随机小时
        calendar.set(Calendar.HOUR_OF_DAY, RandomUtils.getNumberWithRight(1, 24));
        //设置随机分钟
        calendar.set(Calendar.MINUTE, RandomUtils.getNumberWithRight(1, 60));
        //设置随机秒
        calendar.set(Calendar.SECOND, RandomUtils.getNumberWithRight(1, 60));

        //返回随机日期
        return calendar.getTime();
    }

    /**
     * 返回一个随机日期的字符串
     */
    public static String date(String format) {
        return new SimpleDateFormat(format).format(randomDateTime());
    }

    /**
     * 返回一个随机日期的字符串，格式为yyyy-dd-MM
     */
    public static String date() {
        return SIMPLE_DATE_FORMAT.format(randomDateTime());
    }

    /**
     * 返回一个随机日期的字符串
     */
    public static String dateTime(String format) {
        return new SimpleDateFormat(format).format(randomDateTime());
    }

    /**
     * 返回一个随机日期的字符串，格式为yyyy-dd-MM
     */
    public static String dateTime() {
        return SIMPLE_DATE_FORMAT.format(randomDateTime());
    }

    /**
     * 返回一个随机日期的字符串
     */
    public static String now(String format) {
        return new SimpleDateFormat(format).format(System.currentTimeMillis());
    }

    /**
     * 返回一个随机日期的字符串，格式为yyyy-dd-MM
     */
    public static String now() {
        return SIMPLE_DATE_FORMAT.format(System.currentTimeMillis());
    }


    /**
     * 返回一个随机时间的字符串
     */
    public static String time(String format) {
        return new SimpleDateFormat(format).format(randomDateTime());
    }

    /**
     * 返回一个随机时间的字符串，格式为HH:mm:ss
     */
    public static String time() {
        return SIMPLE_TIME_FORMAT.format(randomDateTime());
    }

    /**
     * 返回一个随机时间日期的字符串
     */
    public static String toDateTime(String format) {
        return new SimpleDateFormat(format).format(randomDateTime());
    }

    /**
     * 返回一个随机日期时间的字符串，格式为yyyy-dd-MM HH:mm:ss
     */
    public static String toDateTime() {
        return SIMPLE_DATETIME_FORMAT.format(randomDateTime());
    }

    /* —————————— number age —————————— */

    /**
     * 获取一个随机年龄
     * 12 - 80
     */
    public static Integer age() {
        return RandomUtils.getNumberWithRight(12, 80);
    }

    /**
     * 获取随机数字
     * 0-9
     */
    public static Integer integer() {
        return RandomUtils.getNumber(1);
    }

    /**
     * 获取指定长度的随机数
     *
     * @param length 长度,长度请不要超过整数型上限。<br> 如果需要获取无限长度的整数请使用{@link MockUtils#getNumber(Integer)}
     */
    public static Integer integer(Integer length) {
        return RandomUtils.getNumber(length);
    }

    /**
     * 获取指定区间[a,b]的随机数
     *
     * @param a 最小值
     * @param b 最大值
     */
    public static Integer integer(Integer a, Integer b) {
        return RandomUtils.getNumberWithRight(a, b);
    }


    /**
     * 获取制定区间[a,b]的小数，指定小数位数[endL,endR]，double类型
     *
     * @param a    整数部分的最小值
     * @param b    整数部分的最大值
     * @param endL 小数部分位数最小值
     * @param endR 小数部分位数最大值
     */
    public static Double doubles(Integer a, Integer b, Integer endL, Integer endR) {
        int integer = integer(a, b);
        //获取小数位数值
        int end = RandomUtils.getNumberWithRight(endL, endR);
        double dou = Double.parseDouble(RandomUtils.toFixed(RandomUtils.getRandom().nextDouble(), end));
        return integer + dou;
    }

    /**
     * 获取指定区间[a,b]的小数，指定小数位数[end]，double类型
     */
    public static Double doubles(Integer a, Integer b, Integer end) {
        return doubles(a, b, end, end);
    }

    /**
     * 获取指定区间[a,b]的小数，默认小数位数为0，double类型
     */
    public static Double doubles(Integer a, Integer b) {
        return doubles(a, b, 0, 0);
    }

    /**
     * 获取指定数值为a的小数，默认小数位数为0，double类型
     */
    public static Double doubles(Integer a) {
        return a * 1.0;
    }


    /**
     * 获取一个32位的随机数字
     */
    public static String UUNUM() {
        int length = 32;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(integer());
        }
        return sb.toString();
    }

    /**
     * 获取任意长度的随机整数
     */
    public static String getNumber(Integer length) {
        return getNumber(length, length);
    }

    /**
     * 获取任意长度的随机整数
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String getNumber(Integer min, Integer max) {
        //获取长度
        int length = RandomUtils.getNumberWithRight(min, max);
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(integer());
        }
        return sb.toString();
    }


    /**
     * 获取指定位的小数
     *
     * @param intLength 整数部分的长度
     * @param douLength 保留小数位数
     */
    public static String getDouble(Integer intLength, Integer douLength) {
        return getDouble(intLength, intLength, douLength, douLength);
    }


    /**
     * 获取指定位的小数的最大区间
     *
     * @param intMinLength 整数部分的长度最小值
     * @param intMaxLength 整数部分的长度最大值
     * @param douMinLength 保留小数位数最小值
     * @param douMaxLength 保留小数位数最大值
     */
    public static String getDouble(Integer intMinLength, Integer intMaxLength, Integer douMinLength, Integer douMaxLength) {
        //先获取整数位
        return getNumber(intMinLength, intMaxLength) +
                "." +
                getNumber(douMinLength, douMaxLength);
    }


    /**
     * 获取32位小数，小数为2位
     */
    public static String UUDOUBLE() {
        return getDouble(32, 2);
    }



    /* ——————————————————————String character code—————————————————————— */

    /**
     * 获取一个随机字符
     */
    public static Character character() {
        return RandomUtils.getRandomChar();
    }

    public static String character(String chars) {
        //合并集合
        if (StringUtils.isBlank(chars)) {
            return null;
        } else if (StringUtils.equalsIgnoreCase("lower", chars)) {
            return character().toString().toLowerCase();
        } else if (StringUtils.equalsIgnoreCase("upper", chars)) {
            return character().toString().toUpperCase();
        }

        char[] charArray = chars.toCharArray();
        Character[] characters = new Character[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            characters[i] = charArray[i];
        }
        int num = RandomUtils.getNumber(0, characters.length);
        return characters[num].toString();
    }

    /**
     * 返回一个随机的假单词
     */
    public static String word() {
        return word(3, 12);
    }

    /**
     * 返回一个随机的假单词,指定长度
     *
     * @param length 指定长度
     */
    public static String word(Integer length) {
        return RandomUtils.getRandomString(length, false);
    }

    /**
     * 返回一个随机的假单词,指定长度区间[min,max]
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String word(Integer min, Integer max) {
        int num = RandomUtils.getNumberWithRight(min, max);
        return RandomUtils.getRandomString(num, false);
    }

    /**
     * 返回一个随机的假中文词语，指定长度区间[min,max]
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String cword(Integer min, Integer max) {
        return ctitle(min, max);
    }

    /**
     * 返回一个随机的假中文词语，指定长度
     *
     * @param length 单词长度
     */
    public static String cword(Integer length) {
        return ctitle(length);
    }

    /**
     * 返回一个随机的假中文词语,长度2-4
     */
    public static String cword() {
        return ctitle(1, 1);
    }


    /* —————————————————————— color —————————————————————— */

    /**
     * 获取一个随机颜色的16进制代码
     */
    public static String color() {
        return RandomUtils.randomColor$hexString();
    }

    /* —————————————————————— boolean —————————————————————— */

    /**
     * 返回一个随机布尔值
     */
    public static Boolean bool() {
        return RandomUtils.getRandom().nextBoolean();
    }

    /**
     * 根据概率返回布尔值
     *
     * @param prob 返回true的概率，建议取值区间：0-1
     */
    public static Boolean bool(double prob) {
        return RandomUtils.getProbability(prob);
    }

    /* —————————————————————— text —————————————————————— */

    /**
     * 随机假英文句子,句子中的单词数量为参数的区间[min,max]
     *
     * @param min 单词最少数量
     * @param max 单词最多数量
     */
    public static String sentence(Integer min, Integer max) {
        int num = RandomUtils.getNumberWithRight(min, max);
        StringBuilder sb = new StringBuilder(num);
        for (int i = 1; i <= num; i++) {
            //首句子字母大写
            sb.append(i == 0 ? FieldUtils.headUpper(word()) : word());
            if (i != num) {
                sb.append(' ');
            } else {
                //30%概率为！结尾
                if (RandomUtils.getProbability(0.3)) {
                    sb.append("! ");
                    //否则30%概率？结尾
                } else if (RandomUtils.getProbability(0.3)) {
                    sb.append("? ");
                    //否则。结尾
                } else {
                    sb.append(". ");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 返回指定长度的句子
     */
    public static String sentence(Integer length) {
        return sentence(length, length);
    }

    /**
     * 返回长度为12-18长度的句子
     */
    public static String sentence() {
        return sentence(12, 18);
    }

    /**
     * 随机假中文句子,句子中的单词数量为参数的区间[min,max]
     *
     * @param min 单词最少数量
     * @param max 单词最多数量
     */
    public static String csentence(Integer min, Integer max) {
        StringBuilder sb = new StringBuilder();
        int num = RandomUtils.getNumberWithRight(min, max);
        for (int i = 1; i <= num; i++) {
            //首句子字母大写
            sb.append(cword());
            if (i == num) {
                //30%概率为！结尾
                if (RandomUtils.getProbability(0.3)) {
                    sb.append("！");
                    //否则30%概率？结尾
                } else if (RandomUtils.getProbability(0.3)) {
                    sb.append("？");
                    //否则。结尾
                } else {
                    sb.append("。");
                }
            }
        }
        return sb.toString();
    }


    /**
     * 返回指定长度的中文句子
     */
    public static String csentence(Integer length) {
        return csentence(length, length);
    }

    /**
     * 返回长度为5-10长度的中文句子
     */
    public static String csentence() {
        return csentence(5, 10);
    }

    /**
     * 返回一个文本，文中句子数量为参数区间[min,max]
     */
    public static String paragraph(Integer min, Integer max) {
        int num = RandomUtils.getNumberWithRight(min, max);
        StringBuilder sb = new StringBuilder(num);
        for (int i = 1; i <= num; i++) {
            sb.append(sentence());
        }
        return sb.toString();
    }

    /**
     * 返回指定句子数量的文本
     */
    public static String paragraph(Integer length) {
        return paragraph(length, length);
    }

    /**
     * 返回一个有3-7个句子的文本
     */
    public static String paragraph() {
        return paragraph(3, 7);
    }

    /**
     * 返回一个文本，文中句子数量为参数区间[min,max]
     *
     * @param min 最小数量
     * @param max 最大数量
     */
    public static String cparagraph(Integer min, Integer max) {
        int num = RandomUtils.getNumberWithRight(min, max);
        StringBuilder sb = new StringBuilder(num);
        for (int i = 1; i <= num; i++) {
            sb.append(csentence());
        }
        return sb.toString();
    }

    /**
     * 返回指定句子数量的文本
     */
    public static String cparagraph(Integer length) {
        return cparagraph(length, length);
    }

    /**
     * 返回一个有3-7个句子的文本
     */
    public static String cparagraph() {
        return cparagraph(3, 7);
    }


    /* —————————————————————— web —————————————————————— */

    /**
     * 获取一个随机IP
     */
    public static String ip() {
        Random random = RandomUtils.getRandom();
        return (random.nextInt(255) + 1) +
                "." +
                (random.nextInt(255) + 1) +
                '.' +
                (random.nextInt(255) + 1) +
                '.' +
                (random.nextInt(255) + 1);
    }

    /**
     * 获取一个随机的顶级域名
     */
    public static String tId() {
        return RandomUtils.getRandomElement(DOMAINS);
    }

    /**
     * 返回一个随机邮箱,可以指定邮箱的名称（@后面的名字）和顶级域名
     */
    public static String email(String emailName, String tid) {

        return word() +
                '@' +
                emailName +
                '.' +
                tid;
    }

    /**
     * 返回一个随机邮箱,可以指定邮箱的名称（@后面的名字）
     */
    public static String email(String emailName) {
        return email(emailName, tId());
    }

    /**
     * 返回一个随机邮箱
     */
    public static String email() {
        return email(word(), tId());
    }

    /**
     * 随机生成一个域名，可指定顶级域名
     *
     * @param tid 指定顶级域名
     */
    public static String domain(String tid) {
        if (RandomUtils.getRandom().nextBoolean()) {
            return "www." + word() + "." + tid;
        }
        return word() + '.' + tid;
    }

    /**
     * 随机生成一个域名
     */
    public static String domain() {
        return domain(tId());
    }

    /**
     * 随机一个url路径，可指定域名
     *
     * @param protocol 指定域名
     */
    public static String url(String protocol) {
        StringBuilder sb = new StringBuilder(32);
        //url前半部分
        sb.append(protocol).append("://").append(domain()).append('/').append(word());
        //每次有0.2的概率再追加一层路径
        while (RandomUtils.getProbability(0.2)) {
            sb.append('/').append(word());
        }
        return sb.toString();
    }

    /**
     * 随机一个url
     */
    public static String url() {
        return url(domain());
    }


    /* —————————————————————— 构造 —————————————————————— */

    /* —————————————————————— natural —————————————————————— */
    public static int natural() {
        return RandomUtils.getNumber(0, Integer.MAX_VALUE);
    }

    public static int natural(int min, int max) {
        if (min > max) {
            return RandomUtils.getNumber(0, max);
        }
        return RandomUtils.getNumber(min, max);
    }

    /* —————————————————————— natural —————————————————————— */

    public static float floatNumber(int min, int max, int minDecimalPlaces, int maxDecimalPlaces) {
        Random random = new Random();
        double randomNumber = min + (max - min) * random.nextDouble(); // 生成1到10之间的随机浮点数

        // 生成随机小数部分位数
        int decimalPlaces = random.nextInt(maxDecimalPlaces - minDecimalPlaces + 1) + minDecimalPlaces;
        double multiplier = Math.pow(10, decimalPlaces);
        double result = Math.round(randomNumber * multiplier) / multiplier;
        return (float) result;
    }

    public static float floatNumber() {
        Random random = RandomUtils.getLocalRandom();
        return random.nextFloat();
    }

    public static String range(int start, int stop, int step) {
        if (step <= 0) {
            return Arrays.toString(new int[0]);
        }

        int size = (int) Math.ceil((double) (stop - start) / step);
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = start + i * step;
        }
        return Arrays.toString(result);
    }

    public static String range() {
        return Arrays.toString(new int[0]);
    }

    public static String protocol() {
        return RandomUtils.randomProtocol();
    }

    public static String tld() {
        return RandomUtils.randomTID();
    }

    public static String region() {
        return RandomUtils.generateRandomChinaRegion();
    }

    public static String province() {
        return RandomUtils.randomProvince();
    }

    public static String city() {
        return RandomUtils.randomCity();
    }

    public static String zip() {
        return RandomUtils.zip();
    }

    public static String idCard() {
        return RandomUtils.getIdNo(true);
    }

    public static String idCard(String birthYear) {
        return IDCardGenerator.generateIDCard(birthYear);
    }

    public static String phoneNumber() {
        return RandomUtils.phoneNumber();
    }

    public static String first() {
        return RandomUtils.englishName();
    }

    public static String last() {
        return RandomUtils.englishSurname();
    }

    public static String clast() {
        return getFamilyName();
    }

    public static String xrgb() {
        return RandomUtils.rgb();
    }

    public static String rgb(int red, int green, int blue) {
        return RandomUtils.rgb(red, green, blue);
    }

    public static String rgba() {
        return RandomUtils.rgba();
    }

    public static String rgba(int red, int green, int blue, float alpha) {
        return RandomUtils.rgba(red, green, blue, alpha);
    }

    public static String hsl() {
        return RandomUtils.hsl();
    }

    public static String hsl(int hue, int saturation, int lightness) {
        return RandomUtils.hsl(hue, saturation, lightness);
    }

    public static String regexp(String input, String regex) {
        return RandomUtils.regexp(input + regex);
    }

    public static String regexp(String regex) {
        return RandomUtils.regexp(regex);
    }

    public static String county(Boolean prefix) {
        return RandomUtils.randomCounty(prefix);
    }

    public static String county() {
        return RandomUtils.randomCounty(false);
    }

}
