package cn.org.byc.schedule.engine.domain.repository;

import cn.org.byc.schedule.base.api.model.Result;
import cn.org.byc.schedule.engine.domain.model.entity.RuleSet;
import cn.org.byc.schedule.engine.domain.model.query.RuleSetQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 规则集仓储接口
 *
 * @author Ken
 */
public interface RuleSetRepository {

    /**
     * 保存规则集
     *
     * @param ruleSet 规则集信息
     * @return 保存结果
     */
    Mono<Result<RuleSet>> save(RuleSet ruleSet);

    /**
     * 更新规则集
     *
     * @param ruleSet 规则集信息
     * @return 更新结果
     */
    Mono<Result<RuleSet>> update(RuleSet ruleSet);

    /**
     * 根据ID查询规则集
     *
     * @param id 规则集ID
     * @return 查询结果
     */
    Mono<Result<RuleSet>> findById(Long id);

    /**
     * 根据课表ID查询规则集列表
     *
     * @param timeTableId 课表ID
     * @return 查询结果
     */
    Mono<Result<List<RuleSet>>> findByTimeTableId(Long timeTableId);

    /**
     * 查询规则集列表
     *
     * @param query 查询条件
     * @return 查询结果
     */
    Flux<RuleSet> query(RuleSetQuery query);

    /**
     * 删除规则集
     *
     * @param id 规则集ID
     * @return 删除结果
     */
    Mono<Result<Void>> deleteById(Long id);

    /**
     * 根据课表ID删除规则集
     *
     * @param timeTableId 课表ID
     * @return 删除结果
     */
    Mono<Result<Void>> deleteByTimeTableId(Long timeTableId);
} 