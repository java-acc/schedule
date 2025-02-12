package cn.org.byc.schedule.engine.infrastructure.repository.mapper;

import cn.org.byc.schedule.engine.infrastructure.dataobject.RuleSetDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 排课规则集数据访问接口
 *
 * @author Ken
 */
public interface RuleSetDao extends JpaRepository<RuleSetDO, Long> {

    /**
     * 根据课表ID查询规则集列表
     *
     * @param timeTableId 课表ID
     * @return 规则集列表
     */
    List<RuleSetDO> findByTimeTableId(Long timeTableId);

    /**
     * 根据课表ID删除规则集
     *
     * @param timeTableId 课表ID
     */
    @Modifying
    @Query("DELETE FROM RuleSetDO r WHERE r.timeTableId = :timeTableId")
    void deleteByTimeTableId(@Param("timeTableId") Long timeTableId);
} 