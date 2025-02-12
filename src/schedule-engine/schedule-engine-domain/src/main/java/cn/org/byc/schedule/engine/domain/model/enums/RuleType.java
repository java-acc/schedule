/*
 * Copyright 2025 Ken(kan.zhang-cn@hotmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.org.byc.schedule.engine.domain.model.enums;

/**
 * 排课规则类型枚举
 *
 * @author Ken
 */
public enum RuleType {
    /**
     * 课节禁排规则
     * 课位提前剔除，无需单独处理规则
     */
    PERIOD_FORBIDDEN(1, "课节禁排", "periodForbiddenRuleService", "periodForbiddenRuleChecker"),

    /**
     * 课节固定排课规则
     * 课位提前剔除，无需单独处理规则
     */
    PERIOD_FIXED(2, "课节固排", "periodFixedRuleService", "periodFixedRuleChecker"),

    /**
     * 科目连续排课规则
     */
    SUBJECT_CONTINUOUS(3, "科目连排次数", "subjectContinuousRuleService", "subjectContinuousRuleChecker"),

    /**
     * 教案平头规则
     */
    SUBJECT_ALIGNED(4, "教案平头", "subjectAlignedRuleService", "subjectAlignedRuleChecker"),

    /**
     * 科目互斥规则
     */
    SUBJECT_MUTUALLY_EXCLUSIVE(5, "科目互斥", "subjectMutuallyExclusiveRuleService", "subjectMutuallyExclusiveRuleChecker"),

    /**
     * 教师互斥规则
     */
    TEACHER_MUTUALLY_EXCLUSIVE(7, "老师互斥", "teacherMutuallyExclusiveRuleService", "teacherMutuallyExclusiveRuleChecker"),

    /**
     * 教师禁排规则
     */
    TEACHER_PERIOD_FORBIDDEN(8, "老师禁排", "teacherPeriodForbiddenRuleService", "teacherPeriodForbiddenRuleChecker"),

    /**
     * 教师每天最大课时数规则
     */
    TEACHER_DAILY_MAX_PERIODS(9, "老师每天最大课时数", "teacherDailyMaxPeriodsRuleService", "teacherDailyMaxPeriodsRuleChecker"),

    /**
     * 教师每周最大工作天数规则
     */
    TEACHER_WEEKLY_MAX_WORKDAYS(10, "老师每周最大天数", "teacherWeeklyMaxWorkdaysRuleService", "teacherWeeklyMaxWorkdaysRuleChecker"),

    /**
     * 课表不连排规则
     */
    TIMETABLE_NON_CONTINUOUS(11, "课表不连排", "timetableNonContinuousRuleService", "timetableNonContinuousRuleChecker"),

    /**
     * 课表禁排规则
     * 课位提前剔除，无需单独处理规则
     */
    TIMETABLE_FORBIDDEN(12, "课表禁排", "timetableForbiddenRuleService", "timetableForbiddenRuleChecker"),

    /**
     * 资源冲突检查规则
     */
    RESOURCE_CONFLICT(101, "资源检查", "resourceConflictRuleService", "resourceConflictRuleChecker");

    private final Integer value;
    private final String desc;
    private final String serviceName;
    private final String checkServiceName;

    RuleType(Integer value, String desc, String serviceName, String checkServiceName) {
        this.value = value;
        this.desc = desc;
        this.serviceName = serviceName;
        this.checkServiceName = checkServiceName;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getCheckServiceName() {
        return checkServiceName;
    }
}
