package cn.org.byc.schedule.engine.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校领域实体
 *
 * @author Ken
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 学校名称
     */
    private String name;

    /**
     * 学校代码
     */
    private String code;

    /**
     * 学校地址
     */
    private String address;

    /**
     * 学校联系电话
     */
    private String phone;
} 