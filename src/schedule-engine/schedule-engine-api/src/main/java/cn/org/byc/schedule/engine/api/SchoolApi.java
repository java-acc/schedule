package cn.org.byc.schedule.engine.api;

import cn.org.byc.schedule.base.api.model.Result;
import cn.org.byc.schedule.engine.api.model.request.SchoolAddRequest;
import cn.org.byc.schedule.engine.api.model.request.SchoolQueryRequest;
import cn.org.byc.schedule.engine.api.model.request.SchoolUpdateRequest;
import cn.org.byc.schedule.engine.api.model.response.SchoolResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 学校管理API接口
 *
 * @author Ken
 */
@Tag(name = "学校管理", description = "学校管理相关接口")
@Validated
@RequestMapping("/api/v1/schools")
public interface SchoolApi {

    /**
     * 创建学校
     *
     * @param request 创建请求
     * @return 创建结果
     */
    @Operation(summary = "创建学校")
    @PostMapping
    Mono<Result<SchoolResponse>> add(@Valid @RequestBody SchoolAddRequest request);

    /**
     * 更新学校
     *
     * @param request 更新请求
     * @return 更新结果
     */
    @Operation(summary = "更新学校")
    @PutMapping
    Mono<Result<SchoolResponse>> update(@Valid @RequestBody SchoolUpdateRequest request);

    /**
     * 根据ID查询学校
     *
     * @param id 学校ID
     * @return 查询结果
     */
    @Operation(summary = "根据ID查询学校")
    @GetMapping("/{id}")
    Mono<Result<SchoolResponse>> findById(@Parameter(description = "学校ID") @PathVariable Long id);

    /**
     * 根据编码查询学校
     *
     * @param code 学校编码
     * @return 查询结果
     */
    @Operation(summary = "根据编码查询学校")
    @GetMapping("/code/{code}")
    Mono<Result<SchoolResponse>> findByCode(@Parameter(description = "学校编码") @PathVariable String code);

    /**
     * 查询学校列表
     *
     * @param request 查询请求
     * @return 查询结果
     */
    @Operation(summary = "查询学校列表")
    @GetMapping
    Flux<SchoolResponse> query(@Valid SchoolQueryRequest request);

    /**
     * 删除学校
     *
     * @param id 学校ID
     * @return 删除结果
     */
    @Operation(summary = "删除学校")
    @DeleteMapping("/{id}")
    Mono<Result<Void>> deleteById(@Parameter(description = "学校ID") @PathVariable Long id);
} 