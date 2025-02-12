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

package cn.org.byc.schedule.model;

import cn.org.byc.schedule.base.api.constant.ApiConstant;
import cn.org.byc.schedule.exception.CommonError;
import cn.org.byc.schedule.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;

import java.io.Serial;
import java.io.Serializable;
import java.util.function.Function;

/**
 * 统一响应结果
 *
 * @author Ken
 */
@Getter
@Setter
@Schema(description = "统一响应结果")
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * HTTP状态码
     */
    @Schema(description = "HTTP状态码")
    private int status;

    /**
     * 追踪码
     */
    @Schema(description = "追踪码")
    private String traceId =  MDC.get(ApiConstant.TRACE_ID_KEY_HEADER);;
    /**
     * 是否成功
     */
    @Schema(description = "是否成功")
    private boolean success;

    /**
     * 错误码
     */
    @Schema(description = "错误码")
    private String code;

    /**
     * 错误信息
     */
    @Schema(description = "错误信息")
    private String message;

    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    private T data;

    private Result(){
        this.status = CommonError.NoError.getStatus();
        this.message = CommonError.NoError.getMessage();
        this.code = CommonError.NoError.getCode();
        this.data = null;
    }

    private Result(T data){
        this.status = CommonError.NoError.getStatus();
        this.message = CommonError.NoError.getMessage();
        this.code = CommonError.NoError.getCode();
        this.data = data;
    }

    private Result(ErrorCode error){
        this.status = error.getStatus();
        this.message = error.getMessage();
        this.code = error.getCode();
        this.data = null;
    }

    private Result(ErrorCode error, T data){
        this.status = error.getStatus();
        this.message = error.getMessage();
        this.code = error.getCode();
        this.data = data;
    }

    public Result<T> withMessage(String message){
        this.message = message;
        return this;
    }

    public static <T> Result<T> success(){
        return new Result<>();
    }

    public static <T> Result<T> success(T data){
        return new Result<>(CommonError.NoError, data);
    }

    public static <T> Result<T> fail(){
        return new Result<>(CommonError.UnExpected);
    }

    public static <T> Result<T> fail(ErrorCode error){
        return new Result<>(error);
    }

    public static <T> Result<T> fail(ErrorCode error, T data){
        return new Result<>(error, data);
    }

    /**
     * 创建失败结果
     *
     * @param code    错误码
     * @param message 错误信息
     * @param <T>     数据类型
     * @return 失败结果
     */
    public static <T> Result<T> fail(String code, String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setStatus(CommonError.UnExpected.getStatus());
        result.setMessage(message);
        return result;
    }

    /**
     * 映射数据
     *
     * @param mapper 映射函数
     * @param <R>    目标数据类型
     * @return 映射后的结果
     */
    public <R> Result<R> map(Function<T, R> mapper) {
        if (!this.isSuccess()) {
            return Result.fail(this.getCode(), this.getMessage());
        }
        return Result.success(mapper.apply(this.getData()));
    }
} 