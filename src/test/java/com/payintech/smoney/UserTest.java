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

import com.payintech.smoney.entity.AddressEntity;
import com.payintech.smoney.entity.CompanyEntity;
import com.payintech.smoney.entity.ProfileEntity;
import com.payintech.smoney.entity.UserEntity;
import com.payintech.smoney.enumeration.CountryCodeEnum;
import com.payintech.smoney.enumeration.UserRoleEnum;
import com.payintech.smoney.enumeration.UserTypeEnum;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * UserTest.
 *
 * @author Pierre Adam
 * @author Jean-Pierre Boudic
 * @version 15.11.01
 * @since 15.11.01
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

    public static SMoneyService service = SMoneyServiceFactory.createService();
    private static Boolean TEST_CREATION = false;

    public static void assertUser(UserEntity u1, UserEntity u2) {
        Assert.assertNotNull(u1.Id);
        Assert.assertNotNull(u1.Amount);
        Assert.assertEquals(u1.AppUserId, u2.AppUserId);
        Assert.assertEquals(u1.Type, u2.Type);
        Assert.assertEquals(u1.Role, u2.Role);
        Assert.assertEquals(u1.Status, u2.Status);
        Assert.assertEquals(u1.Profile.Civility, u2.Profile.Civility);
        Assert.assertEquals(u1.Profile.FirstName, u2.Profile.FirstName);
        Assert.assertEquals(u1.Profile.LastName, u2.Profile.LastName);
        Assert.assertEquals(u1.Profile.Birthdate, u2.Profile.Birthdate);
        Assert.assertEquals(u1.Profile.PhoneNumber, u2.Profile.PhoneNumber);
        Assert.assertEquals(u1.Profile.Email, u2.Profile.Email);
        Assert.assertEquals(u1.Profile.Address.Street, u2.Profile.Address.Street);
        Assert.assertEquals(u1.Profile.Address.ZipCode, u2.Profile.Address.ZipCode);
        Assert.assertEquals(u1.Profile.Address.City, u2.Profile.Address.City);
        Assert.assertEquals(u1.Profile.Address.Country, u2.Profile.Address.Country);
    }

    @Test
    public void user_001_list() throws IOException {
        final Call<List<UserEntity>> listCall = service.listUsers();
        final Response<List<UserEntity>> response = listCall.execute();
        Assert.assertEquals(response.code(), 200);

        final List<UserEntity> users = response.body();
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void user_001_list_page() throws IOException {
        final Call<List<UserEntity>> listCallPage0 = service.listUsers(0, 1);
        final Call<List<UserEntity>> listCallPage1 = service.listUsers(1, 1);
        final Response<List<UserEntity>> response0 = listCallPage0.execute();
        final Response<List<UserEntity>> response1 = listCallPage1.execute();
        Assert.assertTrue(response0.body().size() == 1);
        Assert.assertTrue(response1.body().size() == 1);
        Assert.assertEquals(response0.body().get(0).AppUserId, response1.body().get(0).AppUserId);
    }

    @Test
    public void user_001_list_page2() throws IOException {
        final Call<List<UserEntity>> listCallPage0 = service.listUsers(1, 1);
        final Call<List<UserEntity>> listCallPage1 = service.listUsers(2, 1);
        final Response<List<UserEntity>> response0 = listCallPage0.execute();
        final Response<List<UserEntity>> response1 = listCallPage1.execute();
        Assert.assertTrue(response0.body().size() == 1);
        Assert.assertTrue(response1.body().size() == 1);
        Assert.assertNotEquals(response0.body().get(0).AppUserId, response1.body().get(0).AppUserId);
    }

    @Test
    public void user_002_get() throws IOException {
        final Call<UserEntity> call = service.getUser(TestSettings.testUserAppUserId);
        final Response<UserEntity> response = call.execute();
        if (response.code() != 200) {
            System.out.println(response.errorBody().string());
        }
        Assert.assertEquals(response.code(), 200);

        final UserEntity user = response.body();
        UserTest.assertUser(user, TestSettings.testUser);
    }

    @Test
    public void user_003_get() throws IOException {
        final Call<UserEntity> call = service.getUser(TestSettings.testCompanyAppUserId);
        final Response<UserEntity> response = call.execute();
        Assert.assertEquals(response.code(), 200);

        final UserEntity user = response.body();
        UserTest.assertUser(user, TestSettings.testCompany);
    }

    @Test
    public void user_004_update() throws IOException {
        TestSettings.testUser.Profile.Alias = String.format("Test-%s", UUID.randomUUID().toString().split("-")[0]);
        final Call<UserEntity> call = service.updateUser(TestSettings.testUserAppUserId, TestSettings.testUser);
        final Response<UserEntity> response = call.execute();
        Assert.assertEquals(response.code(), 200);

        final UserEntity user = response.body();
        UserTest.assertUser(user, TestSettings.testUser);
    }

    @Test
    public void user_005_find() throws IOException {
        final Map<String, String> options = new HashMap<>();
        options.put("lastname", TestSettings.testUser.Profile.LastName);

        final Call<List<UserEntity>> listCall = service.findUsers(options);
        final Response<List<UserEntity>> response = listCall.execute();
        Assert.assertEquals(response.code(), 200);

        final List<UserEntity> users = response.body();
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void user_006_create_normal() throws IOException {
        if (!TEST_CREATION) {
            return;
        }
        final UserEntity u = new UserEntity();
        u.AppUserId = String.format("Test-%s", UUID.randomUUID().toString().split("-")[0]);
        u.Profile = new ProfileEntity();
        u.Profile.FirstName = "TEST";
        u.Profile.LastName = "TEST";
        u.Profile.Birthdate = DateTime.parse("1990-01-01T00:00:00", DateTimeFormat.forPattern("yyyy-MM-ss'T'HH:mm:ss"));
        u.Profile.Address = new AddressEntity();
        u.Profile.Address.Country = CountryCodeEnum.FR;

        final Call<UserEntity> call = service.createUser(u);
        final Response<UserEntity> response = call.execute();
        Assert.assertEquals(response.code(), 201);

        final UserEntity user = response.body();
        Assert.assertEquals(user.AppUserId, u.AppUserId);
        Assert.assertEquals(user.Profile.FirstName, u.Profile.FirstName);
        Assert.assertEquals(user.Role, UserRoleEnum.CLIENT);
        Assert.assertTrue(user.Id > 0);
    }

    @Test
    public void user_007_create_pro() throws IOException {
        if (!TEST_CREATION) {
            return;
        }
        final UserEntity u = new UserEntity();
        u.AppUserId = String.format("Test-%s", UUID.randomUUID().toString().split("-")[0]);
        u.Type = UserTypeEnum.PROFESSIONAL;
        u.Profile = new ProfileEntity();
        u.Profile.FirstName = "TEST";
        u.Profile.LastName = "TEST";
        u.Profile.Birthdate = DateTime.parse("1990-11-06T00:00:00");
        u.Profile.Address = new AddressEntity();
        u.Profile.Address.Country = CountryCodeEnum.FR;
        u.Company = new CompanyEntity();
        u.Company.Name = "Test";
        u.Company.Siret = "Test";

        final Call<UserEntity> call = service.createUser(u);
        final Response<UserEntity> response = call.execute();
        Assert.assertEquals(response.code(), 201);

        final UserEntity user = response.body();
        Assert.assertEquals(user.AppUserId, u.AppUserId);
        Assert.assertEquals(user.Profile.FirstName, u.Profile.FirstName);
        Assert.assertEquals(user.Type, UserTypeEnum.PROFESSIONAL);
        Assert.assertTrue(user.Id > 0);
    }
}
