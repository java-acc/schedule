package cn.org.byc.schedule.engine.domain.model.query;

import cn.org.byc.schedule.engine.domain.model.enums.RuleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 规则集查询对象
 *
 * @author Ken
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleSetQuery {

    /**
     * 课表ID
     */
    private Long timeTableId;

    /**
     * 规则ID
     */
    private Long ruleId;

    /**
     * 规则类型
     */
    private RuleType ruleType;

    /**
     * 规则对象
     */
    private String ruleObject;

    /**
     * 课表输入ID
     */
    private Long timeTableInputId;

    /**
     * 整齐科目ID
     */
    private Long neatSubjectId;

    /**
     * 最大课时数
     */
    private Integer maxClassPeriodNumber;

    /**
     * 最大工作日数
     */
    private Integer maxWorkDayNumber;
} 