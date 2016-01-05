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
import com.squareup.okhttp.RequestBody;
import retrofit.Call;
import retrofit.http.*;

import java.util.List;
import java.util.Map;

/**
 * SMoneyService.
 *
 * @author Pierre Adam
 * @author Jean-Pierre Boudic
 * @version 15.11
 * @since 15.11
 */
public interface SMoneyService {

    /**
     * Use S-money API to create a new user
     * User.id was generate by API
     * Define Type for Account type (normal, Pro)
     * Field to fill on the user :
     * <pre>
     * - AppUserId     (required)
     * - Type          (required)
     * - Profile       (required)
     * --- Civility    (optional)
     * --- FirstName   (required)
     * --- LastName    (required)
     * --- Birthdate   (required)
     * --- PhoneNumber (optional)
     * --- Email       (optional)
     * --- Alias       (optional)
     * --- Address     (required)
     * ----- Street    (optional)
     * ----- ZipCode   (optional)
     * ----- City      (optional)
     * ----- Country   (required)
     * - Company       (required when type == 2)
     * --- Name        (required)
     * --- Siret       (required)
     * </pre>
     *
     * @param user A new user (local instance)
     * @return The User created (All information)
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @POST("users")
    Call<UserEntity> createUser(@Body UserEntity user);

    /**
     * Use S-money API to list all users.
     *
     * @return The list of all users registered (All information)
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
    })
    @GET("users")
    //TODO: Implement pagination
    Call<List<UserEntity>> listUsers();

    /**
     * Use S-money API get a specified user by this ClientId (appUserId).
     *
     * @param appUserId Local/Client Id
     * @return The asked user (All Information)
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
    })
    @GET("users/{appuserid}")
    Call<UserEntity> getUser(@Path("appuserid") String appUserId);

    /**
     * Use S-money API to update a existed user.
     * Only profile data can be updated.
     *
     * @param appUserId Local/Client Id
     * @param user      A user to update
     * @return The User updated (All information)
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @PUT("users/{appuserid}")
    Call<UserEntity> updateUser(@Path("appuserid") String appUserId, @Body UserEntity user);

    /**
     * Use S-money API to find users by criteria.
     * <pre>
     * - firstname (optional)
     * - lastname  (optional)
     * - email     (optional)
     * </pre>
     *
     * @param userOptions List of criteria (must be username, lastname, email)
     * @return The list of all users matched (All information)
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
    })
    @GET("users/search")
    //TODO: Implement pagination
    Call<List<UserEntity>> findUsers(@QueryMap Map<String, String> userOptions);

    /**
     * Use S-money API to list all sub accounts of an user.
     *
     * @param appUserId The User Id
     * @return The list of all the sub accounts of the user
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/subaccounts")
    //TODO: Implement pagination
    Call<List<SubAccountEntity>> listSubAccounts(@Path("appuserid") String appUserId);

    /**
     * Use S-money API to get a sub accounts of an user.
     *
     * @param appUserId    The User Id
     * @param appAccountId The Account Id
     * @return The sub account of the user
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/subaccounts/{appaccountid}")
    Call<SubAccountEntity> getSubAccount(@Path("appuserid") String appUserId, @Path("appaccountid") String appAccountId);

    /**
     * Use S-money API to create a sub account for an user.
     * Field to fill on the subAccount :
     * <pre>
     * - AppAccountId (required)
     * - DisplayName (optional)
     * </pre>
     *
     * @param appUserId  The User Id
     * @param subAccount The sub account information
     * @return The sub account created (All information)
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @POST("users/{appuserid}/subaccounts")
    Call<SubAccountEntity> createSubAccount(@Path("appuserid") String appUserId, @Body SubAccountEntity subAccount);

    /**
     * Use S-money API to update a sub account for an user.
     * Field to fill on the subAccount :
     * <pre>
     * - DisplayName
     * </pre>
     *
     * @param appUserId    The User Id
     * @param appAccountId The Account Id
     * @param subAccount   The sub account information
     * @return The sub account updated (All information)
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @PUT("users/{appuserid}/subaccounts/{appaccountid}")
    Call<SubAccountEntity> updateSubAccount(@Path("appuserid") String appUserId, @Path("appaccountid") String appAccountId, @Body SubAccountEntity subAccount);

    /**
     * Use S-money API to delete a sub account for an user.
     *
     * @param appUserId    The User Id
     * @param appAccountId The Account Id
     * @return Nothing
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @DELETE("users/{appuserid}/subaccounts/{appaccountid}")
    Call<String> deleteSubAccount(@Path("appuserid") String appUserId, @Path("appaccountid") String appAccountId);

    /**
     * Use S-money API to list all card registered on an user.
     *
     * @param appUserId The User Id
     * @return The list of all the card registered for the user
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/cards")
    //TODO: Implement pagination
    Call<List<CardEntity>> listCards(@Path("appuserid") String appUserId);

    /**
     * Use S-money API to create a card for an user.
     * Field to fill on the card registration application :
     * <pre>
     * - Card           (required)
     * --- AppCardId    (required)
     * --- Name         (optional)
     * - UrlReturn      (required)
     * - AvailableCards (optional)
     * </pre>
     *
     * @param appUserId       The User Id
     * @param cardApplication The card application form
     * @return The created entity (All information)
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @POST("users/{appuserid}/cards/registrations")
    Call<CardRegistrationAnswerEntity> createCard(@Path("appuserid") String appUserId, @Body CardRegistrationApplicationEntity cardApplication);

    /**
     * Use S-money API to get a card of an user.
     *
     * @param appUserId The User Id
     * @param appCardId The Card Id
     * @return The card of the user
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/cards/{appcardid}")
    Call<CardEntity> getCard(@Path("appuserid") String appUserId, @Path("appcardid") String appCardId);

    /**
     * Use S-money API to delete a card for an user
     *
     * @param appUserId The User Id
     * @param appCardId The Card Id
     * @return Nothing
     * @since 15.11
     */
    @DELETE("users/{appuserid}/cards/{appcardid}")
    Call<String> deleteCard(@Path("appuserid") String appUserId, @Path("appcardid") String appCardId);

