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

package cn.org.byc.schedule.jpa.domain.audit;

import cn.org.byc.schedule.jpa.config.SnowflakeIdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 审计基础实体
 *
 * @author Ken
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditEntity {

    /**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "snowFlakeIdGenerator", type = SnowflakeIdGenerator.class)
    @GeneratedValue(generator = "snowFlakeIdGenerator")
    @Column(name = "id")
    private Long id;

    /**
     * 创建人
     */
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private Long createdBy;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdTime;

    /**
     * 最后修改人
     */
    @LastModifiedBy
    @Column(nullable = false)
    private Long lastModifiedBy;

    /**
     * 最后修改时间
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedTime;
}
