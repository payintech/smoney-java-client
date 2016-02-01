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
package com.payintech.smoney.enumeration;

/**
 * CardTypeEnum.
 *
 * @author Thibault Meyer
 * @version 16.02
 * @since 16.02
 */
public enum CardTypeEnum {

    /**
     * American Express.
     *
     * @since 16.02
     */
    AMEX("3[47][0-9X]{13}"),

    /**
     * Visa Electron.
     *
     * @since 16.02
     */
    ELECTRON("(4026|417500|4405|4508|4844|4913|4917)XXXXXX[0-9]{4}"),

    /**
     * Mastercard Maestro.
     *
     * @since 16.02
     */
    MAESTRO("(5[0678]|6304|6390|67)\\S+{8,15}"),

    /**
     * Mastercard.
     *
     * @since 16.02
     */
    MASTERCARD("5[1-5][0-9X]{14}"),

    /**
     * Visa
     *
     * @since 16.02
     */
    VISA("4[0-9X]{12}(?:[0-9]{3})?"),

    /**
     * Unknown card type.
     *
     * @since 16.01
     */
    UNKNOWN("(.*)");

    /**
     * Pattern used to resolve card type.
     *
     * @since 16.02
     */
    private String pattern;

    /**
     * Build a default instance.
     *
     * @param pattern The pattern to assign to this card type
     * @since 16.02
     */
    CardTypeEnum(final String pattern) {
        this.pattern = pattern;
    }

    /**
     * Check if the card number match the pattern for the given card type.
     *
     * @param cardNumber The card number to check
     * @return {@code true} if pattern match, otherwise, {@code false}
     * @since 16.02
     */
    public boolean checkCardNumber(final String cardNumber) {
        return cardNumber.matches(this.pattern);
    }
}
