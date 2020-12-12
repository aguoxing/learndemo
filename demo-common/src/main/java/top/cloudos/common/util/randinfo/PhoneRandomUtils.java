package top.cloudos.common.util.randinfo;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/10 11:07
 * @description
 **/
public class PhoneRandomUtils {
    /**
     * 返回手机号码
     */
    private static final String[] TEL_FIRST = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153,189,199".split(",");

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public static String getTel() {
        int index = getNum(0, TEL_FIRST.length - 1);
        String first = TEL_FIRST[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String tel = getTel();
            System.out.println(tel);
        }
    }
}