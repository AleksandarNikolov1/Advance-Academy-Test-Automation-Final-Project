package configurations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static final Properties properties = new Properties();

    static {
            try (FileInputStream configFile =
                         new FileInputStream("src/test/resources/config.properties")) {
                properties.load(configFile);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load configuration file");
            }
        }

        public static String getUrl() {
            return properties.getProperty("urlAddress");
        }

        public static int getImplicitWait() {
            return Integer.parseInt(properties.getProperty("implicitWait"));
        }

        public static String getBrowser() {
            return properties.getProperty("browser");
        }
    }

