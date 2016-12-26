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
package com.payintech.smoney;

import com.payintech.smoney.entity.*;
import com.payintech.smoney.enumeration.PaymentStatusEnum;
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
 * StoredCardPaymentTest.
 *
 * @author Jean-Pierre Boudic
 * @version 15.11.01
 * @since 15.11.01
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoredCardPaymentTest {

    private static final Boolean TEST_REFUND = false;
    public static SMoneyService service = SMoneyServiceFactory.createService();
    public static StoredCardPaymentEntity scp;

    static {
        scp = new StoredCardPaymentEntity();
        scp.Id = 0L; //Set On created (test 002)
        scp.OrderId = String.format("order-test-%s", UUID.randomUUID().toString().split("-")[0]);
        scp.AccountId = new SubAccountEntity();
        scp.AccountId.AppAccountId = TestSettings.testUserAppUserId;
        scp.Card = new CardEntity();
        scp.Card.AppCardId = TestSettings.testUserAppCardId;
        scp.IsMine = true;
        scp.Amount = 10L;
    }

    @Test
    public void storedcardpayment_001_list() throws IOException {
        final Call<List<StoredCardPaymentEntity>> listCall = service.listStoredCardPayments(TestSettings.testUserAppUserId);
        final Response<List<StoredCardPaymentEntity>> response = listCall.execute();
        if (response.code() != 200) {
            System.out.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);

        final List<StoredCardPaymentEntity> payments = response.body();
        Assert.assertTrue(payments.size() > 0);
    }

    @Test
    public void storedcardpayment_002_create() throws IOException {
        final Call<StoredCardPaymentEntity> call = service.createStoredCardPayment(TestSettings.testUserAppUserId, StoredCardPaymentTest.scp);
        final Response<StoredCardPaymentEntity> response = call.execute();
        if (response.code() != 201) {
            System.out.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 201);

        final StoredCardPaymentEntity storedCardPay = response.body();
        Assert.assertEquals(StoredCardPaymentTest.scp.OrderId, storedCardPay.OrderId);
        Assert.assertEquals(StoredCardPaymentTest.scp.AccountId.AppAccountId, storedCardPay.AccountId.AppAccountId);
        Assert.assertEquals(StoredCardPaymentTest.scp.Card.AppCardId, storedCardPay.Card.AppCardId);
        Assert.assertEquals(StoredCardPaymentTest.scp.IsMine, storedCardPay.IsMine);
        Assert.assertEquals(StoredCardPaymentTest.scp.Amount, storedCardPay.Amount);
        Assert.assertTrue(storedCardPay.Id > 0);
        Assert.assertNotNull(storedCardPay.OperationDate);

        StoredCardPaymentTest.scp.Id = storedCardPay.Id;
    }

    @Test
    public void storedcardpayment_003_get() throws IOException {
        final Call<StoredCardPaymentEntity> call = service.getStoredCardPayment(TestSettings.testUserAppUserId, StoredCardPaymentTest.scp.OrderId);
        final Response<StoredCardPaymentEntity> response = call.execute();
        if (response.code() != 200) {
            System.out.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);

        final StoredCardPaymentEntity storedCardPay = response.body();
        Assert.assertEquals(StoredCardPaymentTest.scp.OrderId, storedCardPay.OrderId);
        Assert.assertEquals(StoredCardPaymentTest.scp.AccountId.AppAccountId, storedCardPay.AccountId.AppAccountId);
        Assert.assertEquals(StoredCardPaymentTest.scp.Card.AppCardId, storedCardPay.Card.AppCardId);
        Assert.assertEquals(StoredCardPaymentTest.scp.IsMine, storedCardPay.IsMine);
        Assert.assertEquals(StoredCardPaymentTest.scp.Amount, storedCardPay.Amount);
        Assert.assertEquals(StoredCardPaymentTest.scp.Id, storedCardPay.Id);
        Assert.assertNotNull(storedCardPay.OperationDate);
    }

    @Test
    public void storedcardpayment_004_refund() throws IOException {
        if (!StoredCardPaymentTest.TEST_REFUND) {
            return;
        }
        final CardPaymentRefundApplicationEntity refund = new CardPaymentRefundApplicationEntity();
        refund.OrderId = String.format("order-test-%s", UUID.randomUUID().toString().split("-")[0]);
        refund.RefundFee = false;
        refund.Amount = StoredCardPaymentTest.scp.Amount;

        final Call<CardPaymentRefundAnswerEntity> call = service.refundStoredCardPayment(TestSettings.testUserAppUserId, StoredCardPaymentTest.scp.OrderId, refund);
        final Response<CardPaymentRefundAnswerEntity> response = call.execute();
        if (response.code() != 201) {
            System.out.println(response.errorBody().string());
        }
        Assert.assertEquals(201, response.code());

        final CardPaymentRefundAnswerEntity refunded = response.body();
        Assert.assertEquals(refund.OrderId, refunded.OrderId);
        Assert.assertEquals(StoredCardPaymentTest.scp.Amount, refunded.Amount);
        Assert.assertEquals(PaymentStatusEnum.SUCCEEDED, refunded.Status);
        Assert.assertEquals(StoredCardPaymentTest.scp.Id, refunded.OriginalPayment.Id);
        Assert.assertEquals(StoredCardPaymentTest.scp.OrderId, refunded.OriginalPayment.OrderId);
        Assert.assertNotNull(refunded.PaymentDate);
        Assert.assertTrue(refunded.Id > 0);
    }
}