    /**
     * Use S-money API to create a payment.
     * Field to fill on the payment :
     * <pre>
     * - OrderId         (required)
     * - Beneficiary     (required)
     * --- AppAccountId  (required)
     * - Sender          (optional)
     * --- AppAccountId  (required)
     * - Amount          (required)
     * - message         (optional)
     * - fee             (optional)
     * --- AmountWithVAT (required)
     * --- VAT           (required)
     * </pre>
     *
     * @param appUserId The Client Id sender
     * @param payment   An instantiated entity payment
     * @return The created payment
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @POST("users/{appuserid}/payments")
    Call<PaymentEntity> createPayment(@Path("appuserid") String appUserId, @Body PaymentEntity payment);

    /**
     * Use S-money API to create a payment. The sender is
     * required in Payment Body.
     *
     * @param appUserId The Client Id sender
     * @param payments  A list of instantiated entity payment
     * @return The list of created payments
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @POST("users/{appuserid}/payments")
    Call<List<PaymentEntity>> createPayments(@Path("appuserid") String appUserId, @Body List<PaymentEntity> payments);

    /**
     * Use S-money API get a payment by Id.
     *
     * @param appUserId User Client Id
     * @param orderId   Payment Client Id
     * @return The payment asked
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/payments/{orderid}")
    Call<PaymentEntity> getPayment(@Path("appuserid") String appUserId, @Path("orderid") String orderId);

    /**
     * Use S-money API list all payments for a given user.
     *
     * @param appUserId User Client Id
     * @return The list user payments
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/payments")
    //TODO: Implement pagination
    Call<List<PaymentEntity>> listPayments(@Path("appuserid") String appUserId);

    /**
     * Use S-money API list all bank accounts for a given user.
     *
     * @param appUserId User Client Id
     * @return The list user bank account
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/bankaccounts")
    //TODO: Implement pagination
    Call<List<BankAccountEntity>> listBankAccounts(@Path("appuserid") String appUserId);

    /**
     * Use S-money API get a bank account.
     *
     * @param appUserId     User Client Id
     * @param bankAccountId The bank account Id
     * @return An user bank account
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/bankaccounts/{bankaccountid}")
    Call<BankAccountEntity> getBankAccount(@Path("appuserid") String appUserId, @Path("bankaccountid") Long bankAccountId);

    /**
     * Use S-money API to create a Bank Account.
     * Field to fill on the bank account :
     * <pre>
     * - DisplayName  (required)
     * - Bic          (required)
     * - Iban         (required)
     * - IsMine       (required)
     * </pre>
     *
     * @param appUserId   The Client Id Owner
     * @param bankAccount a BankAccount entity
     * @return The created Bank Account
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @POST("users/{appuserid}/bankaccounts")
    Call<BankAccountEntity> createBankAccount(@Path("appuserid") String appUserId, @Body BankAccountEntity bankAccount);

    /**
     * Use S-money API to update a Bank Account.
     *
     * @param appUserId   The Client Id Owner
     * @param bankAccount a BankAccount to update
     * @return The updated Bank Account
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @PUT("users/{appuserid}/bankaccounts")
    Call<BankAccountEntity> updateBankAccount(@Path("appuserid") String appUserId, @Body BankAccountEntity bankAccount);

    /**
     * Use S-money API to delete a Bank Account.
     *
     * @param appUserId     The Client Id Owner
     * @param bankAccountId An Id of BankAccount to delete
     * @return Nothing
     * @since 15.11
     */
    @DELETE("users/{appuserid}/bankaccounts/{bankaccountid}")
    Call<String> deleteBankAccount(@Path("appuserid") String appUserId, @Path("bankaccountid") Long bankAccountId);

