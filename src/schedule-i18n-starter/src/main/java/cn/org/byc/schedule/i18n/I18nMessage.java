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

package cn.org.byc.schedule.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * @author Ken
 */
public class I18nMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(I18nMessage.class);

    private final ResourceBundleMessageSource messageSource;

    public I18nMessage(String... beanNames){
        this.messageSource = I18nMessageFactory.messageSource(beanNames);
    }

    public String toLocale(final String key, final Object... params) {
        return this.toLocale(key, null, params);
    }

    public String toLocale(final String key, final String defaultMessage, final Object... params) {
        return this.messageSource.getMessage(key, params, defaultMessage, isEngLocale() ? Locale.US : Locale.CHINA);
    }


    public static boolean isEngLocale() {
        try {
            final String language = LocaleContextHolder.getLocale().getLanguage();
            if ("en".equalsIgnoreCase(language)) {
                return true;
            }
        } catch (final Exception ignored) {

        }
        return false;
    }
}
