package top.cloudos.task.thread;

import lombok.extern.slf4j.Slf4j;
import top.cloudos.task.entity.User;
import top.cloudos.task.service.UserService;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/12 9:30
 * @description
 **/
@Slf4j
public class UserThread implements Callable<String> {
    private UserService userService;
    /**
     * 当前是属于第几段线程
     */
    private int pageIndex;
    /**
     * 此段数据的集合
     */
    private List<User> userList;

    public UserThread(int pageIndex, List<User> userList, UserService userService) {
        this.pageIndex = pageIndex;
        this.userList = userList;
        this.userService = userService;
    }

    @Override
    public String call() throws Exception {
        if (!userList.isEmpty()) {
            return userService.saveBatch(userList) ? "success" : "fail";
        }
        return "fail";
    }
}
