package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * This is the class for the Web Driver.
 */
public class DriverWeb {

        private final WebDriver driver;

        /**
         * Constructor method.
         * Only Chrome browser is defined.
         */
        public DriverWeb() {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        }

        /**
         * Allows to get the Web Driver.
         * @return WebDriver
         */
        public WebDriver getDriver() {
            return this.driver;
        }
}
