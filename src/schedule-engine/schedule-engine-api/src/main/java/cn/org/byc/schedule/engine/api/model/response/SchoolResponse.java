package cn.org.byc.schedule.engine.api.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 学校响应对象
 *
 * @author Ken
 */
@Data
@Schema(description = "学校响应")
public class SchoolResponse {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 学校代码
     */
    @Schema(description = "学校代码")
    private String code;

    /**
     * 学校名称
     */
    @Schema(description = "学校名称")
    private String name;

    /**
     * 学校地址
     */
    @Schema(description = "学校地址")
    private String address;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
} 