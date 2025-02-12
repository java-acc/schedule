package cn.org.byc.schedule.engine.controller;

import cn.org.byc.schedule.base.api.model.Result;
import cn.org.byc.schedule.engine.adapter.converter.SchoolConverter;
import cn.org.byc.schedule.engine.api.SchoolApi;
import cn.org.byc.schedule.engine.api.model.request.SchoolAddRequest;
import cn.org.byc.schedule.engine.api.model.request.SchoolQueryRequest;
import cn.org.byc.schedule.engine.api.model.request.SchoolUpdateRequest;
import cn.org.byc.schedule.engine.api.model.response.SchoolResponse;
import cn.org.byc.schedule.engine.domain.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 学校管理控制器
 *
 * @author Ken
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SchoolController implements SchoolApi {

    private final SchoolRepository schoolRepository;

    @Override
    public Mono<Result<SchoolResponse>> add(SchoolAddRequest request) {
        log.info("创建学校: {}", request);
        return schoolRepository.save(SchoolConverter.INSTANCE.toDomain(request))
                .map(result -> Result.success(SchoolConverter.INSTANCE.toResponse(result.getData())));
    }

    @Override
    public Mono<Result<SchoolResponse>> update(SchoolUpdateRequest request) {
        log.info("更新学校: {}", request);
        return schoolRepository.update(SchoolConverter.INSTANCE.toDomain(request))
                .map(result -> Result.success(SchoolConverter.INSTANCE.toResponse(result.getData())));
    }

    @Override
    public Mono<Result<SchoolResponse>> findById(Long id) {
        log.info("查询学校, id: {}", id);
        return schoolRepository.findById(id)
                .map(result -> Result.success(SchoolConverter.INSTANCE.toResponse(result.getData())));
    }

    @Override
    public Mono<Result<SchoolResponse>> findByCode(String code) {
        log.info("查询学校, code: {}", code);
        return schoolRepository.findByCode(code)
                .map(result -> Result.success(SchoolConverter.INSTANCE.toResponse(result.getData())));
    }

    @Override
    public Flux<SchoolResponse> query(SchoolQueryRequest request) {
        log.info("查询学校列表: {}", request);
        return schoolRepository.query(SchoolConverter.INSTANCE.toQuery(request))
                .map(SchoolConverter.INSTANCE::toResponse);
    }

    @Override
    public Mono<Result<Void>> deleteById(Long id) {
        log.info("删除学校, id: {}", id);
        return schoolRepository.deleteById(id);
    }
} 