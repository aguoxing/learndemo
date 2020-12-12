package top.cloudos.task.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cloudos.common.RandUserInfo;
import top.cloudos.common.util.EmailRandomUtils;
import top.cloudos.common.util.PhoneRandomUtils;
import top.cloudos.task.config.UserBatchInsertThread;
import top.cloudos.task.entity.User;
import top.cloudos.task.mapper.UserMapper;
import top.cloudos.task.service.UserService;
import top.cloudos.task.thread.UserThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author GX
 * @since 2020-12-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> createUserList() {
        List<User> users = new ArrayList<>();
        RandUserInfo randUserInfo = new RandUserInfo();
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            String info = randUserInfo.randFamilyName() + randUserInfo.randName(randUserInfo.randSex());
            String name = info.split("-")[0];
            String sex = info.split("-")[1];
            user.setName(name);
            user.setAge(String.valueOf(randUserInfo.randAge()));
            user.setSex(sex);
            user.setPhoneNumber(PhoneRandomUtils.getTel());
            user.setEmail(EmailRandomUtils.getEmail(8, 12));
            users.add(user);
        }
        return users;
    }

    @Override
    public void batchInsertByThread(List<User> userList) throws InterruptedException {
        if (userList == null || userList.isEmpty()) {
            return;
        }
        // 一个线程处理300条数据
        int count = 1000;
        // 数据集合大小
        int listSize = userList.size();
        // 开启的线程数
        int runSize = (listSize / count) + 1;
        // 存放每个线程的执行数据
        List<User> newList = null;
        // 创建一个线程池，数量和开启线程的数量一样
        ExecutorService executor = Executors.newFixedThreadPool(runSize);
        // 创建两个个计数器
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(runSize);

        for (int i = 0; i < runSize; i++) {
            // 计算每个线程执行的数据
            if ((i + 1) == runSize) {
                int startIdx = (i * count);
                int endIdx = userList.size();
                newList = userList.subList(startIdx, endIdx);
            } else {
                int startIdx = (i * count);
                int endIdx = (i + 1) * count;

                newList = userList.subList(startIdx, endIdx);
            }
            UserBatchInsertThread thread = new UserBatchInsertThread(newList, begin, end, this);

            executor.execute(thread);
        }
        begin.countDown();
        end.await();

        executor.shutdown();
    }

    @Override
    public void userThread() {
        List<User> userList = this.createUserList();
        // 接收集合各段的 执行的返回结果
        List<Future<String>> futures = new ArrayList<>();
        // 集合总条数
        int size = userList.size();
        // 将集合切分的段数(2*CPU的核心数)
        int sunSum = 2 * Runtime.getRuntime().availableProcessors();
        int listStart, listEnd;
        // 当总条数不足sunSum条时 用总条数 当做线程切分值
        if (sunSum > size) {
            sunSum = size;
        }

        //将list切分多份多线程执行
        for (int i = 0; i < sunSum; i++) {
            // 计算切割 开始和结束
            listStart = size / sunSum * i;
            listEnd = size / sunSum * (i + 1);
            // 最后一段线程会出现与其他线程不等的情况
            if (i == sunSum - 1) {
                listEnd = size;
            }
            // 线程切断
            List<User> sunList = userList.subList(listStart, listEnd);
            // 子线程初始化
            UserThread userThread = new UserThread(i, sunList, this);
            // 创建线程池
            ExecutorService taskExecutor = Executors.newFixedThreadPool(5);
            // 多线程执行
            futures.add(taskExecutor.submit(userThread));
        }

        //对各个线程段结果进行解析
        for (Future<String> future : futures) {
            try {
                String str;
                if (null != future) {
                    str = future.get();
                    System.out.println("##############current thread id =" + Thread.currentThread().getName() + ",result=" + str);
                } else {
                    System.err.println("失败");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

}
