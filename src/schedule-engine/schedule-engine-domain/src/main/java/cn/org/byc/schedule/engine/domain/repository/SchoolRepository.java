package cn.org.byc.schedule.engine.domain.repository;

import cn.org.byc.schedule.engine.domain.model.entity.School;
import reactor.core.publisher.Mono;

/**
 * 学校仓储接口
 *
 * @author Ken
 */
public interface SchoolRepository {

    /**
     * 保存学校信息
     *
     * @param school 学校信息
     * @return 保存后的学校信息
     */
    Mono<School> save(School school);

    /**
     * 根据ID查询学校信息
     *
     * @param id 学校ID
     * @return 学校信息
     */
    Mono<School> findById(Long id);

    /**
     * 根据学校代码查询学校信息
     *
     * @param code 学校代码
     * @return 学校信息
     */
    Mono<School> findByCode(String code);
} 