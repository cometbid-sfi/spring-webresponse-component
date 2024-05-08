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
package org.cometbid.component.payroll.json.util.test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import org.cometbid.component.api.jackson.ZonedDateTimeSerializer;
import static org.cometbid.component.api.util.TimeZoneUtils.setContextZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author samueladebowale
 */
public class ZonedDateTimeSerializerTest extends AbstractJackson2MarshallingTest {

    ZonedDateTimeSerializer serializer = null;

    @BeforeEach
    public void init() {
        serializer = new ZonedDateTimeSerializer();

        setContextZoneId("America/New_York");
    }

    @Test
    public void test() throws IOException {

        ZonedDateTime utcValue = ZonedDateTime.now();
        StringWriter stringJson = new StringWriter();
        JsonGenerator generator = new JsonFactory().createGenerator(stringJson);

        serializer.serialize(utcValue, generator, serializerProvider);
    }
}
