/*
 * Copyright 2025 Ken(kan.zhang-cn@hotmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.org.byc.schedule.exception;

import java.io.Serial;

/**
 * 调度系统基础异常类
 * 
 * <p>所有调度系统的自定义异常的基类，提供：
 * <ul>
 *     <li>错误码支持 - 通过{@link ErrorCode}接口</li>
 *     <li>参数传递 - 支持传递异常相关的参数数组</li>
 *     <li>异常链支持 - 支持包装其他异常</li>
 * </ul>
 *
 * @author Ken
 * @see ErrorCode
 * @see DomainException
 */
public class ScheduleBaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected ErrorCode error;

    /**
     * 异常参数数组，用于格式化错误消息
     */
    protected Object[] params;

    /**
     * 使用指定的错误消息构造异常
     *
     * @param message 错误消息
     */
    public ScheduleBaseException(String message) {
        super(message);
    }

    /**
     * 使用指定的错误消息和原因构造异常
     *
     * @param message 错误消息
     * @param e 原因异常
     */
    public ScheduleBaseException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 获取错误码
     *
     * @return 错误码对象
     */
    public ErrorCode getError() {
        return error;
    }

    /**
     * 获取异常参数数组
     *
     * @return 参数数组
     */
    public Object[] getParams() {
        return params;
    }

    /**
     * 设置异常参数数组
     *
     * @param params 参数数组
     */
    public void setParams(Object[] params) {
        this.params = params;
    }
}
