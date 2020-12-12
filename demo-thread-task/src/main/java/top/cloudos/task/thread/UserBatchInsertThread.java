package top.cloudos.task.thread;

import top.cloudos.task.entity.User;
import top.cloudos.task.service.UserService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/8 10:59
 * @description
 **/
public class UserBatchInsertThread implements Runnable {

    private UserService userService;

    /**
     * 数据集合
     */
    private List<User> list;
    /**
     * 每个线程处理的起始数据
     */
    private CountDownLatch begin;
    /**
     * 每个线程处理的结束数据
     */
    private CountDownLatch end;

    public UserBatchInsertThread() {
    }

    public UserBatchInsertThread(List<User> list, CountDownLatch begin, CountDownLatch end, UserService userService) {
        this.list = list;
        this.begin = begin;
        this.end = end;
        this.userService = userService;
    }

    @Override
    public void run() {
        try {
            if (list != null && !list.isEmpty()) {
                userService.saveBatch(list);
            }
            // 执行完让线程直接进入等待
            begin.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 当一个线程执行完 了计数要减一不然这个线程会被一直挂起
            end.countDown();
        }
    }
}
