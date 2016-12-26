/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 - 2017 PayinTech
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

import java.io.Serializable;
import java.util.TimeZone;

/**
 * SubAccountEntity.
 *
 * @author Pierre Adam
 * @author Jean-Pierre Boudic
 * @author Thibault Meyer
 * @version 16.02
 * @since 15.11.01
 */
public class SubAccountEntity implements Serializable {

    /**
     * Account ID.
     *
     * @since 15.11.01
     */
    public Long Id;

    /**
     * Account ID on the 3rd party application.
     *
     * @since 15.11.01
     */
    public String AppAccountId;

    /**
     * Usual name of the account.
     *
     * @since 15.11.01
     */
    public String DisplayName;

    /**
     * Account amount (in cents).
     *
     * @since 15.11.01
     */
    public Long Amount;

    /**
     * Is the user's principal account?
     *
     * @since 15.11.01
     */
    public Boolean IsDefault;

    /**
     * Account creation date.
     *
     * @since 15.11.01
     */
    public DateTime CreationDate;

    /**
     * Reference to the account details.
     *
     * @since 15.11.01
     */
    public String Href;

    /**
     * Get the creation date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 15.12.01
     */
    public DateTime getCreationDate(final String timeZone) {
        return this.CreationDate.toDateTime(DateTimeZone.forID(timeZone));
    }

    /**
     * Get the creation date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 16.02.01
     */
    public DateTime getCreationDate(final TimeZone timeZone) {
        return this.CreationDate.toDateTime(DateTimeZone.forTimeZone(timeZone));
    }
}
