/*
 * The MIT License
 *
 * Copyright 2024 Cometbid.Org.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.cometbid.component.api.util;

import java.util.Locale;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;

/**
 *
 * @author samueladebowale
 */
@Log4j2
public final class LocaleContextUtils {

    private LocaleContextUtils() {
    }

    public static final String THREAD_CONTEXT_LOCALE_KEY = "locale";
    public static String DEFAULT_LANG_CODE = "en_US";

    /**
     *
     * @return
     */
    public static String getContextLocaleAsString() {
        String localeStr = ThreadContext.get(THREAD_CONTEXT_LOCALE_KEY);

        log.info("Get context locale as String {}", localeStr);
        return StringUtils.isNotBlank(localeStr) ? localeStr : DEFAULT_LANG_CODE;
    }

    /**
     *
     * @return
     */
    public static Locale getContextLocale() {
        String localeStr = getContextLocaleAsString();

        log.info("Get context locale {}", localeStr);
        return LocaleUtils.toLocale(localeStr);
    }

    /**
     *
     * @param localeStr
     */
    public static void setContextLocaleAsString(String localeStr) {

        log.info("Set context locale as String {}", localeStr);
        ThreadContext.put(THREAD_CONTEXT_LOCALE_KEY, localeStr);
    }

    /**
     *
     * @param locale
     */
    public static void setContextLocale(Locale locale) {

        log.info("Set context locale {}", locale);
        ThreadContext.put(THREAD_CONTEXT_LOCALE_KEY, locale.toString());
    }

}
