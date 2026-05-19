package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * This is the class for the Web Driver.
 */
public class DriverWeb {

        private final WebDriver driver;

        /**
         * Constructor method.
         * Only Chrome browser is defined.
         * Supports headless mode via system property for CI/CD environments.
         */
        public DriverWeb() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            
            // Enable headless mode for CI environments (GitHub Actions, etc.)
            String headlessMode = System.getProperty("headlessMode", "false");
            if ("true".equalsIgnoreCase(headlessMode)) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver(options);
        }

        /**
         * Allows to get the Web Driver.
         * @return WebDriver
         */
        public WebDriver getDriver() {
            return this.driver;
        }
}
