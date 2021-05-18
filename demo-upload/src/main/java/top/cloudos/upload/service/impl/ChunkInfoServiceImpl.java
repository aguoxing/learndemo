package top.cloudos.upload.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.cloudos.upload.config.ChunkResult;
import top.cloudos.upload.entity.ChunkInfo;
import top.cloudos.upload.mapper.ChunkInfoMapper;
import top.cloudos.upload.service.ChunkInfoService;
import top.cloudos.upload.utils.FileInfoUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GX
 * @since 2021-05-13
 */
@Service
public class ChunkInfoServiceImpl extends ServiceImpl<ChunkInfoMapper, ChunkInfo> implements ChunkInfoService {
    @Value("${base.file-path}")
    private String uploadFolder;
    @Autowired
    private ChunkInfoMapper chunkInfoMapper;

    /**
     * 校验当前文件
     * @param chunkInfo
     * @param response
     * @return 秒传？续传？新传？
     */
    @Override
    public ChunkResult checkChunkState(ChunkInfo chunkInfo, HttpServletResponse response) {
        ChunkResult chunkResult = new ChunkResult();
        String file = uploadFolder + File.separator + chunkInfo.getIdentifier() + File.separator + chunkInfo.getFilename();
        if(FileInfoUtils.fileExists(file)) {
            chunkResult.setSkipUpload(true);
            chunkResult.setLocation(file);
            response.setStatus(HttpServletResponse.SC_OK);
            chunkResult.setMessage("完整文件已存在，直接跳过上传，实现秒传");
            return chunkResult;
        }
        ArrayList<Integer> list = chunkInfoMapper.selectChunkNumbers(chunkInfo);
        if (list !=null && list.size() > 0) {
            chunkResult.setSkipUpload(false);
            chunkResult.setUploadedChunks(list);
            response.setStatus(HttpServletResponse.SC_OK);
            chunkResult.setMessage("部分文件块已存在，继续上传剩余文件块，实现断点续传");
            return chunkResult;
        }
        return chunkResult;
    }

    /**
     * 写文件
     * @param chunk
     * @return
     */
    @Override
    public Integer uploadFile(ChunkInfo chunk) {
        int apiRlt = HttpServletResponse.SC_OK;
        MultipartFile file = chunk.getMultipartFile();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FileInfoUtils.generatePath(uploadFolder, chunk));
            Files.write(path, bytes);
            if(chunkInfoMapper.insert(chunk) < 0){
                apiRlt = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
            }
        } catch (IOException e) {
//            log.error("写文件出错：{}",e.getMessage());
            apiRlt = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
        }
        return apiRlt;
    }
}
