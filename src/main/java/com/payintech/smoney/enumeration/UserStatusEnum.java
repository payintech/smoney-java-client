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
 * UserStatusEnum.
 *
 * @author Pierre Adam
 * @version 15.11.01
 * @since 15.11.01
 */
public enum UserStatusEnum {

    /**
     * @since 15.11.01
     */
    @SerializedName("0")
    NOT_CONFIRMED,

    /**
     * @since 15.11.01
     */
    @SerializedName("1")
    OK,

    /**
     * @since 15.11.01
     */
    @SerializedName("2")
    FROZEN,

    /**
     * @since 15.11.01
     */
    @SerializedName("3")
    ON_THE_FLY,

    /**
     * @since 15.11.01
     */
    @SerializedName("4")
    BEING_CLOSED,

    /**
     * @since 15.11.01
     */
    @SerializedName("5")
    CLOSED,

    /**
     * @since 15.11.01
     */
    @SerializedName("6")
    WAITING_KYC,
}
