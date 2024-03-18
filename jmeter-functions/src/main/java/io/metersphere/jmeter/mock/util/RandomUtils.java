package io.metersphere.jmeter.mock.util;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机值获取工具类
 */
public class RandomUtils {
    // ------------------------------- 部分测试数据---------------------------
    private static final String[] PROTOCOLS = {"http", "ftp", "smtp", "tcp", "udp"};

    private static final String[] CHINA_REGIONS = {"华北", "华东", "华南", "华中", "西北", "西南", "东北", "台港澳"};

    private static final String[] PROVINCES = {
            "北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "辽宁省", "吉林省",
            "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省",
            "湖北省", "湖南省", "广东省", "广西壮族自治区", "海南省", "四川省", "贵州省",
            "云南省", "陕西省", "甘肃省", "青海省", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
            "香港特别行政区", "澳门特别行政区", "台湾省"
    };

    private static final String[] TLD = {"com", "net", "org", "gov", "edu", "int", "mil", "arpa"};

    private static final String[] CHINA_CITIES = {
            "北京", "上海", "广州", "深圳", "天津", "重庆", "成都", "杭州",
            "南京", "武汉", "西安", "苏州", "郑州", "长沙", "厦门", "青岛",
            "沈阳", "大连", "哈尔滨", "济南", "长春", "西宁", "呼和浩特",
            "南昌", "合肥", "福州", "昆明", "南宁", "贵阳", "太原", "石家庄"
    };

    private static final String[] COMMON_NAMES = {
            "John", "Emily", "Michael", "Emma", "William",
            "Olivia", "James", "Sophia", "Benjamin", "Ava", "Just"
    };

    private static final String[] COMMON_SUR_NAMES = {
            "Smith", "Johnson", "Williams", "Jones", "Brown",
            "Davis", "Miller", "Wilson", "Moore", "Taylor"
            // Add more surnames as needed
    };

    public static final List<String> COUNTY = Arrays.asList(
            "东风区",
            "郊区",
            "桦南县",
            "桦川县",
            "汤原县",
            "抚远县",
            "同江市",
            "富锦市",
            "七台河市",
            "新兴区",
            "桃山区",
            "茄子河区",
            "勃利县",
            "其它区",
            "牡丹江市",
            "东安区",
            "阳明区",
            "爱民区",
            "西安区",
            "东宁县",
            "林口县",
            "绥芬河市",
            "海林市",
            "宁安市",
            "穆棱市",
            "其它区",
            "黑河市",
            "爱辉区",
            "嫩江县",
            "逊克县",
            "孙吴县",
            "北安市",
            "五大连池市",
            "其它区",
            "绥化市",
            "北林区",
            "望奎县",
            "兰西县",
            "青冈县",
            "庆安县",
            "明水县",
            "绥棱县",
            "安达市",
            "肇东市",
            "海伦市",
            "其它区",
            "大兴安岭地区",
            "松岭区",
            "新林区",
            "呼中区",
            "呼玛县",
            "塔河县",
            "漠河县",
            "加格达奇区",
            "其它区",
            "上海",
            "上海市",
            "黄浦区",
            "徐汇区",
            "长宁区",
            "静安区",
            "普陀区",
            "闸北区",
            "虹口区",
            "杨浦区",
            "闵行区",
            "宝山区",
            "嘉定区",
            "浦东新区",
            "金山区",
            "松江区",
            "青浦区",
            "奉贤区",
            "崇明县",
            "其它区",
            "江苏省",
            "南京市",
            "玄武区",
            "秦淮区",
            "建邺区",
            "鼓楼区",
            "浦口区",
            "栖霞区",
            "雨花台区",
            "江宁区",
            "六合区",
            "溧水区",
            "高淳区",
            "其它区",
            "无锡市",
            "崇安区",
            "南长区",
            "北塘区",
            "锡山区",
            "惠山区",
            "滨湖区",
            "江阴市",
            "宜兴市",
            "其它区",
            "徐州市",
            "鼓楼区",
            "云龙区",
            "贾汪区",
            "泉山区",
            "丰县",
            "沛县",
            "铜山区",
            "睢宁县",
            "新沂市",
            "邳州市",
            "其它区",
            "常州市",
            "天宁区",
            "钟楼区",
            "戚墅堰区",
            "新北区",
            "武进区",
            "溧阳市",
            "金坛市",
            "其它区",
            "苏州市",
            "虎丘区",
            "吴中区",
            "相城区",
            "姑苏区",
            "常熟市",
            "张家港市",
            "昆山市",
            "吴江区",
            "太仓市",
            "其它区",
            "南通市",
            "崇川区",
            "港闸区",
            "通州区",
            "海安县",
            "如东县",
            "启东市",
            "如皋市",
            "海门市",
            "其它区",
            "连云港市",
            "连云区",
            "新浦区",
            "海州区",
            "赣榆县",
            "东海县",
            "灌云县",
            "灌南县",
            "其它区",
            "淮安市",
            "清河区",
            "淮安区",
            "淮阴区",
            "清浦区",
            "涟水县",
            "洪泽县",
            "盱眙县",
            "金湖县",
            "其它区",
            "盐城市",
            "亭湖区",
            "盐都区",
            "响水县",
            "滨海县",
            "阜宁县",
            "射阳县",
            "建湖县",
            "东台市",
            "大丰市",
            "其它区",
            "扬州市",
            "广陵区",
            "邗江区",
            "宝应县",
            "仪征市",
            "高邮市",
            "江都区",
            "其它区",
            "镇江市",
            "京口区",
            "润州区",
            "丹徒区",
            "丹阳市",
            "扬中市",
            "句容市",
            "其它区",
            "泰州市",
            "海陵区",
            "高港区",
            "兴化市",
            "靖江市",
            "泰兴市",
            "姜堰区",
            "其它区",
            "宿迁市",
            "宿城区",
            "宿豫区");

