package cn.org.byc.schedule.exception.auth;

import cn.org.byc.schedule.exception.CommonError;
import cn.org.byc.schedule.exception.ScheduleBaseException;

/**
 * 未授权异常
 * 
 * <p>当用户未登录或登录已过期时抛出此异常，表示用户需要进行身份认证。
 * 此异常通常用于以下场景：
 * <ul>
 *     <li>访问需要登录的资源时未提供认证信息</li>
 *     <li>提供的认证信息已过期</li>
 *     <li>认证信息无效</li>
 * </ul>
 *
 * @author Ken
 */
public class UnauthorizedException extends ScheduleBaseException {

    public UnauthorizedException() {
        super(CommonError.Unauthorized);
    }

    public UnauthorizedException(String message) {
        super(CommonError.Unauthorized, message);
    }

    public UnauthorizedException(String message, Object... params) {
        super(CommonError.Unauthorized, message, params);
    }
} 