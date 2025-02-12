package cn.org.byc.schedule.engine.api.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 学校查询请求对象
 *
 * @author Ken
 */
@Data
@Schema(description = "学校查询请求")
public class SchoolQueryRequest {

    /**
     * 学校代码
     */
    @Schema(description = "学校代码", example = "SCH001")
    private String code;

    /**
     * 学校名称
     */
    @Schema(description = "学校名称", example = "示例学校")
    private String name;

    /**
     * 学校地址
     */
    @Schema(description = "学校地址", example = "北京市海淀区")
    private String address;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话", example = "010-12345678")
    private String phone;
} 