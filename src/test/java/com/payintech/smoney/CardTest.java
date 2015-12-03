/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 - 2015 PayinTech
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
import com.payintech.smoney.entity.CardRegistrationAnswerEntity;
import com.payintech.smoney.entity.CardRegistrationApplicationEntity;
import com.payintech.smoney.enumeration.CardRegistrationStatusEnum;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * CardTest.
 *
 * @author Pierre Adam
 * @version 15.11
 * @since 15.11
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardTest {
    public static SMoneyService service = SMoneyServiceFactory.createService();

    public static String appCardId;

    static {
        appCardId = UUID.randomUUID().toString();
    }

    @Test
    public void card_001_list() throws IOException {
        Call<List<CardEntity>> listCall = service.listCards(TestSettings.testUserAppUserId);
        Response<List<CardEntity>> response = listCall.execute();
        Assert.assertEquals(response.code(), 200);
        List<CardEntity> cardEntityList = response.body();
        Assert.assertNotNull(cardEntityList);
        Assert.assertTrue(cardEntityList.size() > 0);
    }

    @Test
    public void card_002_registration() throws IOException {
        CardRegistrationApplicationEntity cardApplication = new CardRegistrationApplicationEntity();
        cardApplication.Card = new CardEntity();
        cardApplication.UrlReturn = "http://127.0.0.1";
        cardApplication.Card.AppCardId = CardTest.appCardId;
        cardApplication.Card.Name = "Testing card";
        Call<CardRegistrationAnswerEntity> call = service.createCard(TestSettings.testUserAppUserId, cardApplication);
        Response<CardRegistrationAnswerEntity> response = call.execute();
        Assert.assertEquals(response.code(), 201);
        CardRegistrationAnswerEntity cardAnswer = response.body();
        Assert.assertNotNull(cardAnswer);
        Assert.assertEquals(cardAnswer.Status, CardRegistrationStatusEnum.WAITING);
    }

    @Test
    public void card_003_get() throws IOException {
        Call<CardEntity> call = service.getCard(TestSettings.testUserAppUserId, TestSettings.testUserAppCardId);
        Response<CardEntity> response = call.execute();
        Assert.assertEquals(response.code(), 200);
        CardEntity card = response.body();
        Assert.assertEquals(card.Name, "Testing card");
    }
}
