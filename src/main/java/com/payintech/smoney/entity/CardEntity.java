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

/**
 * CardEntity.
 *
 * @author Pierre Adam
 * @author Jean-Pierre Boudic
 * @version 15.11
 * @since 15.11
 */
public class CardEntity {

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
}
