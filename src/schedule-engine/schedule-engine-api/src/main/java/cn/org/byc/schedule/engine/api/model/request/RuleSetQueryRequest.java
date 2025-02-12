package cn.org.byc.schedule.engine.api.model.request;

import cn.org.byc.schedule.engine.domain.model.enums.RuleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 规则集查询请求对象
 *
 * @author Ken
 */
@Data
@Schema(description = "规则集查询请求")
public class RuleSetQueryRequest {

    /**
     * 课表编号
     */
    @Schema(description = "课表编号")
    private Long timeTableId;

    /**
     * 关联的各个规则表主键
     */
    @Schema(description = "关联的各个规则表主键")
    private Long ruleId;

    /**
     * 规则类型
     */
    @Schema(description = "规则类型")
    private RuleType ruleType;

    /**
     * 规则对象
     */
    @Schema(description = "规则对象")
    private String ruleObject;

    /**
     * 如果有关联到课表时，课表输入表的主键
     */
    @Schema(description = "课表输入表的主键")
    private Long timeTableInputId;

    /**
     * 教案平头科目编号
     */
    @Schema(description = "教案平头科目编号")
    private Long neatSubjectId;

    /**
     * 老师每天上课的最大课时数
     */
    @Schema(description = "老师每天上课的最大课时数")
    private Integer maxClassPeriodNumber;

    /**
     * 老师每周最大天数
     */
    @Schema(description = "老师每周最大天数")
    private Integer maxWorkDayNumber;
} 