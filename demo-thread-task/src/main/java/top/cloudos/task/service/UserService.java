package top.cloudos.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.cloudos.task.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author GX
 * @since 2020-12-09
 */
public interface UserService extends IService<User> {
    /**
     * 创建测试数据
     *
     * @return
     */
    List<User> createUserList();

    /**
     * 多线程插入用户信息
     *
     * @param userList
     */
    void batchInsertByThread(List<User> userList) throws InterruptedException;

    /**
     * new
     */
    void userThread();
}
