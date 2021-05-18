package top.cloudos.upload.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import top.cloudos.upload.entity.FileInfo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GX
 * @since 2021-05-13
 */
public interface FileInfoMapper extends BaseMapper<FileInfo> {
    /**
     * description：
     *
     * @param page
     * @param fileInfo
     * @return {@link IPage< FileInfo>}
     * @author gx
     * @date 2021/5/13 11:53
     */
    IPage<FileInfo> selectFileList(IPage<FileInfo> page, FileInfo fileInfo);
}
