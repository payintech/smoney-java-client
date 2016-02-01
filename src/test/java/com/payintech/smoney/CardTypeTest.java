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
package com.payintech.smoney;

import com.payintech.smoney.entity.CardEntity;
import com.payintech.smoney.enumeration.CardTypeEnum;
import junit.framework.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * CardTypeTest.
 *
 * @author Thibault Meyer
 * @version 16.02
 * @since 16.02
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardTypeTest {

    @Test
    public void cardtype_001() {
        final CardEntity card = new CardEntity();
        card.Hint = "497010XXXXXX0000";
        Assert.assertTrue(CardTypeEnum.VISA.checkCardNumber(card.Hint));

        card.Hint = "552723XXXXXX4664";
        Assert.assertTrue(CardTypeEnum.MASTERCARD.checkCardNumber(card.Hint));

        card.Hint = "417500XXXXXX0000";
        Assert.assertTrue(CardTypeEnum.ELECTRON.checkCardNumber(card.Hint));

        card.Hint = "345227XXXXX2883";
        Assert.assertTrue(CardTypeEnum.AMEX.checkCardNumber(card.Hint));

        card.Hint = "501971XXXXXX3742";
        Assert.assertTrue(CardTypeEnum.MAESTRO.checkCardNumber(card.Hint));
    }
}
