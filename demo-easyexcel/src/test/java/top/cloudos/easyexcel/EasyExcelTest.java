package top.cloudos.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.cloudos.EasyExcelApplication;
import top.cloudos.easyexcel.entity.User;
import top.cloudos.easyexcel.service.UserService;
import top.cloudos.easyexcel.utils.excel.EasyExcelUtils;
import top.cloudos.easyexcel.utils.excel.EasyExcelWriterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gx
 * @date 2021/4/19 17:35
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyExcelApplication.class)
public class EasyExcelTest {
    @Autowired
    private UserService userService;

    @Test
    public void simpleRead() {
        String fileName = "E:\\download\\1618652843962用户数据.xlsx";
        List<Map<Integer, String>> data = EasyExcelUtils.syncRead(fileName,0,0);
        for (Map<Integer, String> obj : data) {
            System.out.println(obj);
        }
    }

    @Test
    public void simpleWrite() {
        //导出路径
        String path = "E:\\user.xlsx";
        List<User> userList = userService.list();
        //导出Excel，链式添加sheet（使用此方法前提要将上文提到的三个类到导入项目）
        EasyExcelWriterFactory res = EasyExcelUtils.writeWithSheets(path)
                .writeModel(User.class, userList, "用户数据");
        //关闭流，不然会报错
        res.finish();
    }

    /**
     * 不创建对象的写
     */
    @Test
    public void noModelWrite() {
        // 写法1
        String fileName = "E:\\nomodel.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(dataList());
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("姓名" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<String>();
        head1.add("年龄" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<String>();
        head2.add("性别" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private List<User> dataList() {
        return userService.list();
    }
}
