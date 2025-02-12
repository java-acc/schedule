package cn.org.byc.schedule.exception.auth;

import cn.org.byc.schedule.exception.CommonError;
import cn.org.byc.schedule.exception.ScheduleBaseException;

/**
 * 访问拒绝异常
 * 
 * <p>当用户尝试访问没有权限的资源时抛出此异常。
 * 此异常通常用于以下场景：
 * <ul>
 *     <li>用户访问未被授权的API</li>
 *     <li>用户尝试执行未被授权的操作</li>
 *     <li>用户角色权限不足</li>
 * </ul>
 *
 * @author Ken
 */
public class AccessDeniedException extends ScheduleBaseException {

    public AccessDeniedException() {
        super(CommonError.AccessDenied);
    }

    public AccessDeniedException(String message) {
        super(CommonError.AccessDenied, message);
    }

    public AccessDeniedException(String message, Object... params) {
        super(CommonError.AccessDenied, message, params);
    }
} 