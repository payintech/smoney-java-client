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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Get information about version.
 *
 * @author Thibault Meyer
 * @version 16.01.01
 * @since 16.01.01
 */
final class Version {

    /**
     * Version of the server.
     *
     * @since 16.01
     */
    public static String projectVersion;

    /**
     * The date when server has been compiled.
     *
     * @since 16.01
     */
    public static String buildDate;

    /**
     * Static constructor.
     *
     * @since 16.01
     */
    static {
        final InputStream fis = Version.class.getResourceAsStream("/version.properties");
        try {
            final Properties properties = new Properties();
            properties.load(fis);
            Version.projectVersion = (String) properties.get("MAVEN_PROJECT_VERSION");
            Version.buildDate = (String) properties.get("MAVEN_PROJECT_BUILD");
        } catch (IOException ignore) {
            Version.projectVersion = "00.00";
            Version.buildDate = "1970-01-01T00:00:00UTC";
        }
        try {
            fis.close();
        } catch (IOException ignore) {
        }
    }

    /**
     * Default constructor.
     *
     * @since 16.01
     */
    private Version() {
    }
}
