package top.cloudos.upload.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.cloudos.upload.config.ChunkResult;
import top.cloudos.upload.entity.ChunkInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GX
 * @since 2021-05-13
 */
public interface ChunkInfoService extends IService<ChunkInfo> {
    /**
     * 校验当前文件
     *
     * @param chunkInfo
     * @param response
     * @return 秒传？续传？新传？
     */
    ChunkResult checkChunkState(ChunkInfo chunkInfo, HttpServletResponse response);

    /**
     * 上传文件
     *
     * @param chunk
     * @return
     */
    Integer uploadFile(ChunkInfo chunk);
}
