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

package cn.org.byc.schedule.base.convert;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.util.StringValueResolver;

/**
 * Schedule项目的类型转换服务
 * 扩展Spring Boot的ApplicationConversionService，添加了自定义的枚举转换器
 * 采用单例模式实现，确保全局使用同一个转换服务实例
 *
 * @author Ken
 */
public class ScheduleConversionService extends ApplicationConversionService {
    /**
     * 静态内部类，用于实现延迟加载的单例模式
     * 利用类加载机制确保线程安全
     */
    private static class Holder {
        static final ScheduleConversionService INSTANCE = new ScheduleConversionService();
    }

    public ScheduleConversionService(){}

    /**
     * 带有字符串值解析器的构造函数
     * 在构造时添加自定义的枚举转换器
     *
     * @param embeddedValueResolver Spring的字符串值解析器，用于解析配置文件中的占位符
     */
    public ScheduleConversionService(StringValueResolver embeddedValueResolver) {
        super(embeddedValueResolver);
        super.addConverter(new EnumToStringConverter());
        super.addConverter(new StringToEnumConverter());
    }

    /**
     * 获取ScheduleConversionService的单例实例
     * 采用延迟加载策略，在首次调用时才创建实例
     *
     * @return 返回GenericConversionService类型的转换服务实例，实际上是ScheduleConversionService的实例
     */
    public static GenericConversionService getInstance() {
        return Holder.INSTANCE;
    }
}
