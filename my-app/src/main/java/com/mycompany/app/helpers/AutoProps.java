package com.mycompany.app.helpers;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AutoProps
 *
 * This is a Singleton method that reads the settings
 * file and stores that data for later consumption
 */
public final class AutoProps {
    // allows logging
    private static final Logger LOG = LogManager.getLogger();
    // constructs the instance
    private static final AutoProps THIS_INSTANCE = new AutoProps();
    // configs is the interface for accessing the settings
    public Properties configs = new Properties();

    /**
     * AutoProps()
     * constructor is private so as not to be called externally
     */
    private AutoProps() {
        getProperties();
    }

    // public instance interface
    public static AutoProps getInstance() {
        return THIS_INSTANCE;
    }

    /**
     * getProperties
     *
     * method for reading in the settings file
     */
    private void getProperties() {
        // settings file hard coded as we have not read the settings yet ;)
        String propFileName = "settings.properties";
        configs = loadPropertiesFile(propFileName);
    }

    /**
     * loadPropertiesFile
     *
     * @param filename file to load properties from
     * @return Properties
     */
    private Properties loadPropertiesFile(final String filename) {
        Properties properties = new Properties();
        String propFileName = filename;
        InputStream inputStream;
        inputStream =
        getClass().getClassLoader().getResourceAsStream(propFileName);

        try {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException(
                    "property file " + filename + " not found");
            }
        } catch (IOException e) {
            LOG.warn("Could not open the file.");
            e.printStackTrace();
            return null;
        }

        try {
            inputStream.close();
        } catch (NullPointerException | IOException e) {
            LOG.warn("Could not close the file properly.");
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * getSettingAsInteger
     *
     * converts a setting to an Integer
     * @param config property to be converted
     * @return Integer
     */
    public Integer getSettingAsInteger(final String config) {
        return Integer.parseInt(configs.get(config).toString());
    }
}
