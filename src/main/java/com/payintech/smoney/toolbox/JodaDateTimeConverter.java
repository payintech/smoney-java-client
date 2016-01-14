/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 - 2016 PayinTech
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.payintech.smoney.toolbox;

import com.google.gson.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;

/**
 * Implementation of the JsonSerializer and JsonDeserializer for the Joda DateTime.
 *
 * @author Pierre Adam
 * @version 15.11
 * @since 15.11
 */
public class JodaDateTimeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    /**
     * The date formatter.
     *
     * @since 15.11
     */
    private DateTimeFormatter formatter;

    /**
     * ISO Date formatter.
     *
     * @since 15.11
     */
    private DateTimeFormatter isoFormatter;

    /**
     * Build a converter instance with a specific format.
     *
     * @param pattern Pattern respecting the {@code Joda.DateTimeFormatter} syntax.
     * @see DateTimeFormatter
     * @since 15.11
     */
    public JodaDateTimeConverter(final String pattern) {
        this.formatter = DateTimeFormat.forPattern(pattern);
        this.isoFormatter = ISODateTimeFormat.dateTime();
    }

    @Override
    public JsonElement serialize(final DateTime dateTime, final Type type, final JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(this.formatter.print(dateTime));
    }

    @Override
    public DateTime deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.getAsString() == null || jsonElement.getAsString().isEmpty()) {
            return null;
        }

        try {
            return this.formatter.parseDateTime(jsonElement.getAsString());
        } catch (IllegalArgumentException ignore) {
            return this.isoFormatter.parseDateTime(jsonElement.getAsString());
        }
    }
}
