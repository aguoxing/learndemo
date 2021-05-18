package top.cloudos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cloudos.entity.TColumns;
import top.cloudos.mapper.TColumnsMapper;
import top.cloudos.service.TColumnsService;

/**
 * <p>
 * 初始化图片和录音录像的元数据表 服务实现类
 * </p>
 *
 * @author GX
 * @since 2021-04-28
 */
@Service
public class TColumnsServiceImpl extends ServiceImpl<TColumnsMapper, TColumns> implements TColumnsService {

}
