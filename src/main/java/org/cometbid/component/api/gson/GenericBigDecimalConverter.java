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
package org.cometbid.component.api.gson;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import com.google.common.collect.ImmutableSet;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

/**
 *
 * @author samueladebowale
 */
public class GenericBigDecimalConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {

        ConvertiblePair[] pairs = new ConvertiblePair[]{
            new ConvertiblePair(Number.class, BigDecimal.class),
            new ConvertiblePair(String.class, BigDecimal.class)};

        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getType() == BigDecimal.class) {
            return source;
        }

        if (sourceType.getType() == String.class) {
            String number = (String) source;
            return new BigDecimal(number);
        } else {
            Number number = (Number) source;
            BigDecimal converted = new BigDecimal(number.doubleValue());
            return converted.setScale(2, RoundingMode.HALF_UP);
        }
    }
}
