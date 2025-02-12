package cn.org.byc.schedule.engine.api.model.request;

import cn.org.byc.schedule.engine.domain.model.enums.RuleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 规则集新增请求对象
 *
 * @author Ken
 */
@Data
@Schema(description = "规则集新增请求")
public class RuleSetAddRequest {

    /**
     * 课表编号
     */
    @NotNull(message = "课表编号不能为空")
    @Schema(description = "课表编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long timeTableId;

    /**
     * 关联的各个规则表主键
     */
    @NotNull(message = "规则表主键不能为空")
    @Schema(description = "关联的各个规则表主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long ruleId;

    /**
     * 规则类型
     */
    @NotNull(message = "规则类型不能为空")
    @Schema(description = "规则类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private RuleType ruleType;

    /**
     * 规则对象
     */
    @Size(max = 500, message = "规则对象长度不能超过500个字符")
    @Schema(description = "规则对象")
    private String ruleObject;

    /**
     * 规则详情
     */
    @Size(max = 2000, message = "规则详情长度不能超过2000个字符")
    @Schema(description = "规则详情")
    private String ruleDetail;

    /**
     * 规则权重
     */
    @Size(max = 50, message = "规则权重长度不能超过50个字符")
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
    @Size(max = 1000, message = "禁排的课节列表长度不能超过1000个字符")
    @Schema(description = "禁排的课节列表")
    private String forbidClassPlaceList;

    /**
     * 固定课节编号列表
     */
    @Size(max = 1000, message = "固定课节编号列表长度不能超过1000个字符")
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
    @Size(max = 1000, message = "教案平头班级编号列表长度不能超过1000个字符")
    @Schema(description = "教案平头班级编号列表")
    private String neatClassIdList;

    /**
     * 科目互斥规则，科目互斥列表
     */
    @Size(max = 1000, message = "科目互斥列表长度不能超过1000个字符")
    @Schema(description = "科目互斥列表")
    private String subjectMutexIdList;

    /**
     * 同时上课规则，输入项编号列表
     */
    @Size(max = 1000, message = "输入项编号列表长度不能超过1000个字符")
    @Schema(description = "输入项编号列表")
    private String sameTimeInputIdList;

    /**
     * 老师编号列表(适用于老师互斥，老师禁排，老师最大工作天数，老师最大工作课时)
     */
    @Size(max = 1000, message = "老师编号列表长度不能超过1000个字符")
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