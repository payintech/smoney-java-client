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

import com.payintech.smoney.enumeration.CardTypeEnum;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.Serializable;
import java.util.TimeZone;

/**
 * CardEntity.
 *
 * @author Pierre Adam
 * @author Jean-Pierre Boudic
 * @author Thibault Meyer
 * @version 16.02
 * @since 15.11
 */
public class CardEntity implements Serializable {

    /**
     * S-Money card ID.
     *
     * @since 15.11
     */
    public Long Id;

    /**
     * Card ID on the 3rd party application.
     *
     * @since 15.11
     */
    public String AppCardId;

    /**
     * Usual card name.
     *
     * @since 15.11
     */
    public String Name;

    /**
     * Masked card number (ie: {@code 597010XXXXXX0009}).
     *
     * @since 15.11
     */
    public String Hint;

    /**
     * Country where the card come from.
     *
     * @since 15.11
     */
    //TODO: Use CountryCodeEnum rather than String?
    public String Country;

    /**
     * Reference of the current card.
     *
     * @since 15.11
     */
    public String Href;

    /**
     * TODO: Add documentation about this variable.
     *
     * @since 15.11
     */
    public Long Network;

    /**
     * Card expiry date. In some cases, this variable could be {@code null}.
     *
     * @since 16.01
     */
    public DateTime ExpiryDate;

    /**
     * Get the card expiry date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 16.01
     */
    public DateTime getExpiryDate(final String timeZone) {
        if (this.ExpiryDate == null) {
            return null;
        }
        return this.ExpiryDate.toDateTime(DateTimeZone.forID(timeZone));
    }

    /**
     * Get the card expiry date on a specific timezone.
     *
     * @param timeZone The timezone to use
     * @return The datetime converted to the specific timezone
     * @since 16.02
     */
    public DateTime getExpiryDate(final TimeZone timeZone) {
        if (this.ExpiryDate == null) {
            return null;
        }
        return this.ExpiryDate.toDateTime(DateTimeZone.forTimeZone(timeZone));
    }

    /**
     * Check if the card is expired.
     *
     * @return {@code true} if the card is expired, otherwise, {@code false}
     * @since 16.01
     */
    public boolean isExpired() {
        return this.ExpiryDate != null && this.ExpiryDate.isBeforeNow();
    }

    /**
     * Try to resolve the card type (ie: MasterCard). In case of failure,
     * {@code null} value will be returned.
     *
     * @return The card type in case of success, otherwise, {@code null}
     * @see CardTypeEnum
     * @since 16.02
     */
    public CardTypeEnum getCardType() {
        for (final CardTypeEnum cardType : CardTypeEnum.values()) {
            if (cardType.checkCardNumber(this.Hint)) {
                return cardType;
            }
        }
        return null;
    }
}
