package cn.org.byc.schedule.engine.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校领域对象
 *
 * @author Ken
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School {

    /**
     * 学校ID
     */
    private Long id;

    /**
     * 学校编码
     */
    private String code;

    /**
     * 学校名称
     */
    private String name;

    /**
     * 学校地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;
} 