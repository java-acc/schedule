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

/**
 * 通用错误码枚举
 * 
 * <p>定义了系统中常见的错误类型，每个错误包含：
 * <ul>
 *     <li>HTTP状态码 - 符合HTTP协议规范的状态码</li>
 *     <li>业务错误码 - 以"S_"开头的唯一标识符</li>
 *     <li>错误消息 - 描述错误的具体信息</li>
 * </ul>
 * 
 * <p>错误码格式说明：
 * <ul>
 *     <li>S_0000: 成功</li>
 *     <li>S_4xx_xxx: 客户端错误，如参数错误、认证失败等</li>
 *     <li>S_5xx_xxx: 服务器错误，如系统异常、数据一致性错误等</li>
 * </ul>
 *
 * @author Ken
 * @see ErrorCode
 */
public enum CommonError implements ErrorCode {
    // 成功状态
    NoError(200, "S_0000", "Success"),
    
    // 4xx 客户端错误
    PersistentDataError(400, "S_400_001", "Failed to persist data"),
    InvalidProperty(400, "S_400_002", "Invalid property value"),
    RequestParamsInvalid(400, "S_400_003", "Invalid request parameters"),
    NeedAuthentication(401, "S_401_001", "Authentication required"),
    NotAuthorized(403, "S_403_001", "Not authorized to perform this operation"),
    NoEventBus(404, "S_404_001", "Event bus not found"),
    NoResource(404, "S_404_002", "Resource not found"),
    ConcurrencyConflict(409, "S_409_001", "Concurrent operation conflict detected"),
    
    // 5xx 服务器错误
    UnExpected(500, "S_E_500_001", "An unexpected error occurred"),
    SchedulerError(500, "S_500_002", "Scheduler operation failed"),
    FailToGetLocalIp(500, "S_500_003", "Failed to obtain local IP address"),
    DataConsistencyError(500, "S_500_004", "Data consistency check failed");
    
    

    /**
     * HTTP状态码
     */
    private final int status;
    
    /**
     * 业务错误码
     */
    private final String code;
    
    /**
     * 错误消息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param httpStatus HTTP状态码
     * @param code 业务错误码
     * @param message 错误消息
     */
    CommonError(int httpStatus, String code, String message) {
        this.status = httpStatus;
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
