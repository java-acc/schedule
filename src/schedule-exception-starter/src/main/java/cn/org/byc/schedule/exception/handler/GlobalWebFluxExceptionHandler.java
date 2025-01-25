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

package cn.org.byc.schedule.exception.handler;

import cn.hutool.core.util.StrUtil;
import cn.org.byc.schedule.base.api.constant.ApiConstant;
import cn.org.byc.schedule.base.api.model.Result;
import cn.org.byc.schedule.base.constant.StringPool;
import cn.org.byc.schedule.exception.CommonError;
import cn.org.byc.schedule.exception.ScheduleBaseException;
import cn.org.byc.schedule.i18n.I18nMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

/**
 * WebFlux全局异常处理器
 * 
 * <p>处理WebFlux应用程序中的所有未捕获异常，提供统一的异常处理和响应格式。
 * 主要功能：
 * <ul>
 *     <li>处理自定义业务异常（{@link ScheduleBaseException}）</li>
 *     <li>处理参数验证异常（{@link MethodArgumentNotValidException}）</li>
 *     <li>处理其他未预期的异常</li>
 *     <li>支持国际化错误消息</li>
 *     <li>提供统一的JSON响应格式</li>
 *     <li>维护请求追踪信息</li>
 * </ul>
 *
 * @author Ken
 * @see ErrorWebExceptionHandler
 * @see ScheduleBaseException
 * @see Result
 */
public class GlobalWebFluxExceptionHandler implements ErrorWebExceptionHandler, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalWebFluxExceptionHandler.class);
    
    // 错误消息模板
    private static final String ERROR_MESSAGE_TEMPLATE = "错误：{}";
    private static final String PARAM_ERROR_MESSAGE_TEMPLATE = "请求参数错误：{}";
    private static final String UNEXPECTED_ERROR_MESSAGE_TEMPLATE = "系统异常：{}";
    
    /**
     * 国际化消息服务
     */
    private final I18nMessage i18nMessage;
    
    /**
     * JSON对象映射器
     */
    private final ObjectMapper objectMapper;

    /**
     * 构造函数
     *
     * @param i18nMessage 国际化消息服务
     * @param objectMapper JSON对象映射器
     */
    public GlobalWebFluxExceptionHandler(I18nMessage i18nMessage, ObjectMapper objectMapper) {
        this.i18nMessage = i18nMessage;
        this.objectMapper = objectMapper;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 处理异常并生成响应
     * 
     * <p>根据不同的异常类型生成相应的错误响应：
     * <ul>
     *     <li>ScheduleBaseException - 使用错误码和国际化消息</li>
     *     <li>MethodArgumentNotValidException - 处理参数验证错误</li>
     *     <li>其他异常 - 使用通用错误码和原始错误消息</li>
     * </ul>
     *
     * @param exchange WebFlux的服务器交换对象
     * @param cause 捕获的异常
     * @return 包含错误信息的响应
     */
    @SneakyThrows
    @Override
    public Mono<Void> handle(final ServerWebExchange exchange, final Throwable cause) {
        // 创建失败结果对象
        final Result<Void> result = Result.fail();
        final ServerHttpResponse response = exchange.getResponse();
        final HttpHeaders headers = response.getHeaders();
        // 获取追踪ID
        final String traceId = MDC.get(ApiConstant.TRACE_ID_KEY_HEADER);

        // 处理不同类型的异常
        if (cause instanceof ScheduleBaseException e) {
            // 处理自定义业务异常
            result.setCode(e.getError().getCode());
            result.setStatus(e.getError().getStatus());
            // 如果有参数，使用国际化消息；否则使用原始消息
            String message = e.getParams() == null ? 
                e.getMessage() : 
                i18nMessage.toLocale(e.getError().getCode(), e.getParams());
            result.setMessage(StrUtil.format(ERROR_MESSAGE_TEMPLATE, message));
            
        } else if (cause instanceof final MethodArgumentNotValidException e) {
            // 处理参数验证异常
            String validationErrors = formatValidationErrors(e.getBindingResult());
            result.setMessage(StrUtil.format(PARAM_ERROR_MESSAGE_TEMPLATE, validationErrors));
            result.setStatus(CommonError.RequestParamsInvalid.getStatus());
            result.setCode(CommonError.RequestParamsInvalid.getCode());
            
        } else {
            // 处理其他未预期的异常
            LOGGER.error("未预期的异常", cause);
            result.setMessage(StrUtil.format(UNEXPECTED_ERROR_MESSAGE_TEMPLATE, cause.getMessage()));
            result.setCode(CommonError.UnExpected.getCode());
            result.setStatus(CommonError.UnExpected.getStatus());
        }

        // 设置响应头
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ApiConstant.TRACE_ID_KEY_HEADER, traceId);
        response.setStatusCode(HttpStatusCode.valueOf(result.getStatus()));

        // 将结果转换为JSON并写入响应
        final byte[] bytes = this.objectMapper.writeValueAsBytes(result);
        final DataBuffer wrap = response.bufferFactory().wrap(bytes);

        return response.writeWith(Mono.just(wrap));
    }

    /**
     * 格式化参数验证错误信息
     * 
     * <p>将验证错误信息转换为统一的文本格式。
     * 格式示例：字段1：错误信息1；字段2：错误信息2
     *
     * @param result 包含验证错误信息的BindingResult
     * @return 格式化后的错误信息
     */
    private String formatValidationErrors(final BindingResult result) {
        if (!result.hasErrors()) {
            return "";
        }
        
        return result.getFieldErrors().stream()
            .map(error -> error.getField() + StringPool.COLON + error.getDefaultMessage())
            .collect(Collectors.joining(StringPool.SEMICOLON));
    }
}
