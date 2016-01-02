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

import com.payintech.smoney.entity.KycEntity;
import com.payintech.smoney.enumeration.KycStatusEnum;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import retrofit.Call;
import retrofit.Response;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * KycTest.
 *
 * @author Jean-Pierre Boudic
 * @version 15.11
 * @since 15.11
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KycTest {

    public static SMoneyService service = SMoneyServiceFactory.createService();
    public static Long kycId = 0L;

    @Test
    public void kyc_001_create_one_file() throws IOException {
        File tmpFile = File.createTempFile("file1", ".jpg");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
        bw.write("This is the temporary file content");
        bw.close();
        RequestBody file1 = RequestBody.create(MediaType.parse("image/jpeg"), tmpFile);
        Call<KycEntity> call = service.createKYCRequest(TestSettings.testUserAppUserId, file1);
        Response<KycEntity> response = call.execute();
        if (response.code() != 201) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 201);
        KycEntity kyc = response.body();
        Assert.assertTrue(kyc.Id > 0);
        Assert.assertTrue(kyc.VoucherCopies.size() > 0);
        Assert.assertEquals(kyc.Status, KycStatusEnum.IN_PROGRESS);
        Assert.assertNotNull(kyc.RequestDate);
        KycTest.kycId = kyc.Id;
    }

    @Test
    public void kyc_002_create_multiple_files() throws IOException {
        File tmpFile1 = File.createTempFile("file1", ".jpg");
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(tmpFile1));
        bw1.write("This is the temporary file content");
        bw1.close();
        RequestBody file1 = RequestBody.create(MediaType.parse("image/jpeg"), tmpFile1);
        File tmpFile2 = File.createTempFile("file2", ".png");
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(tmpFile2));
        bw2.write("This is the temporary file content");
        bw2.close();
        RequestBody file2 = RequestBody.create(MediaType.parse("image/png"), tmpFile2);

        Map<String, RequestBody> files = new HashMap<>();
        files.put("file1", file1);
        files.put("file2", file2);

        Call<KycEntity> call = service.createKYCRequest(TestSettings.testUserAppUserId, files);
        Response<KycEntity> response = call.execute();
        if (response.code() != 201) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 201);
        KycEntity kyc = response.body();
        Assert.assertTrue(kyc.Id > 0);
        Assert.assertTrue(kyc.VoucherCopies.size() > 0);
        Assert.assertEquals(kyc.Status, KycStatusEnum.IN_PROGRESS);
        Assert.assertNotNull(kyc.RequestDate);
        KycTest.kycId = kyc.Id;
    }

    @Test
    public void kyc_002_list() throws IOException {
        Call<List<KycEntity>> listCall = service.getKYC(TestSettings.testUserAppUserId);
        Response<List<KycEntity>> response = listCall.execute();
        if (response.code() != 200) {
            System.err.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);
        List<KycEntity> kycs = response.body();
        Assert.assertNotNull(kycs);
        Assert.assertTrue(kycs.size() > 0);
    }
}
