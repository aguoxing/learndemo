package top.cloudos.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.cloudos.entity.DaTColumns;
import top.cloudos.entity.TColumns;
import top.cloudos.entity.TTemplateField;
import top.cloudos.service.DaTColumnsService;
import top.cloudos.service.TColumnsService;
import top.cloudos.service.TTemplateFieldCService;
import top.cloudos.service.TTemplateFieldService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gx
 * @date 2021/4/28 14:59
 **/
@SpringBootTest
public class FieldTest {
    @Autowired
    private TTemplateFieldService templateFieldService;
    @Autowired
    private TColumnsService tColumnsService;
    @Autowired
    private DaTColumnsService daTColumnsService;
    @Autowired
    private TTemplateFieldCService tt;

    @Test
    public void Field() {
        List<DaTColumns> daTColumnsList = daTColumnsService.list();
        List<TColumns> tColumns = tColumnsService.list();
        String templateId = "YSJCKMB001";
        List<TTemplateField> tTemplateFields = templateFieldService.list(new QueryWrapper<TTemplateField>().eq("field_column", "f"));

        AtomicInteger atomicInteger = new AtomicInteger(0);
        tTemplateFields.forEach(i -> {
            atomicInteger.getAndIncrement();
            if (atomicInteger.get() < 10) {
                i.setFieldColumn("f00" + atomicInteger.get());
            } else if (atomicInteger.get() < 100) {
                i.setFieldColumn("f0" + atomicInteger.get());
            } else {
                i.setFieldColumn("f" + atomicInteger.get());
            }
            templateFieldService.updateById(i);
        });
    }
}
