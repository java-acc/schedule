package cn.org.byc.schedule.engine.api.model.response;

import cn.org.byc.schedule.engine.domain.model.enums.RuleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 规则集响应对象
 *
 * @author Ken
 */
@Data
@Schema(description = "规则集响应")
public class RuleSetResponse {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

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
     * 规则详情
     */
    @Schema(description = "规则详情")
    private String ruleDetail;

    /**
     * 规则权重
     */
    @Schema(description = "规则权重")
    private String ruleWeight;

    /**
     * 如果有关联到课表时，课表输入表的主键
     */
    @Schema(description = "课表输入表的主键")
    private Long timeTableInputId;

    /**
     * 课节禁排规则时，禁排的课节列表
     */
    @Schema(description = "禁排的课节列表")
    private String forbidClassPlaceList;

    /**
     * 固定课节编号列表
     */
    @Schema(description = "固定课节编号列表")
    private String fixedClassPlaceList;

    /**
     * 连排次数
     */
    @Schema(description = "连排次数")
    private Integer continuityNumber;

    /**
     * 不连排课节序号
     */
    @Schema(description = "不连排课节序号")
    private Integer notContinuityNumber;

    /**
     * 教案平头科目编号
     */
    @Schema(description = "教案平头科目编号")
    private Long neatSubjectId;

    /**
     * 教案平头班级编号列表
     */
    @Schema(description = "教案平头班级编号列表")
    private String neatClassIdList;

    /**
     * 科目互斥规则，科目互斥列表
     */
    @Schema(description = "科目互斥列表")
    private String subjectMutexIdList;

    /**
     * 同时上课规则，输入项编号列表
     */
    @Schema(description = "输入项编号列表")
    private String sameTimeInputIdList;

    /**
     * 老师编号列表(适用于老师互斥，老师禁排，老师最大工作天数，老师最大工作课时)
     */
    @Schema(description = "老师编号列表")
    private String teacherIdList;

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