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

import com.payintech.smoney.enumeration.ErrorCodeEnum;
import com.payintech.smoney.enumeration.PaymentOperationStatusEnum;
import com.payintech.smoney.enumeration.PaymentStatusEnum;
import com.payintech.smoney.enumeration.PaymentTypeEnum;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.Serializable;
import java.util.List;
import java.util.TimeZone;

/**
 * CardPaymentEntity.
 *
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 16.05
 * @since 15.11
 */
public class CardPaymentEntity implements Serializable {

    /**
     * S-Money card payment ID.
     *
     * @since 15.11
     */
    public Long Id;

    /**
     * Card payment ID on the 3rd party application.
     *
     * @since 15.11
     */
    public String OrderId;

    /**
     * Card payment date.
     *
     * @since 15.11
     */
    public DateTime PaymentDate;

    /**
     * Payment amount (in cents) without fee.
     *
     * @since 15.11
     */
    public Long Amount;

    /**
     * Fee amount (int cents)
     *
     * @since 15.11
     */
    public Long Fee;

    /**
     * Payment status.
     *
     * @see PaymentStatusEnum
     * @since 16.05
     */
    public PaymentOperationStatusEnum Status;

    /**
     * Beneficiary details.
     *
     * @see SubAccountEntity
     * @since 15.11
     */
    public SubAccountEntity Beneficiary;

    /**
     * Customizable message.
     *
     * @since 15.11
     */
    public String Message;

    /**
     * {@code true} if the account owner is the
     * same as the card owner.
     *
     * @since 15.11
     */
    public Boolean IsMine;

    /**
     * Card payment error code.
     *
     * @see ErrorCodeEnum
     * @since 15.11
     */
    public ErrorCodeEnum ErrorCode;

    /**
     * More information about results.
     *
     * @see ExtraResultsEntity
     * @since 15.11
     */
    public ExtraResultsEntity ExtraResults;

    /**
     * URL used to redirect user at the process end.
     *
     * @since 15.11
     */
    public String UrlReturn;

    /**
     * Callback URL to use (only server to server).
     *
     * @since 15.11
     */
    public String UrlCallback;

    /**
     * List of allowed cards on the S-Money web page. By default, all cards
     * are allowed ({@code CB;MASTERCARD;MAESTRO;VISA;VISA_ELECTRON}).
     *
     * @since 15.11
     */
    public String AvailableCards;

    /**
     * Reference to this card payment.
     *
     * @since 15.11
     */
    public String Href;

    /**
     * Card payment type.
     *
     * @see PaymentTypeEnum
     * @since 15.11
     */
    public PaymentTypeEnum Type;

    /**
     * Card details.
     *
     * @see CardEntity
     * @since 15.11
     */
    public CardEntity Card;

    /**
     * List of scheduled payments (if applicable).
     *
     * @see PaymentScheduleEntity
     * @since 15.11
     */
    public List<PaymentScheduleEntity> PaymentSchedule;

    /**
     * List of payments in case of multiple beneficiaries (if applicable).
     *
     * @since 15.11
     */
    public List<PaymentEntity> Payments;

    /**
     * Build a basic instance.
     *
     * @since 15.11
     */
    public CardPaymentEntity() {
        this.AvailableCards = "CB;MASTERCARD;MAESTRO;VISA;VISA_ELECTRON";
    }

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

    /**
     * Get the payment date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 16.02
     */
    public DateTime getPaymentDate(final TimeZone timeZone) {
        return this.PaymentDate.toDateTime(DateTimeZone.forTimeZone(timeZone));
    }
}