    /**
     * Use S-money API list all moneyouts for a given user.
     *
     * @param appUserId User Client Id
     * @return The list moneyouts
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/moneyouts")
    //TODO: Implement pagination
    Call<List<MoneyOutEntity>> listMoneyOuts(@Path("appuserid") String appUserId);

    /**
     * Use S-money API to create a Money Out.
     * Field to fill on the moneyout :
     * <pre>
     * - Amount          (required)
     * - AccountId       (optional)
     * --- AppAccountId  (required)
     * - BankAccount     (required)
     * --- Id            (required)
     * - Fee             (optional)
     * --- AmountWithVAT (required)
     * --- VAT           (required)
     * - Message         (optional)
     * </pre>
     *
     * @param appUserId The Client Id sender
     * @param moneyOut  An instantiated entity MoneyOut
     * @return The created MoneyOut
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @POST("users/{appuserid}/moneyouts/oneshot")
    Call<MoneyOutEntity> createMoneyOutOneShot(@Path("appuserid") String appUserId, @Body MoneyOutEntity moneyOut);

    /**
     * Use S-money API to create a Money Out
     * Field to fill on the moneyout :
     * <pre>
     * - Amount          (required)
     * - AccountId       (optional)
     * --- AppAccountId  (required)
     * - BankAccount     (required)
     * --- displayname   (optional)
     * --- Bic           (required)
     * --- Iban          (required)
     * --- IsMine        (required)
     * - Fee             (optional)
     * --- AmountWithVAT (required)
     * --- VAT           (required)
     * - Message         (optional)
     * </pre>
     *
     * @param appUserId The Client Id sender
     * @param moneyOut  An instantiated entity MoneyOut
     * @return The created MoneyOut
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v1+json",
            "Content-Type: application/vnd.s-money.v1+json"
    })
    @POST("users/{appuserid}/moneyouts/recurring")
    Call<MoneyOutEntity> createMoneyOutRecurring(@Path("appuserid") String appUserId, @Body MoneyOutEntity moneyOut);

    /**
     * Use S-money API get MoneyOut by Id.
     *
     * @param appUserId  The Client Id sender
     * @param moneyOutId Id of MoneyOut
     * @return The moneyout
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/moneyouts/{moneyoutid}")
    Call<MoneyOutEntity> getMoneyOut(@Path("appuserid") String appUserId, @Path("moneyoutid") Long moneyOutId);

    /**
     * Use S-money API to create a Card Payment.
     * Field to fill on the card payment entity :
     * <pre>
     * - Amount             (required)
     * - OrderId            (required)
     * - AvailableCards     (optional)
     * - Beneficiary        (optional)
     * --- AppAccountId     (required)
     * - Message            (optional)
     * - IsMine             (required)
     * - UrlReturn          (required)
     * - UrlCallback        (optional)
     * - Fee                (optional)
     * - Card               (optional)
     * --- AppCardId        (required)
     * --- Name             (optional)
     * - PaymentSchedule[]  (optional)
     * --- Amount           (required)
     * --- Date             (required)
     * --- Fee              (optional)
     * </pre>
     *
     * @param appUserId   The Client Id Owner
     * @param cardPayment A CardPayment entity
     * @return The created a credit card payment
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v2+json",
            "Content-Type: application/vnd.s-money.v2+json"
    })
    @POST("users/{appuserid}/payins/cardpayments")
    Call<CardPaymentEntity> createCardPayment(@Path("appuserid") String appUserId, @Body CardPaymentEntity cardPayment);

    /**
     * Use S-money API list all the card payments for a given user.
     *
     * @param appUserId User Client Id
     * @return The list of the card payments
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v2+json")
    @GET("users/{appuserid}/payins/cardpayments")
    //TODO: Implement pagination
    Call<List<CardPaymentEntity>> listCardPayments(@Path("appuserid") String appUserId);

    /**
     * Use S-money API get a card payments for a given user.
     *
     * @param appUserId User Client Id
     * @param orderId   The Order Id
     * @return The card payment
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v2+json")
    @GET("users/{appuserid}/payins/cardpayments/{orderid}")
    Call<CardPaymentEntity> getCardPayment(@Path("appuserid") String appUserId, @Path("orderid") String orderId);

    /**
     * Use S-money API list a scheduled payment for a given user.
     *
     * @param appUserId      User Client Id
     * @param orderId        The Order Id
     * @param sequenceNumber The scheduled payment sequence number
     * @return The scheduled payment
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v2+json")
    @GET("users/{appuserid}/payins/cardpayments/{orderid}/{sequencenumber}")
    Call<PaymentScheduleEntity> getCardScheduledPayment(@Path("appuserid") String appUserId, @Path("orderid") String orderId, @Path("sequencenumber") Integer sequenceNumber);

    /**
     * Use S-money API to refund an card payment order.
     *
     * @param appUserId  The Client Id Owner
     * @param orderId    The Order Id
     * @param refundForm The form to refund
     * @return The refund information
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v2+json",
            "Content-Type: application/vnd.s-money.v2+json"
    })
    @POST("users/{appuserid}/payins/cardpayments/{orderid}/refunds")
    Call<CardPaymentRefundAnswerEntity> refundCardPayment(@Path("appuserid") String appUserId, @Path("orderid") String orderId, @Body CardPaymentRefundApplicationEntity refundForm);

    /**
     * Use S-money API to refund a scheduled payment of card payment order.
     *
     * @param appUserId      The Client Id Owner
     * @param orderId        The Order Id
     * @param sequenceNumber The scheduled payment sequence number
     * @param refundForm     The form to refund
     * @return The refund information
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v2+json",
            "Content-Type: application/vnd.s-money.v2+json"
    })
    @POST("users/{appuserid}/payins/cardpayments/{orderid}/{sequencenumber}/refunds")
    Call<CardPaymentRefundAnswerEntity> refundCardScheduledPayment(@Path("appuserid") String appUserId, @Path("orderid") String orderId, @Path("sequencenumber") Integer sequenceNumber, @Body CardPaymentRefundApplicationEntity refundForm);

    /**
     * Use S-money API to edit a scheduled payment of card payment order.
     *
     * @param appUserId      The Client Id Owner
     * @param orderId        The Order Id
     * @param sequenceNumber The scheduled payment sequence number
     * @param payment        The scheduled payment information
     * @return The refund information
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v2+json",
            "Content-Type: application/vnd.s-money.v2+json"
    })
    @PUT("users/{appuserid}/payins/cardpayments/{orderid}/{sequencenumber}")
    Call<PaymentScheduleEntity> refundCardScheduledPayment(@Path("appuserid") String appUserId, @Path("orderid") String orderId, @Path("sequencenumber") Integer sequenceNumber, @Body PaymentScheduleEntity payment);

    /**
     * Use S-money API to create KYC Request.
     * Field to fill on the KYC request :
     * <pre>
     * - MultiPart File (required)
     * </pre>
     *
     * @param appUserId The Client Id sender
     * @param file      The file to upload
     * @return The KYC (entity) in request status
     * @since 15.11
     */
    @Multipart
    @Headers("Accept: application/vnd.s-money.v1+json")
    @POST("users/{appuserid}/kyc")
    Call<KycEntity> createKYCRequest(@Path("appuserid") String appUserId, @Part("file1") RequestBody file);

