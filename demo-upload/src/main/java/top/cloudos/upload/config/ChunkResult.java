package top.cloudos.upload.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author gx
 * @date 2021/5/13 11:54
 **/
@Data
@NoArgsConstructor
public class ChunkResult implements Serializable{
    private static final long serialVersionUID = -9000695051292877324L;
    /**
     * 是否跳过上传（已上传的可以直接跳过，达到秒传的效果）
     */
    private boolean skipUpload;
    /**
     *已经上传的文件块编号，可以跳过，断点续传
     */
    private ArrayList<Integer> uploadedChunks;
    /**
     *返回结果信息
     */
    private String message;
    /**
     *已上传完整附件的地址
     */
    private String location;

}
