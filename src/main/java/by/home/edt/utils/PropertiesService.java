package by.home.edt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesService {
    private static final String PROP_NAME = "/edt.properties";

    public static Properties getProperties() {
        Properties appProps = new Properties();
        try (InputStream propsStream = PropertiesService.class.getResourceAsStream(PROP_NAME)) {
            appProps.load(propsStream);
            return appProps;
        } catch (IOException e) {
            return new Properties();
        }
    }
}