    /**
     * Use S-money API to create KYC Request.
     * Field to fill on the KYC request :
     * <pre>
     * - MultiPart File[] (required)
     * </pre>
     *
     * @param appUserId The Client Id sender
     * @param files     The files to upload
     * @return The KYC (entity) in request status
     * @since 15.11
     */
    @Multipart
    @Headers("Accept: application/vnd.s-money.v1+json")
    @POST("users/{appuserid}/kyc")
    Call<KycEntity> createKYCRequest(@Path("appuserid") String appUserId, @PartMap Map<String, RequestBody> files);

    /**
     * Use S-money API to get a KYC.
     *
     * @param appUserId The Client Id sender
     * @return The KYC (entity)
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/kyc")
    Call<List<KycEntity>> getKYC(@Path("appuserid") String appUserId);

    /**
     * Use S-money API to list payments by StoredCard.
     *
     * @param appUserId The Client Id sender
     * @return The list of payments by StoredCard
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/payins/storedcardpayments")
    //TODO: Implement pagination
    Call<List<StoredCardPaymentEntity>> listStoredCardPayment(@Path("appuserid") String appUserId);

    /**
     * Use S-money API to create payment by StoredCard.
     * Field to fill on the payment by StoredCard :
     * <pre>
     * - OrderId            (required)
     * - AccountId          (optional)
     * --- AppAccountId     (required)
     * - Card               (required)
     * --- AppCardId        (required)
     * --- IsMine           (required - If AccountId is not the card owner)
     * - Amount             (required)
     * - Fee                (optional)
     * --- AmountWithVAT    (required)
     * --- VAT              (required)
     * </pre>
     *
     * @param appUserId     The Client Id sender
     * @param storedCardPay The StoredCardPayment Entity
     * @return The created payment by StoredCard
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v2+json",
            "Content-Type: application/vnd.s-money.v2+json"
    })
    @POST("users/{appuserid}/payins/storedcardpayments")
    Call<StoredCardPaymentEntity> createStoredCardPayment(@Path("appuserid") String appUserId, @Body StoredCardPaymentEntity storedCardPay);

    /**
     * Use S-money API to get payment by StoredCard.
     *
     * @param orderId   The Client Payment OrderId
     * @param appUserId The Client Id sender
     * @return The asked payment by StoredCard
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/payins/storedcardpayments/{orderid}")
    Call<StoredCardPaymentEntity> getStoredCardPayment(@Path("appuserid") String appUserId, @Path("orderid") String orderId);

    /**
     * Use S-money API to create payment by StoredCard.
     * Field to fill on the refund by StoredCard :
     * <pre>
     * - amount     (required)
     * - orderId    (optional)
     * - refundFee  (required)
     * </pre>
     *
     * @param orderId       The Client OrderId for this refund
     * @param appUserId     The Client Id sender
     * @param refundRequest The request for refund
     * @return The created Refund
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v2+json",
            "Content-Type: application/vnd.s-money.v2+json"
    })
    @POST("users/{appuserid}/payins/storedcardpayments/{orderid}/refunds")
    Call<CardPaymentRefundAnswerEntity> refundStoredCardPayment(@Path("appuserid") String appUserId, @Path("orderid") String orderId, @Body CardPaymentRefundApplicationEntity refundRequest);

    /**
     * Use S-money API to get history : all payments (All type: CB, moneyout...).
     *
     * @param appUserId The Client Id sender
     * @return The List of History Items
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/historyitems")
    Call<List<HistoryItemEntity>> listHistoryItems(@Path("appuserid") String appUserId);

    /**
     * Use S-money API to get all reference.
     *
     * @param appUserId The Client Id sender
     * @return The List of References
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/payins/banktransfers/references")
    //TODO: Implement pagination
    Call<List<ReferenceEntity>> listReferences(@Path("appuserid") String appUserId);

    /**
     * Use S-money API to create a reference for bank transfer.
     * Field to fill on the reference :
     * <pre>
     * - Beneficiary    (optional)
     * --- AppAccountId (required)
     * - IsMine         (required)
     * </pre>
     *
     * @param appUserId The Client Id sender
     * @param reference The reference for BankTransfer
     * @return The Created reference
     * @since 15.11
     */
    @Headers({
            "Accept: application/vnd.s-money.v2+json",
            "Content-Type: application/vnd.s-money.v2+json"
    })
    @POST("users/{appuserid}/payins/banktransfers/references")
    Call<ReferenceEntity> createReference(@Path("appuserid") String appUserId, @Body ReferenceEntity reference);

