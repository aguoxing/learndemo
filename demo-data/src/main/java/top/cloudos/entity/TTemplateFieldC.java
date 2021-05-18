package top.cloudos.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 字段信息表
 * </p>
 *
 * @author GX
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TTemplateFieldC implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 字段ID
     */
    private String fieldId;

    /**
     * 元数据中文名称
     */
    private String fieldNameCn;

    /**
     * 元数据英文名称
     */
    private String fieldNameEn;

    /**
     * 对应数据表字段列
     */
    private String fieldColumn;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 档案分类
     */
    private String archiveCate;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 字段长度
     */
    private Long fieldLength;

    /**
     * 数据格式
     */
    private String dataFormat;

    /**
     * 默认值
     */
    private String defaultData;

    /**
     * 有效值规则
     */
    private String verifyRule;

    /**
     * 选项名称
     */
    private String optionName;

    /**
     * 属性项名称
     */
    private String propertyName;

    /**
     * 输入提示
     */
    private String prompts;

    /**
     * 显示宽度
     */
    private Long displayWidth;

    /**
     * 显示高度
     */
    private Long displayHeight;

    /**
     * 安全级别
     */
    private String securityLevel;

    /**
     * 删除界面
     */
    private String delInterface;

    /**
     * 阅览界面
     */
    private String readInterface;

    /**
     * 检索界面
     */
    private String searchInterface;

    /**
     * 列表界面
     */
    private String listInterface;

    /**
     * 增加界面
     */
    private String addInterface;

    /**
     * 修改界面
     */
    private String editInterface;

    /**
     * 是否索引
     */
    private String isIndex;

    /**
     * 必填项
     */
    private String requiredFields;

    /**
     * 档号构成
     */
    private String archivesConstitute;

    /**
     * 是否自增
     */
    private String isAutoIncrement;

    /**
     * 是否为卷内关联字段
     */
    private String isAssociationField;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改者
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
