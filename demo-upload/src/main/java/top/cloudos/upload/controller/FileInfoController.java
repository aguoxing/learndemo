package top.cloudos.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.cloudos.upload.config.ApiResult;
import top.cloudos.upload.config.ChunkResult;
import top.cloudos.upload.entity.ChunkInfo;
import top.cloudos.upload.entity.FileInfo;
import top.cloudos.upload.service.ChunkInfoService;
import top.cloudos.upload.service.FileInfoService;
import top.cloudos.upload.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GX
 * @since 2021-05-13
 */
@Slf4j
@RestController
@RequestMapping("/file-info")
public class FileInfoController {
    @Autowired
    private ChunkInfoService chunkInfoService;
    @Autowired
    private FileInfoService fileInfoService;

    /**
     * 校验文件
     *
     * @param chunk
     * @param response
     * @return
     */
    @GetMapping("/chunk")
    public ChunkResult checkChunk(ChunkInfo chunk, HttpServletResponse response) {
        log.info("校验文件：{}", chunk);
        return chunkInfoService.checkChunkState(chunk, response);
    }

    /**
     * 文件块上传
     *
     * @param chunk
     * @return
     */
    @PostMapping("/chunk")
    public Integer uploadChunk(ChunkInfo chunk) {
        return chunkInfoService.uploadFile(chunk);
    }

    @PostMapping("/mergeFile")
    public HttpServletResponse mergeFile(@RequestBody FileInfo fileInfo, HttpServletResponse response) {
        return fileInfoService.mergeFile(fileInfo, response);
    }

    /**
     * 查询列表
     *
     * @return ApiResult
     */
    @GetMapping(value = "/selectFileList/{page}/{size}")
    public ApiResult selectFileList(@RequestBody FileInfo file,
                                    @PathVariable("page") Integer pageNum,
                                    @PathVariable("size") Integer pageSize) {
        log.info("查询文件列表：{}", file);
        return ApiResult.success(fileInfoService.selectFileList(file, pageNum, pageSize));
    }

    /**
     * 删除
     */
    @DeleteMapping(value = "/deleteFile")
    public ApiResult deleteFile(@RequestBody FileInfo fileInfo) {
        return ApiResult.success(fileInfoService.deleteFile(fileInfo));
    }

    /**
     * 下载文件
     *
     * @param fileInfo
     * @param resp
     */
    @GetMapping(value = "/download")
    public void download(@RequestBody FileInfo fileInfo,
                         HttpServletRequest req,
                         HttpServletResponse resp) {
        String location = fileInfo.getLocation();
        String fileName = fileInfo.getFilename();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(location));
            fos = resp.getOutputStream();
            bos = new BufferedOutputStream(fos);
            ServletUtils.setFileDownloadHeader(req, resp, fileName);
            int byteRead = 0;
            byte[] buffer = new byte[8192];
            while ((byteRead = bis.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, byteRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.flush();
                bis.close();
                fos.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
