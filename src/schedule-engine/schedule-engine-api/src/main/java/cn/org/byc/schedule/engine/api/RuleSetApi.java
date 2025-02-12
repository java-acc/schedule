package cn.org.byc.schedule.engine.api;

import cn.org.byc.schedule.base.api.model.Result;
import cn.org.byc.schedule.engine.api.model.request.RuleSetAddRequest;
import cn.org.byc.schedule.engine.api.model.request.RuleSetQueryRequest;
import cn.org.byc.schedule.engine.api.model.request.RuleSetUpdateRequest;
import cn.org.byc.schedule.engine.api.model.response.RuleSetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 规则集管理API接口
 *
 * @author Ken
 */
@Tag(name = "规则集管理", description = "规则集管理相关接口")
@Validated
@RequestMapping("/api/v1/rule-sets")
public interface RuleSetApi {

    /**
     * 创建规则集
     *
     * @param request 创建请求
     * @return 创建结果
     */
    @Operation(summary = "创建规则集")
    @PostMapping
    Mono<Result<RuleSetResponse>> add(@Valid @RequestBody RuleSetAddRequest request);

    /**
     * 更新规则集
     *
     * @param request 更新请求
     * @return 更新结果
     */
    @Operation(summary = "更新规则集")
    @PutMapping
    Mono<Result<RuleSetResponse>> update(@Valid @RequestBody RuleSetUpdateRequest request);

    /**
     * 根据ID查询规则集
     *
     * @param id 规则集ID
     * @return 查询结果
     */
    @Operation(summary = "根据ID查询规则集")
    @GetMapping("/{id}")
    Mono<Result<RuleSetResponse>> findById(@Parameter(description = "规则集ID") @PathVariable(name = "id") Long id);

    /**
     * 根据课表ID查询规则集列表
     *
     * @param timeTableId 课表ID
     * @return 查询结果
     */
    @Operation(summary = "根据课表ID查询规则集列表")
    @GetMapping("/time-table/{timeTableId}")
    Mono<Result<RuleSetResponse>> findByTimeTableId(@Parameter(description = "课表ID") @PathVariable(name = "timeTableId") Long timeTableId);

    /**
     * 查询规则集列表
     *
     * @param request 查询请求
     * @return 查询结果
     */
    @Operation(summary = "查询规则集列表")
    @GetMapping
    Flux<RuleSetResponse> query(@Valid RuleSetQueryRequest request);

    /**
     * 删除规则集
     *
     * @param id 规则集ID
     * @return 删除结果
     */
    @Operation(summary = "删除规则集")
    @DeleteMapping("/{id}")
    Mono<Result<Void>> deleteById(@Parameter(description = "规则集ID") @PathVariable(name = "id") Long id);

    /**
     * 根据课表ID删除规则集
     *
     * @param timeTableId 课表ID
     * @return 删除结果
     */
    @Operation(summary = "根据课表ID删除规则集")
    @DeleteMapping("/time-table/{timeTableId}")
    Mono<Result<Void>> deleteByTimeTableId(@Parameter(description = "课表ID") @PathVariable(name = "timeTableId") Long timeTableId);
} 