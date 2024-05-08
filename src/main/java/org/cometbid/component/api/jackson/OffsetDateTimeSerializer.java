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
package org.cometbid.component.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import static org.cometbid.component.api.util.TimeZoneUtils.*;

/**
 *
 * @author samueladebowale
 */
@Log4j2
public class OffsetDateTimeSerializer extends StdSerializer<OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    public OffsetDateTimeSerializer() {
        super(OffsetDateTime.class);
    }

    @Override
    public void serialize(OffsetDateTime utcValue, JsonGenerator gen,
            SerializerProvider provider) throws IOException {

        log.info("OffsetDateTime from: {}", utcValue);

        if (!Objects.isNull(utcValue)) {
            ZoneId zoneId = getContextZoneId();
            log.info("Zone id: {}", zoneId);

            OffsetDateTime convertedValue = convertOffset(utcValue, zoneId);
            log.info("OffsetDateTime to: {}", convertedValue);

            String dateTimeOffset = UTC_DATE_FORMATTER.format(convertedValue);
            log.info("OffsetDateTime formatted: {}", dateTimeOffset);

            gen.writeString(dateTimeOffset);
        }
    }

}
