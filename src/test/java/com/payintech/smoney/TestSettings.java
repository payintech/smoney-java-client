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

import com.payintech.smoney.entity.*;
import com.payintech.smoney.enumeration.*;
import org.joda.time.DateTime;
import retrofit.Response;

import java.io.IOException;
import java.util.UUID;

/**
 * TestSettings.
 *
 * @author Pierre Adam
 * @version 15.11
 * @since 15.11
 */
public class TestSettings {

    public static String testUserAppUserId = "SMoney-testing-001";
    public static String testUserAppCardId = "17baf19c-8dc1-4c9d-a50c-086d3d99d29d";
    public static String testCompanyAppUserId = "SMoney-testing-002";
    public static UserEntity testUser;
    public static UserEntity testCompany;

    static {
        SMoneyService service = SMoneyServiceFactory.createService();

        testUser = new UserEntity();
        testUser.Profile = new ProfileEntity();
        testUser.Profile.Address = new AddressEntity();
        testUser.AppUserId = testUserAppUserId;
        testUser.Type = UserTypeEnum.PARTICULAR;
        testUser.Role = UserRoleEnum.CLIENT;
        testUser.Status = UserStatusEnum.OK;
        testUser.Profile.Civility = CivilityEnum.MISTER;
        testUser.Profile.FirstName = "Robert";
        testUser.Profile.LastName = "Ledoux";
        testUser.Profile.Birthdate = DateTime.parse("1991-03-10T00:00:00");
        testUser.Profile.PhoneNumber = "33615727102";
        testUser.Profile.Email = "robert.ledoux@yopmail.com";
        testUser.Profile.Alias = "Beber";
        testUser.Profile.Address.Street = "4 Rue des Veaux";
        testUser.Profile.Address.ZipCode = "67000";
        testUser.Profile.Address.City = "Strasbourg";
        testUser.Profile.Address.Country = CountryCodeEnum.FR;
        createOrReset(service, testUser);

        testCompany = new UserEntity();
        testCompany.Profile = new ProfileEntity();
        testCompany.Profile.Address = new AddressEntity();
        testCompany.AppUserId = testCompanyAppUserId;
        testCompany.Type = UserTypeEnum.PROFESSIONAL;
        testCompany.Role = UserRoleEnum.CLIENT;
        testCompany.Status = UserStatusEnum.OK;
        testCompany.Profile.Civility = CivilityEnum.MISTER;
        testCompany.Profile.FirstName = "MyMonopoly";
        testCompany.Profile.LastName = "Company";
        testCompany.Profile.Birthdate = DateTime.parse("1900-01-01T00:00:00");
        testCompany.Profile.Email = "my.monopoly.company@yopmail.com";
        testCompany.Profile.Alias = "MMC";
        testCompany.Profile.Address.Street = "10 rue du Faubourg Montmartre";
        testCompany.Profile.Address.ZipCode = "75009";
        testCompany.Profile.Address.City = "Paris";
        testCompany.Profile.Address.Country = CountryCodeEnum.FR;
        testCompany.Company = new CompanyEntity();
        testCompany.Company.Name = "My Monopoly Company";
        testCompany.Company.Siret = "79496963400011";
        createOrReset(service, testCompany);
    }

    public static void createOrReset(SMoneyService service, UserEntity user) {
        try {
            if (service.getUser(user.AppUserId).execute().code() == 200) {
                final Response<UserEntity> response = service.updateUser(user.AppUserId, user).execute();
                if (response.code() != 200) {
                    System.err.println(String.format("[TestSettings] Unable to reset user \"%s\"", user.AppUserId));
                    System.err.println(response.errorBody().string());
                }
            } else {
                final Response<UserEntity> response = service.createUser(user).execute();
                if (response.code() != 201) {
                    System.err.println(String.format("[TestSettings] Unable to create user \"%s\"", user.AppUserId));
                    System.err.println(response.errorBody().string());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void migrateAmount(SMoneyService service, UserEntity sender, UserEntity beneficiary) {
        try {
            final Response<UserEntity> response1 = service.getUser(sender.AppUserId).execute();
            if (response1.code() != 200) {
                System.err.println(String.format("[TestSettings] Unable to find user \"%s\"", sender.AppUserId));
                System.err.println(response1.errorBody().string());
            } else {
                UserEntity testUserAPI = response1.body();
                PaymentEntity p = new PaymentEntity();
                p.Amount = testUserAPI.SubAccounts.get(0).Amount;
                p.OrderId = String.format("RESET-%s", UUID.randomUUID().toString().split("-")[0]);
                p.Beneficiary = new SubAccountEntity();
                p.Beneficiary.AppAccountId = beneficiary.AppUserId;
                p.Message = "RESET - Migration du solde.";

                final Response<PaymentEntity> response2 = service.createPayment(sender.AppUserId, p).execute();
                if (response2.code() != 201) {
                    System.err.println(String.format("[TestSettings] Unable to recredit subaccount \"%s\" from subaccount \"%s\"", beneficiary.AppUserId, sender.AppUserId));
                    System.err.println(response1.errorBody().string());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
