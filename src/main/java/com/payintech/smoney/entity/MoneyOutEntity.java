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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * MoneyOutEntity.
 *
 * @author Jean-Pierre Boudic
 * @author Thibault Meyer
 * @version 15.12
 * @since 15.11
 */
public class MoneyOutEntity {

    /**
     * S-Money money-out transfer ID.
     *
     * @since 15.11
     */
    public Long Id;

    /**
     * Used S-Money account for the transfer.
     *
     * @since 15.11
     */
    public SubAccountEntity AccountId;

    /**
     * Used bank account as receiver.
     *
     * @since 15.11
     */
    public BankAccountEntity BankAccount;

    /**
     * Transfer amount (in cents).
     *
     * @since 15.11
     */
    public Long Amount;

    /**
     * Fee details.
     *
     * @see FeeEntity
     * @since 15.11
     */
    public FeeEntity Fee;

    /**
     * Transfer date.
     *
     * @since 15.11
     */
    public DateTime OperationDate;

    /**
     * Customizable message (ie: {@code S-Money 2015-12-22}). This message
     * will be displayed on the bank account activity log.
     *
     * @since 15.11
     */
    public String Message;

    /**
     * Get the operation date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 15.12
     */
    public DateTime getOperationDate(final String timeZone) {
        return this.OperationDate.toDateTime(DateTimeZone.forID(timeZone));
    }
}