    private static final int CITIES[] = {
            350602, 370782, 513431, 532624, 530426, 370203, 350128, 421002, 350624, 430225, 360300, 350203,
            220211, 420822, 530625, 653126, 420203, 220182, 230603, 533323, 430121, 621225, 652827, 511500,
            450205, 652824, 411402, 440781, 469022, 370214, 542521, 433101, 460100, 530381, 411722, 533400,
            110229, 640300, 210700, 450127, 440105, 530828, 120000, 420000, 211402, 341823, 220402, 330500,
            371324, 150500, 150927, 321284, 230231, 150926, 630123, 341700, 441400, 542330, 370684, 370828,
            654326, 610831, 140300, 350581, 421182, 421200, 341124, 371423, 445302, 513225, 532522, 469026,
            450102, 433130, 222406, 511325, 410328, 210422, 430405, 341100, 140212, 445222, 350403, 430521,
            520111, 652924, 522201, 542327, 110103, 530826, 630122, 610527, 330481, 522424, 820000, 231081,
            410103, 431223, 230524, 441284, 500226, 152524, 211382, 530300, 411102, 410727, 152223, 451200,
            610203, 230805, 500116, 341222, 420324, 610500, 141128, 371202, 140426, 510181, 341021, 340421,
            130623, 152529, 130626, 530902, 220102, 532801, 220183, 632122, 371622, 140721, 340121, 420503,
            632524, 610327, 130500, 152923, 150422, 420528, 140221, 430502, 610921, 422822, 130627, 430105,
            410926, 340603, 510321, 211202, 522729, 653100, 421123, 310104, 341282, 410602, 510304, 230712,
            320481, 532627, 610800, 610522, 360829, 410105, 410106, 431126, 330127, 131126, 350428, 130930,
            430621, 130724, 450681, 411381, 130208, 411200, 522327, 640105, 321282, 632323, 371481, 420800,
            621124, 341825, 340300, 450923, 530500, 411423, 150302, 530821, 140802, 310115, 410203, 420116,
            371724, 430922, 130800, 150502, 210711, 230207, 511529, 530325, 320402, 542300, 140723, 542221,
            511800, 150430, 440700, 220421, 350181, 520329, 350784, 440115, 330304, 411221, 510107, 360803,
            520221, 350603, 421003, 411522, 150205, 220503, 620421, 370303, 451031, 150525, 360700, 340711,
            620403, 610924, 500117, 542626, 511922, 620800, 450123, 533123, 320900, 410423, 330902, 451002,
            623027, 620923, 220204, 420923, 210922, 150429, 150929, 420684, 610427, 150624, 340702, 360313,
            320300, 320600, 431228, 621000, 370323, 530421, 640104, 370503, 533421, 420102, 371428, 220105,
            350981, 370113, 230503, 130925, 231025, 620300, 341524, 130127, 231200, 130224, 420606, 652922,
            371426, 130402, 140826, 511304, 610724, 511823, 542100, 510303, 440113, 500114, 542337, 451381,
            513336, 441427, 450311, 630105, 361122, 610829, 360425, 130128, 350105, 511525, 220202, 632724,
            350205, 310112, 640303, 623023, 140726, 513229, 330103, 420204, 140725, 350500, 511826, 211322,
            630102, 230822, 653022, 130426, 371121, 632200, 640521, 621100, 511524, 130803, 130982, 451202,
            350600, 450324, 130424, 510800, 640324, 131028, 450305, 340828, 140423, 654028, 110114, 340503,
            653128, 610823, 510184, 310105, 542622, 610821, 511602, 522730, 331000, 360100, 410421, 450107,
            150826, 610525, 140922, 140107, 632522, 130283, 130121, 130431, 632523, 430223, 530112, 361023,
            361181, 411421, 340302, 654325, 360733, 370322, 450126, 632721, 320924, 430321, 532625, 652927,
            440523, 230302, 511702, 361025, 150725, 360731, 321183, 451024, 440404, 320205, 321302, 370306,
            371322, 510311, 360102, 431124, 410883, 500238, 230110, 522422, 331123, 411403, 522229, 520424,
            510682, 320000, 231102, 652101, 371000, 130107, 420527, 542121, 500112, 150425, 140225, 341623,
            331100, 430522, 540123, 500000, 652923, 130726, 150223, 420529, 440783, 530921, 211003, 320281,
            513223, 530700, 450400, 331004, 410303, 511681, 640205, 640424, 620821, 441424, 530522, 630000,
            370634, 360726, 230604, 150523, 371302, 340323, 141023, 220581, 610431, 610524, 220724, 150922,
            410600, 211103, 440800, 632222, 230505, 330211, 360721, 130406, 522627, 422826, 220122, 210882,
            230826, 530102, 130921, 469002, 360424, 340123, 220502, 120112, 450422, 370830, 152201, 320107,
            361028, 510104, 440923, 654000, 330621, 141034, 450803, 510727, 621222, 530000, 610825, 610302,
            510400, 450109, 441202, 429021, 654201, 211300, 140624, 360302, 511528, 130108, 532527, 652826,
            520303, 530825, 330322, 511600, 430281, 340406, 230624, 522223, 500235, 220281, 411503, 610322,
            411330, 533100, 210122, 411726, 341102, 220181, 530626, 130826, 411622, 232722, 340825, 230102,
            441500, 360826, 420111, 141082, 141182, 231281, 620200, 441581, 431129, 440183, 130604, 220800,
            532329, 620321, 610526, 510000, 411224, 621022, 130428, 340521, 130205, 421087, 532621, 130628,
            310118, 440902, 510502, 321000, 420583, 130804, 542522, 360103, 410800, 420113, 530829, 532524,
            421125, 542301, 513424, 460107, 320830, 421000, 513230, 331024, 360222, 220303, 530602, 511522,
            620982, 110107, 350429, 623021, 230708, 371328, 131082, 441825, 370783, 610400, 140781, 421122,
    };

