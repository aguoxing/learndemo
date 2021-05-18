package top.cloudos.upload.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.cloudos.upload.entity.ChunkInfo;

import java.util.ArrayList;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author GX
 * @since 2021-05-13
 */
public interface ChunkInfoMapper extends BaseMapper<ChunkInfo> {
    /**
     * 查询文件块号
     *
     * @param record
     * @return
     */
    ArrayList<Integer> selectChunkNumbers(ChunkInfo record);
}
