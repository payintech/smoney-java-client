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

import com.payintech.smoney.enumeration.FeeStatusEnum;

import java.io.Serializable;

/**
 * FeeEntity.
 *
 * @author Jean-Pierre Boudic
 * @author Thibault Meyer
 * @version 16.02
 * @since 15.11
 */
public class FeeEntity implements Serializable {

    /**
     * Net amount of the fee (in cents).
     *
     * @since 15.11
     */
    public Long Amount;

    /**
     * Fee's percentage (in percentage).
     *
     * @since 15.11
     */
    public Long AmountWithVAT;

    /**
     * Tax price of the fee (in cents).
     *
     * @since 15.11
     */
    public Double VAT;

    /**
     * Fee status.
     *
     * @see FeeStatusEnum
     * @since 15.11
     */
    public FeeStatusEnum Status;
}
