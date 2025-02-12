package cn.org.byc.schedule.engine.domain.model.entity;

import cn.org.byc.schedule.engine.domain.model.enums.RuleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 规则集领域对象
 *
 * @author Ken
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleSet {

    /**
     * 主键ID
     */
    private Long id;

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
     * 规则详情
     */
    private String ruleDetail;

    /**
     * 规则权重
     */
    private Integer ruleWeight;

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

    /**
     * 禁止上课位置列表
     */
    private String forbidClassPlaceList;
} 