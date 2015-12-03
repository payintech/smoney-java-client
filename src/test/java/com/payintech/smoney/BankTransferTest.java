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

import com.payintech.smoney.entity.BankTransferEntity;
import com.payintech.smoney.entity.ReferenceEntity;
import com.payintech.smoney.entity.SubAccountEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;
import java.util.List;

/**
 * BankTransferTest.
 *
 * @author Jean-Pierre Boudic
 * @version 15.11
 * @since 15.11
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankTransferTest {
    public static SMoneyService service = SMoneyServiceFactory.createService();

    public static ReferenceEntity reference;
    public static BankTransferEntity banTransfer;

    @Test
    public void banktransfer_001_listRef() throws IOException {
        Call<List<ReferenceEntity>> listCall = service.listReferences(TestSettings.testUserAppUserId);
        Response<List<ReferenceEntity>> response = listCall.execute();
        Assert.assertEquals(response.code(), 200);
        if (response.code() != 200) {
            System.err.println(response.errorBody().string());
        }
        List<ReferenceEntity> references = response.body();
        Assert.assertNotNull(references);
        Assert.assertTrue(references.size() > 0);
    }

    @Test
    public void banktransfer_002_createRef() throws IOException {
        ReferenceEntity r = new ReferenceEntity();
        r.Beneficiary = new SubAccountEntity();
        r.Beneficiary.AppAccountId = TestSettings.testUserAppUserId;
        r.IsMine = true;
        Call<ReferenceEntity> call = service.createReference(TestSettings.testUserAppUserId, r);
        Response<ReferenceEntity> response = call.execute();
        Assert.assertEquals(response.code(), 201);
        if (response.code() != 201) {
            System.err.println(response.errorBody().string());
        }
        ReferenceEntity reference = response.body();
        Assert.assertTrue(reference.Id > 0);
        Assert.assertEquals(reference.Beneficiary.AppAccountId, r.Beneficiary.AppAccountId);
        Assert.assertEquals(reference.IsMine, r.IsMine);
        Assert.assertNotNull(reference.Reference);
        Assert.assertNotNull(reference.BankAccount);
        BankTransferTest.reference = reference;
    }

    @Test
    public void banktransfer_003_getRef() throws IOException {
        Call<ReferenceEntity> call = service.getReference(TestSettings.testUserAppUserId, BankTransferTest.reference.Id);
        Response<ReferenceEntity> response = call.execute();
        Assert.assertEquals(response.code(), 200);
        if (response.code() != 200) {
            System.err.println(response.errorBody().string());
        }
        ReferenceEntity reference = response.body();

        Assert.assertEquals(reference.Id, reference.Id);
        Assert.assertEquals(reference.Beneficiary.Id, reference.Beneficiary.Id);
        Assert.assertEquals(reference.Beneficiary.AppAccountId, reference.Beneficiary.AppAccountId);
        Assert.assertEquals(reference.Beneficiary.DisplayName, reference.Beneficiary.DisplayName);
        Assert.assertEquals(reference.IsMine, reference.IsMine);
        Assert.assertEquals(reference.Reference, reference.Reference);
        Assert.assertEquals(reference.BankAccount.DisplayName, reference.BankAccount.DisplayName);
        Assert.assertEquals(reference.BankAccount.Bic, reference.BankAccount.Bic);
        Assert.assertEquals(reference.BankAccount.Iban, reference.BankAccount.Iban);
    }

    @Test
    public void banktransfer_004_get() throws IOException {
        Call<ReferenceEntity> call = service.getReference(TestSettings.testUserAppUserId, BankTransferTest.reference.Reference);
        Response<ReferenceEntity> response = call.execute();
        Assert.assertEquals(response.code(), 200);
        if (response.code() != 200) {
            System.err.println(response.errorBody().string());
        }
        ReferenceEntity reference = response.body();
        Assert.assertEquals(reference.Id, reference.Id);
        Assert.assertEquals(reference.Beneficiary.Id, reference.Beneficiary.Id);
        Assert.assertEquals(reference.Beneficiary.AppAccountId, reference.Beneficiary.AppAccountId);
        Assert.assertEquals(reference.Beneficiary.DisplayName, reference.Beneficiary.DisplayName);
        Assert.assertEquals(reference.IsMine, reference.IsMine);
        Assert.assertEquals(reference.Reference, reference.Reference);
        Assert.assertEquals(reference.BankAccount.DisplayName, reference.BankAccount.DisplayName);
        Assert.assertEquals(reference.BankAccount.Bic, reference.BankAccount.Bic);
        Assert.assertEquals(reference.BankAccount.Iban, reference.BankAccount.Iban);
    }

    @Test
    public void banktransfer_005_list() throws IOException {
        Call<List<BankTransferEntity>> listCall = service.listBankTransfers(TestSettings.testUserAppUserId);
        Response<List<BankTransferEntity>> response = listCall.execute();
        Assert.assertEquals(response.code(), 200);
    }
}
