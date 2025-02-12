package cn.org.byc.schedule.engine.adapter.converter;

import cn.org.byc.schedule.engine.api.model.request.RuleSetAddRequest;
import cn.org.byc.schedule.engine.api.model.request.RuleSetQueryRequest;
import cn.org.byc.schedule.engine.api.model.request.RuleSetUpdateRequest;
import cn.org.byc.schedule.engine.api.model.response.RuleSetResponse;
import cn.org.byc.schedule.engine.domain.model.entity.RuleSet;
import cn.org.byc.schedule.engine.domain.model.query.RuleSetQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 规则集对象转换工具
 *
 * @author Ken
 */
@Mapper
public interface RuleSetConverter {

    RuleSetConverter INSTANCE = Mappers.getMapper(RuleSetConverter.class);

    /**
     * 将新增请求转换为领域对象
     *
     * @param request 新增请求
     * @return 领域对象
     */
    RuleSet toDomain(RuleSetAddRequest request);

    /**
     * 将更新请求转换为领域对象
     *
     * @param request 更新请求
     * @return 领域对象
     */
    RuleSet toDomain(RuleSetUpdateRequest request);

    /**
     * 将查询请求转换为查询对象
     *
     * @param request 查询请求
     * @return 查询对象
     */
    RuleSetQuery toQuery(RuleSetQueryRequest request);

    /**
     * 将领域对象转换为响应对象
     *
     * @param ruleSet 领域对象
     * @return 响应对象
     */
    RuleSetResponse toResponse(RuleSet ruleSet);

    /**
     * 将领域对象列表转换为响应对象列表
     *
     * @param ruleSets 领域对象列表
     * @return 响应对象列表
     */
    List<RuleSetResponse> toResponseList(List<RuleSet> ruleSets);
} 