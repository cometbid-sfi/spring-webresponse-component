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

import java.io.IOException;
import java.math.BigDecimal;
import javax.money.*;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Allows parsing of any class that implements {@link MonetaryAmount}.
 */
class MonetaryAmountAdapter extends TypeAdapter<MonetaryAmount> {

    private final MonetaryAmountFactory<? extends MonetaryAmount> monetaryFactory;

    MonetaryAmountAdapter(final MonetaryAmountFactory<? extends MonetaryAmount> monetaryFactory) {
        this.monetaryFactory = monetaryFactory;
    }

    @Override
    public void write(final JsonWriter writer, final MonetaryAmount value) throws IOException {

        if (value == null) {
            writer.nullValue();
            return;
        }

        writer.beginObject() //
                .name("amount") //
                .value(value.getNumber().numberValueExact(BigDecimal.class)) //
                .name("currency").value(value.getCurrency().getCurrencyCode()) //
                .endObject();
    }

    @Override
    public MonetaryAmount read(final JsonReader reader) throws IOException {

        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }

        BigDecimal amount = null;
        CurrencyUnit currency = null;

        try {

            reader.beginObject();
            while (reader.hasNext()) {

                switch (reader.nextName()) {

                    case "amount":
                        amount = new BigDecimal(reader.nextString());
                        break;

                    case "currency":
                        currency = Monetary.getCurrency(reader.nextString());
                        break;

                    default:
                        reader.skipValue();
                }
            }

            reader.endObject();

        } catch (NumberFormatException e) {
            throw new JsonSyntaxException("Non numeric String contained in the [amount] field.", e);
        } catch (UnknownCurrencyException e) {
            throw new JsonSyntaxException(e);
        }

        if (amount == null || currency == null) {
            String errorMessage = buildMissingFieldsErrorMessage(amount, currency);
            throw new JsonSyntaxException(errorMessage);
        }

        return monetaryFactory.setCurrency(currency).setNumber(amount).create();
    }

    private String buildMissingFieldsErrorMessage(final BigDecimal amount, final CurrencyUnit currency) {
        StringBuilder builder = new StringBuilder();
        builder.append("Missing required fields from Monetary Amount: [");

        if (amount == null) {
            builder.append("amount");
        }

        if (currency == null) {
            builder.append("currency");
        }

        builder.append("].");
        return builder.toString();
    }
}
