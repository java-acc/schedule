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

package cn.org.byc.schedule.engine.infrastructure.dataobject;

import cn.org.byc.schedule.engine.domain.model.enums.RuleType;
import lombok.Data;
import jakarta.persistence.*;

/**
 * 规则集数据库实体
 *
 * @author Ken
 */
@Data
@Entity
@Table(name = "t_rule_set")
public class RuleSetDO {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 课表ID
     */
    @Column(name = "time_table_id", nullable = false)
    private Long timeTableId;

    /**
     * 规则ID
     */
    @Column(name = "rule_id", nullable = false)
    private Long ruleId;

    /**
     * 规则类型
     */
    @Column(name = "rule_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RuleType ruleType;

    /**
     * 规则对象
     */
    @Column(name = "rule_object", length = 500)
    private String ruleObject;

    /**
     * 规则详情
     */
    @Column(name = "rule_detail", length = 500)
    private String ruleDetail;

    /**
     * 规则权重
     */
    @Column(name = "rule_weight")
    private Integer ruleWeight;

    /**
     * 课表输入ID
     */
    @Column(name = "time_table_input_id", length = 50)
    private Long timeTableInputId;

    /**
     * 整齐科目ID
     */
    @Column(name = "neat_subject_id", length = 50)
    private Long neatSubjectId;

    /**
     * 最大课时数
     */
    @Column(name = "max_class_period_number")
    private Integer maxClassPeriodNumber;

    /**
     * 最大工作日数
     */
    @Column(name = "max_work_day_number")
    private Integer maxWorkDayNumber;

    /**
     * 禁止上课位置列表
     */
    @Column(name = "forbid_class_place_list", length = 500)
    private String forbidClassPlaceList;
}
