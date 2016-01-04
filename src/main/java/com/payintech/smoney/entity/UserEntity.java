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

import com.google.gson.annotations.SerializedName;
import com.payintech.smoney.enumeration.UserRoleEnum;
import com.payintech.smoney.enumeration.UserStatusEnum;
import com.payintech.smoney.enumeration.UserTypeEnum;

import java.util.List;

/**
 * UserEntity.
 *
 * @author Pierre Adam
 * @author Jean-Pierre Boudic
 * @version 15.11
 * @since 15.11
 */
public class UserEntity {

    /**
     * S-Money user ID.
     *
     * @since 15.11
     */
    public Long Id;

    /**
     * User ID on the 3rd party application.
     *
     * @since 15.11
     */
    public String AppUserId;

    /**
     * Current role of the user.
     *
     * @see UserRoleEnum
     * @since 15.11
     */
    @SerializedName("Role")
    public UserRoleEnum Role;

    /**
     * User type.
     *
     * @see UserTypeEnum
     * @since 15.11
     */
    @SerializedName("Type")
    public UserTypeEnum Type;

    /**
     * User profile information.
     *
     * @since 15.11
     */
    public ProfileEntity Profile;

    /**
     * Cumulative amount from all sub-accounts (in cents).
     *
     * @since 15.11
     */
    public Long Amount;

    /**
     * List of user's associated sub-accounts.
     *
     * @since 15.11
     */
    public List<SubAccountEntity> SubAccounts;

    /**
     * List of user's associated bank accounts.
     *
     * @since 15.11
     */
    public List<BankAccountEntity> BankAccounts;

    /**
     * List of user's associated bank cards.
     *
     * @since 15.11
     */
    public List<CardEntity> CBCards;

    /**
     * User status.
     *
     * @see UserStatusEnum
     * @since 15.11
     */
    public UserStatusEnum Status;

    /**
     * Information about the company. This field is only used
     * if the user have created a company.
     *
     * @since 15.11
     */
    public CompanyEntity Company;
}