    private static final int[] calcC = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    private static final char[] calcR = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    // ------------------------------- 部分测试数据---------------------------

    /**
     * 保存一个单例Random
     */
    private static final Random LOCAL_RANDOM = new Random();

    /**
     * 获取一个Random实例。
     * 是一个线程ThreadLocalRandom对象。
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获取一个单例的Random对象。
     */
    public static Random getLocalRandom() {
        return LOCAL_RANDOM;
    }

    /**
     * 获取指定长度的随机数
     *
     * @param length 数字的长度
     */
    public static int getNumber(int length) {
        length--;
        int pow = (int) Math.pow(10, length);
        ThreadLocalRandom random = getRandom();
        if (length >= 1) {
            //参照算法：random.nextInt(9000)+1000;
            //(9 * pow)
            int nextInt = (pow << 3) + pow;
            return (random.nextInt(nextInt) + pow);
        } else {
            return random.nextInt(10);
        }
    }

    /**
     * 获取一个随机整数
     */
    public static int getInteger() {
        return getRandom().nextInt(10);
    }

    /**
     * 获取某个区间中的随机数[a,b)
     */
    public static int getNumber(int a, int b) {
        return getRandom().nextInt(a, b);
    }


    /**
     * 获取某个区间中的随机数[a,b]
     *
     * @param a min number
     * @param b max number
     */
    public static int getNumberWithRight(int a, int b) {
        return getNumber(a, b + 1);
    }

