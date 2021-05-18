package top.cloudos.upload.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("t_file_info")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
    private String id;

    /**
     * 文件名
     */
    @TableField("FILENAME")
    private String filename;

    /**
     * 文件标识
     */
    @TableField("IDENTIFIER")
    private String identifier;

    /**
     * 总大小
     */
    @TableField("TOTAL_SIZE")
    private Long totalSize;
    @TableField(exist = false)
    private String totalSizeName;

    /**
     * 存储地址
     */
    @TableField("LOCATION")
    private String location;

    /**
     * 文件类型
     */
    @TableField("FILETYPE")
    private String filetype;

    /**
     * 文件所属
     */
    @TableField("REF_PROJECT_ID")
    private String refProjectId;

    /**
     * 上传用户
     */
    @TableField("UPLOAD_USER")
    private Integer uploadUser;

    /**
     * 上传时间
     */
    @TableField("UPLOAD_TIME")
    private LocalDateTime uploadTime;

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
        if (1024 * 1024 > this.totalSize && this.totalSize >= 1024) {
            this.totalSizeName = String.format("%.2f", this.totalSize.doubleValue() / 1024) + "KB";
        } else if (1024 * 1024 * 1024 > this.totalSize && this.totalSize >= 1024 * 1024) {
            this.totalSizeName = String.format("%.2f", this.totalSize.doubleValue() / (1024 * 1024)) + "MB";
        } else if (this.totalSize >= 1024 * 1024 * 1024) {
            this.totalSizeName = String.format("%.2f", this.totalSize.doubleValue() / (1024 * 1024 * 1024)) + "GB";
        } else {
            this.totalSizeName = this.totalSize.toString() + "B";
        }
    }

    public static final String COL_ID = "ID";

    public static final String COL_FILE_NAME = "FILENAME";

    public static final String COL_IDENTIFIER = "IDENTIFIER";

    public static final String COL_TOTAL_SIZE = "TOTAL_SIZE";

    public static final String COL_LOCATION = "LOCATION";

    public static final String COL_FILE_TYPE = "FILETYPE";

    public static final String COL_REF_PROJECT_ID = "REF_PROJECT_ID";

    public static final String COL_UPLOAD_USER = "UPLOAD_USER";

    public static final String COL_UPLOAD_TIME = "UPLOAD_TIME";

}
