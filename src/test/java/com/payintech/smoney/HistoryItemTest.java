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

import com.payintech.smoney.entity.HistoryItemEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * HistoryItemTest.
 *
 * @author Jean-Pierre Boudic
 * @version 15.11.01
 * @since 15.11.01
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HistoryItemTest {

    public static SMoneyService service = SMoneyServiceFactory.createService();

    @Test
    public void hystoryitem_001_list() throws IOException {
        final Call<List<HistoryItemEntity>> listCall = service.listHistoryItems(TestSettings.testUserAppUserId);
        final Response<List<HistoryItemEntity>> response = listCall.execute();
        Assert.assertEquals(response.code(), 200);

        final List<HistoryItemEntity> historyItems = response.body();
        Assert.assertTrue(historyItems.size() > 0);
    }
}
