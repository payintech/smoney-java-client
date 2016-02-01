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
import com.payintech.smoney.entity.StoredCardPaymentEntity;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * AAA_RechargeTest.
 *
 * @author Pierre Adam
 * @version 16.01
 * @since 16.01
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AAA_RechargeTest {

    public static final SMoneyService service = SMoneyServiceFactory.createService();

    public static Long bankAccountId = 0L;

    @Test
    public void recharge_001_create() throws IOException {
        CardEntity card = null;
        Call<List<CardEntity>> listCardsCall = service.listCards(TestSettings.testUserAppUserId);
        try {
            Response<List<CardEntity>> listCardsResponse = listCardsCall.execute();
            if (!listCardsResponse.isSuccess()) {
                System.out.println("Failed to get the list of registered cards. " + listCardsResponse.errorBody().string());
                return;
            }
            List<CardEntity> listCards = listCardsResponse.body();
            if (listCards.size() == 0) {
                System.out.println("No card registered. Unable to recharge.");
                return;
            }
            card = listCards.get(0);
        } catch (IOException e) {
            System.out.println("Can't get the list of registered cards.");
            return;
        }
        final StoredCardPaymentEntity storedCardPaymentEntity = new StoredCardPaymentEntity();
        storedCardPaymentEntity.OrderId = UUID.randomUUID().toString();
        storedCardPaymentEntity.Card = card;
        storedCardPaymentEntity.IsMine = true;
        storedCardPaymentEntity.Amount = 300L;

        final Call<StoredCardPaymentEntity> callStoredCardPayment = service.createStoredCardPayment(TestSettings.testUserAppUserId, storedCardPaymentEntity);
        try {
            Response<StoredCardPaymentEntity> responseStoredCardPayment = callStoredCardPayment.execute();
            if (!responseStoredCardPayment.isSuccess()) {
                System.out.println("Error while paying. Unable to recharge. " + responseStoredCardPayment.errorBody().string());
            }
        } catch (IOException e) {
            System.out.println("Can't recharge the account.");
            return;
        }
    }
}
