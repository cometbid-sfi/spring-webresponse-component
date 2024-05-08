/*
 * The MIT License
 *
 * Copyright 2024 samueladebowale.
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
package org.cometbid.component.api.interceptors;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.MessageInterpolator;
import org.hibernate.validator.messageinterpolation.AbstractMessageInterpolator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

/**
 *
 * @author samueladebowale
 */
public class RecursiveLocaleContextMessageInterpolator extends AbstractMessageInterpolator {

    private static final Pattern PATTERN_PLACEHOLDER = Pattern.compile("\\{([^}]+)\\}");

    private final MessageInterpolator interpolator;

    public RecursiveLocaleContextMessageInterpolator(ResourceBundleMessageInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    @Override
    public String interpolate(MessageInterpolator.Context context, Locale locale, String message) {
        int level = 0;
        while (containsPlaceholder(message) && (level++ < 2)) {
            message = this.interpolator.interpolate(message, context, locale);
        }
        return message;
    }

    private boolean containsPlaceholder(String code) {
        Matcher matcher = PATTERN_PLACEHOLDER.matcher(code);
        return matcher.find();
    }

}
