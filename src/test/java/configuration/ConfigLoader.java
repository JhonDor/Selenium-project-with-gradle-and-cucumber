package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties = new Properties();

    public ConfigLoader() {
        try (FileInputStream input = new FileInputStream("src/test/resources/utils/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return properties.getProperty("email");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getUserName() {
        return properties.getProperty("userName");
    }
}
