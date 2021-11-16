package com.weathershopper.utils;

import com.weathershopper.constants.FrameworkConstants;
import com.weathershopper.enums.ConfigProperties;
import com.weathershopper.exceptions.PropertyFileUsageException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class PropertyFileUtil {

    private static Properties property = new Properties();
    private static final Map<String, String> CONFIGMAP = new HashMap<>();

    private PropertyFileUtil() {}

    static {
        try(FileInputStream file = new FileInputStream(FrameworkConstants.getConfigFilePath())) {
            property.load(file);
            for (Map.Entry<Object, Object> entry : property.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim()); //remove the trailing and leading spaces
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Receives the {com.weathershopper.enums.ConfigProperties},converts to lowercase , return the corresponding value
     * for the key from the HashMap
     */
    public static String get(ConfigProperties key)  {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new PropertyFileUsageException("Property name " + key + " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }
}
