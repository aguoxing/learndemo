package top.cloudos.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 初始化图片和录音录像的元数据表
 * </p>
 *
 * @author GX
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TColumns implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 国家编号
     */
    @TableField("nationCode")
    private String nationCode;

    /**
     * 广东编号
     */
    private String code;

    /**
     * 父级id
     */
    @TableField("fId")
    private String fId;

    /**
     * 元数据中文名称
     */
    @TableField("metadataChineseName")
    private String metadataChineseName;

    /**
     * 元数据英文名称
     */
    @TableField("metadataEnglishName")
    private String metadataEnglishName;

    /**
     * 数据长度（默认）
     */
    @TableField("dataLength")
    private Integer dataLength;

    /**
     * 约束类型 0可选 1条件选 2必选
     */
    @TableField("bindingForceType")
    private Integer bindingForceType;

    /**
     * 可重复性 0不可重复 1可重复
     */
    @TableField("repeatabilityType")
    private Integer repeatabilityType;

    /**
     * 元数据类型 0简单型 1容器型 2复合型
     */
    @TableField("metadataType")
    private Integer metadataType;

    /**
     * 数据类型 0日期时间型 1日期型 2字符型 3数值型 4整数型
     */
    @TableField("dataType")
    private Integer dataType;

    /**
     * 应用层次 0件 1件和卷
     */
    @TableField("applicationLevelType")
    private Integer applicationLevelType;

    /**
     * 值域
     */
    @TableField("valueRange")
    private String valueRange;

    /**
     * 类型 0照片 1录音录像
     */
    private Integer type;

    /**
     * 默认选取 0否 1是
     */
    @TableField("isDefaultSelect")
    private Integer isDefaultSelect;

    /**
     * 是否公用字段
     */
    @TableField("isSumColumn")
    private Integer isSumColumn;

    /**
     * 是否系统自动填充字段
     */
    @TableField("isSystem")
    private Integer isSystem;

    /**
     * 是否是分类字段
     */
    @TableField("isClassify")
    private Integer isClassify;

    /**
     * 数据类型：0档案实体元数据 1业务实体元数据 2机构人员实体元数据 3授权实体元数据 4自定义
     */
    @TableField("columnType")
    private Integer columnType;


}
