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

package cn.org.byc.schedule.security.filter;

import cn.org.byc.schedule.security.constant.SecurityConstants;
import cn.org.byc.schedule.security.context.UserContext;
import cn.org.byc.schedule.security.model.CurrentUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 用户上下文过滤器，用于从请求中获取用户ID并存储到上下文中
 *
 * @author Ken
 */
@Slf4j
public class UserContextFilter implements WebFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        Long userId = extractUserId(request);

        CurrentUserEntity currentUser = CurrentUserEntity.builder().userId(userId).build();
        if (userId != null) {
            log.debug("从请求中获取到用户ID: {}", userId);
            return chain.filter(exchange)
                    .contextWrite(UserContext.withUserId(currentUser))
                    .doFinally(signalType -> UserContext.clear());
        }
        
        return chain.filter(exchange);
    }

    /**
     * 从请求中提取用户ID
     *
     * @param request HTTP请求
     * @return 用户ID，如果未找到则返回null
     */
    private Long extractUserId(ServerHttpRequest request) {
        // 优先从Header中获取
        String userIdStr = request.getHeaders().getFirst(SecurityConstants.HEADER_USER_ID_KEY);
        
        // 如果Header中没有，则从URL参数中获取
        if (!StringUtils.hasText(userIdStr)) {
            userIdStr = request.getQueryParams().getFirst(SecurityConstants.PARAM_USER_ID_KEY);
        }
        
        if (StringUtils.hasText(userIdStr)) {
            try {
                return Long.parseLong(userIdStr);
            } catch (NumberFormatException e) {
                log.warn("解析用户ID失败: {}", userIdStr);
            }
        }
        
        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 100;
    }
} 