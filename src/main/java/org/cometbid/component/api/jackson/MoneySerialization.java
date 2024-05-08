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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.io.IOException;
import java.util.Locale;

/**
 *
 * @author samueladebowale
 */
@JsonComponent
public class MoneySerialization {

    private static final MonetaryAmountFormat monetaryAmountFormat;

    static {
        monetaryAmountFormat = MonetaryFormats.getAmountFormat(Locale.US);
    }

    public static class MonetaryAmountSerializer extends StdSerializer<MonetaryAmount> {

        public MonetaryAmountSerializer() {
            super(MonetaryAmount.class);
        }

        @Override
        public void serialize(
                MonetaryAmount value,
                JsonGenerator generator,
                SerializerProvider provider) throws IOException {

            generator.writeString(monetaryAmountFormat.format(value));
        }
    }

    public static class MonetaryAmountDeserializer extends StdDeserializer<MonetaryAmount> {

        public MonetaryAmountDeserializer() {
            super(MonetaryAmount.class);
        }

        @Override
        public MonetaryAmount deserialize(
                JsonParser parser,
                DeserializationContext context) throws IOException {

            JsonNode node = parser.getCodec().readTree(parser);
            String text = node.asText();
            return monetaryAmountFormat.parse(text);
        }
    }
}
