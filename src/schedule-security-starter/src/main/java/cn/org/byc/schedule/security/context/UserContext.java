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

package cn.org.byc.schedule.security.context;

import cn.org.byc.schedule.security.model.CurrentUserEntity;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * 用户上下文，用于存储当前用户ID
 *
 * @author Ken
 */
public class UserContext {
    private static final String USER_ID_KEY = "CURRENT_USER_ID";
    private static final ThreadLocal<CurrentUserEntity> USER_ID_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取当前用户ID
     *
     * @return 当前用户ID
     */
    public static Mono<Long> getCurrentUserId() {
        return Mono.deferContextual(ctx -> 
            ctx.hasKey(USER_ID_KEY) ? Mono.just(ctx.get(USER_ID_KEY)) : Mono.empty()
        );
    }

    /**
     * 从ThreadLocal中获取当前用户ID
     *
     * @return 当前用户ID
     */
    public static CurrentUserEntity getCurrentUserIdFromThread() {
        return USER_ID_THREAD_LOCAL.get();
    }

    /**
     * 设置当前用户ID到上下文中
     *
     * @param userId 用户ID
     * @return Context对象
     */
    public static Context withUserId(CurrentUserEntity userId) {
        USER_ID_THREAD_LOCAL.set(userId);
        return Context.of(USER_ID_KEY, userId);
    }

    /**
     * 清除ThreadLocal中的用户ID
     */
    public static void clear() {
        USER_ID_THREAD_LOCAL.remove();
    }
} 