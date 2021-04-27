package top.cloudos.easyexcel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;
import top.cloudos.easyexcel.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GX
 * @since 2021-04-19
 */
public interface UserService extends IService<User> {

    /**
     * save
     * @param list
     */
    void saveExcelData(List<T> list);
}
