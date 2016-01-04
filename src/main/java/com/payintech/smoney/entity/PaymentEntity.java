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

import com.payintech.smoney.enumeration.PaymentStatusEnum;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * PaymentEntity.
 *
 * @author Jean-Pierre Boudic
 * @author Thibault Meyer
 * @version 15.12
 * @since 15.11
 */
public class PaymentEntity {

    /**
     * S-Money payment ID.
     *
     * @since 15.11
     */
    public Long Id;

    /**
     * Payment ID on the 3rd party application.
     *
     * @since 15.11
     */
    public String OrderId;

    /**
     * Payment date.
     *
     * @since 15.11
     */
    public DateTime PaymentDate;

    /**
     * Payment amount (in cents).
     *
     * @since 15.11
     */
    public Long Amount;

    /**
     * Fee information.
     *
     * @see FeeEntity
     * @since 15.11
     */
    public FeeEntity Fee;

    /**
     * Details about the beneficiary.
     *
     * @see SubAccountEntity
     * @since 15.11
     */
    public SubAccountEntity Beneficiary;

    /**
     * A customizable message (ie: {@code Lunch with Renata}).
     *
     * @since 15.11
     */
    public String Message;

    /**
     * Details about the payment emitter.
     *
     * @see SubAccountEntity
     * @since 15.11
     */
    public SubAccountEntity Sender;

    /**
     * Payment status.
     *
     * @see PaymentStatusEnum
     * @since 15.11
     */
    public PaymentStatusEnum Status;

    /**
     * Get the payment date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 15.12
     */
    public DateTime getPaymentDate(final String timeZone) {
        return this.PaymentDate.toDateTime(DateTimeZone.forID(timeZone));
    }
}
