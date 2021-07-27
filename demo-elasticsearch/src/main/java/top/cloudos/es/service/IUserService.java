package top.cloudos.es.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.cloudos.es.entity.User;

/**
 * @author gx
 * @date 2021/7/10 11:28
 **/
public interface IUserService extends IService<User> {
    /**
     * description：删除索引
     *
     * @param index
     * @return
     * @author gx
     * @date 2021/7/10 11:55
     */
    void deleteIndex(String index);
}
