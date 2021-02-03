package top.cloudos.task.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.cloudos.common.util.QRCodeUtils;
import top.cloudos.task.entity.User;
import top.cloudos.task.service.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/10 10:21
 * @description
 **/
@Slf4j
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    /**
     * 多线程插入数据
     *
     * @throws InterruptedException
     */
    @Test
    public void batchInsertByThread() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        List<User> userList = userService.createUserList();
        log.info("生成" + userList.size() + "条数据耗时(ms):" + (System.currentTimeMillis() - startTime));
        log.info("======================>开始插入数据");
        long startTime2 = System.currentTimeMillis();
        userService.batchInsertByThread(userList);
        log.info("插入" + userList.size() + "条数据耗时(ms):" + (System.currentTimeMillis() - startTime2));
    }

    /**
     * 普通插入
     */
    @Test
    public void batchInsert() {
        long startTime = System.currentTimeMillis();
        List<User> userList = userService.createUserList();
        log.info("生成" + userList.size() + "条数据耗时(ms):" + (System.currentTimeMillis() - startTime));
        log.info("======================>开始插入数据");
        long startTime2 = System.currentTimeMillis();
        userService.saveBatch(userList);
        log.info("插入" + userList.size() + "条数据耗时(ms):" + (System.currentTimeMillis() - startTime2));
    }

    @Test
    public void test() {
        long startTime = System.currentTimeMillis();
        userService.userThread();
        log.info("数据耗时(ms):" + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void getUserList() {
        long startTime = System.currentTimeMillis();
        List<User> userList = userService.list();
        log.info("查询"+userList.size()+"数据耗时(ms):" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 二维码
     */
    @Test
    public void qr() throws Exception {
        // 存放在二维码中的内容
        String text = "hello world";
        // 嵌入二维码的图片路径
        String imgPath = "/data/logo.jpg";
        // 生成的二维码的路径及名称
        String destPath =  "/data/QRCode.png";
        //生成二维码
        QRCodeUtils.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtils.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }

    @Test
    public void is() {
        isUnique("hello");
        String str1 = "hello";
        String str2 = "he"+new String("llo");
//        System.err.println(str1==str2);
//        System.out.println(tets(9,6));

        Map<String,String> map = new HashMap<String,String>();
        String s1 = "1";
        String s2 = "2";
        long s3 = 1L;
        map.put(s1,"1");
        map.put(s2,"2");
        System.out.println(map.get(s3));
    }

    public boolean isUnique(String astr) {
        for (int i=0;i<astr.length();i++){
            if (astr.lastIndexOf(astr.charAt(i))!=i){
                return false;
            }
        }
        return true;
    }

    public int tets(int m,int n){
        int k = m>n?m:n;
        for(;k%m!=0||(k%n!=0);k++);
        return k;
    }

}
