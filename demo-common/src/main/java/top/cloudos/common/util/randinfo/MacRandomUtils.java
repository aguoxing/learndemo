package top.cloudos.common.util.randinfo;

import java.util.Random;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/10 11:09
 * @description
 **/
public class MacRandomUtils {
    private static String SEPARATOR_OF_MAC = ":";

    public static String randomMac4Qemu() {
        Random random = new Random();
        String[] mac = {String.format("%02x", 0x52), String.format("%02x", 0x54), String.format("%02x", 0x00), String.format("%02x", random.nextInt(0xff)), String.format("%02x", random.nextInt(0xff)), String.format("%02x", random.nextInt(0xff))};
        return String.join(SEPARATOR_OF_MAC, mac);
    }

    public static void main(String[] args) {
        System.out.println(randomMac4Qemu());
    }
}
