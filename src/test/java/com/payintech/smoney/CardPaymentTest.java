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

import com.payintech.smoney.entity.CardPaymentEntity;
import com.payintech.smoney.entity.SubAccountEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * CardPaymentTest.
 *
 * @author Pierre Adam
 * @version 15.11.01
 * @since 15.11.01
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardPaymentTest {

    public static final SMoneyService service = SMoneyServiceFactory.createService();

    public static final String orderId;

    static {
        orderId = UUID.randomUUID().toString();
    }

    @Test
    public void cardPayment_001_create() throws IOException {
        final CardPaymentEntity cardPaymentEntity = new CardPaymentEntity();
        cardPaymentEntity.Amount = 500L;
        cardPaymentEntity.OrderId = orderId;
        cardPaymentEntity.Beneficiary = new SubAccountEntity();
        cardPaymentEntity.Beneficiary.AppAccountId = TestSettings.testUserAppUserId;
        cardPaymentEntity.Message = "Recharge test";
        cardPaymentEntity.IsMine = true;
        cardPaymentEntity.UrlReturn = "http://127.0.0.1";
        cardPaymentEntity.Fee = 0L;

        final Call<CardPaymentEntity> call = service.createCardPayment(TestSettings.testUserAppUserId, cardPaymentEntity);
        final Response<CardPaymentEntity> response = call.execute();
        if (response.code() != 201) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 201);

        final CardPaymentEntity cardPaymentEntityCreated = response.body();
        Assert.assertNotNull(cardPaymentEntityCreated.Id);
        Assert.assertNotNull(cardPaymentEntityCreated.Href);
    }

    @Test
    public void cardPayment_002_list() throws IOException {
        final Call<List<CardPaymentEntity>> listCall = service.listCardPayments(TestSettings.testUserAppUserId);
        final Response<List<CardPaymentEntity>> response = listCall.execute();
        if (response.code() != 200) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);

        final List<CardPaymentEntity> cardPaymentEntityList = response.body();
        Assert.assertTrue(cardPaymentEntityList.size() > 0);
    }

    @Test
    public void cardPayment_003_get() throws IOException {
        final Call<CardPaymentEntity> call = service.getCardPayment(TestSettings.testUserAppUserId, orderId);
        final Response<CardPaymentEntity> response = call.execute();
        if (response.code() != 200) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);

        final CardPaymentEntity cardPaymentEntity = response.body();
        Assert.assertEquals(cardPaymentEntity.Message, "Recharge test");
    }
}
