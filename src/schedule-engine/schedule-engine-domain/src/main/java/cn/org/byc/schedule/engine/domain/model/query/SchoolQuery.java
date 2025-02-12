package cn.org.byc.schedule.engine.domain.model.query;

import lombok.Data;

/**
 * 学校查询对象
 *
 * @author Ken
 */
@Data
public class SchoolQuery {

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
} 