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
 * BankAccountEntity.
 *
 * @author Jean-Pierre Boudic
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 16.02
 * @since 15.11
 */
public class BankAccountEntity implements Serializable {

    /**
     * S-Money bank account ID.
     *
     * @since 15.11
     */
    public Long Id;

    /**
     * Reference of the current bank account.
     *
     * @since 15.11
     */
    public String Href;

    /**
     * Bank account usual name.
     *
     * @since 15.11
     */
    public String DisplayName;

    /**
     * Bank postal address details.
     *
     * @since 15.11
     */
    public AddressEntity Address;

    /**
     * Bank International Code (ISO 9362).
     *
     * @since 15.11
     */
    public String Bic;

    /**
     * International Bank Account Number (ISO 9362).
     *
     * @since 15.11
     */
    public String Iban;

    /**
     * Is the bank account is owned by the user?
     *
     * @since 15.11
     */
    public Boolean IsMine;

    /**
     * Get the Bank International Code (BIC) number with mask.
     *
     * @return The masked BIC
     * @since 15.11
     */
    public String getMaskedBic() {
        return this.Bic.substring(0, 2) + "xxxx" + this.Bic.substring(6);
    }

    /**
     * Get the International Bank Account Number (IBAN) number with mask.
     *
     * @return The masked IBAN
     * @since 15.11
     */
    public String getMaskedIban() {
        return this.Iban.substring(0, 4) + "xxxxxxxxxxxxxxxx" + this.Iban.substring(20);
    }
}
