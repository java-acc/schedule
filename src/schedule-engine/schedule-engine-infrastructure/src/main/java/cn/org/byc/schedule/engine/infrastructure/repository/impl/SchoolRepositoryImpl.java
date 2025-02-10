package cn.org.byc.schedule.engine.infrastructure.repository.impl;

import cn.org.byc.schedule.engine.domain.model.entity.School;
import cn.org.byc.schedule.engine.domain.repository.SchoolRepository;
import cn.org.byc.schedule.engine.infrastructure.repository.entity.SchoolDO;
import cn.org.byc.schedule.engine.infrastructure.repository.mapper.SchoolDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * 学校仓储实现类
 *
 * @author Ken
 */
@Repository
@RequiredArgsConstructor
public class SchoolRepositoryImpl implements SchoolRepository {

    private final SchoolDao schoolDao;

    @Override
    public Mono<School> save(School school) {
        SchoolDO schoolDO = toSchoolDO(school);
        return Mono.just(schoolDao.save(schoolDO))
                .map(this::toSchool);
    }

    @Override
    public Mono<School> findById(Long id) {
        return Mono.justOrEmpty(schoolDao.findById(id))
                .map(this::toSchool);
    }

    @Override
    public Mono<School> findByCode(String code) {
        return Mono.justOrEmpty(schoolDao.findByCode(code))
                .map(this::toSchool);
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
                .build();
    }
} 