package top.cloudos.common.util.randinfo;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/10 11:08
 * @description
 **/
public class LonLatRandomUtils {
    /**
     * @param MinLon：最新经度 MaxLon： 最大经度   MinLat：最新纬度   MaxLat：最大纬度    type：设置返回经度还是纬度
     * @return
     * @throws
     * @Title: randomLonLat
     * @Description: 在矩形内随机生成经纬度
     */
    public static String randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat) {
        Random random = new Random();
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 小数后6位
        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();

        return lon + "," + lat;
    }

    public static void main(String[] args) {
        String s = randomLonLat(112.1212, 134.1212, 43.12, 65.123);
        System.out.println(s);
    }
}
