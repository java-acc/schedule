package cn.org.byc.schedule.exception.auth;

import cn.org.byc.schedule.exception.CommonError;
import cn.org.byc.schedule.exception.ScheduleBaseException;

/**
 * 认证异常
 * 
 * <p>当用户认证失败时抛出此异常，包括但不限于：
 * <ul>
 *     <li>未登录访问需要认证的资源</li>
 *     <li>登录凭证已过期</li>
 *     <li>用户名或密码错误</li>
 * </ul>
 *
 * @author Ken
 */
public class AuthenticationException extends ScheduleBaseException {

    public AuthenticationException() {
        super(CommonError.AuthenticationFailed);
    }

    public AuthenticationException(String message) {
        super(CommonError.AuthenticationFailed, message);
    }

    public AuthenticationException(String message, Object... params) {
        super(CommonError.AuthenticationFailed, message, params);
    }
} 