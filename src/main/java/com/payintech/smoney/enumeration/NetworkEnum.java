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

import com.google.gson.annotations.SerializedName;

/**
 * NetworkEnum.
 *
 * @author Thibault Meyer
 * @version 16.02
 * @since 16.02
 */
public enum NetworkEnum {

    /**
     * Unknown card type.
     *
     * @since 16.02
     */
    @SerializedName("-1")
    UNKNOWN,

    /**
     * American Express.
     *
     * @since 16.02
     */
    @SerializedName("0")
    AMEX,

    /**
     * No-name Bank Card.
     *
     * @since 16.02
     */
    @SerializedName("1")
    CB,

    /**
     * Mastercard.
     *
     * @since 16.02
     */
    @SerializedName("2")
    MASTERCARD,

    /**
     * Visa.
     *
     * @since 16.02
     */
    @SerializedName("3")
    VISA,

    /**
     * Mastercard Maestro.
     *
     * @since 16.02
     */
    @SerializedName("4")
    MASTERCARD_MAESTRO,

    /**
     * E-Card.
     *
     * @since 16.02
     */
    @SerializedName("5")
    ECARTEBLEUE,

    /**
     * Aurore Card.
     *
     * @since 16.02
     */
    @SerializedName("6")
    AUROREMULTI,

    /**
     * Buyster (Payment platform from Orange / Bouygues
     * Telecom / SFR / Atos SSII).
     *
     * @since 16.02
     */
    @SerializedName("7")
    BUYSTER,

    /**
     * Cofinoga.
     *
     * @since 16.02
     */
    @SerializedName("8")
    COFINOGA,

    /**
     * JCB.
     *
     * @since 16.02
     */
    @SerializedName("9")
    JCB,

    /**
     * Oney.
     *
     * @since 16.02
     */
    @SerializedName("10")
    ONEY,

    /**
     * Oney (Sandbox).
     *
     * @since 16.02
     */
    @SerializedName("11")
    ONEY_SANDBOX,

    /**
     * PayPal.
     *
     * @since 16.02
     */
    @SerializedName("12")
    PAYPAL,

    /**
     * PayPal (Sandbox)
     *
     * @since 16.02
     */
    @SerializedName("13")
    PAYPAL_SB,

    /**
     * Paysafe.
     *
     * @since 16.02
     */
    @SerializedName("14")
    PAYSAFECARD,

    /**
     * Visa Electron.
     *
     * @since 16.02
     */
    @SerializedName("15")
    VISA_ELECTRON,

    /**
     * 3xCB Cofinoga.
     *
     * @since 16.02
     */
    @SerializedName("16")
    COF3XCB,

    /**
     * 3xCB Cofinoga (Sandbox).
     *
     * @since 16.02
     */
    @SerializedName("17")
    COF3XCB_SB,

    /**
     * Sofort.
     *
     * @since 16.02
     */
    @SerializedName("19")
    SOFORT_BANKING,

    /**
     * Bancontact.
     *
     * @since 16.02
     */
    @SerializedName("20")
    BANCONTACT,

    /**
     * Ideal.
     *
     * @since 16.02
     */
    @SerializedName("21")
    IDEAL,

    /**
     * Postfinance.
     *
     * @since 16.02
     */
    @SerializedName("22")
    POSTFINANCE,

    /**
     * E-finance Postfinance.
     *
     * @since 16.02
     */
    @SerializedName("23")
    POSTFINANCE_EFIN
}
