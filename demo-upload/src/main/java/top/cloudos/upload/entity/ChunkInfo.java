package top.cloudos.upload.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author GX
 * @since 2021-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_chunk_info")
public class ChunkInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
    private String id;

    /**
     * 块编号，从1开始
     */
    @TableField("CHUNK_NUMBER")
    private BigDecimal chunkNumber;

    /**
     * 块大小
     */
    @TableField("CHUNK_SIZE")
    private BigDecimal chunkSize;

    /**
     * 当前块大小
     */
    @TableField("CURRENT_CHUNK_SIZE")
    private BigDecimal currentChunkSize;

    /**
     * 文件标识
     */
    @TableField("IDENTIFIER")
    private String identifier;

    /**
     * 文件名
     */
    @TableField("FILENAME")
    private String filename;

    /**
     * 相对路径
     */
    @TableField("RELATIVE_PATH")
    private String relativePath;

    /**
     * 总块数
     */
    @TableField("TOTAL_CHUNKS")
    private BigDecimal totalChunks;

    /**
     * 总大小
     */
    @TableField("TOTAL_SIZE")
    private Integer totalSize;

    /**
     * 文件类型
     */
    @TableField("FILE_TYPE")
    private String fileType;

    /**
     * 块内容
     */
    @TableField(exist = false)
    private MultipartFile multipartFile;

    public static final String COL_ID = "ID";

    public static final String COL_CHUNK_NUMBER = "CHUNK_NUMBER";

    public static final String COL_CHUNK_SIZE = "CHUNK_SIZE";

    public static final String COL_CURRENT_CHUNKSIZE = "CURRENT_CHUNKSIZE";

    public static final String COL_IDENTIFIER = "IDENTIFIER";

    public static final String COL_FILE_NAME = "FILENAME";

    public static final String COL_RELATIVE_PATH = "RELATIVE_PATH";

    public static final String COL_TOTAL_CHUNKS = "TOTAL_CHUNKS";

    public static final String COL_TOTAL_SIZE = "TOTAL_SIZE";

    public static final String COL_FILE_TYPE = "FILETYPE";

}
