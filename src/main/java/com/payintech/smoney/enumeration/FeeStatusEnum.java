/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 - 2017 PayinTech
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
 * FeeStatusEnum.
 *
 * @author Pierre Adam
 * @version 15.11.01
 * @since 15.11.01
 */
public enum FeeStatusEnum {

    /**
     * @since 15.11.01
     */
    @SerializedName("0")
    PENDING,

    /**
     * @since 15.11.01
     */
    @SerializedName("1")
    SUCCEEDED,

    /**
     * @since 15.11.01
     */
    @SerializedName("2")
    CANCELLED,

    /**
     * @since 15.11.01
     */
    @SerializedName("3")
    FAILED,

    /**
     * @since 15.11.01
     */
    @SerializedName("4")
    EXPIRED,

    /**
     * @since 15.11.01
     */
    @SerializedName("5")
    REFUNDED
}
