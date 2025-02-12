package cn.org.byc.schedule.engine.domain.error;

import cn.org.byc.schedule.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 学校错误码
 *
 * @author Ken
 */
@Getter
public enum SchoolError implements ErrorCode {

    /**
     * 学校不存在
     */
    SCHOOL_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "SCH40401", "学校不存在"),

    /**
     * 学校编码已存在
     */
    SCHOOL_CODE_EXISTS(HttpStatus.BAD_REQUEST.value(), "SCH40001", "学校编码已存在"),

    /**
     * 学校编码不匹配
     */
    SCHOOL_CODE_NOT_MATCH(HttpStatus.BAD_REQUEST.value(), "SCH40002", "学校编码不匹配"),

    /**
     * 保存学校信息失败
     */
    SCHOOL_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SCH50001", "保存学校信息失败"),

    /**
     * 更新学校信息失败
     */
    SCHOOL_UPDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SCH50002", "更新学校信息失败"),

    /**
     * 删除学校信息失败
     */
    SCHOOL_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SCH50003", "删除学校信息失败"),

    /**
     * 查询学校信息失败
     */
    SCHOOL_QUERY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SCH50004", "查询学校信息失败");

    /**
     * HTTP状态码
     */
    private final int status;

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String message;

    SchoolError(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
} 