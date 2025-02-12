package cn.org.byc.schedule.engine.infrastructure.repository.impl;

import cn.org.byc.schedule.base.api.model.Result;
import cn.org.byc.schedule.engine.domain.error.SchoolError;
import cn.org.byc.schedule.engine.domain.model.entity.School;
import cn.org.byc.schedule.engine.domain.model.query.SchoolQuery;
import cn.org.byc.schedule.engine.domain.repository.SchoolRepository;
import cn.org.byc.schedule.engine.infrastructure.dataobject.SchoolDO;
import cn.org.byc.schedule.engine.infrastructure.repository.mapper.SchoolDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 学校仓储实现类
 *
 * @author Ken
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class SchoolRepositoryImpl implements SchoolRepository {

    private final SchoolDao schoolDao;

    @Override
    @Transactional
    public Mono<Result<School>> save(School school) {
        try {
            SchoolDO schoolDO = toSchoolDO(school);
            return Mono.just(Result.success(toSchool(schoolDao.save(schoolDO))));
        } catch (Exception e) {
            log.error("保存学校信息失败", e);
            return Mono.just(Result.fail(SchoolError.SCHOOL_SAVE_ERROR));
        }
    }

    @Override
    @Transactional
    public Mono<Result<School>> update(School school) {
        try {
            return findById(school.getId())
                    .flatMap(result -> {
                        if (!result.getData().getCode().equals(school.getCode())) {
                            return Mono.just(Result.fail(SchoolError.SCHOOL_CODE_NOT_MATCH));
                        }
                        SchoolDO schoolDO = toSchoolDO(school);
                        return Mono.just(Result.success(toSchool(schoolDao.save(schoolDO))));
                    });
        } catch (Exception e) {
            log.error("更新学校信息失败", e);
            return Mono.just(Result.fail(SchoolError.SCHOOL_UPDATE_ERROR));
        }
    }

    @Override
    public Mono<Result<School>> findById(Long id) {
        try {
            return Mono.justOrEmpty(schoolDao.findById(id))
                    .map(this::toSchool)
                    .map(Result::success)
                    .defaultIfEmpty(Result.fail(SchoolError.SCHOOL_NOT_FOUND));
        } catch (Exception e) {
            log.error("查询学校信息失败, id: {}", id, e);
            return Mono.just(Result.fail(SchoolError.SCHOOL_QUERY_ERROR));
        }
    }

    @Override
    public Mono<Result<School>> findByCode(String code) {
        try {
            return Mono.justOrEmpty(schoolDao.findByCode(code))
                    .map(this::toSchool)
                    .map(Result::success)
                    .defaultIfEmpty(Result.fail(SchoolError.SCHOOL_NOT_FOUND));
        } catch (Exception e) {
            log.error("查询学校信息失败, code: {}", code, e);
            return Mono.just(Result.fail(SchoolError.SCHOOL_QUERY_ERROR));
        }
    }

    @Override
    public Flux<School> query(SchoolQuery query) {
        try {
            return Flux.fromIterable(schoolDao.findAll())
                    .map(this::toSchool)
                    .filter(school -> matchQuery(school, query));
        } catch (Exception e) {
            log.error("查询学校列表失败", e);
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
                            return Mono.just(Result.fail(SchoolError.SCHOOL_NOT_FOUND));
                        }
                        schoolDao.deleteById(id);
                        return Mono.just(Result.success());
                    });
        } catch (Exception e) {
            log.error("删除学校信息失败, id: {}", id, e);
            return Mono.just(Result.fail(SchoolError.SCHOOL_DELETE_ERROR));
        }
    }

    /**
     * 将领域对象转换为数据库实体
     *
     * @param school 学校领域对象
     * @return 学校数据库实体
     */
    private SchoolDO toSchoolDO(School school) {
        SchoolDO schoolDO = new SchoolDO();
        schoolDO.setId(school.getId());
        schoolDO.setName(school.getName());
        schoolDO.setCode(school.getCode());
        schoolDO.setAddress(school.getAddress());
        schoolDO.setPhone(school.getPhone());
        schoolDO.setRemark(school.getRemark());
        return schoolDO;
    }

    /**
     * 将数据库实体转换为领域对象
     *
     * @param schoolDO 学校数据库实体
     * @return 学校领域对象
     */
    private School toSchool(SchoolDO schoolDO) {
        return School.builder()
                .id(schoolDO.getId())
                .name(schoolDO.getName())
                .code(schoolDO.getCode())
                .address(schoolDO.getAddress())
                .phone(schoolDO.getPhone())
                .remark(schoolDO.getRemark())
                .build();
    }

    /**
     * 匹配查询条件
     *
     * @param school 学校领域对象
     * @param query  查询条件
     * @return 是否匹配
     */
    private boolean matchQuery(School school, SchoolQuery query) {
        if (query == null) {
            return true;
        }
        if (query.getCode() != null && !query.getCode().equals(school.getCode())) {
            return false;
        }
        if (query.getName() != null && !school.getName().contains(query.getName())) {
            return false;
        }
        if (query.getAddress() != null && !school.getAddress().contains(query.getAddress())) {
            return false;
        }
        if (query.getPhone() != null && !query.getPhone().equals(school.getPhone())) {
            return false;
        }
        return true;
    }
} 