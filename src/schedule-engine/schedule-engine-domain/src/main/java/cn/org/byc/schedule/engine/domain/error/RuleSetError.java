package cn.org.byc.schedule.engine.domain.error;

import cn.org.byc.schedule.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 规则集错误码
 *
 * @author Ken
 */
@Getter
@AllArgsConstructor
public enum RuleSetError implements ErrorCode {

    /**
     * 规则集不存在
     */
    RULE_SET_NOT_FOUND("RULE_SET_NOT_FOUND", "规则集不存在", HttpStatus.NOT_FOUND.value()),

    /**
     * 规则集保存失败
     */
    RULE_SET_SAVE_ERROR("RULE_SET_SAVE_ERROR", "规则集保存失败", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 规则集更新失败
     */
    RULE_SET_UPDATE_ERROR("RULE_SET_UPDATE_ERROR", "规则集更新失败", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 规则集查询失败
     */
    RULE_SET_QUERY_ERROR("RULE_SET_QUERY_ERROR", "规则集查询失败", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 规则集删除失败
     */
    RULE_SET_DELETE_ERROR("RULE_SET_DELETE_ERROR", "规则集删除失败", HttpStatus.INTERNAL_SERVER_ERROR.value());

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * HTTP状态码
     */
    private final int status;
} 