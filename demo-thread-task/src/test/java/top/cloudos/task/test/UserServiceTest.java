package top.cloudos.task.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.cloudos.task.entity.User;
import top.cloudos.task.service.UserService;

import java.util.List;

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

}