    /**
     * Use S-money API to get reference.
     *
     * @param appUserId   The Client Id sender
     * @param referenceId The reference Id (Generate by S-Money)
     * @return The asked Reference
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/payins/banktransfers/references/{referenceid}")
    Call<ReferenceEntity> getReference(@Path("appuserid") String appUserId, @Path("referenceid") Long referenceId);

    /**
     * Use S-money API to get reference.
     *
     * @param appUserId     The Client Id sender
     * @param referenceCode The reference Code (Generate by S-Money)
     * @return The asked Reference
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/payins/banktransfers/references/{referencecode}")
    Call<ReferenceEntity> getReference(@Path("appuserid") String appUserId, @Path("referencecode") String referenceCode);

    /**
     * Use S-money API to get all bank transfers.
     *
     * @param appUserId The Client Id sender
     * @return The List of bank transfers
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/payins/banktransfers")
    //TODO: Implement pagination
    Call<List<BankTransferEntity>> listBankTransfers(@Path("appuserid") String appUserId);

    /**
     * Use S-money API to get all bank transfers.
     *
     * @param appUserId      The Client Id sender
     * @param bankTransferId The bank transfer Id
     * @return The List of bank transfers
     * @since 15.11
     */
    @Headers("Accept: application/vnd.s-money.v1+json")
    @GET("users/{appuserid}/payins/banktransfers/{banktransferid}")
    Call<BankTransferEntity> getBankTransfer(@Path("appuserid") String appUserId, @Path("banktransferid") Long bankTransferId);
}
