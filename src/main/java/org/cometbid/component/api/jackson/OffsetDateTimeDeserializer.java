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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import static org.cometbid.component.api.util.TimeZoneUtils.*;

/**
 *
 * @author samueladebowale
 */
public class OffsetDateTimeDeserializer extends StdDeserializer<OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    public OffsetDateTimeDeserializer() {
        this(null);
    }

    public OffsetDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        String dateSpecified = jsonParser.getText();

        // Get specified timezone offset information 
        TemporalAccessor dateTime = parse(dateSpecified);
        ZoneId zoneId = getContextZoneId();

        // Obtain LocalDateTime from specified date
        LocalDateTime localDateTime = LocalDateTime.from(dateTime);

        // Obtain the OffsetDateTime
        OffsetDateTime convertedValue = OffsetDateTime.of(localDateTime, convertToZoneOffset(zoneId));

        // convert to UTC 
        return convertOffset(convertedValue, DEFAULT_ZONEID);
    }

    private TemporalAccessor parse(String v) {
        return GENERIC_DATE_FORMATTER.parseBest(
                v,
                OffsetDateTime::from,
                ZonedDateTime::from,
                LocalDateTime::from,
                LocalDate::from);
    }

    private static ZoneOffset convertToZoneOffset(final ZoneId zoneId) {
        return zoneId.getRules().getOffset(Instant.now());
    }
}
