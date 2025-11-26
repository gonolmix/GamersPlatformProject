package connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AppProperties {
    private static AppProperties instance;

    private static final String FILE_NAME = "app.properties";

    private final Properties properties;

    private AppProperties() {
        this.properties = new Properties();
    }

    public void load() throws IOException, Exception {
        try (InputStream in = AppProperties.class.getClassLoader().
                getResourceAsStream(FILE_NAME)) {
            if (in == null) {
                throw new Exception(FILE_NAME);
            }

            this.properties.load(in);
        }
    }

    public static AppProperties getInstance() {
        if (instance == null) {
            instance = new AppProperties();
        }
        return instance;
    }


    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}