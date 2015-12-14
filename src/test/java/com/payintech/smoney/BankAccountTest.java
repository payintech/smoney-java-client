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

import com.payintech.smoney.entity.BankAccountEntity;
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
 * BankAccountTest.
 *
 * @author Jean-Pierre Boudic
 * @version 15.11
 * @since 15.11
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountTest {

    public static final SMoneyService service = SMoneyServiceFactory.createService();

    public static Long bankAccountId = 0L;

    @Test
    public void bankaccount_001_list() throws IOException {
        Call<List<BankAccountEntity>> listCall = service.listBankAccounts(TestSettings.testUserAppUserId);
        Response<List<BankAccountEntity>> response = listCall.execute();
        Assert.assertEquals(response.code(), 200);
        List<BankAccountEntity> bankAccounts = response.body();
        Assert.assertNotNull(bankAccounts);
        Assert.assertTrue(bankAccounts.size() > 0);
    }

    @Test
    public void bankaccount_002_create() throws IOException {
        BankAccountEntity ba = new BankAccountEntity();
        ba.Bic = "CMCIFR2A";
        ba.DisplayName = "Test Account";
        ba.Iban = "FR7613106005002000743520962";
        ba.IsMine = true;

        Call<BankAccountEntity> call = service.createBankAccount(TestSettings.testUserAppUserId, ba);
        Response<BankAccountEntity> response = call.execute();
        if (response.code() != 201) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 201);
        BankAccountEntity bankAccount = response.body();

        Assert.assertEquals(bankAccount.getMaskedBic(), bankAccount.Bic);
        Assert.assertEquals(ba.DisplayName, bankAccount.DisplayName);
        Assert.assertEquals(bankAccount.getMaskedIban(), bankAccount.Iban);
        Assert.assertEquals(ba.IsMine, bankAccount.IsMine);
        Assert.assertTrue(bankAccount.Id > 0);
        BankAccountTest.bankAccountId = bankAccount.Id;
    }

    @Test
    public void bankaccount_003_get() throws IOException {
        Call<BankAccountEntity> call = service.getBankAccount(TestSettings.testUserAppUserId, BankAccountTest.bankAccountId);
        Response<BankAccountEntity> response = call.execute();
        Assert.assertEquals(response.code(), 200);
        BankAccountEntity bankAccount = response.body();
        Assert.assertNotNull(bankAccount);
    }

    @Test
    public void bankaccount_004_update() throws IOException {
        BankAccountEntity ba = new BankAccountEntity();
        ba.Id = BankAccountTest.bankAccountId;
        ba.DisplayName = String.format("Test-%s", UUID.randomUUID().toString().split("-")[0]);
        ba.IsMine = true;

        Call<BankAccountEntity> call = service.updateBankAccount(TestSettings.testUserAppUserId, ba);
        Response<BankAccountEntity> response = call.execute();
        if (response.code() != 200) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);
        BankAccountEntity bankAccount = response.body();

        Assert.assertEquals(bankAccount.getMaskedBic(), bankAccount.Bic);
        Assert.assertEquals(ba.DisplayName, bankAccount.DisplayName);
        Assert.assertEquals(bankAccount.getMaskedIban(), bankAccount.Iban);
        Assert.assertEquals(ba.IsMine, bankAccount.IsMine);
        Assert.assertEquals(ba.Id, bankAccount.Id);
    }

    @Test
    public void bankaccount_005_delete() throws IOException {
        Call<String> call = service.deleteBankAccount(TestSettings.testUserAppUserId, BankAccountTest.bankAccountId);
        Response<String> response = call.execute();
        if (response.code() != 204) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 204);
        Assert.assertEquals(service.getBankAccount(TestSettings.testUserAppUserId, BankAccountTest.bankAccountId).execute().code(), 404);
    }
}