    /* ——————————————————————— getUUID : 获取随机UUID,java.util自带的UUID方法 ——————————————————————————— */


    /**
     * 获取UUID.toString
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }


    /* ——————————————————————— getRandomChar : 获取随机字符（单个字母） ——————————————————————————— */

    /**
     * 获取一个随机英文字符，小写
     */
    public static char getRandomChar() {
        return (char) (RandomUtils.getRandom().nextInt(26) + 97);
    }

    /**
     * 获取一个随机英文字符，大写
     */
    public static char getRandomUpperChar() {
        return (char) (RandomUtils.getRandom().nextInt(26) + 65);
    }


    /* ———————————————————— getRandomString : 获取随机字符串 ———————————————————————— */

    /**
     * 得到一串纯大写的字符串
     *
     * @param length 字符串长度
     */
    public static String getRandomUpperString(int length) {
        char[] crr = new char[length];
        for (int i = 0; i < length; i++) {
            crr[i] = getRandomUpperChar();
        }
        return String.valueOf(crr);
    }

    /**
     * 获取一串指定长度的随机字符串
     *
     * @param length     字符串长度
     * @param randomCase 是否开启随机大小写
     */
    public static String getRandomString(int length, boolean randomCase) {
        char[] crr = new char[length];
        for (int i = 0; i < length; i++) {
            //如果开启了随机大写，则有概率将字符转为大写 1/2
            if (randomCase) {
                crr[i] = RandomUtils.getRandom().nextBoolean() ? getRandomChar() : getRandomUpperChar();
            } else {
                crr[i] = getRandomChar();
            }
        }
        return String.valueOf(crr);
    }

    /**
     * 获取一串指定长度的随机字符串，默认小写
     *
     * @param length 字符串长度
     */
    public static String getRandomString(int length) {
        return getRandomString(length, false);
    }


    /**
     * 获取一串长度为32的字符串，默认小写
     */
    public static String getRandomString() {
        return getRandomString(32, false);
    }

    /**
     * 数字小数保留
     *
     * @param dnum   需要保留的小数
     * @param length 小数保留位数
     */
    public static String toFixed(Number dnum, int length) {
        final StringBuilder sb = new StringBuilder(2 + length).append("#.");
        //遍历并设置位数
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }

