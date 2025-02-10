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

package cn.org.byc.schedule.engine.infrastructure.repository.entity;

import cn.org.byc.schedule.jpa.domain.audit.BaseAuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 学校实体
 *
 * @author Ken
 */
@Entity
@Getter
@Setter
@Table(name = "t_school")
public class SchoolDO extends BaseAuditEntity {

    /**
     * 学校名称
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * 学校代码
     */
    @Column(nullable = false, length = 50)
    private String code;

    /**
     * 学校地址
     */
    @Column(length = 200)
    private String address;

    /**
     * 学校联系电话
     */
    @Column(length = 20)
    private String phone;
}
