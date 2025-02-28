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

package cn.org.byc.schedule.security.constant;

/**
 * 安全模块常量定义
 * 
 * @author Ken
 */
public interface SecurityConstants {

    /**
     * HTTP请求头中的用户ID键名
     * <p>
     * 用于从请求头中获取当前操作用户的ID，值类型为Long
     * 示例：X-User-Id: 123456
     */
    String HEADER_USER_ID_KEY = "X-User-Id";

    /**
     * URL参数中的用户ID键名
     * <p>
     * 用于从URL查询参数中获取当前操作用户的ID，值类型为Long
     * 示例：/api/resource?currentUserId=123456
     */
    String PARAM_USER_ID_KEY = "currentUserId";
}
