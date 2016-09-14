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

import java.io.Serializable;

/**
 * CardRegistrationApplicationEntity.
 *
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 16.02
 * @since 15.11.01
 */
public class CardRegistrationApplicationEntity implements Serializable {

    /**
     * Card details.
     *
     * @see CardEntity
     * @since 15.11.01
     */
    public CardEntity Card;

    /**
     * URL to redirect user at the process end.
     *
     * @since 15.11.01
     */
    public String UrlReturn;

    /**
     * List of allowed cards on the S-Money web page. By default, all cards
     * are allowed ({@code CB;MASTERCARD;MAESTRO;VISA;VISA_ELECTRON}).
     *
     * @since 15.11.01
     */
    public String AvailableCards;

    /**
     * Build a basic instance.
     *
     * @since 15.11.01
     */
    public CardRegistrationApplicationEntity() {
        this.AvailableCards = "CB;MASTERCARD;MAESTRO;VISA;VISA_ELECTRON";
    }
}