        //返回结果
        String formatStr = sb.toString();
        sb.delete(0, sb.length());
        String douStr = numFormat(dnum, formatStr);
        if (douStr.startsWith(".")) {
            //如果开头是点，说明首位是0，补位
            sb.append('0');
        }
        sb.append(douStr);
        return sb.toString();
    }


    /**
     * 自定义数字格式化
     */
    public static String numFormat(Number dnum, String formatStr) {
        return new DecimalFormat(formatStr).format(dnum);
    }

    /* ———————————————————— getColor : 获取随机颜色 ———————————————————————— */

    /**
     * 返回一个随机颜色
     */
    public static Color randomColor() {
        int[] arr = randomColor$intArr();
        return new Color(arr[0], arr[1], arr[2]);
    }

    /**
     * 返回一个长度为三的数组，三位分别代表了颜色的R、G、B
     */
    public static int[] randomColor$intArr() {
        final int[] arr = new int[3];
        Random random = RandomUtils.getRandom();
        arr[0] = random.nextInt(256);
        arr[1] = random.nextInt(256);
        arr[2] = random.nextInt(256);
        return arr;
    }

    /**
     * 返回16进制颜色代码
     */
    public static String randomColor$hexString() {
        int[] arr = randomColor$intArr();
        StringBuilder sb = new StringBuilder();
        String r = Integer.toHexString(arr[0]);
        r = r.length() == 1 ? '0' + r : r;

        String g = Integer.toHexString(arr[1]);
        g = g.length() == 1 ? '0' + g : g;

        String b = Integer.toHexString(arr[2]);
        b = b.length() == 1 ? '0' + b : b;
        sb.append("#")
                .append(r)
                .append(g)
                .append(b);
        return sb.toString();
    }

    /* ———————————————————— getProbability : 根据概率获取boolean ———————————————————————— */

    /**
     * 根据概率获取boolean，区间：[probL , probR]
     *
     * @param probL 概率百分比区间的左参数，取值范围为0-1之间，对应了0%和100%
     * @param probR 概率百分比区间的右参数，取值范围为0-1之间，对应了0%和100%
     */
    public static Boolean getProbability(double probL, double probR) {
        double v = RandomUtils.getRandom().nextDouble();
        return v >= probL && v <= probR;
    }

    /**
     * 根据概率获取boolean，区间：[0 , prob]
     * 填入的参数即为概率的百分比
     *
     * @param prob 概率百分比的小数形式，参数范围0-1
     */
    public static Boolean getProbability(double prob) {
        return getProbability(0, prob);
    }

    /* ———————————————————— getRandomElement : 从数组或者集合中获取一个随机元素 ———————————————————————— */

    /**
     * 从数组中返回一个随机元素
     *
     * @param trr 数组
     *            随机元素
     */
    public static <T> T getRandomElement(T[] trr) {
        Objects.requireNonNull(trr);
        return trr.length == 0 ? null : trr[RandomUtils.getRandom().nextInt(trr.length)];
    }

    /**
     * 从集合中返回一个随机元素, ru如果数组为空则返回null
     *
     * @param trr 集合
     *            随机元素
     */
    public static <T> T getRandomElement(List<T> trr) {
        Objects.requireNonNull(trr);
        return trr.isEmpty() ? null : trr.get(RandomUtils.getRandom().nextInt(trr.size()));
    }

    public static String randomProtocol() {
        Random random = RandomUtils.getRandom();
        return PROTOCOLS[random.nextInt(PROTOCOLS.length)];
    }

    public static String generateRandomChinaRegion() {
        Random random = RandomUtils.getRandom();
        return CHINA_REGIONS[random.nextInt(CHINA_REGIONS.length)];
    }

    public static String randomProvince() {
        Random random = RandomUtils.getRandom();
        return PROVINCES[random.nextInt(PROVINCES.length)];
    }

    public static String randomTID() {
        Random random = RandomUtils.getLocalRandom();
        return TLD[random.nextInt(TLD.length)];
    }

    public static String randomCity() {
        Random random = RandomUtils.getLocalRandom();
        return CHINA_CITIES[random.nextInt(CHINA_CITIES.length)];
    }

    public static String randomCounty(boolean prefix) {
        Random random = RandomUtils.getLocalRandom();
        if (prefix) {
            return randomProvince() + " " + randomCity() + " " + COUNTY.get(random.nextInt(COUNTY.size()));
        }
        return COUNTY.get(random.nextInt(COUNTY.size()));
    }

    public static String englishName() {
        Random random = RandomUtils.getLocalRandom();
        return COMMON_NAMES[random.nextInt(COMMON_NAMES.length)];
    }

    public static String englishSurname() {
        Random random = RandomUtils.getLocalRandom();
        return COMMON_SUR_NAMES[random.nextInt(COMMON_SUR_NAMES.length)];
    }

    public static String rgb() {
        Random random = RandomUtils.getLocalRandom();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return String.format("rgb(%d, %d, %d)", red, green, blue);
    }

    public static String rgb(int red, int green, int blue) {
        return String.format("rgb(%d, %d, %d)", red, green, blue);
    }

    public static String rgba() {
        Random random = RandomUtils.getLocalRandom();
        // Generate random values for red, green, blue, and alpha components
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        float alpha = random.nextFloat(); // Alpha value between 0.0 and 1.0
        // Format the RGBA color string
        return String.format("rgba(%d, %d, %d, %.2f)", red, green, blue, alpha);
    }

    public static String rgba(int red, int green, int blue, float alpha) {
        // Format the RGBA color string
        return String.format("rgba(%d, %d, %d, %.2f)", red, green, blue, alpha);
    }

    public static String hsl() {
        Random random = RandomUtils.getLocalRandom();
        // Generate random values for hue, saturation, and lightness components
        int hue = random.nextInt(360); // Hue is in the range [0, 360)
        int saturation = random.nextInt(101); // Saturation is in the range [0, 100]
        int lightness = random.nextInt(101); // Lightness is in the range [0, 100]
        // Format the HSL color string
        return String.format("hsl(%d, %d%%, %d%%)", hue, saturation, lightness);
    }

    public static String hsl(int hue, int saturation, int lightness) {
        // Format the HSL color string
        return String.format("hsl(%d, %d%%, %d%%)", hue, saturation, lightness);
    }


    public static String regexp(String pattern) {
        try {
            return RegexUtils.generator(pattern);
        } catch (Exception e) {
            return pattern;
        }
    }

    public static String zip() {
        Random random = RandomUtils.getLocalRandom();
        int postalCode = 100000 + random.nextInt(900000);
        return String.format("%06d", postalCode);
    }

    public static String phoneNumber() {
        Random random = RandomUtils.getLocalRandom();
        // Generate a random 11-digit phone number
        StringBuilder phoneNumberBuilder = new StringBuilder("1"); // Start with the country code (China: +86)
        for (int i = 0; i < 10; i++) {
            phoneNumberBuilder.append(random.nextInt(10));
        }
        return phoneNumberBuilder.toString();
    }

    public static String getIdNo(boolean male) {
        //随机生成生日 1~99岁
        long begin = System.currentTimeMillis() - 3153600000000L;//100年内
        long end = System.currentTimeMillis() - 31536000000L; //1年内
        long rtn = begin + (long) (Math.random() * (end - begin));
        Date date = new Date(rtn);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String birth = simpleDateFormat.format(date);
        return getIdNo(birth, male);

    }

    public static String getIdNo(String birth, boolean male) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int value = random.nextInt(CITIES.length);
        sb.append(CITIES[value]);
        sb.append(birth);
        value = random.nextInt(999) + 1;
        if (male && value % 2 == 0) {
            value++;
        }
        if (!male && value % 2 == 1) {
            value++;
        }
        if (value >= 100) {
            sb.append(value);
        } else if (value >= 10) {
            sb.append('0').append(value);
        } else {
            sb.append("00").append(value);
        }
        sb.append(calcTrailingNumber(sb));
        return sb.toString();

    }

    private static char calcTrailingNumber(StringBuilder sb) {
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(String.valueOf(sb.charAt(i)));
        }
        for (int i = 0; i < n.length; i++) {
            result += calcC[i] * n[i];
        }
        return calcR[result % 11];
    }
}
