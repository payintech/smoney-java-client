package com.payintech.smoney;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Get information about version.
 *
 * @author Thibault Meyer
 * @version 16.01
 * @since 16.01
 */
class Version {

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
            Version.projectVersion = (String) properties.getOrDefault("MAVEN_PROJECT_VERSION", "00.00");
            Version.buildDate = (String) properties.getOrDefault("MAVEN_PROJECT_BUILD", "1970-01-01T00:00:00UTC");
        } catch (IOException ignore) {
            Version.projectVersion = "00.00";
            Version.buildDate = "1970-01-01T00:00:00UTC";
        }
        try {
            fis.close();
        } catch (IOException ignore) {
        }
    }
}
