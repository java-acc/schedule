package cn.org.byc.schedule.engine.infrastructure.repository.impl;

import cn.org.byc.schedule.base.api.model.Result;
import cn.org.byc.schedule.engine.domain.error.RuleSetError;
import cn.org.byc.schedule.engine.domain.model.entity.RuleSet;
import cn.org.byc.schedule.engine.domain.model.query.RuleSetQuery;
import cn.org.byc.schedule.engine.domain.repository.RuleSetRepository;
import cn.org.byc.schedule.engine.infrastructure.dataobject.RuleSetDO;
import cn.org.byc.schedule.engine.infrastructure.repository.mapper.RuleSetDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 规则集仓储实现类
 *
 * @author Ken
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class RuleSetRepositoryImpl implements RuleSetRepository {

    private final RuleSetDao ruleSetDao;

    @Override
    @Transactional
    public Mono<Result<RuleSet>> save(RuleSet ruleSet) {
        try {
            RuleSetDO ruleSetDO = toRuleSetDO(ruleSet);
            return Mono.just(Result.success(toRuleSet(ruleSetDao.save(ruleSetDO))));
        } catch (Exception e) {
            log.error("保存规则集信息失败", e);
            return Mono.just(Result.fail(RuleSetError.RULE_SET_SAVE_ERROR));
        }
    }

    @Override
    @Transactional
    public Mono<Result<RuleSet>> update(RuleSet ruleSet) {
        try {
            return findById(ruleSet.getId())
                    .flatMap(result -> {
                        if (result.getData() == null) {
                            return Mono.just(Result.fail(RuleSetError.RULE_SET_NOT_FOUND));
                        }
                        RuleSetDO ruleSetDO = toRuleSetDO(ruleSet);
                        return Mono.just(Result.success(toRuleSet(ruleSetDao.save(ruleSetDO))));
                    });
        } catch (Exception e) {
            log.error("更新规则集信息失败", e);
            return Mono.just(Result.fail(RuleSetError.RULE_SET_UPDATE_ERROR));
        }
    }

    @Override
    public Mono<Result<RuleSet>> findById(Long id) {
        try {
            return Mono.justOrEmpty(ruleSetDao.findById(id))
                    .map(this::toRuleSet)
                    .map(Result::success)
                    .defaultIfEmpty(Result.fail(RuleSetError.RULE_SET_NOT_FOUND));
        } catch (Exception e) {
            log.error("查询规则集信息失败, id: {}", id, e);
            return Mono.error(e);
        }
    }

    @Override
    public Mono<Result<List<RuleSet>>> findByTimeTableId(Long timeTableId) {
        try {
            List<RuleSet> ruleSets = ruleSetDao.findByTimeTableId(timeTableId)
                    .stream()
                    .map(this::toRuleSet)
                    .collect(Collectors.toList());
            if (ruleSets.isEmpty()) {
                return Mono.just(Result.fail(RuleSetError.RULE_SET_NOT_FOUND));
            }
            return Mono.just(Result.success(ruleSets));
        } catch (Exception e) {
            log.error("查询规则集信息失败, timeTableId: {}", timeTableId, e);
            return Mono.just(Result.fail(RuleSetError.RULE_SET_QUERY_ERROR));
        }
    }

    @Override
    public Flux<RuleSet> query(RuleSetQuery query) {
        try {
            return Flux.fromIterable(ruleSetDao.findAll())
                    .map(this::toRuleSet)
                    .filter(ruleSet -> matchQuery(ruleSet, query));
        } catch (Exception e) {
            log.error("查询规则集列表失败", e);
            return Flux.empty();
        }
    }

    @Override
    @Transactional
    public Mono<Result<Void>> deleteById(Long id) {
        try {
            return findById(id)
                    .flatMap(result -> {
                        if (result.getData() == null) {
                            return Mono.just(Result.fail(RuleSetError.RULE_SET_NOT_FOUND));
                        }
                        ruleSetDao.deleteById(id);
                        return Mono.just(Result.success());
                    });
        } catch (Exception e) {
            log.error("删除规则集信息失败, id: {}", id, e);
            return Mono.just(Result.fail(RuleSetError.RULE_SET_DELETE_ERROR));
        }
    }

    @Override
    @Transactional
    public Mono<Result<Void>> deleteByTimeTableId(Long timeTableId) {
        try {
            ruleSetDao.deleteByTimeTableId(timeTableId);
            return Mono.just(Result.success());
        } catch (Exception e) {
            log.error("删除规则集信息失败, timeTableId: {}", timeTableId, e);
            return Mono.just(Result.fail(RuleSetError.RULE_SET_DELETE_ERROR));
        }
    }

    /**
     * 将领域对象转换为数据库实体
     *
     * @param ruleSet 规则集领域对象
     * @return 规则集数据库实体
     */
    private RuleSetDO toRuleSetDO(RuleSet ruleSet) {
        RuleSetDO ruleSetDO = new RuleSetDO();
        ruleSetDO.setId(ruleSet.getId());
        ruleSetDO.setTimeTableId(ruleSet.getTimeTableId());
        ruleSetDO.setRuleId(ruleSet.getRuleId());
        ruleSetDO.setRuleType(ruleSet.getRuleType());
        ruleSetDO.setRuleObject(ruleSet.getRuleObject());
        ruleSetDO.setRuleDetail(ruleSet.getRuleDetail());
        ruleSetDO.setRuleWeight(ruleSet.getRuleWeight());
        ruleSetDO.setTimeTableInputId(ruleSet.getTimeTableInputId());
        ruleSetDO.setNeatSubjectId(ruleSet.getNeatSubjectId());
        ruleSetDO.setMaxClassPeriodNumber(ruleSet.getMaxClassPeriodNumber());
        ruleSetDO.setMaxWorkDayNumber(ruleSet.getMaxWorkDayNumber());
        ruleSetDO.setForbidClassPlaceList(ruleSet.getForbidClassPlaceList());
        return ruleSetDO;
    }

    /**
     * 将数据库实体转换为领域对象
     *
     * @param ruleSetDO 规则集数据库实体
     * @return 规则集领域对象
     */
    private RuleSet toRuleSet(RuleSetDO ruleSetDO) {
        return RuleSet.builder()
                .id(ruleSetDO.getId())
                .timeTableId(ruleSetDO.getTimeTableId())
                .ruleId(ruleSetDO.getRuleId())
                .ruleType(ruleSetDO.getRuleType())
                .ruleObject(ruleSetDO.getRuleObject())
                .ruleDetail(ruleSetDO.getRuleDetail())
                .ruleWeight(ruleSetDO.getRuleWeight())
                .timeTableInputId(ruleSetDO.getTimeTableInputId())
                .neatSubjectId(ruleSetDO.getNeatSubjectId())
                .maxClassPeriodNumber(ruleSetDO.getMaxClassPeriodNumber())
                .maxWorkDayNumber(ruleSetDO.getMaxWorkDayNumber())
                .forbidClassPlaceList(ruleSetDO.getForbidClassPlaceList())
                .build();
    }

    /**
     * 匹配查询条件
     *
     * @param ruleSet 规则集领域对象
     * @param query   查询条件
     * @return 是否匹配
     */
    private boolean matchQuery(RuleSet ruleSet, RuleSetQuery query) {
        if (query == null) {
            return true;
        }
        if (query.getTimeTableId() != null && !query.getTimeTableId().equals(ruleSet.getTimeTableId())) {
            return false;
        }
        if (query.getRuleId() != null && !query.getRuleId().equals(ruleSet.getRuleId())) {
            return false;
        }
        if (query.getRuleType() != null && !query.getRuleType().equals(ruleSet.getRuleType())) {
            return false;
        }
        if (query.getRuleObject() != null && !ruleSet.getRuleObject().contains(query.getRuleObject())) {
            return false;
        }
        if (query.getTimeTableInputId() != null && !query.getTimeTableInputId().equals(ruleSet.getTimeTableInputId())) {
            return false;
        }
        if (query.getNeatSubjectId() != null && !query.getNeatSubjectId().equals(ruleSet.getNeatSubjectId())) {
            return false;
        }
        if (query.getMaxClassPeriodNumber() != null && !query.getMaxClassPeriodNumber().equals(ruleSet.getMaxClassPeriodNumber())) {
            return false;
        }
        if (query.getMaxWorkDayNumber() != null && !query.getMaxWorkDayNumber().equals(ruleSet.getMaxWorkDayNumber())) {
            return false;
        }
        return true;
    }
} 