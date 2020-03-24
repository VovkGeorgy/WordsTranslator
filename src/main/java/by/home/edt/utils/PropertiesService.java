package by.home.edt.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesService {
    private static final String PROP_NAME = "edt.properties";

    public static Properties getProperties() {
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String appPropPath = rootPath + PROP_NAME;

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appPropPath));
            return appProps;
        } catch (IOException e) {
            return new Properties();
        }
    }
}
