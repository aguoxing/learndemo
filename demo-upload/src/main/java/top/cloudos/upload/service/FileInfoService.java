package top.cloudos.upload.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.cloudos.upload.entity.FileInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author GX
 * @since 2021-05-13
 */
public interface FileInfoService extends IService<FileInfo> {
    /**
     * 合并文件
     *
     * @param fileInfo
     * @return
     */
    HttpServletResponse mergeFile(FileInfo fileInfo, HttpServletResponse response);

    /**
     * 查询文件列表
     *
     * @param file
     * @return
     */
    IPage<FileInfo> selectFileList(FileInfo file, Integer pageNum, Integer pageSize);

    /**
     * 删除
     *
     * @param fileInfo
     * @return
     */
    Integer deleteFile(FileInfo fileInfo);
}
