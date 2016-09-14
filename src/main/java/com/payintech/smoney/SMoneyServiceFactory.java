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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.payintech.smoney.toolbox.JodaDateTimeConverter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.joda.time.DateTime;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * This class consists exclusively of static methods that return an object
 * providing implementation of methods defined on the {@code SMoneyService}
 * interface.
 *
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 16.09.14
 * @see SMoneyService
 * @since 15.11.01
 */
public final class SMoneyServiceFactory {

    /**
     * Define the date format to use with S-Money API: {@code yyyy-MM-dd'T'HH:mm:ss}.
     *
     * @since 15.11.01
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Define the default API URL.
     *
     * @since 15.11.01
     */
    private static final String DEFAULT_API_URL = "https://rest-pp.s-money.fr/api/";

    /**
     * Define the default network transport timeout value (in seconds).
     *
     * @since 16.01.01
     */
    private static final int DEFAULT_TRANSPORT_TIMEOUT = 8;

    /**
     * Default constructor.
     *
     * @since 16.01.01
     */
    private SMoneyServiceFactory() {
    }

    /**
     * Build the {@code SMoneyService} instance. This method will use the file
     * "smoney.properties" found on the "resources" directory. You can specify
     * another properties file location by using the "smoney.properties"
     * property: {@code -Dsmoney.properties=file:/etc/conf/smoney.properties}
     *
     * @return The newly created {@code SMoneyService} instance or {@code null}
     * @see SMoneyService
     * @since 15.11.01
     */
    public static SMoneyService createService() {
        final Properties properties = new Properties();
        InputStream fis = null;
        try {
            if (System.getProperty("smoney.properties", null) == null) {
                if (SMoneyService.class.getResource("/smoney.properties") == null) {
                    try {
                        final Path path = Paths.get(SMoneyService.class.getProtectionDomain().getCodeSource().getLocation().toURI());
                        final URL u = new URL(String.format("file:%s/smoney.properties", path.getParent()));
                        fis = u.openStream();
                    } catch (URISyntaxException ignore) {
                    }
                } else {
                    fis = SMoneyService.class.getResourceAsStream("/smoney.properties");
                }
            } else {
                final URL u = new URL(System.getProperty("smoney.properties"));
                fis = u.openStream();
            }

            if (fis != null) {
                properties.load(fis);
                fis.close();
            }

            return SMoneyServiceFactory.createService(
                properties.getProperty("smoney.api.token"),
                properties.getProperty("smoney.api.endpoint", SMoneyServiceFactory.DEFAULT_API_URL),
                Integer.valueOf(properties.getProperty("smoney.transport.timeout", String.valueOf(SMoneyServiceFactory.DEFAULT_TRANSPORT_TIMEOUT))));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Build the {@code SMoneyService} instance.
     *
     * @param token   The token for the API
     * @param baseUrl The base URL to use
     * @return The newly created {@code SMoneyService} instance
     * @see SMoneyService
     * @since 15.11.01
     */
    public static SMoneyService createService(final String token, final String baseUrl) {
        return SMoneyServiceFactory.createService(token, baseUrl, SMoneyServiceFactory.DEFAULT_TRANSPORT_TIMEOUT);
    }

    /**
     * Build the {@code SMoneyService} instance.
     *
     * @param token   The token for the API
     * @param baseUrl The base URL to use
     * @param timeout The http transport timeout value
     * @return The newly created {@code SMoneyService} instance
     * @see SMoneyService
     * @since 15.11.01
     */
    public static SMoneyService createService(final String token, final String baseUrl, final int timeout) {
        final String userAgent = String.format("SMoneyJavaClient/%s", Version.projectVersion);
        final String authBearer = String.format("Bearer %s", token);
        final OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .readTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor() {

                @Override
                public Response intercept(final Chain chain) throws IOException {
                    final Request original = chain.request();
                    final Request.Builder requestBuilder = original.newBuilder()
                        .header("User-Agent", userAgent)
                        .header("Authorization", authBearer)
                        .method(original.method(), original.body());
                    final Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            })
            .build();

        final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DateTime.class, new JodaDateTimeConverter(SMoneyServiceFactory.DATE_FORMAT))
            .create();
        final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build();

        return retrofit.create(SMoneyService.class);
    }
}
