package top.cloudos.easyexcel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.poi.ss.formula.functions.T;
import top.cloudos.easyexcel.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GX
 * @since 2021-04-19
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * save
     * @param list
     */
    void insertExcelData(List<T> list);
}
