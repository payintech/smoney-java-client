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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.payintech.smoney.toolbox.JodaDateTimeConverter;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import org.joda.time.DateTime;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * SMoneyServiceFactory.
 *
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 15.11
 * @since 15.11
 */
public final class SMoneyServiceFactory {

    /**
     * Define the date format to use with S-Money API.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Define the default API URL.
     */
    private static final String DEFAULT_API_URL = "https://rest-pp.s-money.fr/api/";

    /**
     * Build the SMoneyService instance. This method will use the file
     * "smoney.properties" found on the "resources" directory.
     *
     * @return The instance of the SMoneyService
     * @since 15.11
     */
    public static SMoneyService createService() {
        InputStream fis = null;
        if (SMoneyService.class.getResource("/smoney.properties") == null) {
            if (System.getProperty("smoney.properties", null) == null) {
                try {
                    final Path path = Paths.get(SMoneyService.class.getProtectionDomain().getCodeSource().getLocation().toURI());
                    final URL u = new URL(String.format("file:%s/smoney.properties", path.getParent()));
                    fis = u.openStream();
                } catch (URISyntaxException | IOException ignore) {
                }
            }
        } else {
            fis = SMoneyService.class.getResourceAsStream("/smoney.properties");
        }
        final Properties properties = new Properties();
        try {
            properties.load(fis);
            return SMoneyServiceFactory.createService(
                    properties.getProperty("smoney.api.token"),
                    properties.getProperty("smoney.api.endpoint", SMoneyServiceFactory.DEFAULT_API_URL),
                    Integer.valueOf(properties.getProperty("smoney.transport.timeout", "8")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Build the SMoneyService instance.
     *
     * @param token   The token for the API
     * @param baseUrl The base URL to use
     * @return The instance of the SMoneyService
     * @since 15.11
     */
    public static SMoneyService createService(final String token, final String baseUrl) {
        return SMoneyServiceFactory.createService(token, baseUrl, 8);
    }

    /**
     * Build the SMoneyService instance.
     *
     * @param token   The token for the API
     * @param baseUrl The base URL to use
     * @param timeout The http transport timeout value
     * @return The instance of the SMoneyService
     * @since 15.11
     */
    public static SMoneyService createService(final String token, final String baseUrl, final int timeout) {
        final OkHttpClient httpClient = new OkHttpClient();

        httpClient.setReadTimeout(timeout, TimeUnit.SECONDS);
        httpClient.setConnectTimeout(timeout, TimeUnit.SECONDS);
        httpClient.interceptors().clear();
        httpClient.interceptors().add(chain -> {
            final Request original = chain.request();
            final Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", String.format("Bearer %s", token))
                    .method(original.method(), original.body());
            final Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new JodaDateTimeConverter(DATE_FORMAT))
                .create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        return retrofit.create(SMoneyService.class);
    }
}