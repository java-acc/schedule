package cn.org.byc.schedule.engine.api.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 学校创建请求对象
 *
 * @author Ken
 */
@Data
@Schema(description = "学校创建请求")
public class SchoolCreateRequest {

    /**
     * 学校代码
     */
    @NotBlank(message = "学校代码不能为空")
    @Size(max = 50, message = "学校代码长度不能超过50个字符")
    @Schema(description = "学校代码", example = "SCH001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    /**
     * 学校名称
     */
    @NotBlank(message = "学校名称不能为空")
    @Size(max = 100, message = "学校名称长度不能超过100个字符")
    @Schema(description = "学校名称", example = "示例学校", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 学校地址
     */
    @Size(max = 200, message = "学校地址长度不能超过200个字符")
    @Schema(description = "学校地址", example = "北京市海淀区")
    private String address;

    /**
     * 联系电话
     */
    @Size(max = 20, message = "联系电话长度不能超过20个字符")
    @Schema(description = "联系电话", example = "010-12345678")
    private String phone;

    /**
     * 备注
     */
    @Size(max = 500, message = "备注长度不能超过500个字符")
    @Schema(description = "备注")
    private String remark;
} 