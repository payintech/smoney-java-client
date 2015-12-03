/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 - 2015 PayinTech
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
package com.payintech.smoney.entity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * SubAccountEntity.
 *
 * @author Pierre Adam
 * @author Jean-Pierre Boudic
 * @author Thibault Meyer
 * @version 15.12
 * @since 15.11
 */
public class SubAccountEntity {
    public Long Id;
    public String AppAccountId;
    public String DisplayName;
    public Long Amount;
    public Boolean IsDefault;
    public DateTime CreationDate;
    public String Href;

    /**
     * Get the creation date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 15.12
     */
    public DateTime getCreationDate(final String timeZone) {
        return this.CreationDate.toDateTime(DateTimeZone.forID(timeZone));
    }
}
