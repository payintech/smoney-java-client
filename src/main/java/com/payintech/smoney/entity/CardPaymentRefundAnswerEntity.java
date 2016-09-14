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

import java.io.Serializable;
import java.util.TimeZone;

/**
 * CardPaymentRefundAnswerEntity.
 *
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 16.02.01
 * @since 15.11.01
 */
public class CardPaymentRefundAnswerEntity implements Serializable {

    /**
     * S-Money card payment refund ID.
     *
     * @since 15.11.01
     */
    public Long Id;

    /**
     * Card payment refund ID on the 3rd party application.
     *
     * @since 15.11.01
     */
    public String OrderId;

    /**
     * Refund amount (in cents).
     *
     * @since 15.11.01
     */
    public Long Amount;

    /**
     * Is fee amount has been refunded?
     *
     * @since 15.11.01
     */
    public Boolean RefundedFee;

    /**
     * Card refund status.
     *
     * @see PaymentStatusEnum
     * @since 15.11.01
     */
    public PaymentStatusEnum Status;

    /**
     * Refund date.
     *
     * @since 15.11.01
     */
    public DateTime PaymentDate;

    /**
     * The original payment linked to this refund.
     *
     * @since 15.11.01
     */
    public CardPaymentEntity OriginalPayment;

    /**
     * Refund type.
     *
     * @since 15.11.01
     */
    public Long Type;

    /**
     * Get the payment date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 15.12.01
     */
    public DateTime getPaymentDate(final String timeZone) {
        return this.PaymentDate.toDateTime(DateTimeZone.forID(timeZone));
    }

    /**
     * Get the payment date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 16.02.01
     */
    public DateTime getPaymentDate(final TimeZone timeZone) {
        return this.PaymentDate.toDateTime(DateTimeZone.forTimeZone(timeZone));
    }
}
