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

package cn.org.byc.schedule.jpa.audit;

import cn.org.byc.schedule.security.context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * JPA审计用户提供者，用于自动填充创建人和修改人
 *
 * @author Ken
 */
@Slf4j
@Component
public class SecurityAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        try {
            Long userId = UserContext.getCurrentUserIdFromThread().getUserId();
            return Optional.ofNullable(userId).or(() -> Optional.of(0L));
        } catch (Exception e) {
            log.warn("获取当前用户ID失败", e);
            return Optional.of(0L);
        }
    }
}
