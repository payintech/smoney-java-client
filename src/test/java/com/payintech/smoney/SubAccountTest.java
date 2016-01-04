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

import com.payintech.smoney.entity.SubAccountEntity;
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
 * SubAccountTest.
 *
 * @author Pierre Adam
 * @version 15.11
 * @since 15.11
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SubAccountTest {

    public static SMoneyService service = SMoneyServiceFactory.createService();

    public static String TestAppAccountId;

    static {
        TestAppAccountId = UUID.randomUUID().toString();
    }

    @Test
    public void subAccount_001_list() throws IOException {
        final Call<List<SubAccountEntity>> listCall = service.listSubAccounts(TestSettings.testCompanyAppUserId);
        final Response<List<SubAccountEntity>> response = listCall.execute();
        Assert.assertEquals(response.code(), 200);

        final List<SubAccountEntity> subAccountEntityList = response.body();
        Assert.assertNotNull(subAccountEntityList);
        Assert.assertTrue(subAccountEntityList.size() > 0);
    }

    @Test
    public void subAccount_002_get() throws IOException {
        final Call<SubAccountEntity> call = service.getSubAccount(TestSettings.testCompanyAppUserId, TestSettings.testCompanyAppUserId);
        final Response<SubAccountEntity> response = call.execute();
        Assert.assertEquals(response.code(), 200);

        final SubAccountEntity subAccountEntity = response.body();
        Assert.assertNotNull(subAccountEntity);
    }

    @Test
    public void subAccount_003_create() throws IOException {
        SubAccountEntity subAccountEntity = new SubAccountEntity();
        subAccountEntity.AppAccountId = TestAppAccountId;
        subAccountEntity.DisplayName = "My sub account";

        final Call<SubAccountEntity> call = service.createSubAccount(TestSettings.testCompanyAppUserId, subAccountEntity);
        final Response<SubAccountEntity> response = call.execute();
        Assert.assertEquals(response.code(), 201);
        subAccountEntity = response.body();
        Assert.assertNotNull(subAccountEntity);
        Assert.assertEquals(subAccountEntity.DisplayName, "My sub account");
    }

    @Test
    public void subAccount_004_update() throws IOException {
        SubAccountEntity subAccountEntity = new SubAccountEntity();
        subAccountEntity.AppAccountId = TestAppAccountId;
        subAccountEntity.DisplayName = "Reserve fund";

        final Call<SubAccountEntity> call = service.updateSubAccount(TestSettings.testCompanyAppUserId, TestAppAccountId, subAccountEntity);
        final Response<SubAccountEntity> response = call.execute();
        Assert.assertEquals(response.code(), 200);
        subAccountEntity = response.body();
        Assert.assertNotNull(subAccountEntity);
        Assert.assertEquals(subAccountEntity.DisplayName, "Reserve fund");
    }

    @Test
    public void subAccount_005_delete() throws IOException {
        final Call<String> call = service.deleteSubAccount(TestSettings.testCompanyAppUserId, TestAppAccountId);
        final Response<String> response = call.execute();
        Assert.assertEquals(response.code(), 204);
    }
}
