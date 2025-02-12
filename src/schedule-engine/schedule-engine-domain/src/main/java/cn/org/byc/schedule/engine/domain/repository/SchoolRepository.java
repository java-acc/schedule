package cn.org.byc.schedule.engine.domain.repository;

import cn.org.byc.schedule.base.api.model.Result;
import cn.org.byc.schedule.engine.domain.model.entity.School;
import cn.org.byc.schedule.engine.domain.model.query.SchoolQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 学校仓储接口
 *
 * @author Ken
 */
public interface SchoolRepository {

    /**
     * 保存学校
     *
     * @param school 学校信息
     * @return 保存结果
     */
    Mono<Result<School>> save(School school);

    /**
     * 更新学校
     *
     * @param school 学校信息
     * @return 更新结果
     */
    Mono<Result<School>> update(School school);

    /**
     * 根据ID查询学校
     *
     * @param id 学校ID
     * @return 查询结果
     */
    Mono<Result<School>> findById(Long id);

    /**
     * 根据编码查询学校
     *
     * @param code 学校编码
     * @return 查询结果
     */
    Mono<Result<School>> findByCode(String code);

    /**
     * 查询学校列表
     *
     * @param query 查询条件
     * @return 查询结果
     */
    Flux<School> query(SchoolQuery query);

    /**
     * 删除学校
     *
     * @param id 学校ID
     * @return 删除结果
     */
    Mono<Result<Void>> deleteById(Long id);
} 