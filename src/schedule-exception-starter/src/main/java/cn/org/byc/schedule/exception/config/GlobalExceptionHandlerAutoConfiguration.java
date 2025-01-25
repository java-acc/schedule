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

package cn.org.byc.schedule.exception.config;

import cn.org.byc.schedule.exception.handler.GlobalWebFluxExceptionHandler;
import cn.org.byc.schedule.i18n.I18nMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 全局异常处理器自动配置类
 * 
 * <p>为WebFlux应用程序提供全局异常处理的自动配置。主要功能：
 * <ul>
 *     <li>自动配置全局WebFlux异常处理器</li>
 *     <li>支持国际化错误消息</li>
 *     <li>提供JSON格式的错误响应</li>
 * </ul>
 * 
 * <p>配置条件：
 * <ul>
 *     <li>仅在WebFlux环境下生效</li>
 *     <li>当没有自定义的异常处理器时生效</li>
 *     <li>依赖于i18nMessage Bean的初始化</li>
 * </ul>
 *
 * @author Ken
 * @see GlobalWebFluxExceptionHandler
 * @see I18nMessage
 */
@Configuration
public class GlobalExceptionHandlerAutoConfiguration {

    /**
     * 创建全局WebFlux异常处理器
     * 
     * <p>该Bean仅在以下条件满足时创建：
     * <ul>
     *     <li>应用程序是WebFlux应用（REACTIVE）</li>
     *     <li>容器中不存在其他GlobalWebFluxExceptionHandler实例</li>
     *     <li>i18nMessage Bean已经初始化</li>
     * </ul>
     *
     * @param i18nMessage 国际化消息服务
     * @param objectMapper JSON对象映射器
     * @return 全局WebFlux异常处理器实例
     */
    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    @ConditionalOnMissingBean
    @DependsOn("i18nMessage")
    public GlobalWebFluxExceptionHandler globalWebFluxExceptionHandler(final I18nMessage i18nMessage, final ObjectMapper objectMapper) {
        return new GlobalWebFluxExceptionHandler(i18nMessage, objectMapper);
    }
}

