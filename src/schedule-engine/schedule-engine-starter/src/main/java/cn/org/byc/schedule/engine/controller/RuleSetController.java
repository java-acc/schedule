package cn.org.byc.schedule.engine.controller;

import cn.org.byc.schedule.base.api.model.Result;
import cn.org.byc.schedule.engine.adapter.converter.RuleSetConverter;
import cn.org.byc.schedule.engine.api.RuleSetApi;
import cn.org.byc.schedule.engine.api.model.request.RuleSetAddRequest;
import cn.org.byc.schedule.engine.api.model.request.RuleSetQueryRequest;
import cn.org.byc.schedule.engine.api.model.request.RuleSetUpdateRequest;
import cn.org.byc.schedule.engine.api.model.response.RuleSetResponse;
import cn.org.byc.schedule.engine.domain.repository.RuleSetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 规则集管理控制器
 *
 * @author Ken
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RuleSetController implements RuleSetApi {

    private final RuleSetRepository ruleSetRepository;

    @Override
    public Mono<Result<RuleSetResponse>> add(RuleSetAddRequest request) {
        log.info("创建规则集: {}", request);
        return ruleSetRepository.save(RuleSetConverter.INSTANCE.toDomain(request))
                .map(result -> Result.success(RuleSetConverter.INSTANCE.toResponse(result.getData())));
    }

    @Override
    public Mono<Result<RuleSetResponse>> update(RuleSetUpdateRequest request) {
        log.info("更新规则集: {}", request);
        return ruleSetRepository.update(RuleSetConverter.INSTANCE.toDomain(request))
                .map(result -> Result.success(RuleSetConverter.INSTANCE.toResponse(result.getData())));
    }

    @Override
    public Mono<Result<RuleSetResponse>> findById(Long id) {
        log.info("查询规则集, id: {}", id);
        return ruleSetRepository.findById(id)
                .map(result -> Result.success(RuleSetConverter.INSTANCE.toResponse(result.getData())));
    }

    @Override
    public Mono<Result<RuleSetResponse>> findByTimeTableId(Long timeTableId) {
        log.info("查询规则集列表, timeTableId: {}", timeTableId);
        return ruleSetRepository.findByTimeTableId(timeTableId)
                .map(result -> {
                    List<RuleSetResponse> responses = RuleSetConverter.INSTANCE.toResponseList(result.getData());
                    return Result.success(responses.get(0));
                });
    }

    @Override
    public Flux<RuleSetResponse> query(RuleSetQueryRequest request) {
        log.info("查询规则集列表: {}", request);
        return ruleSetRepository.query(RuleSetConverter.INSTANCE.toQuery(request))
                .map(RuleSetConverter.INSTANCE::toResponse);
    }

    @Override
    public Mono<Result<Void>> deleteById(Long id) {
        log.info("删除规则集, id: {}", id);
        return ruleSetRepository.deleteById(id);
    }

    @Override
    public Mono<Result<Void>> deleteByTimeTableId(Long timeTableId) {
        log.info("删除规则集, timeTableId: {}", timeTableId);
        return ruleSetRepository.deleteByTimeTableId(timeTableId);
    }
} 