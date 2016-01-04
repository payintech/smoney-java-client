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

import com.payintech.smoney.entity.BankAccountEntity;
import com.payintech.smoney.entity.MoneyOutEntity;
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
 * MoneyOutTest.
 *
 * @author Jean-Pierre Boudic
 * @version 15.11
 * @since 15.11
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MoneyOutTest {

    public static SMoneyService service = SMoneyServiceFactory.createService();

    public static Long moneyOutId = 0L;
    public static BankAccountEntity testUserDefaultBankAccount;

    static {
        try {
            testUserDefaultBankAccount = service.listBankAccounts(TestSettings.testUserAppUserId).execute().body().get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void moneyout_001_list() throws IOException {
        final Call<List<MoneyOutEntity>> listCall = service.listMoneyOuts(TestSettings.testUserAppUserId);
        final Response<List<MoneyOutEntity>> response = listCall.execute();
        if (response.code() != 200) {
            System.out.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);

        final List<MoneyOutEntity> users = response.body();
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void moneyout_002_create_oneshot() throws IOException {
        final MoneyOutEntity mo = new MoneyOutEntity();
        mo.Amount = 10L;
        mo.AccountId = new SubAccountEntity();
        mo.AccountId.AppAccountId = TestSettings.testUserAppUserId;
        mo.BankAccount = new BankAccountEntity();
        mo.BankAccount.Bic = testUserDefaultBankAccount.Bic;
        mo.BankAccount.DisplayName = testUserDefaultBankAccount.DisplayName;
        mo.BankAccount.Iban = testUserDefaultBankAccount.Iban;
        mo.BankAccount.IsMine = true;
        mo.Message = String.format("Test-%s", UUID.randomUUID().toString().split("-")[0]);

        final Call<MoneyOutEntity> call = service.createMoneyOutOneShot(TestSettings.testUserAppUserId, mo);
        final Response<MoneyOutEntity> response = call.execute();
        if (response.code() != 201) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 201);

        final MoneyOutEntity moneyOut = response.body();
        Assert.assertEquals(mo.Amount, moneyOut.Amount);
        Assert.assertEquals(mo.BankAccount.getMaskedBic(), moneyOut.BankAccount.Bic);
        Assert.assertEquals(mo.BankAccount.DisplayName, moneyOut.BankAccount.DisplayName);
        Assert.assertEquals(mo.BankAccount.getMaskedIban(), moneyOut.BankAccount.Iban);
        Assert.assertEquals(mo.BankAccount.IsMine, moneyOut.BankAccount.IsMine);
        Assert.assertEquals(mo.Message, moneyOut.Message);
        Assert.assertTrue(moneyOut.Id > 0);
        Assert.assertNotNull(moneyOut.OperationDate);

        MoneyOutTest.moneyOutId = moneyOut.Id;
    }

    @Test
    public void moneyout_003_get_oneshot() throws IOException {
        final Call<MoneyOutEntity> call = service.getMoneyOut(TestSettings.testUserAppUserId, MoneyOutTest.moneyOutId);
        final Response<MoneyOutEntity> response = call.execute();
        if (response.code() != 200) {
            System.out.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);

        final MoneyOutEntity moneyOut = response.body();
        Assert.assertEquals(moneyOut.Amount, new Long(10));
        Assert.assertEquals(moneyOut.AccountId.AppAccountId, TestSettings.testUserAppUserId);
        Assert.assertEquals(moneyOut.BankAccount.Bic, testUserDefaultBankAccount.getMaskedBic());
        Assert.assertEquals(moneyOut.BankAccount.DisplayName, testUserDefaultBankAccount.DisplayName);
        Assert.assertEquals(moneyOut.BankAccount.Iban, testUserDefaultBankAccount.getMaskedIban());
        Assert.assertEquals(moneyOut.BankAccount.IsMine, testUserDefaultBankAccount.IsMine);
    }

    @Test
    public void moneyout_004_create_recurring() throws IOException {
        final MoneyOutEntity mo = new MoneyOutEntity();
        mo.Amount = 10L;
        mo.AccountId = new SubAccountEntity();
        mo.AccountId.AppAccountId = TestSettings.testUserAppUserId;
        mo.BankAccount = new BankAccountEntity();
        mo.BankAccount.Id = testUserDefaultBankAccount.Id;
        mo.Message = String.format("Test-%s", UUID.randomUUID().toString().split("-")[0]);

        final Call<MoneyOutEntity> call = service.createMoneyOutRecurring(TestSettings.testUserAppUserId, mo);
        final Response<MoneyOutEntity> response = call.execute();
        if (response.code() != 201) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 201);

        final MoneyOutEntity moneyOut = response.body();
        Assert.assertEquals(mo.Amount, moneyOut.Amount);
        Assert.assertEquals(testUserDefaultBankAccount.getMaskedBic(), moneyOut.BankAccount.Bic);
        Assert.assertEquals(testUserDefaultBankAccount.DisplayName, moneyOut.BankAccount.DisplayName);
        Assert.assertEquals(testUserDefaultBankAccount.getMaskedIban(), moneyOut.BankAccount.Iban);
        Assert.assertEquals(testUserDefaultBankAccount.IsMine, moneyOut.BankAccount.IsMine);
        Assert.assertEquals(mo.Message, moneyOut.Message);
        Assert.assertTrue(moneyOut.Id > 0);
        Assert.assertNotNull(moneyOut.OperationDate);

        MoneyOutTest.moneyOutId = moneyOut.Id;
    }

    @Test
    public void moneyout_005_get_recurring() throws IOException {
        final Call<MoneyOutEntity> call = service.getMoneyOut(TestSettings.testUserAppUserId, MoneyOutTest.moneyOutId);
        final Response<MoneyOutEntity> response = call.execute();
        if (response.code() != 200) {
            System.out.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);

        final MoneyOutEntity moneyOut = response.body();
        Assert.assertEquals(moneyOut.Amount, new Long(10));
        Assert.assertEquals(moneyOut.AccountId.AppAccountId, TestSettings.testUserAppUserId);
        Assert.assertEquals(moneyOut.BankAccount.Bic, testUserDefaultBankAccount.getMaskedBic());
        Assert.assertEquals(moneyOut.BankAccount.DisplayName, testUserDefaultBankAccount.DisplayName);
        Assert.assertEquals(moneyOut.BankAccount.Iban, testUserDefaultBankAccount.getMaskedIban());
        Assert.assertEquals(moneyOut.BankAccount.IsMine, testUserDefaultBankAccount.IsMine);
    }
}
