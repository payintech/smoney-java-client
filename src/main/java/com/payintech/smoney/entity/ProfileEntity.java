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
package com.payintech.smoney.entity;

import com.payintech.smoney.enumeration.CivilityEnum;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * ProfileEntity.
 *
 * @author Pierre Adam
 * @author Jean-Pierre Boudic
 * @author Thibault Meyer
 * @version 15.12
 * @since 15.11
 */
public class ProfileEntity {

    /**
     * Civility.
     *
     * @see CivilityEnum
     * @since 15.11
     */
    public CivilityEnum Civility;

    /**
     * User first name.
     *
     * @since 15.11
     */
    public String FirstName;

    /**
     * User last name.
     *
     * @since 15.11
     */
    public String LastName;

    /**
     * User birthdate.
     *
     * @since 15.11
     */
    public DateTime Birthdate;

    /**
     * Phone number to contact user.
     *
     * @since 15.11
     */
    public String PhoneNumber;

    /**
     * A valid email address used to contact user.
     *
     * @since 15.11
     */
    public String Email;

    /**
     * User nickname.
     *
     * @since 15.11
     */
    public String Alias;

    /**
     * Link to the user picture.
     *
     * @since 15.11
     */
    public String Photo;

    /**
     * Postal address.
     *
     * @since 15.11
     */
    public AddressEntity Address;

    /**
     * Get the birthdate date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 15.12
     */
    public DateTime getBirthdate(final String timeZone) {
        return this.Birthdate.toDateTime(DateTimeZone.forID(timeZone));
    }
}
