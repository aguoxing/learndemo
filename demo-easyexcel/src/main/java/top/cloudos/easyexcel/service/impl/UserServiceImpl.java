package top.cloudos.easyexcel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import top.cloudos.easyexcel.entity.User;
import top.cloudos.easyexcel.mapper.UserMapper;
import top.cloudos.easyexcel.service.UserService;
import top.cloudos.easyexcel.utils.excel.EasyExcelUtils;
import top.cloudos.easyexcel.utils.excel.EasyExcelWriterFactory;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GX
 * @since 2021-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void saveExcelData(List<T> list) {
        baseMapper.insertExcelData(list);
    }

    private void test() {
        //导出路径
        String path = "E:\\user.xlsx";
        List<User> userList = this.list();
        //导出Excel，链式添加sheet（使用此方法前提要将上文提到的三个类到导入项目）
        EasyExcelWriterFactory res = EasyExcelUtils.writeWithSheets(path)
                .writeModel(User.class, userList, "用户数据");
        //关闭流，不然会报错
        res.finish();
    }
}
