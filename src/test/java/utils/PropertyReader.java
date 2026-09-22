package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Читает настройки из src/test/resources/config.properties. */

public final class PropertyReader {
    
    private static final String PROPERTIES_FILE = "/config.properties";
    private static Properties properties;
    
    private PropertyReader() {
    }
    
    public static String getProperty(String propertyName) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(propertyName);
    }
    
    private static void loadProperties() {
        properties = new Properties();
        
        try (InputStream inputStream = PropertyReader.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Не найден файл " + PROPERTIES_FILE);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать " + PROPERTIES_FILE, e);
        }
    }
}
