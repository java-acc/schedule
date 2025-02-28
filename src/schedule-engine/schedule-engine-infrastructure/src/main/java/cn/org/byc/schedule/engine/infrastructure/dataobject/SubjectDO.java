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

import cn.org.byc.schedule.jpa.domain.audit.BaseAuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_subject")
public class SubjectDO extends BaseAuditEntity {
    /**
     * 学校编号
     */
    @Column(nullable = false)
    private Long schoolId;

    /**
     * 名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 简称
     */
    @Column(nullable = false)
    private String abbreviation;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 英文简称
     */
    private String enAbbreviation;

    /**
     * 状态
     */
    private Integer status;
}
