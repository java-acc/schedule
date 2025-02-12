package cn.org.byc.schedule.engine.adapter.converter;

import cn.org.byc.schedule.engine.api.model.request.SchoolAddRequest;
import cn.org.byc.schedule.engine.api.model.request.SchoolQueryRequest;
import cn.org.byc.schedule.engine.api.model.request.SchoolUpdateRequest;
import cn.org.byc.schedule.engine.api.model.response.SchoolResponse;
import cn.org.byc.schedule.engine.domain.model.entity.School;
import cn.org.byc.schedule.engine.domain.model.query.SchoolQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 学校对象转换工具
 *
 * @author Ken
 */
@Mapper
public interface SchoolConverter {

    SchoolConverter INSTANCE = Mappers.getMapper(SchoolConverter.class);

    /**
     * 将新增请求转换为领域对象
     *
     * @param request 新增请求
     * @return 领域对象
     */
    School toDomain(SchoolAddRequest request);

    /**
     * 将更新请求转换为领域对象
     *
     * @param request 更新请求
     * @return 领域对象
     */
    School toDomain(SchoolUpdateRequest request);

    /**
     * 将查询请求转换为查询对象
     *
     * @param request 查询请求
     * @return 查询对象
     */
    SchoolQuery toQuery(SchoolQueryRequest request);

    /**
     * 将领域对象转换为响应对象
     *
     * @param school 领域对象
     * @return 响应对象
     */
    SchoolResponse toResponse(School school);

    /**
     * 将领域对象列表转换为响应对象列表
     *
     * @param schools 领域对象列表
     * @return 响应对象列表
     */
    List<SchoolResponse> toResponseList(List<School> schools);
} 