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

import com.payintech.smoney.enumeration.CardRegistrationStatusEnum;
import com.payintech.smoney.enumeration.ErrorCodeEnum;

import java.io.Serializable;

/**
 * CardRegistrationAnswerEntity.
 *
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 16.02
 * @since 15.11
 */
public class CardRegistrationAnswerEntity implements Serializable {

    /**
     * Card details.
     *
     * @see CardEntity
     * @since 15.11
     */
    public CardEntity Card;

    /**
     * Card registration status.
     *
     * @see CardRegistrationStatusEnum
     * @since 15.11
     */
    public CardRegistrationStatusEnum Status;

    /**
     * Error code. Useful to determine what happened during the
     * card registration.
     *
     * @see ErrorCodeEnum
     * @since 15.11
     */
    public ErrorCodeEnum ErrorCode;

    /**
     * Reference to the newly registered card.
     *
     * @since 15.11
     */
    public String Href;

    /**
     * More information about the returned status codes.
     *
     * @since 15.11
     */
    public ExtraResultsEntity ExtraResults;
}
